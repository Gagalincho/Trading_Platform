package com.example.backend.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.backend.error.trade.InsufficientFundsException;
import com.example.backend.model.Holdings;

@Service
public class TradeService {

    private final UserService userService;
    private final TransactionService transactionService;
    private final HoldingsService holdingsService;

    public TradeService(UserService userService, TransactionService transactionService, HoldingsService holdingsService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.holdingsService = holdingsService;
    }
    
    public void executeTrade(long userId, String symbol, BigDecimal quantity, BigDecimal price, String type) {
        userService.ensureUserExists(userId);

        BigDecimal total = price.multiply(quantity);
        BigDecimal currentBalance = userService.getUserBalanceById(userId);

        if (type.equalsIgnoreCase("BUY")) {
            if (currentBalance.compareTo(total) < 0) {
                throw new InsufficientFundsException("Insufficient balance");
            }

            userService.updateUserBalance(userId, currentBalance.subtract(total));

            Holdings holding = holdingsService.getHoldingByUserIdAndSymbol(userId, symbol);
            if (holding != null) {
                holdingsService.updateHolding(userId, symbol, holding.getQuantity().add(quantity));
            } else {
                holdingsService.createHolding(userId, symbol, quantity);
            }

        } else if (type.equalsIgnoreCase("SELL")) {
            Holdings holding = holdingsService.getHoldingByUserIdAndSymbol(userId, symbol);

            if (holding == null || holding.getQuantity().compareTo(quantity) < 0) {
                throw new RuntimeException("Not enough crypto to sell");
            }

            BigDecimal newQty = holding.getQuantity().subtract(quantity);
            holdingsService.updateHolding(userId, symbol, newQty);
            userService.updateUserBalance(userId, currentBalance.add(total));
        }

        transactionService.createTransaction(userId, symbol, quantity, price, type.toUpperCase());
    }
}
