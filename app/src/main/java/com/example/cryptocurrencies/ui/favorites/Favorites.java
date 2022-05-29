package com.example.cryptocurrencies.ui.favorites;

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

import com.example.cryptocurrencies.App;
import com.example.cryptocurrencies.Models.AppDatabase;
import com.example.cryptocurrencies.Models.FavItem;
import com.example.cryptocurrencies.Models.FavItemDao;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoCustomAdapter;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoDetailsActivity;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoOnFetchDataListener;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoRequestManager;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoSelectListener;
import com.example.cryptocurrencies.Models.CryptoHeadlines;
import com.example.cryptocurrencies.R;

import java.util.List;

public class Favorites extends Fragment implements CryptoSelectListener {
    RecyclerView recyclerView;
    CryptoCustomAdapter adapter;


    private FavoritesViewModel mViewModel;

    public static Favorites newInstance() {
        return new Favorites();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);


        AppDatabase db = App.getInstance().getDatabase();

        FavItemDao favItemDao = db.favItemDao();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String ids = "";
                List<String> list = favItemDao.getNameByTipe("crypto");
                for (String i : list) {
                    ids = ids+i+",";
                }
                if(!list.isEmpty()){
                CryptoRequestManager manager = new CryptoRequestManager(getActivity());
                manager.getCryptoHeadlines(crypto_listener, "usd", ids, "market_cap_desc", 100, "1h,24h,7d");}

            }
        });


    }


    private final CryptoOnFetchDataListener crypto_listener = new CryptoOnFetchDataListener() {
        @Override
        public void onFetchData(List<CryptoHeadlines> list, String message) {
            if (list.isEmpty()){
                Toast.makeText(getActivity(), "No data found!!!", Toast.LENGTH_SHORT).show();
            }
            else {
                showCrypto(list);
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(getActivity(), "An Error Occured!!!", Toast.LENGTH_SHORT).show();

        }
    };

    private void showCrypto(List<CryptoHeadlines> list){
        Context context = getActivity();
        if (context!=null){
            recyclerView = getView().findViewById(R.id.favorites_recycler_cryptocurrencies);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            adapter = new CryptoCustomAdapter(context, list, this);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void OnCryptoClicked(CryptoHeadlines headlines) {
        startActivity(new Intent(getActivity(), CryptoDetailsActivity.class)
                .putExtra("data", headlines));
    }
}