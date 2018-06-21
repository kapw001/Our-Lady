package com.pappayaed.dowloadservices;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.pappayaed.R;

/**
 * Created by yasar on 28/4/18.
 */

public class ProgressNotification {

    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

    public ProgressNotification(Context context) {

        mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotifyManager.createNotificationChannel(getNotificationChannel());
        }


        mBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        mBuilder.setContentTitle("Download")
                .setContentText("Download in progress")
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setSmallIcon(R.mipmap.ic_launcher);

    }

    public void setTitle(String title) {
        mBuilder.setContentTitle(title);
    }

    public void setContentText(String msg) {
        mBuilder.setContentText(msg);
    }

    public void setOnGoing(boolean onGoing) {
        mBuilder.setOngoing(onGoing);
    }

    public void setPendingIntent(PendingIntent pendingIntent) {

        mBuilder.setOngoing(false);
        mBuilder.setAutoCancel(true);
        mBuilder.setContentIntent(pendingIntent);

    }

    public void setCancelable(DownloadService downloadService, int id) {


    }

    public void updateProgress(DownloadService downloadService, int id, int max, int progress, boolean indeterminate) {
        mBuilder.setProgress(max, progress, indeterminate);
//        mNotifyManager.notify(id, mBuilder.build());

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        downloadService.startForeground(id, mBuilder.build());

//        if (progress == 0) {
//            downloadService.stopForeground(false);
//        }
//        }
    }


    private NotificationChannel getNotificationChannel() {
        NotificationChannel notificationChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
        }

        return notificationChannel;
    }

    public NotificationCompat.Builder getmBuilder() {

        if (mBuilder != null) {

            return mBuilder;
        }

        return null;

    }
}
