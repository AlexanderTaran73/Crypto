package com.example.cryptocurrencies.ui.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.cryptocurrencies.Models.NotificationsItem;

import java.util.Calendar;

public class NotificationsAlarm {
    public void setNotificationsAlarm(NotificationsItem item, int hours, int minutes, Context context){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, hours);
        calendar.set(Calendar.MILLISECOND, minutes);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiverNotifications.class);
        intent.putExtra("item", new String[]{Integer.toString(item.getId()), item.getSymbol(), item.getImage(), item.getType(), item.getTime()});

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, item.getId(), intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void cancelNotificationsAlarm(NotificationsItem item, Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiverNotifications.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, item.getId(), intent, 0);
        alarmManager.cancel(pendingIntent);
    }
}
