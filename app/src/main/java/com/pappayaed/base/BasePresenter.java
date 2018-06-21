package com.pappayaed.base;

import com.pappayaed.App.App;
import com.pappayaed.common.Utils;
import com.pappayaed.data.DataSource;

/**
 * Created by yasar on 5/4/18.
 */

public class BasePresenter<V extends BaseView> implements MvpPresenter<V> {

    private V mMvpView;
    private DataSource dataSource;

    public BasePresenter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void onAttach(V mvpView) {

        this.mMvpView = mvpView;
    }

    @Override
    public void onDetach() {

        this.mMvpView = null;

    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public boolean checkNetwork() {
        return App.getApp().checkIfHasNetwork();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
