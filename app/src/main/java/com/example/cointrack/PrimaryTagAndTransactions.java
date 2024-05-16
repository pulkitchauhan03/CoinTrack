package com.example.cointrack;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PrimaryTagAndTransactions {
    @Embedded public PrimaryTag primaryTag;
    @Relation(
            parentColumn = "primaryTagId",
            entityColumn = "userPrimaryTagId"
    )
    public List<Transaction> transactionList;
}
