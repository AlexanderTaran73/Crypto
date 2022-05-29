package com.example.cryptocurrencies.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class NotificationsItem {

    public NotificationsItem(String symbol, String name, String image, String type) {
        this.symbol = symbol;
        this.name = name;
        this.image = image;
        this.type = type;
    }

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "symbol")
    public String symbol;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "type")
    public String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationsItem that = (NotificationsItem) o;
        return id == that.id && Objects.equals(symbol, that.symbol) && Objects.equals(name, that.name) && Objects.equals(image, that.image) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() { return Objects.hash(id, symbol, name, image, type); }


}
