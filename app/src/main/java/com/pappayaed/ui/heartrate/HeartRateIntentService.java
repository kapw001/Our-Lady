package com.pappayaed.ui.heartrate;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Color;

import com.pappayaed.common.Utils;
import com.pappayaed.data.DataRepository;
import com.pappayaed.data.DataSource;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.data.pref.Pref;
import com.pappayaed.data.pref.PreferencesHelper;
import com.pappayaed.data.remote.ApiService;
import com.pappayaed.data.remote.RemoteDataSourceHelper;
import com.pappayaed.data.retrofitclient.ApiEndPoint;
import com.pappayaed.data.retrofitclient.RetrofitClient;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class HeartRateIntentService extends IntentService {

    private Pref pref;
    private RetrofitClient retrofitClient;
    private ApiService apiService;
    private RemoteDataSourceHelper remoteDataSource;
    public DataSource dataSource;

    public HeartRateIntentService() {
        super("HeartRateIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        pref = PreferencesHelper.getPreferencesInstance(getApplicationContext());

        retrofitClient = RetrofitClient.getRetrofitClientInstance(ApiEndPoint.BASE_URL);

        apiService = retrofitClient.getRetrofit().create(ApiService.class);

        remoteDataSource = new RemoteDataSourceHelper(apiService);

        dataSource = new DataRepository(getApplicationContext(), remoteDataSource, pref);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();

            long id = intent.getLongExtra("id", 0);


            Map<Object, Object> json = new HashMap<>();
            Map<Object, Object> params = new HashMap<>();
            params.put("login", dataSource.getEmailOrUsername());
            params.put("password", dataSource.getPassword());
            params.put("user_type", dataSource.getUserType());
            params.put("student_id", id);


            json.put("params", params);

            final String js = new JSONObject(json).toString();

            Utils.showProgress(this, "Loading");

            dataSource.get_Standard_HeartRate_list(js, new DataListener() {
                @Override
                public void onSuccess(Object object) {

                    Utils.hideProgress();

                    ResultResponse response = (ResultResponse) object;

                    sendMsg(response);

                }

                @Override
                public void onFail(Throwable throwable) {
                    Utils.hideProgress();
                    sendMsg(null);
                }

                @Override
                public void onNetworkFailure() {
                    Utils.hideProgress();
                    sendMsg(null);
                }
            });


        }
    }


    private void sendMsg(ResultResponse resultResponse) {

//        Intent broadcastIntent = new Intent();
//        broadcastIntent.setAction(HeartRateChartFragment.ResponseReceiver.ACTION_RESP);
//        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
//        broadcastIntent.putExtra("result", resultResponse);
//        sendBroadcast(broadcastIntent);

    }

}
