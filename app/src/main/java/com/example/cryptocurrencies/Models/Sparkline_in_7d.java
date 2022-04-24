package com.example.cryptocurrencies.Models;

import java.io.Serializable;
import java.util.List;

public class Sparkline_in_7d implements Serializable {
    List<Double> price = null;

    public List<Double> getPrice() {
        return price;
    }

    public void setPrice(List<Double> price) {
        this.price = price;
    }
}
