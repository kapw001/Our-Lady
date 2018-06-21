package com.pappayaed.ui.attendance;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AttendanceActivity extends BaseActivity implements PresentAbsentFragment.ShowHideCallBack {

    private static final String TAG = "AttendanceActivity";
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.save)
    TextView save;

    private PresentAbsentFragment presentAbsentFragment;

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
        setContentView(R.layout.activity_attendance);
        ButterKnife.bind(this);
        title.setText("Attendance");


        presentAbsentFragment = (PresentAbsentFragment) getSupportFragmentManager().findFragmentById(R.id.presentabsentfragment);


    }

    @OnClick(R.id.back)
    public void onBack() {

        super.onBackPressed();

    }

    @OnClick(R.id.save)
    public void onSave(View view) {
        presentAbsentFragment.updateDailyAttendance();
    }


    @Override
    public void callNetwork() {

    }

    @Override
    public void show(boolean isEnabled) {

        if (isEnabled)
            save.setVisibility(View.VISIBLE);
        else save.setVisibility(View.GONE);
    }
}
