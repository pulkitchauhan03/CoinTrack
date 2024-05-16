package com.example.cointrack;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Transaction")
public class Transaction {
    @ColumnInfo(name = "transactionId")
    @PrimaryKey(autoGenerate = true)
    private long transactionId;
    @ColumnInfo(name = "message")
    private String message;
    @ColumnInfo(name = "sender")
    private String sender;
    @ColumnInfo(name = "timestamp")
    private Date timestamp;
    @ColumnInfo(name = "bank")
    private String bank;
    @ColumnInfo(name = "amount")
    private double amount;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "userPrimaryTagId")
    private long userPrimaryTagId;

    @Ignore
    public Transaction() { }

    public Transaction(String message, String sender, Date timestamp, String bank, double amount, String type, long userPrimaryTagId) {
        this.message = message;
        this.sender = sender;
        this.timestamp = timestamp;
        this.bank = bank;
        this.amount = amount;
        this.type = type;
        this.userPrimaryTagId = userPrimaryTagId;
        this.transactionId = 0;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUserPrimaryTagId() {
        return userPrimaryTagId;
    }

    public void setUserPrimaryTagId(long userPrimaryTagId) {
        this.userPrimaryTagId = userPrimaryTagId;
    }
}
