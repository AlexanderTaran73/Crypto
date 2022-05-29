package com.example.cryptocurrencies.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cryptocurrencies.Models.CryptoHeadlines;
import com.example.cryptocurrencies.Models.NotificationsHeadlines;
import com.example.cryptocurrencies.R;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class NotificationsCustomAdapter extends RecyclerView.Adapter<NotificationsCustomViewHolder> {

    private Context context;
    private List<NotificationsHeadlines> headlines;
    private NotificationsSelectListener listener;

    public NotificationsCustomAdapter(Context context, List<NotificationsHeadlines> headlines, NotificationsSelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener = listener;
    }


    @NonNull
    @Override
    public NotificationsCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationsCustomViewHolder(LayoutInflater.from(context).inflate(R.layout.notifications_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsCustomViewHolder holder, int position) {

        holder.symbol.setText(headlines.get(position).getSymbol().toUpperCase(Locale.ROOT));
        holder.type.setText(headlines.get(position).getType());

        if (headlines.get(position).getImage()!=null){
            Picasso.get().load(headlines.get(position).getImage()).into(holder.icon);
        }

        holder.cardView.setOnClickListener(v -> listener.OnNotificationsClicked(headlines.get(position)));
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
