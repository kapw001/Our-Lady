package com.pappayaed.dowloadservices;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.pappayaed.common.ActivityUtils;
import com.pappayaed.common.FileSaveAndOpen;
import com.pappayaed.data.DataSource;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.AttachmentDetail;
import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.data.pref.Pref;
import com.pappayaed.data.remote.ApiService;
import com.pappayaed.data.remote.RemoteDataSourceHelper;
import com.pappayaed.data.retrofitclient.RetrofitClient;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yasar on 2/5/18.
 */

public class DownloadServiceImpl implements IDownloadPresenter {

    private static final String TAG = "DownloadServiceImpl";
    private DownloadServiceCallback callback;
    private Pref pref;
    private RetrofitClient retrofitClient;
    private ApiService apiService;
    private RemoteDataSourceHelper remoteDataSource;
    public DataSource dataSource;

    private ProgressNotification notification;


    public DownloadServiceImpl(DownloadServiceCallback callback, Pref pref, RetrofitClient retrofitClient, ApiService apiService, RemoteDataSourceHelper remoteDataSource, DataSource dataSource) {
        this.callback = callback;
        this.pref = pref;
        this.retrofitClient = retrofitClient;
        this.apiService = apiService;
        this.remoteDataSource = remoteDataSource;
        this.dataSource = dataSource;
    }


    @Override
    public void download(Context context, final String attachId) {

        notification = new ProgressNotification(context);
//        notification.updateProgress(Integer.parseInt(attachId), 100, 0, true);
        Map<Object, Object> json = new HashMap<>();
        Map<Object, Object> params = new HashMap<>();
        params.put("login", dataSource.getEmailOrUsername());
        params.put("password", dataSource.getPassword());
        params.put("user_type", dataSource.getUserType());
        params.put("attachment_id", Integer.parseInt(attachId));
        json.put("params", params);

        final String js = new JSONObject(json).toString();


        Log.e(TAG, "downLoading: " + js.toString());

        dataSource.getAttachment_id_data(js, new DataListener() {
            @Override
            public void onSuccess(Object object) {

                Log.e(TAG, "onSuccess: Downloading " + object.toString());

                ResultResponse resultResponse = (ResultResponse) object;

                try {

                    if (resultResponse.getResult().getAttachmentDetail() != null) {

                        AttachmentDetail attachmentDetail = resultResponse.getResult().getAttachmentDetail();

                        callback.downloadFile(attachmentDetail);

                    } else {

                        callback.failed(attachId);

                    }
                } catch (NullPointerException e) {
                    callback.failed(attachId);
                }


            }

            @Override
            public void onFail(Throwable throwable) {

                Log.e(TAG, "onSuccess: Downloading " + throwable.toString());
                callback.failed(attachId);
            }

            @Override
            public void onNetworkFailure() {
                Log.e(TAG, "onSuccess: Downloading  no network ");
                callback.failed(attachId);
            }

        });

    }

    @Override
    public void downloadFile(Context context, AttachmentDetail attachmentDetail) {

        String fileName = attachmentDetail.getName();
        String data = attachmentDetail.getAttachmentData();

        Log.e(TAG, "downloadFile: " + fileName);

        FileSaveAndOpen.saveFile(fileName, data);


        dataSource.saveDownloadedfile(String.valueOf(attachmentDetail.getAttachId()), "finished");


        Bundle bundle = new Bundle();
        bundle.putInt("attachment_id", attachmentDetail.getAttachId());
        bundle.putString("name", fileName);
        Intent intent = ActivityUtils.getIntentWithAction(context, DownloadService.class, bundle, "finished");


        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setTitle(fileName);
        notification.setContentText("Download complete");
        notification.setPendingIntent(pendingIntent);
//        notification.updateProgress(attachmentDetail.getAttachId(), 0, 0, false);

    }

    @Override
    public void failed(Context context, String attachId) {

        dataSource.saveDownloadedfile(attachId, "failed");
        notification.setContentText("Download failed");
        notification.setOnGoing(false);
//        notification.updateProgress(Integer.parseInt(attachId), 0, 0, false);


    }

    @Override
    public NotificationCompat.Builder getBuilder() {
        return notification.getmBuilder();
    }


}
