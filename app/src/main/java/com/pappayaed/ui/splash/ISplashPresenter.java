package com.pappayaed.ui.splash;

import com.pappayaed.base.MvpPresenter;

/**
 * Created by yasar on 26/3/18.
 */

public interface ISplashPresenter<V extends ISplashView> extends MvpPresenter<V> {

    void moveToNextActivity();

    void cancelHandler();

}
