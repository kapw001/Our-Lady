package com.pappayaed.dowloadservices;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.pappayaed.R;
import com.pappayaed.common.ActivityUtils;
import com.pappayaed.common.FileSaveAndOpen;
import com.pappayaed.data.DataRepository;
import com.pappayaed.data.DataSource;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.AttachmentDetail;
import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.data.pref.Pref;
import com.pappayaed.data.pref.PreferencesHelper;
import com.pappayaed.data.remote.ApiService;
import com.pappayaed.data.remote.RemoteDataSourceHelper;
import com.pappayaed.data.retrofitclient.ApiEndPoint;
import com.pappayaed.data.retrofitclient.RetrofitClient;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;


public class DownloadService extends Service implements DownloadServiceCallback {

    private static final String TAG = "DownloadService";
    private String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
    private Pref pref;
    private RetrofitClient retrofitClient;
    private ApiService apiService;
    private RemoteDataSourceHelper remoteDataSource;
    public DataSource dataSource;

    private Context mContext;

    private IDownloadPresenter iDownloadPresenter;


    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;


    public boolean isDownloading = false;

    public static Queue<QueueData> downloadQueue = new LinkedList<>();


    //    public DownloadService() {
//        super("DownloadService");
//
//
//    }
    NotificationCompat.Builder builder;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel notificationChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            mNotifyManager.createNotificationChannel(notificationChannel);
        }

        mBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        mBuilder.setContentTitle("Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.mipmap.ic_launcher);


        NotificationChannel notificationChannel1 = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel1 = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);

            // Configure the notification channel.
            notificationChannel1.setDescription("Channel description");
            notificationChannel1.enableLights(true);
            notificationChannel1.setLightColor(Color.RED);
            notificationChannel1.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel1.enableVibration(true);

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel1);
        }
//
        builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Downloading") // use something from something from R.string
                .setContentTitle("Download in progress") // use something from something from
                .setContentText("Downloading") // use something from something from
        ; // display indeterminate progress


        startForeground(99999, builder.build());


        pref = PreferencesHelper.getPreferencesInstance(getApplicationContext());

        retrofitClient = RetrofitClient.getRetrofitClientInstance(ApiEndPoint.BASE_URL);

        apiService = retrofitClient.getRetrofit().create(ApiService.class);

        remoteDataSource = new RemoteDataSourceHelper(apiService);

        dataSource = new DataRepository(getApplicationContext(), remoteDataSource, pref);

