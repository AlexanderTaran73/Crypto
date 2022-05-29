package com.example.cryptocurrencies.Models;

import java.io.Serializable;

public class NotificationsHeadlines implements Serializable {

    String  id = "",
            symbol = "",
            name = "",
            image = "",
            type = "";

    public NotificationsHeadlines(String id, String symbol, String name, String image, String type) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.image = image;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
