package com.pappayaed.ui.main;

import com.pappayaed.base.BaseView;

/**
 * Created by yasar on 26/3/18.
 */

public interface IMainView extends BaseView {

    void inflateBottomViewParent();

    void moveToHomeWorkActivity();

    void moveToAttendanceActivity();

    void moveToMoreFragment();

    void logout();


}
