package com.pappayaed.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.internal.BaselineLayout;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.pappayaed.R;
import com.pappayaed.base.BaseActivity;
import com.pappayaed.common.ActivityUtils;
import com.pappayaed.common.BottomNavigationViewHelper;
import com.pappayaed.common.Config;
import com.pappayaed.common.NotificationUtils;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.fragmentnavigation.FragmentNavigationManager;
import com.pappayaed.fragmentnavigation.NavigationManager;
import com.pappayaed.ui.attendance.AttendanceActivity;
import com.pappayaed.ui.circular.CircularActivity;
import com.pappayaed.ui.feedetails.FeeDetailsActivity;
import com.pappayaed.ui.heartrate.HeartRateActivity;
import com.pappayaed.ui.homework.HomeWorkActivity;
import com.pappayaed.ui.login.LoginActivity;
import com.pappayaed.ui.whereismystudent.WhereisMyStudentActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity implements IMainView {

    private Fragment fragment = null;
    private static final String TAG = "MainActivity";

    private NavigationManager navigationManager;
    private IMainPresenter iMainPresenter;
    private BottomNavigationView navigation;

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(android.view.Window.FEATURE_CONTENT_TRANSITIONS);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow();
//            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
////            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            Drawable background = getResources().getDrawable(R.drawable.gradient_view);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
////            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
////            window.setNavigationBarColor(getResources().getColor(android.R.color.transparent));
//            window.setBackgroundDrawable(background);
//        }

        setGradient();
        setContentView(R.layout.activity_main);

        setCustomView(getResources().getString(R.string.title_dashboard));


        navigationManager = FragmentNavigationManager.obtain(this);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        iMainPresenter = new MainPresenterImpl(dataSource);

        iMainPresenter.onAttach(this);
        iMainPresenter.loadMoreFragment();

//        iMainPresenter.bottomNavigationViewPosition(navigation.getMenu().findItem(R.id.txtDate));
        navigation.getMenu().findItem(R.id.attendance).setCheckable(false);

//        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//                // checking for type intent filter
//                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
//                    // gcm successfully registered
//                    // now subscribe to `global` topic to receive app wide notifications
//                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
//
//                    displayFirebaseRegId();
//
//                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
//                    // new push notification is received
//
//                    String message = intent.getStringExtra("message");
//
//                    Toast.makeText(context, "Push notification: " + message, Toast.LENGTH_LONG).show();
//
//                }
//            }
//        };
//
//        displayFirebaseRegId();

    }


    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId)) {


            Log.e(TAG, "displayFirebaseRegId: " + "Firebase Reg Id: " + regId);
        } else
            Log.e(TAG, "displayFirebaseRegId: Firebase Reg Id is not received yet! ");


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        fragment = navigationManager.getFragment();

    }


    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


    @Override
    public void inflateBottomViewParent() {


    }

    @Override
    public void moveToHomeWorkActivity() {

        ActivityUtils.startActivity(this, HomeWorkActivity.class, new Bundle());

    }


    @Override
    public void moveToAttendanceActivity() {

        ActivityUtils.startActivity(this, AttendanceActivity.class, new Bundle());

    }


    @Override
    public void moveToMoreFragment() {

        navigationManager.MoreFragment(getString(R.string.title_more));
        setTitle(getString(R.string.title_dashboard));


    }

    @Override
    public void logout() {
        startActivity(new Intent(this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iMainPresenter.onDetach();
    }

    private void setTitle(String title) {

        setActionBarTitle(title, false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:

                iMainPresenter.logout();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = (item) -> {
        item.setCheckable(false);
        iMainPresenter.bottomNavigationViewPosition(item);
        return true;
    };
}
