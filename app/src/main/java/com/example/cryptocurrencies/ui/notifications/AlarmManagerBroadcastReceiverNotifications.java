package com.example.cryptocurrencies.ui.notifications;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.PowerManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import com.example.cryptocurrencies.MainActivity;
import com.example.cryptocurrencies.Models.CryptoHeadlines;
import com.example.cryptocurrencies.R;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoOnFetchDataListener;


import java.util.List;



public class AlarmManagerBroadcastReceiverNotifications extends BroadcastReceiver {
    CryptoHeadlines cryptoHeadlines;
    @Override
    public void onReceive(Context context, Intent intent) {

        String[] item = intent.getStringArrayExtra("item");
//        CryptoRequestManager manager = new CryptoRequestManager(context);
//        manager.getCryptoHeadlines(crypto_listener, "usd", item[1].toLowerCase(Locale.ROOT), "market_cap_desc", 10, "1h,24h,7d");

//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, String.valueOf(1))
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setWhen(System.currentTimeMillis())
//                .setContentTitle("Alarm")
//                .setContentText("alarm")
//                .setAutoCancel(false)
//                .setDefaults(NotificationCompat.DEFAULT_ALL)
//                .setPriority(NotificationCompat.PRIORITY_HIGH);
//////"Price: "+ cryptoHeadlines.getCurrent_price()+"\n Change: "+ cryptoHeadlines.getPrice_change_24h()
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
//        notificationManagerCompat.notify(123, builder.build());
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,Integer.parseInt(item[0]),intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, "CHANNEL_ID")
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setContentTitle("TEST")
                .setContentText("TEST")
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        createChanelIfNeedIt(notificationManager);
        notificationManager.notify(1, notificationBuilder.build());
    }

    

    private static void createChanelIfNeedIt(NotificationManager notificationManager){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("1", "1", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
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
