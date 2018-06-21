package com.pappayaed.dowloadservices;

import com.pappayaed.data.model.AttachmentDetail;

/**
 * Created by yasar on 2/5/18.
 */

public interface DownloadServiceCallback {


    void downloadFile(AttachmentDetail attachmentDetail);

    void failed(String attachId);

    void progressUpadte(int id, int max, int progress, boolean indeterminate);


}
