package com.example.cryptocurrencies.ui.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrencies.R;

public class NewsCustomViewHolder extends RecyclerView.ViewHolder {
    TextView text_title, text_source, news_pubDate;
    ImageView img_headline;
    CardView cardView;
    public NewsCustomViewHolder(@NonNull View itemView) {
        super(itemView);

        text_title = itemView.findViewById(R.id.news_text_title);
        text_source = itemView.findViewById(R.id.news_text_description);
        img_headline = itemView.findViewById(R.id.news_img);
        cardView = itemView.findViewById(R.id.news_container);
        news_pubDate = itemView.findViewById(R.id.news_pubDate);
    }
}
