package com.example.cryptocurrencies.ui.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrencies.Models.NewsHeadlines;
import com.example.cryptocurrencies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsCustomAdapter extends RecyclerView.Adapter<NewsCustomViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlines;
    private NewsSelectListener listener;

    public NewsCustomAdapter(Context context, List<NewsHeadlines> headlines, NewsSelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsCustomViewHolder(LayoutInflater.from(context).inflate(R.layout.news_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.text_title.setText(headlines.get(position).getTitle());
        holder.text_source.setText(headlines.get(position).getDescription());
        holder.news_pubDate.setText(headlines.get(position).getPubDate());

        if (headlines.get(position).getImage_url()!=null){
            holder.img_headline.setVisibility(View.VISIBLE);
            Picasso.get().load(headlines.get(position).getImage_url()).into(holder.img_headline);
        }
        else {
            holder.img_headline.setVisibility(View.GONE);
        }

        holder.cardView.setOnClickListener(v -> listener.OnNewsClicked(headlines.get(position)));
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
