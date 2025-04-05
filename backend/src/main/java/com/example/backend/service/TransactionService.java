package com.example.backend.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.NoTransactionException;

import com.example.backend.error.user.NoUserWithSuchIdExistsException;
import com.example.backend.dao.TransactionDAO;
import com.example.backend.error.transaction.NoTransactionWithSuchIdException;
import com.example.backend.error.transaction.QuantityCannotBeZeroException;
import com.example.backend.model.Transaction;

@Service
public class TransactionService {

    private final TransactionDAO transactionDAO;
    private final UserService userService;

    public TransactionService(TransactionDAO transactionDAO, UserService userService) {
        this.transactionDAO = transactionDAO;
        this.userService = userService;
    }

    public int createTransaction(long userId, String symbol, BigDecimal quantity, BigDecimal priceAtTransaction, String transactionType) {

        userService.ensureUserExists(userId);

        if (quantity.compareTo(BigDecimal.ZERO) <= 0) {
            throw new QuantityCannotBeZeroException("Quantity must be greater than zero");
        }

        return transactionDAO.createTransaction(userId, symbol, quantity, priceAtTransaction, transactionType);
    }

    public List<Transaction> getTransactionsByUserId(long userId) {

        userService.ensureUserExists(userId);

        return transactionDAO.getTransactionsByUserId(userId);
    }

    public Transaction getTransactionById(long transactionId) {

        Transaction transaction = transactionDAO.getTransactionById(transactionId);

        if (transaction == null) {
            throw new NoTransactionWithSuchIdException("Transaction not found");
        }

        return transaction;
    }

    public List<Transaction> getTransactionsByUserIdForTransactionType(long userId, String transactionType) {

        userService.ensureUserExists(userId);

        return transactionDAO.getTransactionsByUserIdForTransactionTypeQuery(userId, transactionType);
    }

    public int deleteTransactionsByUserId(long userId) {

        userService.ensureUserExists(userId);

        return transactionDAO.deleteTransactionsByUserId(userId);
    }

    public int deleteTransactionById(long transactionId) {

        Transaction transaction = transactionDAO.getTransactionById(transactionId);

        if (transaction == null) {
            throw new NoUserWithSuchIdExistsException("Transaction not found");
        }

        return transactionDAO.deleteTransactionById(transactionId);
    }
}
