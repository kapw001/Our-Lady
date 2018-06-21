package com.pappayaed.common;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.pappayaed.BuildConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;

/**
 * Created by yasar on 2/5/18.
 */

public class FileSaveAndOpen {

    private static final String TAG = "FileSaveAndOpen";


    public static void saveFile(String fileName, String data) {


        if (isSDCardFound()) {


            File myFile = getFile(fileName);

//            if (!myFile.exists()) {

            try {
//                Log.e(TAG, "run: data " + data);
                byte[] pdfAsBytes = Base64.decode(data, Base64.DEFAULT);
                FileOutputStream os;
                os = new FileOutputStream(myFile, false);
                os.write(pdfAsBytes);
                os.flush();
                os.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

//            } else {
//
//                Log.e(TAG, "saveFile: it has file already ");
//
//            }

        }

    }


    public static void openFile(Context context, File myFile) {

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

            Log.e(TAG, "openSavedFile: " + "The file format not supported");
            Toast.makeText(context, "The file format not supported", Toast.LENGTH_SHORT).show();

        }
    }

    public static File getFile(String fileName) {

        final File myFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName);
        return myFile;

    }

    public static boolean isSDCardFound() {

        Boolean isSDPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        return isSDPresent;

    }

    public static boolean isFileExists(String fileName) {

        if (isSDCardFound()) {

            if (getFile(fileName).exists()) {
                return true;
            }

            return false;

        }
        return false;
    }

    public static boolean isFileFound(Context context) {

        return false;
    }
}
