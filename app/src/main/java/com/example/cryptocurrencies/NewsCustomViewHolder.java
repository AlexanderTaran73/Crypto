package com.example.cryptocurrencies;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NewsCustomViewHolder extends RecyclerView.ViewHolder {
    TextView text_title, text_source;
    ImageView img_headline;
    CardView cardView;
    public NewsCustomViewHolder(@NonNull View itemView) {
        super(itemView);

        text_title = itemView.findViewById(R.id.news_text_title);
        text_source = itemView.findViewById(R.id.news_text_source);
        img_headline = itemView.findViewById(R.id.news_img);
        cardView = itemView.findViewById(R.id.news_container);
    }
}
