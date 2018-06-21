package com.pappayaed.ui.login;

/**
 * Created by yasar on 22/3/18.
 */

public interface ILoginIntractor {

    void validateCredentials(String email, String password, OnFinishedListener onFinishedListener);

    void getList(OnFinishedListener onFinishedListener);


    interface OnFinishedListener {

        void onSuccssLogin(String s);

        void onFail(Throwable throwable);

        void onNetworkFailure();
    }


}
