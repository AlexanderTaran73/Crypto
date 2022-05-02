package com.example.cryptocurrencies.ui.cryptocurrencies;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrencies.Models.CryptoHeadlines;
import com.example.cryptocurrencies.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

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

        holder.symbol.setText(headlines.get(position).getSymbol().toUpperCase(Locale.ROOT));
        holder.price.setText(headlines.get(position).getCurrent_price().toString()+" $");
        if (headlines.get(position).getPrice_change_percentage_24h()>=0) {
            holder.price_change_percentage.setText(("+"+headlines.get(position).getPrice_change_percentage_24h().toString()).substring(0, 6));
            holder.price_change_percentage.setTextColor(Color.parseColor("#159800"));
        }
        else {
            holder.price_change_percentage.setText((headlines.get(position).getPrice_change_percentage_24h().toString()).substring(0, 6));
            holder.price_change_percentage.setTextColor(Color.parseColor("#FF0000"));
        }


        if (headlines.get(position).getImage()!=null){
            Picasso.get().load(headlines.get(position).getImage()).into(holder.icon);
        }

        holder.cardView.setOnClickListener(v -> listener.OnCryptoClicked(headlines.get(position)));
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
