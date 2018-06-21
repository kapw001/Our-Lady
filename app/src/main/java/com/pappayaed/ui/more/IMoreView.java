package com.pappayaed.ui.more;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by yasar on 26/3/18.
 */

public interface IMoreView {

    void displayProfile(String profileName, String userType, String profileImage);

    void gotoProfileActivity();

    void logout();
}
