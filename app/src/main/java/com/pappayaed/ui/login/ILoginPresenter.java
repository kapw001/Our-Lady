package com.pappayaed.ui.login;

import com.pappayaed.base.MvpPresenter;

/**
 * Created by yasar on 22/3/18.
 */

public interface ILoginPresenter<V extends ILoginView> extends MvpPresenter<V> {

    void validateCredentials(String email, String password);

}
