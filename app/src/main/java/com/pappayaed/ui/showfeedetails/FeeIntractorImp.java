package com.pappayaed.ui.showfeedetails;

import com.pappayaed.data.DataSource;
import com.pappayaed.data.helper.L;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.ResultResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yasar on 27/3/18.
 */

public class FeeIntractorImp implements IFeeIntractor {


    private DataSource dataSource;

    private Map<Long, ResultResponse> cached = new HashMap<>();

    public FeeIntractorImp(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void getStudentFeeList(final long id, final OnFinishedListener onFinishedListener) {


        if (cached.get(id) != null) {

            ResultResponse resultResponse = cached.get(id);

            onFinishedListener.onSuccss(resultResponse);

            return;

        }


        Map<Object, Object> json = new HashMap<>();
        Map<Object, Object> params = new HashMap<>();
        params.put("login", dataSource.getEmailOrUsername());
        params.put("password", dataSource.getPassword());
        params.put("user_type", dataSource.getUserType());
        params.put("student_id", id);

        json.put("params", params);

        final String js = new JSONObject(json).toString();

        L.loge("params :  " + js);

        dataSource.getStudentFeeList(js, new DataListener() {
            @Override
            public void onSuccess(Object object) {

                ResultResponse resultResponse = (ResultResponse) object;

                cached.put(id, resultResponse);
                onFinishedListener.onSuccss(resultResponse);
            }

            @Override
            public void onFail(Throwable throwable) {

                cached.put(id, null);
                onFinishedListener.onFail(throwable);

            }

            @Override
            public void onNetworkFailure() {
                cached.put(id, null);
                onFinishedListener.onNetworkFailure();

            }
        });


    }
}
