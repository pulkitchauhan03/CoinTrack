package com.example.cointrack.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

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
    private String timestamp;
    @ColumnInfo(name = "trBankId")
    private long trBankId;
    @ColumnInfo(name = "amount")
    private double amount;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "trPrimaryTagId")
    private long trPrimaryTagId;

    @Ignore
    public Transaction() { }

    public Transaction(String message, String sender, String timestamp, long trBankId, double amount, String type, long trPrimaryTagId) {
        this.message = message;
        this.sender = sender;
        this.timestamp = timestamp;
        this.trBankId = trBankId;
        this.amount = amount;
        this.type = type;
        this.trPrimaryTagId = trPrimaryTagId;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public long getTrBankId() {
        return trBankId;
    }

    public void setTrBankId(long trBankId) {
        this.trBankId = trBankId;
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

    public long getTrPrimaryTagId() {
        return trPrimaryTagId;
    }

    public void setTrPrimaryTagId(long trPrimaryTagId) {
        this.trPrimaryTagId = trPrimaryTagId;
    }
}
