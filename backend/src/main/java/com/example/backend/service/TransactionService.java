package com.example.backend.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.model.Transaction;
import com.example.dao.TransactionDAO;

@Service
public class TransactionService {
    
    private final TransactionDAO transactionDAO;

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public int createTransaction(long userId, String symbol, BigDecimal quantity, BigDecimal priceAtTransaction,
            String transactionType) {
        return transactionDAO.createTransaction(userId, symbol, quantity, priceAtTransaction, transactionType);
    }

    public List<Transaction> getTransactionsByUserId(long userId) {
        return transactionDAO.getTransactionsByUserId(userId);
    }

    public List<Transaction> getTransactionsByUserIdForTransactionType(long userId, String transactionType) {
        return transactionDAO.getTransactionsByUserIdForTransactionTypeQuery(userId, transactionType);
    }

    public int deleteTransactionsByUserId(long userId) {
        return transactionDAO.deleteTransactionsByUserId(userId);
    }

    public int deleteTransactionById(long transactionId) {
        return transactionDAO.deleteTransactionById(transactionId);
    }
}
