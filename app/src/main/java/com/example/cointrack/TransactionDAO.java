package com.example.cointrack;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionDAO {
    @Insert
    public void addTransaction(Transaction transaction);
    @Update
    public void updateTransaction(Transaction transaction);
    @Delete
    public void deleteTransaction(Transaction transaction);
    @Query("select * from `Transaction`")
    public List<Transaction> getAllTransaction();
}
