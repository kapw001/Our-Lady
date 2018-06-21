package com.pappayaed.ui.assignmentdownload;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pappayaed.BuildConfig;
import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.common.Utils;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.AttachmentDetail;
import com.pappayaed.data.model.AttachmentFileId;
import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.data.model.TimetableDatum;
import com.pappayaed.errormsg.Error;
import com.pappayaed.ui.showprofile.RecyclerViewAdapter;
import com.pappayaed.ui.showprofile.StudentList;
import com.pappayaed.ui.showprofile.UserDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class AttachmentListActivityBackup extends BaseActivity implements RecyclerViewAdapter.RecyclerAdapterPositionClicked {

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<UserDetails> list;
    private List<AttachmentFileId> attachmentFileIdList;
    private TimetableDatum assignmentList;
    private CircleImageView profileimage;
    private TextView profilename;
    private int downloadPosition = 0;
    private View coordinatorLayout;
    private View relativeLayout;

    private LinearLayout linearLayout;

    private TextView error;
    private static final String TAG = "AttachmentListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        getWindow().requestFeature(android.view.Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.assignmenttab);
        error = (TextView) findViewById(R.id.error);
        error.setVisibility(View.GONE);

        attachmentFileIdList = new ArrayList<>();

        linearLayout = (LinearLayout) findViewById(R.id.lay);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        relativeLayout = (RelativeLayout) findViewById(R.id.anitest);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        attachmentFileIdList = (List<AttachmentFileId>) bundle.getSerializable("assignmentlist");

        toolbar.setTitle("Attachment List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list = new ArrayList<>();


        profileimage = (CircleImageView) findViewById(R.id.profile_image);
        profilename = (TextView) findViewById(R.id.profilename);

        recyclerViewAdapter = new RecyclerViewAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);


        linearLayout.setVisibility(View.GONE);

        updateListAssignment(attachmentFileIdList);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);

