package com.pappayaed.ui.login;

import android.text.TextUtils;

import com.pappayaed.base.BasePresenter;
import com.pappayaed.data.DataSource;

/**
 * Created by yasar on 17/4/17.
 */

public class LoginPresenterImpl<V extends ILoginView> extends BasePresenter<V> implements ILoginPresenter<V>, ILoginIntractor.OnFinishedListener {


    private ILoginIntractor iLoginIntractor;

    public LoginPresenterImpl(DataSource dataSource) {
        super(dataSource);

        iLoginIntractor = new LoginIntractorImpl(dataSource);
    }

    @Override
    public void validateCredentials(String username, String password) {

        if (checkNetwork()) {

            if (TextUtils.isEmpty(username)) {
                getMvpView().showEmailError();
                return;
            } else if (TextUtils.isEmpty(password)) {
                getMvpView().showPasswordError();
                return;
            }

            getMvpView().showLoading();
            iLoginIntractor.validateCredentials(username, password, this);

        } else {

            getMvpView().onNetworkFailure();
            return;

        }

    }


    @Override
    public void onSuccssLogin(String s) {

        getMvpView().hideLoading();
//        iLoginView.onSuccssLogin(s);
        getMvpView().gotoMainActivity();
    }


    @Override
    public void onFail(Throwable throwable) {

        getMvpView().hideLoading();


        if (throwable.getMessage().equalsIgnoreCase("Invalid Email ID and Password")) {
            getMvpView().showEmailAndPasswordError();
            return;
        }

        getMvpView().onFail(throwable);

    }

    @Override
    public void onNetworkFailure() {

        getMvpView().hideLoading();
        getMvpView().onNetworkFailure();
    }

}
