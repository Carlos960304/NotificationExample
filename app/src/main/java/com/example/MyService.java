package com.example;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {
    private final String channelID = "exampleServiceChannel";
    private final String TAG = "My Service";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreated...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, channelID)
                .setContentTitle("Ubicación actual")
                .setContentText("Estas son las coordenadas de la ubicación de tu dispositivo.")
                .setSmallIcon(R.drawable.ic_location)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        return START_STICKY;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            String channelName = "Example Service Notification";
            NotificationChannel notificationChannel = new NotificationChannel(channelID, channelName, importance);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableLights(true);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        stopSelf();
        super.onDestroy();
        Log.d(TAG, "Service destroyed...");
    }
}
