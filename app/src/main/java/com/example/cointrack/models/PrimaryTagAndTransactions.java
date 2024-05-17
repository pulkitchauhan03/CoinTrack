package com.example.cointrack.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.cointrack.models.PrimaryTag;
import com.example.cointrack.models.Transaction;

import java.util.List;

public class PrimaryTagAndTransactions {
    @Embedded public PrimaryTag primaryTag;
    @Relation(
            parentColumn = "primaryTagId",
            entityColumn = "trPrimaryTagId"
    )
    public List<Transaction> transactionList;
}
