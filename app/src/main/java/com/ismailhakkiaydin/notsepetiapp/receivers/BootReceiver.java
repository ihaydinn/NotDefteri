package com.ismailhakkiaydin.notsepetiapp.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ismailhakkiaydin.notsepetiapp.services.Bildirimler;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent2 = new Intent(context, Bildirimler.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 100, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, 240000, pendingIntent);

    }
}
