package com.pappayaed.ui.showfeedetails;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pappayaed.App.App;
import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.FeesPaymentHistoryList;
import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.data.model.TermFeesCollectionList;
import com.pappayaed.ui.showfeedetails.paymenthistory.PaymentHistory;
import com.pappayaed.ui.showfeedetails.termfees.TermFeeFragment;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Retrofit;

public class FeeDetailsActivity extends BaseActivity implements IFeeDetailsView {
    private static final String TAG = "FeeDetailsActivity";
    private RecyclerView recyclerView;
    private TextView error;
    private ArrayList<TermFeesCollectionList> list;
    private ArrayList<FeesPaymentHistoryList> feesPaymentHistoryLists;

    private LinearLayout dateslayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private long id;

    private RelativeLayout tabslay;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ResultResponse myResponse = null;
    private ViewPagerAdapter adapter;

    private IFeeDetailsPresenter iFeeDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_details);

        getSupportActionBar().setTitle("Fee Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tabslay = (RelativeLayout) findViewById(R.id.tabslay);
        tabslay.setVisibility(View.VISIBLE);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        dateslayout = (LinearLayout) findViewById(R.id.dateslayout);
        dateslayout.setVisibility(View.GONE);
        list = new ArrayList<>();
        feesPaymentHistoryLists = new ArrayList<>();


        id = getIntent().getLongExtra("studentid", 0);


        iFeeDetailsPresenter = new FeePresenter(new FeeIntractorImp(dataSource), this);


        callServices(id);


    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), myResponse, false);
        adapter.addFragment(new TermFeeFragment(), "Term Fee");
        adapter.addFragment(new PaymentHistory(), "Payment History");
//        adapter.addFragment(new MissLesadFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }


    private void callServices(long id) {

        if (iFeeDetailsPresenter != null) {
            iFeeDetailsPresenter.getStudentFeeList(id);
        } else Log.e(TAG, "presenter not initialized");

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
    public void pageUpdate(ResultResponse resultResponse) {
        adapter.update(resultResponse, false);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void loadData(Map<String, Object> response) {

    }

    @Override
    public void setAcademicYear(String academicYear) {

    }

    @Override
    public void showFeeFullDeatils(TermFeesCollectionList termFeesCollectionList) {

    }

    @Override
    public void onFail(Throwable throwable) {

        adapter.update(null, true);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onNetworkFailure() {

        adapter.update(null, true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

        Utils.showProgress(this, "Loading");
    }

    @Override
    public void hideLoading() {

        Utils.hideProgress();
    }
}