package com.pappayaed.ui.showprofile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pappayaed.App.App;
import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.common.Utils;
import com.pappayaed.errormsg.Error;
import com.pappayaed.ui.parentprofile.PersonalInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends BaseActivity implements RecyclerViewAdapter.RecyclerAdapterPositionClicked, IProfileView {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private ArrayList<UserDetails> list;
    private CircleImageView profileimage;
    private TextView profilename;
    private Profile profile;

    private ProgressBar progressBar;
    private RelativeLayout viewRoot;

    private CoordinatorLayout coordinatorLayout;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private IProfilePresenter iProfilePresenter;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        viewRoot = (RelativeLayout) findViewById(R.id.anitest);

        profile = new Profile();
        list = new ArrayList<>();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);

        profileimage = (CircleImageView) findViewById(R.id.profile_image);
        profilename = (TextView) findViewById(R.id.profilename);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);


        recyclerViewAdapter = new RecyclerViewAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);

        mSwipeRefreshLayout.setEnabled(false);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callServices();
//                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


        coordinatorLayout.post(new Runnable() {
            @Override
            public void run() {
                Utils.animateRevealShow(coordinatorLayout);
            }
        });

        Map<String, String> profileCached = App.getApp().getProfileCached();

        IProfileIntractor iProfileIntractor = new ProfileIntractorImpl(dataSource, profileCached);

        iProfilePresenter = new ProfilePresenterImpl(this, iProfileIntractor);

        callServices();


    }

    private void callServices() {

        if (iProfilePresenter != null) {
            iProfilePresenter.displayProfile();
            iProfilePresenter.getAllProfile();
        } else Log.e(TAG, "Presenter not initialized........................................ ");

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

    private static final String TAG = "ProfileActivity";

    @Override
    public void position(int pos) {

    }

    @Override
    public void position(int pos, StudentList studentList) {
        Intent in = new Intent(this, StudentProfileViewActivity.class);

        View pimage = profileimage;
        View pname = profilename;
        Pair<View, String> pair1 = Pair.create(pimage, "profile_image");
        Pair<View, String> pair2 = Pair.create(pname, "profilename");
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair1, pair2);


        Bundle bundle = new Bundle();
        bundle.putSerializable("studentprofile", studentList);
        in.putExtras(bundle);
        startActivity(in, optionsCompat.toBundle());
    }

    @Override
    public void displayProfile(String profileName, String userType, String profileImage) {

        this.name = profileName;

        profilename.setText(profileName);

        Bitmap imagebmp = Utils.decodeBitmap(this, profileImage);
        profileimage.setImageBitmap(imagebmp);

    }

    @Override
    public void gotoStudentProfileActivity() {

    }

    @Override
    public void setData(List list) {

        if (list != null && list.size() > 0) {
            this.list.clear();
            this.list.addAll(list);
            findViewById(R.id.error).setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerViewAdapter.updateList(list);
        } else {
            TextView textView = (TextView) findViewById(R.id.error);
            textView.setVisibility(View.VISIBLE);
            textView.setText(Error.nodata);
            recyclerView.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void setData(Map<Object, List> map, PersonalInfo personalInfo) {

    }

    @Override
    public void setError(String msg) {

        TextView textView = (TextView) findViewById(R.id.error);
        textView.setVisibility(View.VISIBLE);
        textView.setText(msg);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setEmptyData() {
        TextView textView = (TextView) findViewById(R.id.error);
        textView.setVisibility(View.VISIBLE);
        textView.setText(Error.nodata);
        recyclerView.setVisibility(View.INVISIBLE);
    }
}
