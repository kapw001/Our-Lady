package com.pappayaed.ui.showprofile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.common.Utils;
import com.pappayaed.data.parser.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentProfileViewActivity extends BaseActivity implements RecyclerViewAdapter.RecyclerAdapterPositionClicked {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<UserDetails> list;
    private StudentList studentList;
    private CircleImageView profileimage;
    private TextView profilename;
    private ProgressBar progressBar;
    private CoordinatorLayout coordinatorLayout;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        studentList = (StudentList) bundle.getSerializable("studentprofile");

        list = new ArrayList<>();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setEnabled(false);
        profileimage = (CircleImageView) findViewById(R.id.profile_image);
        profilename = (TextView) findViewById(R.id.profilename);

        final String name = studentList.getName();

        profilename.setText(name);
        Bitmap imagebmp = Utils.decodeBitmap(this, studentList.getPhoto());
        profileimage.setImageBitmap(imagebmp);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
//        progressBar.setVisibility(View.VISIBLE);


        recyclerViewAdapter = new RecyclerViewAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);


        List<UserDetails> list = Parser.updateList(studentList);

        recyclerViewAdapter.updateList(list);


        coordinatorLayout.post(new Runnable() {
            @Override
            public void run() {
                Utils.animateRevealShow(coordinatorLayout);
            }
        });

    }

    public void updateList(List<UserDetails> list) {
        if (list != null && list.size() > 0) {
            findViewById(R.id.error).setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerViewAdapter.updateList(list);
        } else {
            findViewById(R.id.error).setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void position(int pos) {

    }

    @Override
    public void position(int pos, StudentList studentList) {

    }
}
