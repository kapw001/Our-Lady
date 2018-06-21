package com.pappayaed.ui.assignmentdownload;

import com.pappayaed.data.model.AttachmentDetail;
import com.pappayaed.data.model.CircularAndHomeWork;

import java.io.File;

/**
 * Created by yasar on 4/4/18.
 */

public interface IAttachmentIntractor {


    interface OnFinishedListener {

        void onSuccss(AttachmentDetail attachmentDetail);

        void onFail(Throwable throwable);

        void onNetworkFailure();

    }

    interface OnDownloadedFileFinishedListener {

        void onDownloadSuccss(File file);

        void onDownloadFail(String msg);

    }

    void downloadAttachment(long attachId, OnFinishedListener onFinishedListener);

    void downloadFile(AttachmentDetail attachmentDetail, OnDownloadedFileFinishedListener onDownloadedFileFinishedListener);

}
