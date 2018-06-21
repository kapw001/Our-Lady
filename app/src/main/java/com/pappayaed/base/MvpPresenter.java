package com.pappayaed.base;

/**
 * Created by yasar on 5/4/18.
 */

public interface MvpPresenter<V extends BaseView> {

    void onAttach(V mvpView);

    void onDetach();

}
