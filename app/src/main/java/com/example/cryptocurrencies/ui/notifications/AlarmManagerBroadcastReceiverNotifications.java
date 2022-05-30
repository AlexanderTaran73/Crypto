package com.example.cryptocurrencies.ui.notifications;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoOnFetchDataListenerNoti;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoRequestManager;


import java.util.List;
import java.util.Locale;


public class AlarmManagerBroadcastReceiverNotifications extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        String[] item = intent.getStringArrayExtra("item");
        CryptoRequestManager manager = new CryptoRequestManager(context);

        System.out.println(1);
        manager.getCryptoHeadlinesNotifications(crypto_listener, "usd", item[1].toLowerCase(Locale.ROOT), "market_cap_desc", 10, "1h,24h,7d", context, item);

        System.out.println(2);

    }



    private static void createChanelIfNeedIt(NotificationManager notificationManager, String i){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(i, i, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private final CryptoOnFetchDataListenerNoti crypto_listener = new CryptoOnFetchDataListenerNoti() {

        @Override
        public void onFetchDataNoti(List<CryptoHeadlines> list, String message, Context context, String[] arr) {

            System.out.println(1);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent intent1 = new Intent(context, MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,Integer.parseInt(arr[0]),intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(context, arr[0])
                            .setAutoCancel(false)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setContentTitle(arr[1])
                            .setContentText("Price: "+list.get(0).getCurrent_price()+"\n Change: "+list.get(0).getPrice_change_24h())
                            .setPriority(NotificationCompat.PRIORITY_HIGH);
            createChanelIfNeedIt(notificationManager, arr[0]);
            notificationManager.notify(1, notificationBuilder.build());

            System.out.println(3);
        }

        @Override
        public void onErrorNoti(String message) {

        }
    };
}
