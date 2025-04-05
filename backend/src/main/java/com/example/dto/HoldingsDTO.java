package com.example.dto;

import java.math.BigDecimal;

public class HoldingsDTO {
    private String cryptoSymbol;
    private BigDecimal quantity;

    public HoldingsDTO(String cryptoSymbol, BigDecimal quantity) {
        this.cryptoSymbol = cryptoSymbol;
        this.quantity = quantity;
    }

    public String getCryptoSymbol() { return cryptoSymbol; }
    public BigDecimal getQuantity() { return quantity; }
}
