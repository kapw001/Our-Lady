package com.pappayaed.ui.assignmentdownload;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pappayaed.BuildConfig;
import com.pappayaed.data.DataSource;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.AttachmentDetail;
import com.pappayaed.data.model.ResultResponse;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yasar on 4/4/18.
 */

public class AttachmentIntractorImpl implements IAttachmentIntractor {

    private static final String TAG = "AttachmentIntractorImpl";

    private DataSource dataSource;

    public AttachmentIntractorImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void downloadAttachment(long attachId, final OnFinishedListener onFinishedListener) {

        Map<Object, Object> json = new HashMap<>();
        Map<Object, Object> params = new HashMap<>();
        params.put("login", dataSource.getEmailOrUsername());
        params.put("password", dataSource.getPassword());
        params.put("user_type", dataSource.getUserType());
        params.put("attachment_id", attachId);
        json.put("params", params);

        final String js = new JSONObject(json).toString();


        dataSource.getAttachment_id_data(js, new DataListener() {
            @Override
            public void onSuccess(Object object) {


                ResultResponse resultResponse = (ResultResponse) object;

                try {

                    if (resultResponse.getResult().getAttachmentDetail() != null) {

                        AttachmentDetail attachmentDetail = resultResponse.getResult().getAttachmentDetail();
                        onFinishedListener.onSuccss(attachmentDetail);

                    } else {

                        onFinishedListener.onFail(new Throwable("There is no data"));

                    }
                } catch (NullPointerException e) {
                    onFinishedListener.onFail(e);
                }


            }

            @Override
            public void onFail(Throwable throwable) {
                onFinishedListener.onFail(throwable);
            }

            @Override
            public void onNetworkFailure() {
                onFinishedListener.onNetworkFailure();
            }

        });

    }


    @Override
    public void downloadFile(AttachmentDetail attachmentDetail, OnDownloadedFileFinishedListener onDownloadedFileFinishedListener) {


        String fileName = attachmentDetail.getName();
        String data = attachmentDetail.getAttachmentData();

        Log.e(TAG, "downloadFile: " + fileName);

        Boolean isSDPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (isSDPresent) {
            final File myFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName);

            try {
                Log.e(TAG, "run: data " + data);
                byte[] pdfAsBytes = Base64.decode(data, Base64.DEFAULT);
                FileOutputStream os;
                os = new FileOutputStream(myFile, false);
                os.write(pdfAsBytes);
                os.flush();
                os.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            onDownloadedFileFinishedListener.onDownloadSuccss(myFile);

        } else {

            onDownloadedFileFinishedListener.onDownloadFail("SdCard not present ");
        }
//                }
//            });

//        }
    }

}
