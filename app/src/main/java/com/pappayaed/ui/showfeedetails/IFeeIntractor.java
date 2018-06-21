package com.pappayaed.ui.showfeedetails;

import com.pappayaed.data.model.ResultResponse;

/**
 * Created by yasar on 27/3/18.
 */

public interface IFeeIntractor {


    interface OnFinishedListener {

        void onSuccss(ResultResponse resultResponse);

        void onFail(Throwable throwable);

        void onNetworkFailure();
    }

    void getStudentFeeList(long id, OnFinishedListener onFinishedListener);

}
