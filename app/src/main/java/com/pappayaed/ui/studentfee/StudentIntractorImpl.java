package com.pappayaed.ui.studentfee;

import com.pappayaed.data.DataSource;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.parser.Parser;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yasar on 26/3/18.
 */

public class StudentIntractorImpl implements IStudentIntractor {


    private DataSource dataSource;

    public StudentIntractorImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void getStudentProfile(final OnFinishedListener onFinishedListener) {




        Map<Object, Object> json = new HashMap<>();
        Map<Object, Object> params = new HashMap<>();
        params.put("login", dataSource.getEmailOrUsername());
        params.put("password", dataSource.getPassword());
        params.put("user_type", dataSource.getUserType());

        json.put("params", params);

        final String js = new JSONObject(json).toString();


        dataSource.getStudentProfile(js, new DataListener() {
            @Override
            public void onSuccess(Object object) {

                Parser.profileParse(object.toString(), onFinishedListener, dataSource);

            }

            @Override
            public void onFail(Throwable throwable) {
                onFinishedListener.onFail(throwable);
            }

            @Override
            public void onNetworkFailure() {
                onFinishedListener.onNetworkFailure();
            }
        });


    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}
