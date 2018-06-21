package com.pappayaed.ui.feedetails;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.interfaces.CallBackStudentID;
import com.pappayaed.ui.showprofile.StudentList;
import com.pappayaed.ui.studentfee.StudentFeeFragment;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class FeeDetailsActivity extends BaseActivity implements CallBackStudentID {

    @BindView(R.id.containerfeedetails)
    FrameLayout containerfeedetails;
    @BindView(R.id.progressLayout)
    ProgressLinearLayout progressLayout;

    private StandardAndFeeFragment standardAndFeeFragment;
    private StudentFeeFragment studentFeeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_details2);
        ButterKnife.bind(this);

        setActionBarTitle("Fee Details", true);

        studentFeeFragment = (StudentFeeFragment) getSupportFragmentManager().findFragmentById(R.id.studentFeeFragment);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        standardAndFeeFragment = new StandardAndFeeFragment();

        fragmentTransaction.replace(R.id.containerfeedetails, standardAndFeeFragment);

        fragmentTransaction.commit();

        callNetwork();

    }


    @Override
    public void loadStudentID(StudentList studentList) {

        hideLoading();

        loadStandardAndFeeFragment(studentList);

    }


    @Override
    public void callNetwork() {
        studentFeeFragment.call();
    }

    private void loadStandardAndFeeFragment(StudentList studentList) {

        standardAndFeeFragment.loadStudentFee(studentList);

    }

    @Override
    public void onFail(Throwable throwable) {

        progressBarStateCall(progressLayout, R.drawable.somethingwentwrong, "error");

    }

    @Override
    public void onNetworkFailure() {

        progressBarStateCall(progressLayout, R.drawable.nointernet, "nointernet");

    }

    @Override
    public void showLoading() {

        progressBarStateCall(progressLayout, R.drawable.nointernet, "loading");

    }

    @Override
    public void hideLoading() {

        progressBarStateCall(progressLayout, R.drawable.nointernet, "content");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
