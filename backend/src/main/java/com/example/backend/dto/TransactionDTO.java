package com.example.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {
    private String cryptoSymbol;
    private BigDecimal quantity;
    private BigDecimal priceAtTransaction;
    private String transactionType;
    private LocalDateTime timestamp;

    public TransactionDTO(String cryptoSymbol, BigDecimal quantity, BigDecimal priceAtTransaction,
                          String transactionType, LocalDateTime timestamp) {
        this.cryptoSymbol = cryptoSymbol;
        this.quantity = quantity;
        this.priceAtTransaction = priceAtTransaction;
        this.transactionType = transactionType;
        this.timestamp = timestamp;
    }

    public String getCryptoSymbol() { return cryptoSymbol; }
    public BigDecimal getQuantity() { return quantity; }
    public BigDecimal getPriceAtTransaction() { return priceAtTransaction; }
    public String getTransactionType() { return transactionType; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
