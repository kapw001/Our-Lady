package com.pappayaed.App;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.pappayaed.common.Utils;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import timber.log.BuildConfig;
import timber.log.Timber;

/**
 * Created by yasar on 18/4/17.
 */

public class App extends Application {

    private static final String TAG = "App";

    private static App app;

    private Map<String, String> profileCached = new HashMap<>();

    public static App getApp() {
        return app;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public Map<String, String> getProfileCached() {
        return profileCached;
    }

    public boolean hasNetwork() {
        return checkIfHasNetwork();
    }

    public boolean checkIfHasNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public String getProfile() {

        return Utils.loadJSONFromAsset(this, "profile.json");

    }
}
