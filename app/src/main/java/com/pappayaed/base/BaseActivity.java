package com.pappayaed.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.pappayaed.R;
import com.pappayaed.common.ActivityUtils;
import com.pappayaed.common.Utils;
import com.pappayaed.data.DataRepository;
import com.pappayaed.data.DataSource;
import com.pappayaed.data.helper.L;
import com.pappayaed.data.pref.Pref;
import com.pappayaed.data.pref.PreferencesHelper;
import com.pappayaed.data.remote.ApiService;
import com.pappayaed.data.remote.RemoteDataSourceHelper;
import com.pappayaed.data.retrofitclient.ApiEndPoint;
import com.pappayaed.data.retrofitclient.RetrofitClient;
import com.vlonjatg.progressactivity.ProgressLayout;

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by yasar on 26/3/18.
 */

public class BaseActivity extends AppCompatActivity implements BaseView, EasyPermissions.PermissionCallbacks,
        EasyPermissions.RationaleCallbacks {

    private static final String TAG = "BaseActivity";
    private Pref pref;
    private RetrofitClient retrofitClient;
    private ApiService apiService;
    private RemoteDataSourceHelper remoteDataSource;
    public DataSource dataSource;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = PreferencesHelper.getPreferencesInstance(getApplicationContext());

        retrofitClient = RetrofitClient.getRetrofitClientInstance(ApiEndPoint.BASE_URL);

        apiService = retrofitClient.getRetrofit().create(ApiService.class);

        remoteDataSource = new RemoteDataSourceHelper(apiService);

        dataSource = new DataRepository(getApplicationContext(), remoteDataSource, pref);

        L.loge("onCreate");
    }


    public void setActionBarTitle(String title, boolean isBackButtonEnabled) {

        if (getSupportActionBar() != null) {

            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(isBackButtonEnabled);
        }
    }


    public void setGradient() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
//            window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent);
            window.setBackgroundDrawableResource(R.drawable.ab_gradient);
        }
    }

    public void setCustomView(String title) {
        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.actionbartitlecenter, null);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);

        TextView titleTV = (TextView) v.findViewById(R.id.title);
        titleTV.setText(title);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(v, params);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onStart() {
        super.onStart();

        L.loge("onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();

        L.loge("onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();

        L.loge("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        L.loge("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        L.loge("onDestory");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        ActivityUtils.exitActivityAnimation(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showSnackBar(View view, String msg) {

        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void onFail(Throwable throwable) {

        Toast.makeText(this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();

//        progressStateCall(R.drawable.somethingwentwrong, "error");
    }

    @Override
    public void onNetworkFailure() {

        Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
//        progressStateCall(R.drawable.nointernet, "nointernet");

    }

    @Override
    public void showLoading() {

        Utils.showProgress(this, "Loading");

//        progressStateCall(R.drawable.nointernet, "loading");

    }

    @Override
    public void hideLoading() {

        Utils.hideProgress();
//        progressStateCall(R.drawable.nointernet, "content");

    }


    @Override
    public void showToast(String msg) {

        Toast.makeText(this, "" + msg
                , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showToast(@StringRes int msgID) {

        Toast.makeText(this, "" + msgID, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
////            String yes = getString(R.string.yes);
////            String no = getString(R.string.no);
////
////            // Do something after user returned from app settings screen, like showing a Toast.
////            Toast.makeText(
////                    this,
////                    getString(R.string.returned_from_app_settings_to_activity,
////                            hasCameraPermission() ? yes : no,
////                            hasLocationAndContactsPermissions() ? yes : no,
////                            hasSmsPermission() ? yes : no),
////                    Toast.LENGTH_LONG)
////                    .show();
//        }
    }

    @Override
    public void onRationaleAccepted(int requestCode) {
        Log.d(TAG, "onRationaleAccepted:" + requestCode);
    }

    @Override
    public void onRationaleDenied(int requestCode) {
        Log.d(TAG, "onRationaleDenied:" + requestCode);
    }


    public View.OnClickListener errorListener = v -> callNetwork();

    public void callNetwork() {

    }


    public void progressBarStateCall(ProgressLayout progressLayout, int drawable, String state) {

        ActivityUtils.progressBarStateCall(progressLayout, drawable, state, errorListener);

    }
}