//                NavUtils.getParentActivityIntent(this);
//                NavUtils.navigateUpTo(this, intent);
//                finish();
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateListAssignment(List<AttachmentFileId> Clist) {

        if (Clist.size() > 0) {

            List<UserDetails> list = new ArrayList<>();
            list.add(new UserDetails("Attachment Detail", null, null, UserDetails.HEADER));
            for (int i = 0; i < Clist.size(); i++) {

                AttachmentFileId attachmentFileId = Clist.get(i);

                list.add(new UserDetails("Name", attachmentFileId.getName(), attachmentFileId.getAttachId() + "", "", UserDetails.ROW));
            }

            this.list.clear();
            this.list.addAll(list);
            recyclerViewAdapter.updateList(list);
        } else {
            error.setText(Error.nodata);
            error.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public void position(int pos) {
        downloadPosition = pos;
        UserDetails userDetails = list.get(pos);

        Log.e(TAG, "position: " + userDetails.getAttachID());

        try {
            downloadAttachment(Integer.parseInt(userDetails.getAttachID()));
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        Log.e(TAG, "position: " + userDetails.toString());

//        if (userDetails.getmName().toLowerCase().equalsIgnoreCase("Attachment List".toLowerCase())) {
//
//            List<AttachmentFileId> list = userDetails.getAttachmentFileIdList();
//
//            Intent intent = new Intent(getActivity(), AttachmentListActivity.class);
//            intent.putExtra("assignmentlist", (Serializable) list);
//            startActivity(intent);

//        requrestPermission(userDetails);
//        } else {
//            Log.e(TAG, "position: Attachment download has problems  so looking into Assignment profile Activity");
//        }
    }


    private void downloadAttachment(int attachId) throws JSONException {


        Utils.showProgress(this, "Downloading...");

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

                Utils.hideProgress();

                ResultResponse resultResponse = (ResultResponse) object;

                try {

                    if (resultResponse.getResult().getAttachmentDetail() != null) {

                        AttachmentDetail attachmentDetail = resultResponse.getResult().getAttachmentDetail();

                        requrestPermission(attachmentDetail.getAttachmentData(), attachmentDetail.getMimetype(), attachmentDetail.getName());

//
//                        Intent in = new Intent(getApplicationContext(), WebviewActivity.class);
//
//                        in.putExtra("att",attachmentDetail);
//
//                        in.putExtra("data", attachmentDetail.getAttachmentData());
//                        in.putExtra("mimetype", attachmentDetail.getMimetype());
//                        in.putExtra("name", attachmentDetail.getName());
//
//                        startActivity(in);


                    } else {
                        Toast.makeText(AttachmentListActivityBackup.this, "Somethink went wrong", Toast.LENGTH_SHORT).show();
                    }
                } catch (NullPointerException e) {
                    Toast.makeText(AttachmentListActivityBackup.this, "Somethink went wrong", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFail(Throwable throwable) {
                Utils.hideProgress();
            }

            @Override
            public void onNetworkFailure() {
                Utils.hideProgress();
            }
        });


//        mSwipeRefreshLayout.setRefreshing(false);
//        Utils.showProgress(this, "Loading");
//
//        ProfileApi profileApi = retrofit.create(ProfileApi.class);
//
//        Map map = sessionManagenent.getSession();
//
//        String username = map.get(SessionManagenent.KEY_EMAIL).toString();
//        String password = map.get(SessionManagenent.KEY_PASSWORD).toString();
//        String usertypes = map.get(SessionManagenent.KEY_USERTYPE).toString();
//
//
//        JSONObject jsonObject = new JSONObject();
//        JSONObject params = new JSONObject();
//        params.put("login", username);
//        params.put("password", password);
//        params.put("user_types", usertypes);
//        params.put("attachment_id", attachId);
//        jsonObject.put("params", params);
//
//        Log.e(TAG, "post data : " + jsonObject.toString());
//
//
//        profileApi.getAttachment_id_data(jsonObject.toString()).enqueue(new Callback<ResultResponse>() {
//            @Override
//            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
//                Utils.hideProgress();
//                try {
//
//                    if (response.body() != null) {
//
//                        if (response.body().getResult().getAttachmentDetail() != null) {
//
//                            AttachmentDetail attachmentDetail = response.body().getResult().getAttachmentDetail();
//
//                            requrestPermission(attachmentDetail.getAttachmentData(), attachmentDetail.getMimetype(), attachmentDetail.getName());
//
////
////                            Intent in = new Intent(getApplicationContext(), WebviewActivity.class);
////
////
////                            in.putExtra("data", attachmentDetail.getAttachmentData());
////                            in.putExtra("mimetype", attachmentDetail.getMimetype());
////                            in.putExtra("name", attachmentDetail.getName());
////
////                            startActivity(in);
//
//
//                        } else {
//                            Toast.makeText(AttachmentListActivity.this, Error.nodata, Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    } else {
//                        Toast.makeText(AttachmentListActivity.this, Error.nodata, Toast.LENGTH_SHORT).show();
//                    }
//                } catch (NullPointerException e) {
//
//                    Log.e(TAG, "onResponse: " + e.getMessage());
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<ResultResponse> call, Throwable t) {
//
//                Utils.hideProgress();
//
//            }
//        });


    }


    @Override
    public void position(int pos, StudentList studentList) {

    }


    private void downloadFile(final String data, final String mimetype, final String fileName) {


//        if (userDetails.getmName().toLowerCase().equalsIgnoreCase("Download ".toLowerCase())) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
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

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            final Snackbar snackbar = Snackbar.make(coordinatorLayout, "Download finished open file", Snackbar.LENGTH_INDEFINITE);

            snackbar.setAction("Open", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        Uri photoURI = FileProvider.getUriForFile(AttachmentListActivityBackup.this,
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
                        myIntent.setDataAndType(Uri.fromFile(myFile), mime);
//                        myIntent.setDataAndType(photoURI, mime);


                        startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "The file format not supported", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            snackbar.show();

            Toast.makeText(getApplicationContext(), "Download finished", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Sdcard not found", Toast.LENGTH_SHORT).show();
        }
//                }
//            });

//        }
    }


    private void requrestPermission(final String data, final String mimetype, final String fileName) {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.

        } else {
            downloadFile(data, mimetype, fileName);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    position(downloadPosition);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    Toast.makeText(getApplicationContext(), "Please give write and read permissions ", Toast.LENGTH_SHORT).show();

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }


}
