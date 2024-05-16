package com.example.cointrack;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Transaction.class, PrimaryTag.class}, version = 1)
public abstract class CoinTrackDatabase extends RoomDatabase {
    public abstract TransactionDAO getTransactionDAO();
    public abstract PrimaryTagDAO getPrimaryTagDAO();
}
