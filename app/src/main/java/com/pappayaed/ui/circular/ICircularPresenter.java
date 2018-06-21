package com.pappayaed.ui.circular;

import android.view.View;

import com.pappayaed.base.MvpPresenter;

/**
 * Created by yasar on 27/3/18.
 */

public interface ICircularPresenter<V extends ICircularView> extends MvpPresenter<V>{


    void getCircularAndHomeWork();

    void loadCircularData();

    void loadHomeWOrkData();

    void setIsCircular(boolean isCircularEnabled);

    void onItemClick(View view, int position, Object o);


}
