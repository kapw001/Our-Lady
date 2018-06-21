package com.pappayaed.ui.login;

import com.pappayaed.data.DataSource;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.parser.Parser;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yasar on 17/4/17.
 */

class LoginIntractorImpl implements ILoginIntractor {

    private static final String TAG = "LoginIntractorImpl";

    private DataSource dataRepository;

    LoginIntractorImpl(DataSource dataRepository) {
        this.dataRepository = dataRepository;
    }


    public void validateCredentials(String email, String password, final OnFinishedListener dataListener) {

        Map<Object, Object> json = new HashMap<>();
        Map<Object, Object> params = new HashMap<>();
        params.put("login", email);
        params.put("password", password);

        json.put("params", params);

        final String js = new JSONObject(json).toString();

        dataRepository.login(js, getDataListener(dataListener));

    }


    public void getList(final OnFinishedListener dataListener) {


    }


    private DataListener getDataListener(final OnFinishedListener iLoginIntractor) {

        return new DataListener() {
            @Override
            public void onSuccess(Object object) {

//                iLoginIntractor.onSuccssLogin(object.toString());

                Parser.loginParse(object.toString(), iLoginIntractor, dataRepository);

            }

            @Override
            public void onFail(Throwable throwable) {

                iLoginIntractor.onFail(throwable);

            }

            @Override
            public void onNetworkFailure() {

                iLoginIntractor.onNetworkFailure();

            }
        };
    }


}
