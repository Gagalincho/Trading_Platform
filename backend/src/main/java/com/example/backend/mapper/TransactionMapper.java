package com.example.backend.mapper;

import com.example.backend.dto.TransactionDTO;
import com.example.backend.model.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionMapper {
    public static TransactionDTO toDTO(Transaction tx) {
        return new TransactionDTO(
            tx.getCryptoSymbol(),
            tx.getQuantity(),
            tx.getPriceAtTransaction(),
            tx.getTransactionType(),
            tx.getTransactionDate()
        );
    }

    public static List<TransactionDTO> toDTOList(List<Transaction> transactions) {
        return transactions.stream().map(TransactionMapper::toDTO).collect(Collectors.toList());
    }
}
