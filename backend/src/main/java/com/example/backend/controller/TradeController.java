package com.example.backend.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.TradeService;

@RestController
@RequestMapping("/trade")
public class TradeController {

    private final TradeService tradeService;
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    public ResponseEntity<String> executeTrade(
        @RequestParam long userId,
        @RequestParam String symbol,
        @RequestParam BigDecimal quantity,
        @RequestParam BigDecimal price,
        @RequestParam String type) {

        tradeService.executeTrade(userId, symbol, quantity, price, type);
        return ResponseEntity.ok("Trade successful");
    }
}

