package com.example.cryptocurrencies.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class FavItem {
    public FavItem(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "name")
    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavItem favItem = (FavItem) o;
        return id == favItem.id && Objects.equals(type, favItem.type) && Objects.equals(name, favItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name);
    }
}
