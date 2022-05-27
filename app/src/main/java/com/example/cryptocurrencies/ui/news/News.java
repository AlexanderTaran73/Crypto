package com.example.cryptocurrencies.ui.news;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cryptocurrencies.Models.NewsApiResponse;
import com.example.cryptocurrencies.Models.NewsHeadlines;
import com.example.cryptocurrencies.R;

import java.util.List;

public class News extends Fragment implements NewsSelectListener {

    RecyclerView recyclerView;
    NewsCustomAdapter adapter;
    SearchView searchView;

    Integer counter = 0;

    private NewsViewModel mViewModel;
    public static News newInstance() {
        return new News();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        // TODO: Use the ViewModel


        searchView = getView().findViewById(R.id.news_search_view);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        NewsSearchRequestManager manager = new  NewsSearchRequestManager(getActivity());
                        manager.getSearchNewsHeadlines(listener,0, query);
                    }
                });

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                NewsRequestManager manager = new  NewsRequestManager(getActivity());
                manager.getNewsHeadlines(listener, counter);
            }
        });
    }
    private final NewsOnFetchDataListener<NewsApiResponse> listener = new NewsOnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if (list.isEmpty()){
                Toast.makeText(getActivity(), "No data found!!!", Toast.LENGTH_SHORT).show();
            }
            else {
                showNews(list);
            }
        }
        @Override
        public void onError(String message) {
            Toast.makeText(getActivity(), "An Error Occured!!!", Toast.LENGTH_SHORT).show();

        }
    };

    private void showNews(List<NewsHeadlines> list) {
        Context context = getActivity();
        if (context!=null) {
            try {
            recyclerView = getView().findViewById(R.id.recycler_news);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            adapter = new NewsCustomAdapter(context, list, this);
            recyclerView.setAdapter(adapter);}
            catch (Exception e){
                System.out.println(e);
            }
        }
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(getActivity(), NewsDetailsActivity.class)
        .putExtra("data", headlines));
    }
}