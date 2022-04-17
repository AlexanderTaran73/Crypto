package com.example.cryptocurrencies;

import com.example.cryptocurrencies.Models.NewsHeadlines;

import java.util.List;

public interface NewsOnFetchDataListener<NewsApiResponse> {
    void  onFetchData(List<NewsHeadlines> list, String message);
    void  onError(String message);
}
