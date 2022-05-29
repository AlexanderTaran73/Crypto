package com.example.cryptocurrencies.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class NotificationsItem implements Serializable {

    public NotificationsItem(String symbol, String time, String image, String type) {
        this.symbol = symbol;
        this.time = time;
        this.image = image;
        this.type = type;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "symbol")
    public String symbol;

    @ColumnInfo(name = "time")
    public String time;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "type")
    public String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationsItem that = (NotificationsItem) o;
        return id == that.id && Objects.equals(symbol, that.symbol) && Objects.equals(time, that.time) && Objects.equals(image, that.image) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() { return Objects.hash(id, symbol, time, image, type); }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String name) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
