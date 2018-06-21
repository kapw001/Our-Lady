package com.pappayaed.common;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yasar on 14/6/18.
 */

public class CameraAndGallary {


    private static final String TAG = "CameraAndGallary";
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private CameraAndGallaryCallBack cameraAndGallaryCallBack;
    private Context mContext;
    private Fragment fragment;

    public CameraAndGallary(Context mContext, CameraAndGallaryCallBack cameraAndGallaryCallBack) {

        this.cameraAndGallaryCallBack = cameraAndGallaryCallBack;
        this.mContext = mContext;

    }

    public CameraAndGallary(Fragment fragment, CameraAndGallaryCallBack cameraAndGallaryCallBack) {

        this.cameraAndGallaryCallBack = cameraAndGallaryCallBack;
        this.fragment = fragment;

    }


    public void selectImage() {

        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};

        Context context = getCurrentContext();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                } else {
                    userChoosenTask = items[item].toString();
                    callCameraOrGallery();
                }
            }
        });
        builder.show();
    }

    private void callCameraOrGallery() {

        if (userChoosenTask.equals("Take Photo")) {
            userChoosenTask = "Take Photo";

            cameraIntent();

        } else if (userChoosenTask.equals("Choose from Library")) {
            userChoosenTask = "Choose from Library";

            galleryIntent();

        } else {
            Log.e(TAG, "callCameraOrGallery: somthing went wrong ");
        }

    }


    private void galleryIntent() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//

        if (mContext instanceof Activity) {

            ((Activity) mContext).startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);

        } else {

            fragment.startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);

        }

    }

    private void cameraIntent() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (mContext instanceof Activity) {

            ((Activity) mContext).startActivityForResult(intent, REQUEST_CAMERA);

        } else {

            fragment.startActivityForResult(intent, REQUEST_CAMERA);

        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == SELECT_FILE)

                onSelectFromGalleryResult(data);

            else if (requestCode == REQUEST_CAMERA)

                onCaptureImageResult(data);

        }
    }

    private Context getCurrentContext() {

        return mContext instanceof Activity ? mContext : fragment.getContext();
    }

    private void onSelectFromGalleryResult(Intent data) {

        Context context = getCurrentContext();

        Bitmap bm = null;
        if (data != null) {
            try {
                assert context != null;
                bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cameraAndGallaryCallBack.onSelectFromGalleryResult(bm);
    }

    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cameraAndGallaryCallBack.onSelectFromGalleryResult(thumbnail);

    }

    public interface CameraAndGallaryCallBack {

        void onSelectFromGalleryResult(Bitmap bitmap);

    }

}