//        iDownloadPresenter = new DownloadServiceImpl(this, pref, retrofitClient, apiService, remoteDataSource, dataSource);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        mContext = getApplicationContext();

        onHandleIntent(intent);

        return START_STICKY;
    }


    protected void onHandleIntent(final Intent intent) {

        if (intent != null) {

//            synchronized (intent) {
//
//            NotificationChannel notificationChannel = null;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
//
//                // Configure the notification channel.
//                notificationChannel.setDescription("Channel description");
//                notificationChannel.enableLights(true);
//                notificationChannel.setLightColor(Color.RED);
//                notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
//                notificationChannel.enableVibration(true);
//
//                NotificationManager manager=(NotificationManager)getSystemService()
//            }
////
//            final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setTicker("Your Ticker") // use something from something from R.string
//                    .setContentTitle("Your content title") // use something from something from
//                    .setContentText("Your content text") // use something from something from
//                    ; // display indeterminate progress


//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }


            new Thread(new Runnable() {
                @Override
                public void run() {


                    final String action = intent.getAction();

                    if (action.equalsIgnoreCase("download")) {

                        final String attachId = intent.getStringExtra("attachment_id");
                        final String fileName = intent.getStringExtra("name");

                        if (!downloadQueue.contains(attachId) && isDownloading) {

                            QueueData queueData = new QueueData(Integer.parseInt(attachId), fileName);

                            downloadQueue.add(queueData);

                            int downlodCount = downloadQueue.size() + 1;

                            builder.setContentText(downlodCount + " Queue for downloading");

                            startForeground(99999, builder.build());

                        } else {

//                            call(Integer.parseInt(attachId));

                            downloadApi(Integer.parseInt(attachId), fileName);
                        }


                    } else if (action.equalsIgnoreCase("finished")) {
                        int attachId = intent.getIntExtra("attachment_id", 0);

                        String fileName = intent.getStringExtra("name");

                        if (FileSaveAndOpen.isFileExists(fileName)) {
                            showDownloadFile(FileSaveAndOpen.getFile(fileName));
                        } else {

                            Log.e(TAG, "onHandleIntent: There is no file, something went wrong ");
//                        Toast.makeText(this, "There is no file, something went wrong", Toast.LENGTH_SHORT).show();

                        }

                        stopForeground(true);

                        stopSelf();

                    }


                }
            }).start();


        }
//        }

    }


    private void call(int id) {

        new Downloader(dataSource, this, id).execute();
//        new Downloader(dataSource, this, id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

//        new DownloadFile(this, iDownloadPresenter, getApplicationContext(), id, dataSource).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    public void showDownloadFile(File myFile) {


        FileSaveAndOpen.openFile(this, myFile);

    }


    private void downLoading(final String attachId) {

        iDownloadPresenter.download(this, attachId);


    }


    @Override
    public void downloadFile(AttachmentDetail attachmentDetail) {
        stopSelf();
        iDownloadPresenter.downloadFile(this, attachmentDetail);


    }

    @Override
    public void failed(String attachId) {
        stopSelf();
        iDownloadPresenter.failed(this, attachId);


    }

    @Override
    public void progressUpadte(int id, int max, int progress, boolean indeterminate) {

    }


    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: Intent services ");
        super.onDestroy();


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void downloadApi(final int id, String fileName) {

        mBuilder.setProgress(100, 0, true);
        mBuilder.setOngoing(true);
        mBuilder.setAutoCancel(false);
        mBuilder.setContentTitle(fileName);
//
//            DownloadService.this.startForeground(id, mBuilder.build());
        mNotifyManager.notify(id, mBuilder.build());


        Map<Object, Object> json = new HashMap<>();
        Map<Object, Object> params = new HashMap<>();
        params.put("login", dataSource.getEmailOrUsername());
        params.put("password", dataSource.getPassword());
        params.put("user_type", dataSource.getUserType());
        params.put("attachment_id", id);
        json.put("params", params);

        final String js = new JSONObject(json).toString();

        new Thread(new Runnable() {
            @Override
            public void run() {

                boolean b = false;
                synchronized (this) {


                    dataSource.getAttachment_id_data(js, new DataListener() {
                        @Override
                        public void onSuccess(Object object) {
                            ResultResponse resultResponse = (ResultResponse) object;

                            if (resultResponse.getResult() != null && resultResponse.getResult().getAttachmentDetail() != null) {
                                AttachmentDetail attachmentDetail = resultResponse.getResult().getAttachmentDetail();
                                finishedDownload(attachmentDetail, id);
                            } else {

                                finishedDownload(null, id);
                            }
                            Thread.currentThread().interrupt();
                        }

                        @Override
                        public void onFail(Throwable throwable) {
                            finishedDownload(null, id);
                            Thread.currentThread().interrupt();
                        }

                        @Override
                        public void onNetworkFailure() {
                            finishedDownload(null, id);
                            Thread.currentThread().interrupt();
                        }
                    });

                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


            }
        }).start();


    }

    private void finishedDownload(AttachmentDetail result, int id) {

        // Removes the progress bar
        mBuilder.setProgress(0, 0, false);


        if (result != null) {

            FileSaveAndOpen.saveFile(result.getName(), result.getAttachmentData());
            isDownloading = false;
            Bundle bundle = new Bundle();
            bundle.putInt("attachment_id", result.getAttachId());
            bundle.putString("name", result.getName());
            Intent intent = ActivityUtils.getIntentWithAction(getApplicationContext(), DownloadService.class, bundle, "finished");
            mBuilder.setContentTitle(result.getName());
            mBuilder.setContentText("Download complete");


            PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pendingIntent);


        } else {

            Bundle bundle = new Bundle();
            Intent intent = ActivityUtils.getIntentWithAction(getApplicationContext(), DownloadService.class, bundle, "finished Nothing");
            isDownloading = false;
            mBuilder.setContentTitle(result.getName());
            mBuilder.setContentText("Download Failed");
            PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pendingIntent);

            Log.e(TAG, "onPostExecute: download Failed ");

        }

//            notification.setOnGoing(false);
//
//            notification.updateProgress(DownloadService.this, id, 0, 0, false);


        mBuilder.setOngoing(false);
        mBuilder.setAutoCancel(true);


