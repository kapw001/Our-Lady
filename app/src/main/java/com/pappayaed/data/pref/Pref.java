package com.pappayaed.data.pref;

import com.pappayaed.DownloadingState;

import java.util.Map;

/**
 * Created by yasar on 22/3/18.
 */

public interface Pref {


    boolean isLoggedIn();

    void saveSession(String email, String password, String username, String userid, String usertype, boolean status, String userimage);

    String getUserType();

    Map getUserDetails();

    String getEmailOrUsername();

    String getPassword();

    String getProfileName();

    String getProfileImage();

    void clear();

    void saveHomeWork(String circular_homework);

    String getHomeWork();

    void saveDownloadedfile(String key, String state);

    String getState(String key);


}
