package com.pappayaed.ui.feedetails;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.data.model.TermFeesCollectionList;

public class TermFeesActivity extends BaseActivity {

    private static final String TAG = "TermFeesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_fees);

        if (getSupportActionBar() != null) {
            setCustomView("Fee Details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        TermFeesFragment fragment = (TermFeesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment5);

        TermFeesCollectionList termFeesCollectionList = (TermFeesCollectionList) getIntent().getSerializableExtra("termfeedetails");


        fragment.setFeeDetails(termFeesCollectionList);


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
