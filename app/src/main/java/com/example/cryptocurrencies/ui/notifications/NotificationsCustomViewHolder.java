package com.example.cryptocurrencies.ui.notifications;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrencies.R;

public class NotificationsCustomViewHolder extends RecyclerView.ViewHolder {
    ImageView icon;
    TextView symbol, type;
    CardView cardView;
    public NotificationsCustomViewHolder(@NonNull View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.notifications_img);
        symbol = itemView.findViewById(R.id.notifications_symbol);
        type = itemView.findViewById(R.id.notifications_type);
        cardView = itemView.findViewById(R.id.notifications_container);
    }
}
