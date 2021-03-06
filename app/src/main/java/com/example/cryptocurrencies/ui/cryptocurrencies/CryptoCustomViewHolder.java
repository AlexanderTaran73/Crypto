package com.example.cryptocurrencies.ui.cryptocurrencies;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrencies.R;

public class CryptoCustomViewHolder extends RecyclerView.ViewHolder {
    ImageView icon;
    TextView symbol, price, price_change_percentage, total_volume;
    CardView cardView;

    public CryptoCustomViewHolder(@NonNull View itemView) {
        super(itemView);

        icon = itemView.findViewById(R.id.cryptocurrencies_img);
        symbol = itemView.findViewById(R.id.cryptocurrencies_symbol);
        price = itemView.findViewById(R.id.cryptocurrencies_price);
        price_change_percentage = itemView.findViewById(R.id.cryptocurrencies_price_change_percentage);

        cardView = itemView.findViewById(R.id.cryptocurrencies_container);
    }
}
