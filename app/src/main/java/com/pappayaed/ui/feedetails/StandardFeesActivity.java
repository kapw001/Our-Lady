package com.pappayaed.ui.feedetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;

public class StandardFeesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_fees);

        if (getSupportActionBar() != null) {

            setCustomView("Standard Fee");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String student_id = getIntent().getStringExtra("student_id");

        StandardFeeFragment fragment = (StandardFeeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment6);

        fragment.loadData(student_id);

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
