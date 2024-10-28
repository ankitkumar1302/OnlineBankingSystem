package com.example.onlinebanking.service;

import com.example.onlinebanking.model.Account;
import com.example.onlinebanking.model.Transaction;
import com.example.onlinebanking.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    public Transaction createTransaction(Transaction transaction, String accountNumber) {
        Account account = accountService.getAccountByNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        double newBalance = account.getBalance() + transaction.getAmount();
        accountService.updateBalance(account, newBalance);

        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
