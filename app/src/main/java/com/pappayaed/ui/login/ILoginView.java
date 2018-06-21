package com.pappayaed.ui.login;

import com.pappayaed.base.BaseView;

/**
 * Created by yasar on 22/3/18.
 */

public interface ILoginView extends BaseView {

    void showEmailError();

    void showPasswordError();

    void showEmailAndPasswordError();

    void onSuccssLogin(String s);

    void gotoMainActivity();

}
