package com.example.backend.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.backend.model.Transaction;

@Repository
public class TransactionDAO {

    private final JdbcTemplate jdbcTemplate;

    public TransactionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createTransaction(long userId, String symbol, BigDecimal quantity, BigDecimal priceAtTransaction, String transactionType) {
        String createTransactionQuery = "INSERT INTO transactions (user_id, crypto_symbol, quantity, price_at_transaction, transaction_type) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(createTransactionQuery, userId, symbol, quantity, priceAtTransaction, transactionType);
    }

    public List<Transaction> getTransactionsByUserId(long UserId) {

        String getAllTransactionsByUserIdQuery = "SELECT * FROM transactions WHERE user_id = ?";

        return jdbcTemplate.query(getAllTransactionsByUserIdQuery, (rs, rowNum) -> {
            Transaction transaction = new Transaction();

            transaction.setId(rs.getLong("id"));
            transaction.setUserId(rs.getLong("user_id"));
            transaction.setCryptoSymbol(rs.getString("crypto_symbol"));
            transaction.setQuantity(rs.getBigDecimal("quantity"));
            transaction.setPriceAtTransaction(rs.getBigDecimal("price_at_transaction"));
            transaction.setTransactionType(rs.getString("transaction_type"));
            transaction.setTransactionDate(rs.getTimestamp("timestamp").toLocalDateTime());

            return transaction;
        }, UserId);
    }

    public Transaction getTransactionById(long transactionId) {

        String sql = "SELECT * FROM transactions WHERE id = ?";
    
        try {
            
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Transaction transaction = new Transaction();

                transaction.setId(rs.getLong("id"));
                transaction.setUserId(rs.getLong("user_id"));
                transaction.setCryptoSymbol(rs.getString("crypto_symbol"));
                transaction.setQuantity(rs.getBigDecimal("quantity"));
                transaction.setPriceAtTransaction(rs.getBigDecimal("price_at_transaction"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setTransactionDate(rs.getTimestamp("timestamp").toLocalDateTime());

                return transaction;
            }, transactionId);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Transaction> getTransactionsByUserIdForTransactionTypeQuery(long userId, String transactionType) {

        String getAllTransactionsByUserIdForTransactionTypeQuery = "SELECT * FROM transactions WHERE user_id = ? AND transaction_type = ?";

        return jdbcTemplate.query(getAllTransactionsByUserIdForTransactionTypeQuery, (rs, rowNum) -> {
            Transaction transaction = new Transaction();

            transaction.setId(rs.getLong("id"));
            transaction.setUserId(rs.getLong("user_id"));
            transaction.setCryptoSymbol(rs.getString("crypto_symbol"));
            transaction.setQuantity(rs.getBigDecimal("quantity"));
            transaction.setPriceAtTransaction(rs.getBigDecimal("price_at_transaction"));
            transaction.setTransactionType(rs.getString("transaction_type"));
            transaction.setTransactionDate(rs.getTimestamp("timestamp").toLocalDateTime());

            return transaction;
        }, userId, transactionType);
    }

    public int deleteTransactionsByUserId(long userId) {

        String deleteTransactionsByUserIdQuery = "DELETE FROM transactions WHERE user_id = ?";

        return jdbcTemplate.update(deleteTransactionsByUserIdQuery, userId);
    }

    public int deleteTransactionById(long transactionId) {

        String deleteTransactionByIdQuery = "DELETE FROM transactions WHERE id = ?";

        return jdbcTemplate.update(deleteTransactionByIdQuery, transactionId);
    }
}