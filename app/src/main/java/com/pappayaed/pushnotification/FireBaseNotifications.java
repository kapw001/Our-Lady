package com.pappayaed.pushnotification;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.pappayaed.R;

import java.util.List;
import java.util.Map;

/**
 * Created by yasar on 14/5/18.
 */

public class FireBaseNotifications {

    private static final String TAG = "FireBaseNotifications";
    private Context mContext;

    public FireBaseNotifications(Context mContext) {
        this.mContext = mContext;
    }


    public void showNotification(RemoteMessage remoteMessage) {


        if (remoteMessage.getData().size() > 0) {

            Log.e(TAG, "showNotification: " + remoteMessage.getData().toString());

            playNotificationSound(mContext);

            notification(remoteMessage.getData(), remoteMessage.getNotification());

        }

    }


    private void notification(Map<String, String> data, RemoteMessage.Notification notification) {

        Log.e(TAG, "notification: data json " + data.toString());
        Intent intent;

//        Log.e(TAG, "notification: " + notification.getClickAction());

        if (notification != null) {
            if (notification.getClickAction() != null)
                intent = new Intent(notification.getClickAction());
            else {
                String click_action = "com.pappayaed.TARGET_NOTIFICATION";//remoteMessage.getNotification().getClickAction();//"com.pappayaed.TARGET_NOTIFICATION";//
                intent = new Intent(click_action);
            }

        } else {
            String click_action = "com.pappayaed.TARGET_NOTIFICATION";//remoteMessage.getNotification().getClickAction();//"com.pappayaed.TARGET_NOTIFICATION";//
            intent = new Intent(click_action);
        }

        intent.putExtra("student_name", data.get("student_name"));
        intent.putExtra("lat", data.get("lat" + ""));
        intent.putExtra("lng", data.get("lng") + "");

        if (!isAppIsInBackground(mContext)) {
            mContext.startActivity(intent);
        } else {


            showNotify(intent);
        }

    }

    private void showNotify(Intent intent) {

        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(mContext.getResources().getString(R.string.default_notification_channel_id), "My Notifications", NotificationManager.IMPORTANCE_MAX);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);

            manager.createNotificationChannel(notificationChannel);

        }


        Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://" + mContext.getPackageName() + "/raw/panic");


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext, mContext.getResources().getString(R.string.default_notification_channel_id));
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Panic Alert")
                .setContentText("Your child is invoked panic alert ")
                .setAutoCancel(true)
//                    .setSound(alarmSound)
                .setContentIntent(pendingIntent);

//        NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(999, notificationBuilder.build());
    }


    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

    // Playing notification sound
    public static void playNotificationSound(Context mContext) {
        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + mContext.getPackageName() + "/raw/panic");
            Ringtone r = RingtoneManager.getRingtone(mContext, alarmSound);
            if (r.isPlaying()) {

                r.stop();

            }
            r.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
