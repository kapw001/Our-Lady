package com.pappayaed.pushnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pappayaed.R;
import com.pappayaed.common.Config;
import com.pappayaed.common.NotificationUtils;
import com.pappayaed.ui.main.MainActivity;
import com.pappayaed.ui.whereisstudent.WhereIsStudentActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by yasar on 10/5/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private FireBaseNotifications notifications;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

//        Log.e(TAG, "onMessageReceived: " + remoteMessage.getNotification().getClickAction());
//
//        Log.e(TAG, "onMessageReceived: " + remoteMessage.getData().toString());

        notifications = new FireBaseNotifications(getApplicationContext());

        notifications.showNotification(remoteMessage);

//        NotificationUtils.playNotificationSound(getApplicationContext());
//
//        if (remoteMessage.getData().size() > 0) {
////            Log.e(TAG, "Message data payload: " + remoteMessage.getData().toString());
////            Log.e(TAG, "Message data payload: notify  " + remoteMessage.getData());
//
//            String student_name = remoteMessage.getData().get("student_name");
//            String lat = remoteMessage.getData().get("lat");
//            String lng = remoteMessage.getData().get("lng");
//
//
//            String click_action = "com.pappayaed.TARGET_NOTIFICATION";//remoteMessage.getNotification().getClickAction();//"com.pappayaed.TARGET_NOTIFICATION";//
//
////            Log.e(TAG, "onMessageReceived: " + click_action);
//
//            sendNotification(student_name, lat, lng, click_action);
//
//
//        }

    }


//    private void sendNotification(String student_name, String lat, String lng, String click_action) {
//
//        Intent intent = new Intent(click_action);
//        intent.putExtra("student_name", student_name);
//        intent.putExtra("lat", lat);
//        intent.putExtra("lng", lng);
//
//        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
//
//            getApplicationContext().startActivity(intent);
//
//            Log.e(TAG, "sendNotification: Activity called ");
//
//
//        } else {
//            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,
//                    PendingIntent.FLAG_ONE_SHOT);
//
//            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//            NotificationChannel notificationChannel = null;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                notificationChannel = new NotificationChannel(getResources().getString(R.string.default_notification_channel_id), "My Notifications", NotificationManager.IMPORTANCE_MAX);
//
//                // Configure the notification channel.
//                notificationChannel.setDescription("Channel description");
//                notificationChannel.enableLights(true);
//                notificationChannel.setLightColor(Color.RED);
//                notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
//                notificationChannel.enableVibration(true);
//
//                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                manager.createNotificationChannel(notificationChannel);
//
//            }
//
//
//            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
//                    + "://" + getPackageName() + "/raw/panic");
//
//
//            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, getResources().getString(R.string.default_notification_channel_id));
//            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentTitle("Panic Alert")
//                    .setContentText("Your child in danger, click to see where is ")
//                    .setAutoCancel(true)
////                    .setSound(alarmSound)
//                    .setContentIntent(pendingIntent);
//
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.notify(999, notificationBuilder.build());
//
//        }
//    }

}