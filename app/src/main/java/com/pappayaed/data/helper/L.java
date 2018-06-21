package com.pappayaed.data.helper;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Created by yasar on 22/3/18.
 */

public class L {

    private static final String TAG = "L";

//    private static Gson gson = new Gson();
//    private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public static void loge(String msg) {

        Log.e(TAG, "" + msg);

    }

    public static void logd(String msg) {

        Log.d(TAG, "" + msg);

    }

    public static void logi(String msg) {

        Log.i(TAG, "" + msg);

    }

    public static void logv(String msg) {

        Log.v(TAG, "" + msg);

    }


    public static <T> void loop(List<T> list) {

        Gson gson = new Gson();
        for (int i = 0; i < list.size(); i++) {

            Object object = list.get(i);

            Log.e(TAG, "loop: " + gson.toJson(object));

        }

    }
}
