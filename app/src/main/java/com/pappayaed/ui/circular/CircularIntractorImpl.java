package com.pappayaed.ui.circular;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.pappayaed.common.Utils;
import com.pappayaed.data.DataSource;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.ResultResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yasar on 27/3/18.
 */

public class CircularIntractorImpl implements ICircularIntractor {


    private Context context;
    private DataSource dataSource;


    public CircularIntractorImpl(Context context, DataSource dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }

    @Override
    public void getCircularAndHomework(final OnFinishedListener onFinishedListener) {


//        String json = Utils.loadJSONFromAsset(context, "circular.json");
//
//        ResultResponse resultResponse = new Gson().fromJson(json, ResultResponse.class);
//
//        Log.e(" Circular Fees  ", "onSuccess: " + new Gson().toJson(resultResponse));
//
//        onFinishedListener.onSuccss(resultResponse);


        Map<Object, Object> json = new HashMap<>();
        Map<Object, Object> params = new HashMap<>();
        params.put("login", dataSource.getEmailOrUsername());
        params.put("password", dataSource.getPassword());
        params.put("user_type", dataSource.getUserType());

        json.put("params", params);

        final String js = new JSONObject(json).toString();

        dataSource.getCircularAndHomeWork(js, new DataListener() {
            @Override
            public void onSuccess(Object object) {


                ResultResponse resultResponse = (ResultResponse) object;

                Log.e(" Circular Fees  ", "onSuccess: " + new Gson().toJson(resultResponse));

                onFinishedListener.onSuccss(resultResponse);

//                Parser.circularParse(object.toString(), onFinishedListener, dataSource);

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
    public void getCircularAndHomeworkFromLocal(OnFinishedListener onFinishedListener) {

        Gson gson = new Gson();
        String json = Utils.loadJSONFromAsset(context, "events.json");
        ResultResponse resultResponse = gson.fromJson(json, ResultResponse.class);
        onFinishedListener.onSuccss(resultResponse);

    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}
