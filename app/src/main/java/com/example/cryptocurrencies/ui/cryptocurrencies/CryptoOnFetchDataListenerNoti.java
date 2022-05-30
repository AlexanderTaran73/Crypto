package com.example.cryptocurrencies.ui.cryptocurrencies;

import android.content.Context;

import com.example.cryptocurrencies.Models.CryptoHeadlines;

import java.util.List;

public interface CryptoOnFetchDataListenerNoti {
    void  onFetchDataNoti(List<CryptoHeadlines> list, String message, Context context, String[] arr);
    void  onErrorNoti(String message);
}
