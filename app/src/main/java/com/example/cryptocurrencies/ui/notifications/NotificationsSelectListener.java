package com.example.cryptocurrencies.ui.notifications;

import com.example.cryptocurrencies.Models.NotificationsItem;

public interface NotificationsSelectListener {
    void OnNotificationsClicked(NotificationsItem headlines);
    void OnNotificationsDeleteClicked(NotificationsItem headlines);
}
