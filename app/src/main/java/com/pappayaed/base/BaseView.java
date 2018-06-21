package com.pappayaed.base;

import android.support.annotation.StringRes;
import android.view.View;

/**
 * Created by yasar on 26/3/18.
 */

public interface BaseView {


    void onFail(Throwable throwable);

    void onNetworkFailure();

    void showLoading();

    void hideLoading();

    void showSnackBar(View view, String msg);

    void showToast(String msg);

    void showToast(@StringRes int msgID);
}
