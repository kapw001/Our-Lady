package com.pappayaed.data.listener;

import io.reactivex.Observer;

/**
 * Created by yasar on 28/2/18.
 */

public interface DataListener {

    void onSuccess(Object object);

    void onFail(Throwable throwable);

    void onNetworkFailure();

}
