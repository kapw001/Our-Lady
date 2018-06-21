package com.pappayaed.dowloadservices;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.pappayaed.data.model.AttachmentDetail;

/**
 * Created by yasar on 2/5/18.
 */

public interface IDownloadPresenter {


    void download(Context context, final String attachId);

    void downloadFile(Context context, AttachmentDetail attachmentDetail);

    void failed(Context context, String attachId);

    NotificationCompat.Builder getBuilder();

}
