package com.example.cryptocurrencies.Models;

import java.io.Serializable;

public class CryptoHeadlines implements Serializable {

    Roi roi = null;

    String  id = "",
            symbol = "",
            name = "", image = "",
            ath_date = "",
            atl_date = "",
            last_updated = "";

    Double current_price = null,
            market_cap = null,
            market_cap_rank = null,
            fully_diluted_valuation = null,
            total_volume = null,
            high_24h = null,
            low_24h = null,
            price_change_24h = null,
            price_change_percentage_24h = null,
            market_cap_change_24h = null,
            market_cap_change_percentage_24h = null,
            circulating_supply = null,
            total_supply = null,
            max_supply = null,
            ath = null,
            ath_change_percentage = null,
            atl = null,
            atl_change_percentage = null,
            price_change_percentage_1h_in_currency = null,
            price_change_percentage_24h_in_currency = null,
            price_change_percentage_7d_in_currency = null;

    public Roi getRoi() {
        return roi;
    }

    public void setRoi(Roi roi) {
        this.roi = roi;
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

    public String getAth_date() {
        return ath_date;
    }

    public void setAth_date(String ath_date) {
        this.ath_date = ath_date;
    }

    public String getAtl_date() {
        return atl_date;
    }

    public void setAtl_date(String atl_date) {
        this.atl_date = atl_date;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public Double getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Double current_price) {
        this.current_price = current_price;
    }

    public Double getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(Double market_cap) {
        this.market_cap = market_cap;
    }

    public Double getMarket_cap_rank() {
        return market_cap_rank;
    }

    public void setMarket_cap_rank(Double market_cap_rank) {
        this.market_cap_rank = market_cap_rank;
    }

    public Double getFully_diluted_valuation() {
        return fully_diluted_valuation;
    }

    public void setFully_diluted_valuation(Double fully_diluted_valuation) {
        this.fully_diluted_valuation = fully_diluted_valuation;
    }

    public Double getTotal_volume() {
        return total_volume;
    }

    public void setTotal_volume(Double total_volume) {
        this.total_volume = total_volume;
    }

    public Double getHigh_24h() {
        return high_24h;
    }

    public void setHigh_24h(Double high_24h) {
        this.high_24h = high_24h;
    }

    public Double getLow_24h() {
        return low_24h;
    }

    public void setLow_24h(Double low_24h) {
        this.low_24h = low_24h;
    }

    public Double getPrice_change_24h() {
        return price_change_24h;
    }

    public void setPrice_change_24h(Double price_change_24h) {
        this.price_change_24h = price_change_24h;
    }

    public Double getPrice_change_percentage_24h() {
        return price_change_percentage_24h;
    }

    public void setPrice_change_percentage_24h(Double price_change_percentage_24h) {
        this.price_change_percentage_24h = price_change_percentage_24h;
    }

    public Double getMarket_cap_change_24h() {
        return market_cap_change_24h;
    }

    public void setMarket_cap_change_24h(Double market_cap_change_24h) {
        this.market_cap_change_24h = market_cap_change_24h;
    }

    public Double getMarket_cap_change_percentage_24h() {
        return market_cap_change_percentage_24h;
    }

    public void setMarket_cap_change_percentage_24h(Double market_cap_change_percentage_24h) {
        this.market_cap_change_percentage_24h = market_cap_change_percentage_24h;
    }

    public Double getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(Double circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public Double getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(Double total_supply) {
        this.total_supply = total_supply;
    }

    public Double getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(Double max_supply) {
        this.max_supply = max_supply;
    }

    public Double getAth() {
        return ath;
    }

    public void setAth(Double ath) {
        this.ath = ath;
    }

    public Double getAth_change_percentage() {
        return ath_change_percentage;
    }

    public void setAth_change_percentage(Double ath_change_percentage) {
        this.ath_change_percentage = ath_change_percentage;
    }

    public Double getAtl() {
        return atl;
    }

    public void setAtl(Double atl) {
        this.atl = atl;
    }

    public Double getAtl_change_percentage() {
        return atl_change_percentage;
    }

    public void setAtl_change_percentage(Double atl_change_percentage) {
        this.atl_change_percentage = atl_change_percentage;
    }

    public Double getPrice_change_percentage_1h_in_currency() {
        return price_change_percentage_1h_in_currency;
    }

    public void setPrice_change_percentage_1h_in_currency(Double price_change_percentage_1h_in_currency) {
        this.price_change_percentage_1h_in_currency = price_change_percentage_1h_in_currency;
    }

    public Double getPrice_change_percentage_24h_in_currency() {
        return price_change_percentage_24h_in_currency;
    }

    public void setPrice_change_percentage_24h_in_currency(Double price_change_percentage_24h_in_currency) {
        this.price_change_percentage_24h_in_currency = price_change_percentage_24h_in_currency;
    }

    public Double getPrice_change_percentage_7d_in_currency() {
        return price_change_percentage_7d_in_currency;
    }

    public void setPrice_change_percentage_7d_in_currency(Double price_change_percentage_7d_in_currency) {
        this.price_change_percentage_7d_in_currency = price_change_percentage_7d_in_currency;
    }

}
