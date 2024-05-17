package com.example.cointrack.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Bank")
public class Bank {
    @ColumnInfo(name = "bankId")
    @PrimaryKey(autoGenerate = true)
    private long bankId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "msgSender")
    private String msgSender;
    @ColumnInfo(name = "regex")
    private String regex;

    @Ignore
    public Bank() {}

    public Bank(String name, String msgSender, String regex) {
        this.name = name;
        this.msgSender = msgSender;
        this.regex = regex;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
