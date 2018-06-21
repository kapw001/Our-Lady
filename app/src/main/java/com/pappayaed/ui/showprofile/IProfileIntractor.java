package com.pappayaed.ui.showprofile;

import com.pappayaed.ui.parentprofile.PersonalInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by yasar on 26/3/18.
 */

public interface IProfileIntractor {

    interface OnProfileListener {

        void displayProfile(String profileName, String userType, String profileImage);
    }

    interface OnFinishedListener {

        void onSuccss(ProfileAndUserDetails profileAndUserDetails);

        void onSuccss(Map map, PersonalInfo personalInfo);

        void onFail(Throwable throwable);

        void onNetworkFailure();


    }


    void getProfile(OnProfileListener onProfileListener);

    void getAllProfile(OnFinishedListener onFinishedListener);

}
