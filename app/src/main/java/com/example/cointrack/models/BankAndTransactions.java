package com.example.cointrack.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class BankAndTransactions {
    @Embedded public Bank bank;
    @Relation(
            parentColumn = "bankId",
            entityColumn = "trBankId"
    )
    public List<Transaction> transactionList;
}
