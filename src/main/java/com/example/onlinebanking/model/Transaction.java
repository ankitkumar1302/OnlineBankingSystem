package com.example.onlinebanking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type; // e.g., "DEPOSIT" or "WITHDRAWAL"
    private double amount;
    private LocalDateTime date;

    @ManyToOne
    private Account account;

}

