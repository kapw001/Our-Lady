package com.pappayaed.ui.circular;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;

public class CircularActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_another);


        setCustomView("Events");
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
