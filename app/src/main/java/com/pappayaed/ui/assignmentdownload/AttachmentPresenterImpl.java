package com.pappayaed.ui.assignmentdownload;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.pappayaed.BuildConfig;
import com.pappayaed.data.model.AttachmentDetail;
import com.pappayaed.data.model.AttachmentFileId;
import com.pappayaed.ui.showprofile.UserDetails;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yasar on 4/4/18.
 */

public class AttachmentPresenterImpl implements AttachmentContract.AttachmentPresenter, IAttachmentIntractor.OnFinishedListener, IAttachmentIntractor.OnDownloadedFileFinishedListener {

    private static final String TAG = "AttachmentPresenterImpl";
    private Context context;
    private AttachmentContract.AttachmnetView attachmnetView;
    private IAttachmentIntractor iAttachmentIntractor;
    private ArrayList<UserDetails> list = new ArrayList<>();
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0;
    private int downloadPosition = 0;


    public AttachmentPresenterImpl(Context context, AttachmentContract.AttachmnetView attachmnetView, IAttachmentIntractor iAttachmentIntractor) {
        this.context = context;
        this.attachmnetView = attachmnetView;
        this.iAttachmentIntractor = iAttachmentIntractor;

    }


    @Override
    public void loadAttachmentList(List<AttachmentFileId> Clist) {


        if (Clist.size() > 0) {

            List<UserDetails> list = new ArrayList<>();
            list.add(new UserDetails("Attachment Detail", null, null, UserDetails.HEADER));
            for (int i = 0; i < Clist.size(); i++) {

                AttachmentFileId attachmentFileId = Clist.get(i);


                list.add(new UserDetails("Name", attachmentFileId.getName(), attachmentFileId.getAttachId() + "", "", UserDetails.ROW));
            }

            this.list.clear();
            this.list.addAll(list);
            attachmnetView.showAttachmentList(list);

        } else {
            attachmnetView.showNodata();
        }


    }

    @Override
    public void downloadAttachment(int pos) {
        downloadPosition = pos;

        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);


        } else {


            UserDetails userDetails = this.list.get(downloadPosition);

            long id = Integer.parseInt(userDetails.getAttachID());


            String fileName = userDetails.getmName();

//            Boolean isSDPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
//            if (isSDPresent) {
//                final File myFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName);
//                if (myFile.exists()) {
//                    attachmnetView.showSnackBar(myFile);
//                } else {

            attachmnetView.startDownloading(String.valueOf(id), fileName);
//                    attachmnetView.showLoading();
//                    iAttachmentIntractor.downloadAttachment(id, this);
//                }
//
//            } else {
//                attachmnetView.showToast("SdCart not present ");
//            }


        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    downloadAttachment(downloadPosition);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    attachmnetView.showToast("Please provide write permission to procced.");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    @Override
    public void showDownloadFile(Context context, File myFile) {

        try {
            Uri photoURI = FileProvider.getUriForFile(context,
                    BuildConfig.APPLICATION_ID + ".provider",
                    myFile);
//                        Uri uri = FileProvider.getUriForFile(getApplicationContext(), getPackageName() + fileName, myFile);
            Intent myIntent = new Intent(Intent.ACTION_VIEW);
            myIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            myIntent.addCategory(Intent.CATEGORY_BROWSABLE);
//                        myIntent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
            String mime = null;
            try {
                mime = URLConnection.guessContentTypeFromStream(new FileInputStream(myFile.getAbsoluteFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (mime == null)
                mime = URLConnection.guessContentTypeFromName(myFile.getName());
//                    myIntent.setDataAndType(Uri.fromFile(myFile), mime);
            myIntent.setDataAndType(photoURI, mime);


            context.startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "The file format not supported", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSuccss(AttachmentDetail attachmentDetail) {

        attachmnetView.hideLoading();
        iAttachmentIntractor.downloadFile(attachmentDetail, this);

    }

    @Override
    public void onFail(Throwable throwable) {
        attachmnetView.hideLoading();
    }

    @Override
    public void onNetworkFailure() {
        attachmnetView.hideLoading();
    }


    @Override
    public void onDownloadSuccss(File file) {
        attachmnetView.showSnackBar(file);
    }

    @Override
    public void onDownloadFail(String msg) {

        Log.e(TAG, "onDownloadFail: " + msg);

    }


}
