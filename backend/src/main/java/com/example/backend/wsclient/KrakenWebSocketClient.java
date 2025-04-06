package com.example.backend.wsclient;

import com.example.backend.dto.CryptoPriceDTO;
import com.example.backend.websocket.CryptoWebSocketHandler;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KrakenWebSocketClient {

    private final CryptoWebSocketHandler wsHandler;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KrakenWebSocketClient(CryptoWebSocketHandler wsHandler) {
        this.wsHandler = wsHandler;
    }

    @PostConstruct
    public void connect() {
        HttpClient.newHttpClient().newWebSocketBuilder()
                .buildAsync(URI.create("wss://ws.kraken.com/v2"), new Listener() {

                    @Override
                    public void onOpen(WebSocket webSocket) {
                        System.out.println("Connected to Kraken");

                        String subscribeMsg = "{\"method\":\"subscribe\",\"params\":{\"channel\":\"ticker\",\"symbols\":[\"XBT/USDT\"]}}";

                        webSocket.sendText(subscribeMsg, true);

                        Listener.super.onOpen(webSocket);
                    }

                    @Override
                    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                        try {
                            JsonNode json = objectMapper.readTree(data.toString());

                            if (json.has("type") && "update".equals(json.get("type").asText())) {

                                String symbol = json.get("symbol").asText();

                                BigDecimal price = new BigDecimal(json.get("data").get("price").asText());

                                wsHandler.broadcastPrice(new CryptoPriceDTO(symbol, price));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        
                        return Listener.super.onText(webSocket, data, last);
                    }
                });
    }
}
