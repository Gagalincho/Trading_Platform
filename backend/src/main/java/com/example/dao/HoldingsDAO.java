package com.example.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Holdings;

@Repository
public class HoldingsDAO {

    private final JdbcTemplate jdbcTemplate;

    public HoldingsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createHolding(int userId, String symbol, BigDecimal quantity) {
        String createHoldingQuery = "INSERT INTO holdings (user_id, crypto_symbol, quantity) VALUES (?, ?, ?)";
        return jdbcTemplate.update(createHoldingQuery, userId, symbol, quantity);
    }

    public int updateHolding(int userId, String symbol, BigDecimal quantity) {
        String updateHoldingQuery = "UPDATE holdings SET quantity = ? WHERE user_id = ? AND crypto_symbol = ?";
        return jdbcTemplate.update(updateHoldingQuery, quantity, userId, symbol);
    }

    public int deleteHoldingByUserId(int userId, String symbol) {
        String deleteHoldingQuery = "DELETE FROM holdings WHERE user_id = ? AND crypto_symbol = ?";
        return jdbcTemplate.update(deleteHoldingQuery, userId, symbol);
    }

    public List<Holdings> getAllHoldingsForUserByUserId(long userId) {
        String getAllHoldingsForUserByUserIdQuery = "SELECT * FROM holdings WHERE user_id = ?";
        return jdbcTemplate.query(getAllHoldingsForUserByUserIdQuery, (rs, rowNum) -> {
            Holdings holding = new Holdings();
            holding.setId(rs.getInt("id"));
            holding.setUserId(rs.getInt("user_id"));
            holding.setCryptoSymbol(rs.getString("crypto_symbol"));
            holding.setQuantity(rs.getBigDecimal("quantity"));
            return holding;
        }, userId);
    }
}
