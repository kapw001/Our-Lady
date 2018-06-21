package com.pappayaed.ui.homework;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.RecyclerViewAdapterMultiView;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.common.Utils;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.HomeworkId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateHomeWorkActivity extends BaseActivity {

    private static final String TAG = "UpdateHomeWorkActivity";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.description)
    EditText description;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.discard)
    Button discard;

    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.title)
    TextView title;


    private HomeworkId homeworkId;
    private RecyclerViewAdapterMultiView mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow();
//            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
////            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

//        setGradient();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
//            window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent);
//            window.setBackgroundDrawableResource(R.drawable.ab_gradient);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_home_work);
        ButterKnife.bind(this);
        title.setText("Home Work");
//        getSupportActionBar().setTitle("Home Work");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);


        mAdapter = new RecyclerViewAdapterMultiView(new ArrayList());
        recyclerview.setAdapter(mAdapter);

        loadData();


    }

    @OnClick(R.id.back)
    public void onBack() {

        super.onBackPressed();

    }

    private void loadData() {


        if (getIntent() != null) {

            String cname = getIntent().getStringExtra("classname");

            homeworkId = (HomeworkId) getIntent().getSerializableExtra("homeworkId");


            List list = new ArrayList();

            HeaderRowData headerRowData = new HeaderRowData(R.drawable.ic_account_circle_black_24dp, "Subject", homeworkId.getSubjectName());
            HeaderRowData headerRowData1 = new HeaderRowData(R.drawable.ic_account_circle_black_24dp, "Primary Teacher", homeworkId.getSubPrimaryTutorName());
            HeaderRowData headerRowData2 = new HeaderRowData(R.drawable.ic_account_circle_black_24dp, "Class", cname);


            list.add(headerRowData);
            list.add(headerRowData1);
            list.add(headerRowData2);

            mAdapter.updateData(list);

            description.setText(homeworkId.getDescription());


        }

    }


    @OnClick(R.id.save)
    public void onSave(View view) {

        if (homeworkId != null) {

            String des = description.getText().toString();

            homeworkId.setDescription(des);


            Utils.showProgress(this, "Loading");
            Map<Object, Object> json = new HashMap<>();
            Map<Object, Object> params = new HashMap<>();
            params.put("login", dataSource.getEmailOrUsername());
            params.put("password", dataSource.getPassword());
            params.put("user_type", dataSource.getUserType());
            params.put("homework_subjects_id", homeworkId.getHomeworkSubjectsId());
            params.put("description", des);

            json.put("params", params);

            final String js = new JSONObject(json).toString();

            dataSource.updateHomeWorkBySubject(js, new DataListener() {
                @Override
                public void onSuccess(Object object) {

                    Utils.hideProgress();

                    try {
                        JSONObject jsonObject = new JSONObject(object.toString());

                        JSONObject jsonObject1 = jsonObject.optJSONObject("result");

                        String des = jsonObject1.optString("description");

                        Intent intent = new Intent();

                        intent.putExtra("des", des);

                        setResult(HomeFragment.REQUEST_HOMEWORK, intent);
                        finish();

                        Toast.makeText(UpdateHomeWorkActivity.this, "Homework successfully updated...", Toast.LENGTH_SHORT).show();


                    } catch (JSONException | NullPointerException e) {//                        e.printStackTrace();

                        setResult(HomeFragment.REQUEST_HOMEWORK, new Intent());
                        finish();

                        Toast.makeText(UpdateHomeWorkActivity.this, "Somthing went wrong...", Toast.LENGTH_SHORT).show();
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


        } else {

            Log.e(TAG, "onSave: homeworkId is null ");
        }


    }

    @OnClick(R.id.discard)
    public void onDiscard(View view) {

        super.onBackPressed();
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
}
