package com.pappayaed.data.retrofitclient;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by yasar on 27/2/18.
 */

public class RetrofitClient {

    private static RetrofitClient retrofitClient;
    private Retrofit retrofit;
    private String url = "";

    private RetrofitClient(String url) {

        this.url = url;

    }

    public static RetrofitClient getRetrofitClientInstance(String url) {

        if (retrofitClient == null) {

            retrofitClient = new RetrofitClient(url);

        }

        return retrofitClient;
    }

    public Retrofit getRetrofit() {

        if (retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS);
            httpClient.addInterceptor(logging);

            retrofit = new Retrofit.Builder()
                    .client(httpClient.build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(url)
                    .build();

        }

        return retrofit;

    }


}
