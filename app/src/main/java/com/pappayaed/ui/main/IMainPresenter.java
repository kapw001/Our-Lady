package com.pappayaed.ui.main;

import android.view.MenuItem;
import android.widget.ImageView;

import com.pappayaed.base.MvpPresenter;

/**
 * Created by yasar on 26/3/18.
 */

public interface IMainPresenter<V extends IMainView> extends MvpPresenter<V> {

    void inflateBottomNavigationView();

    void bottomNavigationViewPosition(MenuItem item);

    void loadMoreFragment();

    void logout();


}
