package com.pappayaed.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pappayaed.R;
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

public class BaseFragment extends Fragment implements BaseView, EasyPermissions.PermissionCallbacks,
        EasyPermissions.RationaleCallbacks {

    private static final String TAG = "BaseFragment";

    private Pref pref;
    private RetrofitClient retrofitClient;
    private ApiService apiService;
    private RemoteDataSourceHelper remoteDataSource;
    public DataSource dataSource;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        L.loge("onAttach Fragment");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        pref = PreferencesHelper.getPreferencesInstance(getContext());

        retrofitClient = RetrofitClient.getRetrofitClientInstance(ApiEndPoint.BASE_URL);

        apiService = retrofitClient.getRetrofit().create(ApiService.class);

        remoteDataSource = new RemoteDataSourceHelper(apiService);

        dataSource = new DataRepository(getContext(), remoteDataSource, pref);


        L.loge("onCreate Fragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        L.loge("onCreateView Fragment");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.loge("onViewCreated Fragment");
    }

    @Override
    public void onStart() {
        super.onStart();

        L.loge("onStart Fragment");
    }

    @Override
    public void onPause() {
        super.onPause();

        L.loge("onPause Fragment");
    }

    @Override
    public void onResume() {
        super.onResume();

        L.loge("onResume Fragment");
    }

    @Override
    public void onStop() {
        super.onStop();

        L.loge("onStop Fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        L.loge("onDestroyView Fragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        L.loge("onDestroy Fragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        L.loge("onDetach Fragment");
    }


    public int getColor(@ColorRes int colorId) {
        return ContextCompat.getColor(getActivity(), colorId);
    }

    @Override
    public void showSnackBar(View view, String msg) {

        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void onFail(Throwable throwable) {

        Toast.makeText(getContext(), "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();

//        progressStateCall(R.drawable.somethingwentwrong, "error");
    }

    @Override
    public void onNetworkFailure() {

        Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
//        progressStateCall(R.drawable.nointernet, "nointernet");

    }

    @Override
    public void showLoading() {

        Utils.showProgress(getContext(), "Loading");

//        progressStateCall(R.drawable.nointernet, "loading");

    }

    @Override
    public void hideLoading() {

        Utils.hideProgress();
//        progressStateCall(R.drawable.nointernet, "content");

    }

    @Override
    public void showToast(String msg) {

        Toast.makeText(getContext(), "" + msg
                , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showToast(@StringRes int msgID) {

        Toast.makeText(getContext(), "" + msgID, Toast.LENGTH_SHORT).show();

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

    public View.OnClickListener errorListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callNetwork();
        }
    };

    public void callNetwork() {

    }


    public void progressBarStateCall(ProgressLayout progressLayout, int drawable, String state) {

        if (progressLayout != null) {
            switch (state.toUpperCase()) {
                case "LOADING":
                    progressLayout.showLoading();
                    break;
                case "EMPTY":
                    progressLayout.showEmpty(R.drawable.nodata,
                            "There is no data",
                            "");
                    break;
                case "ERROR":
                    progressLayout.showError(drawable,
                            "Something went wrong",
                            "",
                            "Try Again", errorListener);

                    break;

                case "NOINTERNET":
                    progressLayout.showError(drawable,
                            "No Connection",
                            "We could not establish a connection with our servers. Please try again when you are connected to the internet.",
                            "Try Again", errorListener);

                    break;

                case "CONTENT":

                    progressLayout.showContent();

                    break;
            }
        } else {

            Log.e(TAG, "progressStateCall: progress layout not used please check  ");
        }

    }


}
