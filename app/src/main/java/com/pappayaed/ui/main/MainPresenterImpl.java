package com.pappayaed.ui.main;

import android.view.MenuItem;

import com.pappayaed.R;
import com.pappayaed.base.BasePresenter;
import com.pappayaed.data.DataSource;

/**
 * Created by yasar on 26/3/18.
 */

public class MainPresenterImpl<V extends IMainView> extends BasePresenter<V> implements IMainPresenter<V> {


    private DataSource dataSource;

    public MainPresenterImpl(DataSource dataSource) {
        super(dataSource);

        this.dataSource = dataSource;

    }

    @Override
    public void inflateBottomNavigationView() {


        String userType = dataSource.getUserType();

        if (userType != null && userType.equalsIgnoreCase("parent")) {

            getMvpView().inflateBottomViewParent();

        }

    }

    @Override
    public void bottomNavigationViewPosition(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.attendance:

                getMvpView().moveToAttendanceActivity();

                break;

            case R.id.homework:

                getMvpView().moveToHomeWorkActivity();

                break;

            default:
                getMvpView().moveToMoreFragment();

                break;

        }

    }

    @Override
    public void loadMoreFragment() {
        getMvpView().moveToMoreFragment();
    }

    @Override
    public void logout() {
        dataSource.clear();
        getMvpView().logout();
    }
}
