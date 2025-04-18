package com.example.backend.dto;

import java.math.BigDecimal;

public class CryptoPriceDTO {
    private String symbol;
    private BigDecimal price;

    public CryptoPriceDTO() {}

    public CryptoPriceDTO(String symbol, BigDecimal price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
