package com.example.backend.wsclient;

import com.example.backend.dto.CryptoPriceDTO;
import com.example.backend.websocket.CryptoWebSocketHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;

@Component
public class KrakenWebSocketClient {

    private final CryptoWebSocketHandler wsHandler;
    private final ObjectMapper objectMapper;

    public KrakenWebSocketClient(CryptoWebSocketHandler wsHandler, ObjectMapper objectMapper) {
        this.wsHandler = wsHandler;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void connect() {
        HttpClient.newHttpClient().newWebSocketBuilder()
            .buildAsync(URI.create("wss://ws.kraken.com/v2"), new Listener() {

                @Override
                public void onOpen(WebSocket webSocket) {
                    System.out.println("Connected to Kraken WebSocket V2");

                    webSocket.sendText(createSubscribeMessage(), true)
                            .thenRun(() -> System.out.println("Sent subscription request"));

                    Listener.super.onOpen(webSocket);
                }

                @Override
                public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                    try {
                        JsonNode json = objectMapper.readTree(data.toString());
                        System.out.println("Kraken sent: " + json);

                        if ("ticker".equals(json.path("channel").asText(null))) {
                            JsonNode dataArray = json.path("data");
                            if (dataArray.isArray()) {
                                for (JsonNode tickerNode : dataArray) {
                                    String symbol = tickerNode.path("symbol").asText(null);
                                    BigDecimal lastPrice = tickerNode.has("last")
                                            ? new BigDecimal(tickerNode.get("last").asText())
                                            : null;

                                    if (symbol != null && lastPrice != null) {
                                        wsHandler.broadcastPrice(new CryptoPriceDTO(symbol, lastPrice));
                                    } else {
                                        System.err.println("Missing symbol or last price in ticker message");
                                    }
                                }
                            }
                        }

                    } catch (Exception e) {
                        System.err.println("Error parsing Kraken message: " + e.getMessage());
                        e.printStackTrace();
                    }

                    return Listener.super.onText(webSocket, data, last);
                }
            });
    }

    private String createSubscribeMessage() {
        return """
        {
          "method": "subscribe",
          "params": {
            "channel": "ticker",
            "symbol": [
              "BTC/USDT", "ETH/USDT", "USDT/USD", "XRP/USDT", "USDC/USD",
              "SOL/USDT ", "DOGE/USDT", "ADA/USDT", "LINK/USDT", "AVAX/USDT",
              "SHIB/USDT", "DOT/USDT", "TRX/USDT", "MATIC/USDT", "LTC/USDT",
               "DAI/USD", "ATOM/USDT", "ETC/USDT", "NEAR/USDT", "ALGO/USDT"
            ]
          }
        }
        """;
    }
}
