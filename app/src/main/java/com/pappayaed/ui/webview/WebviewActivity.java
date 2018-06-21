package com.pappayaed.ui.webview;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.widget.Toast;

import com.pappayaed.R;
import com.pappayaed.data.model.AttachmentDetail;

import java.io.UnsupportedEncodingException;

public class WebviewActivity extends AppCompatActivity {

    private static final String TAG = "WebviewActivity";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = (WebView) findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl(postUrl);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setAllowFileAccess(true);
//        String data = getIntent().getStringExtra("data");

        AttachmentDetail attachmentDetail = (AttachmentDetail) getIntent().getSerializableExtra("att");

        byte[] decodedString = Base64.decode(attachmentDetail.getAttachmentData(), Base64.DEFAULT);
        String b64Image = null;
        try {
            b64Image = Base64.encodeToString(attachmentDetail.getAttachmentData().getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e(TAG, "onCreate: " + e.getMessage());
        }

        Log.e(TAG, "onCreate: " + attachmentDetail.getMimetype());

        webView.loadData(decodedString.toString(), attachmentDetail.getMimetype(), "utf-8");

//        webView.loadData(b64Image, attachmentDetail.getMimetype(), "base64");

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

                Log.e(TAG, "onDownloadStart: " + url);

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setMimeType(mimetype);
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription(getResources().getString(R.string.msg_downloading));
                String filename = URLUtil.guessFileName(url, contentDisposition, mimetype);
                request.setTitle(filename);
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), R.string.msg_downloading, Toast.LENGTH_LONG).show();
            }
        });

    }
}
