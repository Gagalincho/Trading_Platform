package com.example.dto;

import java.math.BigDecimal;

public class UserDTO {
    private String username;
    private String name;
    private String email;
    private BigDecimal currentBalance;

    public UserDTO(String username, String name, String email, BigDecimal currentBalance) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.currentBalance = currentBalance;
    }

    public String getUsername() { return username; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public BigDecimal getCurrentBalance() { return currentBalance; }
}
