package com.example.cryptocurrencies.ui.news;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cryptocurrencies.NewsCustomAdapter;
import com.example.cryptocurrencies.Models.NewsApiResponse;
import com.example.cryptocurrencies.Models.NewsHeadlines;
import com.example.cryptocurrencies.NewsDetailsActivity;
import com.example.cryptocurrencies.NewsRequestManager;
import com.example.cryptocurrencies.NewsSelectListener;
import com.example.cryptocurrencies.NewsOnFetchDataListener;
import com.example.cryptocurrencies.R;

import java.util.List;

public class News extends Fragment implements NewsSelectListener, View.OnClickListener {

    RecyclerView recyclerView;
    NewsCustomAdapter adapter;
    ProgressDialog dialog;
    Button b1,b2,b3,b4,b5,b6,b7;
    SearchView searchView;

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

        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Fetching news articles...");
        dialog.show();

        b1 = getView().findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2 = getView().findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3 = getView().findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4 = getView().findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5 = getView().findViewById(R.id.btn_5);
        b5.setOnClickListener(this);
        b6 = getView().findViewById(R.id.btn_6);
        b6.setOnClickListener(this);
        b7 = getView().findViewById(R.id.btn_7);
        b7.setOnClickListener(this);

        searchView = getView().findViewById(R.id.news_search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching news articles of " + query);
                dialog.show();
                NewsRequestManager manager = new  NewsRequestManager(getActivity());
                manager.getNewsHeadlines(listener, "", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        NewsRequestManager manager = new  NewsRequestManager(getActivity());
        manager.getNewsHeadlines(listener, "technology", null);

    }

    private final NewsOnFetchDataListener<NewsApiResponse> listener = new NewsOnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if (list.isEmpty()){
                Toast.makeText(getActivity(), "No data found!!!", Toast.LENGTH_SHORT).show();
            }
            else {
                showNews(list);
                dialog.dismiss();
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
            recyclerView = getView().findViewById(R.id.recycler_news);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            adapter = new NewsCustomAdapter(context, list, this);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        System.out.println(headlines.getContent());
        startActivity(new Intent(getActivity(), NewsDetailsActivity.class)
        .putExtra("data", headlines));

    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String category = button.getText().toString();

        dialog.setTitle("Fetching news articles of " + category);
        dialog.show();

        NewsRequestManager manager = new  NewsRequestManager(getActivity());
        manager.getNewsHeadlines(listener, category, null);
    }
}