package com.example.cryptocurrencies.Models;

import com.example.cryptocurrencies.Models.NewsHeadlines;

import java.io.Serializable;
import java.util.List;

public class NewsApiResponse implements Serializable {
    String status;
    int titalResults;
    List<NewsHeadlines> articles;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTitalResults() {
        return titalResults;
    }

    public void setTitalResults(int titalResults) {
        this.titalResults = titalResults;
    }

    public List<NewsHeadlines> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsHeadlines> articles) {
        this.articles = articles;
    }
}
