package com.example.backend.controller;

import java.util.List;

import com.example.backend.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.TransactionDTO;
import com.example.backend.mapper.TransactionMapper;
import com.example.backend.service.TransactionService;
import com.example.backend.service.UserService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;

    public TransactionController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }
    
    @GetMapping("/{username}/user")
    public ResponseEntity<List<TransactionDTO>> getUserTransactions(@PathVariable String username) {

        long userId = userService.findUserByUsername(username).getId();

        List<Transaction> transaction = transactionService.getTransactionsByUserId(userId);
        
        return ResponseEntity.ok(TransactionMapper.toDTOList(transaction));
    }

    @GetMapping("/id/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable long transactionId) {

        Transaction transaction = transactionService.getTransactionById(transactionId);

        return ResponseEntity.ok(TransactionMapper.toDTO(transaction));
    }
}
