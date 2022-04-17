package com.example.cryptocurrencies.ui.cryptocurrencies;

import androidx.lifecycle.ViewModelProvider;

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
import android.widget.ImageButton;

import com.example.cryptocurrencies.ActivityCalculator;
import com.example.cryptocurrencies.CryptoCustomAdapter;
import com.example.cryptocurrencies.CryptoSelectListener;
import com.example.cryptocurrencies.Models.CryptoHeadlines;
import com.example.cryptocurrencies.R;

import java.util.List;

public class Cryptocurrencies extends Fragment implements CryptoSelectListener {
    ImageButton Calculator_btn;
    RecyclerView recyclerView;
    CryptoCustomAdapter adapter;

    private CryptocurrenciesViewModel mViewModel;
    public static Cryptocurrencies newInstance() {
        return new Cryptocurrencies();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cryptocurrencies, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CryptocurrenciesViewModel.class);
        // TODO: Use the ViewModel



        Calculator_btn = (ImageButton) getView().findViewById(R.id.calculator_btn);

        View.OnClickListener listener = view -> startActivity(new Intent(getActivity(), ActivityCalculator.class));
        Calculator_btn.setOnClickListener(listener);

    }

    private void showCrypto(List<CryptoHeadlines> list){
        Context context = getActivity();
        if (context!=null){
            recyclerView = getView().findViewById(R.id.recycler_cryptocurrencies);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            adapter = new CryptoCustomAdapter(context, list, this);
            recyclerView.setAdapter(adapter);
        }
    }


    @Override
    public void OnCryptoClicked(CryptoHeadlines headlines) {

    }
}