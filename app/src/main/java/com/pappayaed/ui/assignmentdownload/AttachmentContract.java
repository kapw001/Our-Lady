package com.pappayaed.ui.assignmentdownload;

import android.content.Context;
import android.content.Intent;

import com.pappayaed.base.BaseView;
import com.pappayaed.data.model.AttachmentDetail;
import com.pappayaed.data.model.AttachmentFileId;
import com.pappayaed.ui.showprofile.UserDetails;

import java.io.File;
import java.util.List;

/**
 * Created by yasar on 4/4/18.
 */

public interface AttachmentContract {


    interface AttachmnetView extends BaseView {

        void showAttachmentList(List<UserDetails> list);

        void showNodata();

        void showSnackBar(File myFile);

        void showToast(String msg);

        void startDownloading(String id ,String fileName);

    }

    interface AttachmentPresenter {


        void loadAttachmentList(List<AttachmentFileId> Clist);

        void downloadAttachment(int pos);

        void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults);

        void showDownloadFile(Context context, File file);


    }


}
