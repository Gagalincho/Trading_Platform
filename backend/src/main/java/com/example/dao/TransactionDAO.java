package com.example.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.backend.model.Transaction;

@Repository
public class TransactionDAO {

    private final JdbcTemplate jdbcTemplate;

    public TransactionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createTransaction(long userId, String symbol, BigDecimal quantity, BigDecimal priceAtTransaction,
            String transactionType) {
        String createTransactionQuery = "INSERT INTO transactions (user_id, crypto_symbol, quantity, price_at_transaction, transaction_type) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(createTransactionQuery, userId, symbol, quantity, priceAtTransaction,
                transactionType);
    }

    public List<Transaction> getAllTransactionsByUserId(long UserId) {
        String getAllTransactionsByUserIdQuery = "SELECT * FROM transactions WHERE user_id = ?";
        return jdbcTemplate.query(getAllTransactionsByUserIdQuery, (rs, rowNum) -> {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getInt("id"));
            transaction.setUserId(rs.getInt("user_id"));
            transaction.setCryptoSymbol(rs.getString("crypto_symbol"));
            transaction.setQuantity(rs.getBigDecimal("quantity"));
            transaction.setPriceAtTransaction(rs.getBigDecimal("price_at_transaction"));
            transaction.setTransactionType(rs.getString("transaction_type"));
            transaction.setTransactionDate(rs.getString("transaction_date"));
            return transaction;
        }, UserId);
    }

    public List<Transaction> getAllTransactionsByUserIdForTransactionTypeQuery(long userId, String transactionType) {
        String getAllTransactionsByUserIdForTransactionTypeQuery = "SELECT * FROM transactions WHERE user_id = ? AND transaction_type = ?";
        return jdbcTemplate.query(getAllTransactionsByUserIdForTransactionTypeQuery, (rs, rowNum) -> {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getInt("id"));
            transaction.setUserId(rs.getInt("user_id"));
            transaction.setCryptoSymbol(rs.getString("crypto_symbol"));
            transaction.setQuantity(rs.getBigDecimal("quantity"));
            transaction.setPriceAtTransaction(rs.getBigDecimal("price_at_transaction"));
            transaction.setTransactionType(rs.getString("transaction_type"));
            transaction.setTransactionDate(rs.getString("transaction_date"));
            return transaction;
        }, userId, transactionType);
    }

    public int deleteTransactionsByUserId(long userId) {
        String deleteTransactionsByUserIdQuery = "DELETE FROM transactions WHERE user_id = ?";
        return jdbcTemplate.update(deleteTransactionsByUserIdQuery, userId);
    }
}