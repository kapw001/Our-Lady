package com.pappayaed.ui.more;

import android.graphics.Bitmap;
import android.util.Log;

import com.pappayaed.common.Utils;
import com.pappayaed.data.DataSource;

import java.util.Map;

/**
 * Created by yasar on 26/3/18.
 */

public class MorePresenter implements IMorePresenter {


    private IMoreView iMoreView;

    private DataSource dataSource;

    public MorePresenter(IMoreView iMoreView, DataSource dataSource) {
        this.iMoreView = iMoreView;
        this.dataSource = dataSource;
    }


    @Override
    public void displayProfile() {


        String profileImage = dataSource.getProfileImage();
        String profileName = dataSource.getProfileName();
        String userType = dataSource.getUserType();

        iMoreView.displayProfile(profileName, userType, profileImage);


    }

    @Override
    public void gotoProfileActivity() {

        iMoreView.gotoProfileActivity();

    }

    @Override
    public void logout() {

        dataSource.clear();
        iMoreView.logout();

    }

}
