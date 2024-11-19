package com.example.onlinebanking.repository;

import com.example.onlinebanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTop10ByUserIdOrderByDateDesc(Long userId);
}
