package com.pappayaed.ui.heartrate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.common.Utils;
import com.pappayaed.interfaces.CallBackStudentID;
import com.pappayaed.ui.showprofile.StudentList;
import com.pappayaed.ui.studentfee.StudentFeeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import tr.xip.errorview.ErrorView;

public class HeartRateActivity extends BaseActivity implements CallBackStudentID {


    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.specialErrorView)
    ErrorView specialErrorView;
    private HeartRateChartFragment heartRateChartFragment;
    private StudentFeeFragment studentFeeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);
        ButterKnife.bind(this);


        setCustomView("Heart Rate");


        studentFeeFragment = (StudentFeeFragment) getSupportFragmentManager().findFragmentById(R.id.studentFeeFragment);

        heartRateChartFragment = (HeartRateChartFragment) getSupportFragmentManager().findFragmentById(R.id.heartRateChartFragment);

        callNetwork();
    }


    @Override
    public void callNetwork() {

        studentFeeFragment.call();

    }

    @Override
    public void loadStudentID(StudentList studentList) {


        showContentOrError(true);
        heartRateChartFragment.loadStudentData(studentList);

    }

    private void showContentOrError(boolean isContent) {
        if (isContent) {
            content.setVisibility(View.VISIBLE);
            specialErrorView.setVisibility(View.GONE);
        } else {
            content.setVisibility(View.GONE);
            specialErrorView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFail(Throwable throwable) {

        setEmptyData(throwable.getMessage());
    }

    @Override
    public void onNetworkFailure() {
        setEmptyData("There is no internet.");
    }

    @Override
    public void showLoading() {

        Utils.showProgress(this, "Loading");

    }

    @Override
    public void hideLoading() {
        Utils.hideProgress();

    }


    public void setEmptyData(String msg) {

        showContentOrError(false);

        specialErrorView.setTitle("Oops");
        specialErrorView.setSubtitle(msg);


        Toast.makeText(this, "There is no data", Toast.LENGTH_SHORT).show();

    }
}
