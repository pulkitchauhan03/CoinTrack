package com.example.cointrack;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PrimaryTagDAO {
    @Insert
    public void addPrimaryTag(PrimaryTag primaryTag);
    @Update
    public void updatePrimaryTag(PrimaryTag primaryTag);
    @Delete
    public void deletePrimaryTag(PrimaryTag primaryTag);
    @Query("select * from `PrimaryTag`")
    public List<PrimaryTag> getAllPrimaryTag();
    @Transaction
    @Query("select * from `PrimaryTag`")
    public List<PrimaryTagAndTransactions> getPrimaryTagAndTransactions();
}
