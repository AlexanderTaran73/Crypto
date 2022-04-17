package com.example.cryptocurrencies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrencies.Models.CryptoHeadlines;

import java.util.List;

public class CryptoCustomAdapter  extends RecyclerView.Adapter<CryptoCustomViewHolder> {
    private Context context;
    private List<CryptoHeadlines> headlines;
    private CryptoSelectListener listener;

    public CryptoCustomAdapter(Context context, List<CryptoHeadlines> headlines, CryptoSelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CryptoCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CryptoCustomViewHolder(LayoutInflater.from(context).inflate(R.layout.cryptocurrencies_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoCustomViewHolder holder, int position) {
//        holder...
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