//            Log.e(TAG, "onPostExecute: id is  " + id);
//
//            DownloadService.this.startForeground(id, mBuilder.build());
//            DownloadService.this.stopForeground(false);

        mNotifyManager.notify(id, mBuilder.build());

        if (downloadQueue.isEmpty()) {

            stopForeground(true);

            stopSelf();
        }


        if (/*isDownloading &&*/ !downloadQueue.isEmpty()) {
            QueueData id1 = downloadQueue.poll();

            int downlodCount = downloadQueue.size() + 1;

            builder.setContentText(downlodCount + " Queue for downloading");

            startForeground(99999, builder.build());

            downloadApi(id1.getId(), id1.getFileName());
        }

    }


    private class Downloader extends AsyncTask<Void, Integer, AttachmentDetail> {

        private ProgressNotification notification;
        private DataSource dataSource;
        private Context context;
        private int id;
        AttachmentDetail attachmentDetail = null;

        private Downloader(DataSource dataSource, Context context, int id) {
            this.dataSource = dataSource;
            this.context = context;
            this.id = id;
            this.notification = new ProgressNotification(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            isDownloading = true;
//            notification.updateProgress(DownloadService.this, id, 100, 0, true);

            // Displays the progress bar for the first time.
            mBuilder.setProgress(100, 0, true);
            mBuilder.setOngoing(true);
            mBuilder.setAutoCancel(false);
//
//            DownloadService.this.startForeground(id, mBuilder.build());
            mNotifyManager.notify(id, mBuilder.build());
//            Log.e(TAG, "onPreExecute: id " + id);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // Update progress
//            mBuilder.setProgress(100, values[0], false);
////            mNotifyManager.notify(id, mBuilder.build());
//            DownloadService.this.startForeground(id, mBuilder.build());
//            super.onProgressUpdate(values);
        }

        @Override
        protected AttachmentDetail doInBackground(Void... params1) {

//            DownloadService.this.startForeground(id, mBuilder.build());


            Map<Object, Object> json = new HashMap<>();
            Map<Object, Object> params = new HashMap<>();
            params.put("login", dataSource.getEmailOrUsername());
            params.put("password", dataSource.getPassword());
            params.put("user_type", dataSource.getUserType());
            params.put("attachment_id", id);
            json.put("params", params);

            final String js = new JSONObject(json).toString();


            dataSource.getAttachment_id_data(js, new DataListener() {
                @Override
                public void onSuccess(Object object) {
                    ResultResponse resultResponse = (ResultResponse) object;

                    if (resultResponse.getResult() != null && resultResponse.getResult().getAttachmentDetail() != null) {
                        attachmentDetail = resultResponse.getResult().getAttachmentDetail();
                    } else {

                        attachmentDetail = null;
                    }
                }

                @Override
                public void onFail(Throwable throwable) {
                    attachmentDetail = null;
                }

                @Override
                public void onNetworkFailure() {
                    attachmentDetail = null;
                }
            });


//            try {
//                wait(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }


//            try {
//                URL url = new URL(ApiEndPoint.BASE_URL + "mobile/rest/attachment_id_data");
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("POST");
//                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//                conn.setRequestProperty("Accept", "application/json");
//                conn.setDoOutput(true);
//                conn.setDoInput(true);
//
//
//                Log.e("JSON", js);
//                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
//                //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
//                os.writeBytes(js);
//
//                os.flush();
//                os.close();
//
////                Log.e("STATUS", String.valueOf(conn.getResponseCode()));
////                Log.e("MSG", conn.getResponseMessage());
//
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                byte[] buffer = new byte[1024]; // Experiment with this value
//                int bytesRead;
//
//                while ((bytesRead = conn.getInputStream().read(buffer)) != -1) {
//                    baos.write(buffer, 0, bytesRead);
//                }
//
//                BufferedReader in = new BufferedReader(
//                        new InputStreamReader(new ByteArrayInputStream(baos.toByteArray())));
//
//                Log.e(TAG, "doInBackground: " + in.toString());
//
//                String inputLine;
//                StringBuffer response = new StringBuffer();
//
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//
//
//                Log.e(TAG, "run: " + response.toString());
//
//                if (conn.getResponseCode() == 200) {
//
//                    Gson gson = new Gson();
//
//                    ResultResponse resultResponse = gson.fromJson(response.toString(), ResultResponse.class);
//
//                    if (resultResponse.getResult() != null) {
//                        attachmentDetail = resultResponse.getResult().getAttachmentDetail();
//                    }
//
//
//                }
//
//                conn.disconnect();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }


            return attachmentDetail;
        }

        @Override
        protected void onPostExecute(AttachmentDetail result) {
            super.onPostExecute(result);

            // Removes the progress bar
            mBuilder.setProgress(0, 0, false);


            if (result != null) {

                FileSaveAndOpen.saveFile(result.getName(), result.getAttachmentData());
                isDownloading = false;
                Bundle bundle = new Bundle();
                bundle.putInt("attachment_id", result.getAttachId());
                bundle.putString("name", result.getName());
                Intent intent = ActivityUtils.getIntentWithAction(context, DownloadService.class, bundle, "finished");
                mBuilder.setContentTitle(result.getName());
                mBuilder.setContentText("Download complete");


                PendingIntent pendingIntent = PendingIntent.getService(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(pendingIntent);


            } else {

                Bundle bundle = new Bundle();
                Intent intent = ActivityUtils.getIntentWithAction(context, DownloadService.class, bundle, "finished Nothing");
                isDownloading = false;
                mBuilder.setContentTitle(result.getName());
                mBuilder.setContentText("Download Failed");
                PendingIntent pendingIntent = PendingIntent.getService(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(pendingIntent);

                Log.e(TAG, "onPostExecute: download Failed ");

            }

//            notification.setOnGoing(false);
//
//            notification.updateProgress(DownloadService.this, id, 0, 0, false);


            mBuilder.setOngoing(false);
            mBuilder.setAutoCancel(true);


//            Log.e(TAG, "onPostExecute: id is  " + id);
//
//            DownloadService.this.startForeground(id, mBuilder.build());
//            DownloadService.this.stopForeground(false);

            mNotifyManager.notify(id, mBuilder.build());

            if (downloadQueue.isEmpty()) {

                stopForeground(true);

                stopSelf();
            }


            if (/*isDownloading &&*/ !downloadQueue.isEmpty()) {
                QueueData id = downloadQueue.poll();

                int downlodCount = downloadQueue.size() + 1;

                builder.setContentText(downlodCount + " Queue for downloading");

                startForeground(99999, builder.build());

                call(id.getId());
            }
        }
    }


}




