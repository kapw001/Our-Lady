package com.pappayaed.ui.assignmentdownload;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.AttachmentFileId;
import com.pappayaed.data.model.TimetableDatum;
import com.pappayaed.dowloadservices.DownloadService;
import com.pappayaed.errormsg.Error;
import com.pappayaed.ui.showprofile.RecyclerViewAdapter;
import com.pappayaed.ui.showprofile.StudentList;
import com.pappayaed.ui.showprofile.UserDetails;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AttachmentListActivity extends BaseActivity implements RecyclerViewAdapter.RecyclerAdapterPositionClicked, AttachmentContract.AttachmnetView {

    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0;
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


    private AttachmentContract.AttachmentPresenter attachmentPresenter;


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

//        toolbar.setTitle("Attachments");
        setSupportActionBar(toolbar);


        setActionBarTitle("Attachments", true);


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

        attachmentPresenter = new AttachmentPresenterImpl(this, this, new AttachmentIntractorImpl(dataSource));

        attachmentPresenter.loadAttachmentList(attachmentFileIdList);
    }

    @Override
    public void showNodata() {

        error.setText(Error.nodata);
        error.setVisibility(View.VISIBLE);

    }


    @Override
    public void position(int pos) {

        downloadPosition = pos;

        requrestPermission(downloadPosition);

    }


    @Override
    public void position(int pos, StudentList studentList) {

    }


    private void requrestPermission(int pos) {

        attachmentPresenter.downloadAttachment(pos);

    }

    @Override
    public void showSnackBar(final File myFile) {

        final Snackbar snackbar = Snackbar.make(coordinatorLayout, "Download finished open file", Snackbar.LENGTH_INDEFINITE);

        snackbar.setAction("Open", view -> attachmentPresenter.showDownloadFile(getApplicationContext(), myFile));
        snackbar.show();

    }

    @Override
    public void showToast(String msg) {

        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void startDownloading(String id, String fileName) {

        Intent intent = new Intent(this, DownloadService.class);
        intent.setAction("download");
        intent.putExtra("attachment_id", id);
        intent.putExtra("name", fileName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }


//        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        attachmentPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onFail(Throwable throwable) {
        Utils.hideProgress();
    }

    @Override
    public void onNetworkFailure() {
        Utils.hideProgress();
    }

    @Override
    public void showLoading() {

        Utils.showProgress(this, "Downloading");

    }

    @Override
    public void hideLoading() {

        Utils.hideProgress();
    }

    @Override
    public void showAttachmentList(List<UserDetails> mList) {


        recyclerViewAdapter.updateList(mList);
    }
}
