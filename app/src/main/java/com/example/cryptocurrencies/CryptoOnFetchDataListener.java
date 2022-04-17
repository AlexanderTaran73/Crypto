package com.example.cryptocurrencies;

import com.example.cryptocurrencies.Models.CryptoHeadlines;

import java.util.List;

public interface CryptoOnFetchDataListener {
    void  onFetchData(List<CryptoHeadlines> list, String message);
    void  onError(String message);
}
