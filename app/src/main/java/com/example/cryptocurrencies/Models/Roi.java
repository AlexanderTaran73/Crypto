package com.example.cryptocurrencies.Models;

public class Roi {
    String currency = "";
    Double  times = null,
            percentage = null;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = Double.valueOf(times);
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = Double.valueOf(percentage);
    }
}
