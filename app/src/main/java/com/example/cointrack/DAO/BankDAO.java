package com.example.cointrack.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.cointrack.models.Bank;
import com.example.cointrack.models.BankAndTransactions;

import java.util.List;

@Dao
public interface BankDAO {
    @Insert
    public void addBank(Bank bank);
    @Update
    public void updateBank(Bank bank);
    @Delete
    public void deleteBank(Bank bank);
    @Query("select * from Bank")
    public List<Bank> getAllBank();
    @Transaction
    @Query("select * from Bank")
    public List<BankAndTransactions> getBankAndTransactions();
}
