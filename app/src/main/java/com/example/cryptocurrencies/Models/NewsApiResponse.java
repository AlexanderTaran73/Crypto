package com.example.cryptocurrencies.Models;

import com.example.cryptocurrencies.Models.NewsHeadlines;

import java.io.Serializable;
import java.util.List;

public class NewsApiResponse implements Serializable {
    String status;
    int totalResults, nextPage;
    List<NewsHeadlines> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsHeadlines> getResults() {
        return results;
    }

    public void setResults(List<NewsHeadlines> results) {
        this.results = results;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
