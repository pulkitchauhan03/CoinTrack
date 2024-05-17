package com.example.cointrack.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "PrimaryTag")
public class PrimaryTag {
    @ColumnInfo(name = "primaryTagId")
    @PrimaryKey(autoGenerate = true)
    private long primaryTagId;
    @ColumnInfo(name = "name")
    private String name;

    @Ignore
    public PrimaryTag() {}

    public PrimaryTag(String name) {
        this.name = name;
    }

    public long getPrimaryTagId() {
        return primaryTagId;
    }

    public void setPrimaryTagId(long primaryTagId) {
        this.primaryTagId = primaryTagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
