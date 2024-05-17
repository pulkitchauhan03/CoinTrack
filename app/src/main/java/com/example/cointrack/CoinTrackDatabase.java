package com.example.cointrack;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cointrack.DAO.BankDAO;
import com.example.cointrack.DAO.PrimaryTagDAO;
import com.example.cointrack.DAO.TransactionDAO;
import com.example.cointrack.models.Bank;
import com.example.cointrack.models.PrimaryTag;
import com.example.cointrack.models.Transaction;

@Database(entities = {Transaction.class, PrimaryTag.class, Bank.class}, version = 1)
public abstract class CoinTrackDatabase extends RoomDatabase {
    public abstract TransactionDAO getTransactionDAO();
    public abstract PrimaryTagDAO getPrimaryTagDAO();
    public abstract BankDAO getBankDAO();
}
