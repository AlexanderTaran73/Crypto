package com.example.cryptocurrencies.ui.notifications;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.cryptocurrencies.Models.CryptoHeadlines;
import com.example.cryptocurrencies.R;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoOnFetchDataListener;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoRequestManager;

import java.util.List;
import java.util.Locale;


public class AlarmManagerBroadcastReceiverNotifications extends BroadcastReceiver {
    CryptoHeadlines cryptoHeadlines;
    @Override
    public void onReceive(Context context, Intent intent) {
//        PowerManager pm=(PowerManager) context.getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "app:wakelock_tag");
//        wakeLock.acquire();

        String[] item = intent.getStringArrayExtra("item");


        CryptoRequestManager manager = new CryptoRequestManager(context);
        manager.getCryptoHeadlines(crypto_listener, "usd", item[1].toLowerCase(Locale.ROOT), "market_cap_desc", 10, "1h,24h,7d");


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, item[2])
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(cryptoHeadlines.getSymbol().toUpperCase(Locale.ROOT))
                .setContentText("Price: "+ cryptoHeadlines.getCurrent_price()+"\n Change: "+ cryptoHeadlines.getPrice_change_24h())
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(Integer.parseInt(item[0]), builder.build());

//        wakeLock.release();
    }
    private final CryptoOnFetchDataListener crypto_listener = new CryptoOnFetchDataListener() {
        @Override
        public void onFetchData(List<CryptoHeadlines> list, String message) {
            int counter = 0;
            if (list.isEmpty()){
            }
            else {cryptoHeadlines = list.get(0);
            }
        }

        @Override
        public void onError(String message) {
        }
    };
}
