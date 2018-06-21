package com.pappayaed.data.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.pappayaed.DownloadingState;
import com.pappayaed.dowloadservices.DownloadService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yasar on 7/3/18.
 */

public class PreferencesHelper implements Pref {

    private static PreferencesHelper preferences;

    private SharedPreferences sharedPreferences;

    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_STATUS = "status";
    private static final String KEY_IMAGE = "userimage";
    private static final String KEY_URL = "serverurl";
    private static final String KEY_USERID = "userid";
    private static final String KEY_USERTYPE = "usertype";
    private static final String KEY_HOMEWORK = "homework";


    public String getKeyEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public String getKeyUserName() {
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getKeyPassword() {
        return sharedPreferences.getString(KEY_PASSWORD, null);
    }

    public String getKeyStatus() {
        return sharedPreferences.getString(KEY_STATUS, null);
    }

    public String getKeyImage() {
        return sharedPreferences.getString(KEY_IMAGE, null);
    }

    public String getKeyUrl() {
        return sharedPreferences.getString(KEY_URL, null);
    }

    public String getKeyUserId() {
        return sharedPreferences.getString(KEY_USERID, null);
    }

    public String getKeyUserType() {
        return sharedPreferences.getString(KEY_USERTYPE, null);
    }


    private PreferencesHelper(Context context) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    }

    public static PreferencesHelper getPreferencesInstance(Context context) {

        if (preferences == null) {
            preferences = new PreferencesHelper(context);
        }

        return preferences;
    }

    @Override
    public void saveSession(String email, String password, String username, String userid, String usertype, boolean status, String userimage) {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_USERID, userid);
        editor.putString(KEY_USERTYPE, usertype);
        editor.putBoolean(KEY_STATUS, status);
        editor.putString(KEY_IMAGE, userimage);
        editor.apply();

    }

    @Override
    public String getUserType() {
        return getKeyUserType();
    }

    @Override
    public Map<Object, Object> getUserDetails() {
        return getSession();
    }

    @Override
    public String getEmailOrUsername() {
        return getKeyEmail();
    }

    @Override
    public String getPassword() {
        return getKeyPassword();
    }

    @Override
    public String getProfileName() {
        return getKeyUserName();
    }

    @Override
    public String getProfileImage() {
        return getKeyImage();
    }

    public Map<Object, Object> getSession() {

        Map<Object, Object> hashMap = new HashMap<>();

        hashMap.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));
        hashMap.put(KEY_PASSWORD, sharedPreferences.getString(KEY_PASSWORD, null));
        hashMap.put(KEY_STATUS, sharedPreferences.getBoolean(KEY_STATUS, false));
        hashMap.put(KEY_USERNAME, sharedPreferences.getString(KEY_USERNAME, ""));
        hashMap.put(KEY_IMAGE, sharedPreferences.getString(KEY_IMAGE, ""));
        hashMap.put(KEY_USERID, sharedPreferences.getString(KEY_USERID, ""));
        hashMap.put(KEY_USERTYPE, sharedPreferences.getString(KEY_USERTYPE, ""));

        return hashMap;
    }

    @Override
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_STATUS, false);
    }

    @Override
    public void clear() {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }

    @Override
    public void saveHomeWork(String circular_homework) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_HOMEWORK, circular_homework);
        editor.apply();

    }

    @Override
    public String getHomeWork() {
        return sharedPreferences.getString(KEY_HOMEWORK, null);
    }

    @Override
    public void saveDownloadedfile(String key, String state) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, String.valueOf(state));
        editor.apply();
    }

    @Override
    public String getState(String key) {

        return sharedPreferences.getString(key, "init");
    }

}
