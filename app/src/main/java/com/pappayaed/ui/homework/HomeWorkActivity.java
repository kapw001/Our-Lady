package com.pappayaed.ui.homework;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeWorkActivity extends BaseActivity {

    private static final String TAG = "HomeWorkActivity";
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;


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
        setContentView(R.layout.activity_home_work);
        ButterKnife.bind(this);


        title.setText("Home Work");
//        getSupportActionBar().setTitle("Home Work");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        Toast.makeText(this, "Home work", Toast.LENGTH_SHORT).show();
//
//        if (getIntent() != null) {
//
//            long student_id = getIntent().getLongExtra("studentid", 0);
//
//            HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
//
//            homeFragment.loadData(student_id);
//
//        } else {
//
//            Log.e(TAG, "onCreate: Fragment not found ");
//        }

    }

    @OnClick(R.id.back)
    public void onBack() {

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
