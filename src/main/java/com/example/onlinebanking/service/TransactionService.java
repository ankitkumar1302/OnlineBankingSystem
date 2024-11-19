package com.example.onlinebanking.service;

import com.example.onlinebanking.model.Transaction;
import com.example.onlinebanking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> findRecentTransactionsByUser(Long userId) {
        return transactionRepository.findTop10ByUserIdOrderByDateDesc(userId);
    }
}
