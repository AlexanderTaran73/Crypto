package com.example.cryptocurrencies.Models;

import java.io.Serializable;

public class Roi implements Serializable {
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

    public void setTimes(Double times) {
        this.times = times;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
