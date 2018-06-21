package com.pappayaed.data.remote;

import android.util.Log;

import com.pappayaed.data.DataSource;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.model.ResultResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by yasar on 6/3/18.
 */

public class RemoteDataSourceHelper implements RemoteDataSource {

    private static final String TAG = "RemoteDataSourceHelper";

    private ApiService apiService;

    public RemoteDataSourceHelper(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void login(String json, final DataListener dataListener) {

        Log.e(TAG, "login: " + json);

        apiService.loginValidate(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void getStudentProfile(String json, DataListener dataListener) {

        Log.e(TAG, "getStudentProfile: " + json);

        apiService.getStudentProfile(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));

    }

    @Override
    public void getAllProfile(String json, DataListener dataListener) {
        Log.e(TAG, "getAllProfile: " + json);
        apiService.getStudentProfile(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void getDailyAttendanceList(String json, DataListener dataListener) {

        Log.e(TAG, "getDailyAttendanceList: " + json);

        apiService.getDailyAttendanceList(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void updateAttendanceList(String json, DataListener dataListener) {

        Log.e(TAG, "updateAttendanceList: " + json);

        apiService.updateAttendanceList(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));

    }

    @Override
    public void getDailyHomeWorkList(String json, DataListener dataListener) {

        Log.e(TAG, "getDailyHomeWorkList: " + json);

        apiService.getDailyHomeWorkList(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));

    }

    @Override
    public void updateHomeWorkBySubject(String json, DataListener dataListener) {

        Log.e(TAG, "updateHomeWorkBySubject: " + json);

        apiService.updateHomeWorkBySubject(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));

    }

    @Override
    public void getStudentFeeList(String json, DataListener dataListener) {
        Log.e(TAG, "getStudentFeeList: " + json);
        apiService.getStudentFeeList(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void getStudentAtttendanceList(String json, DataListener dataListener) {
        Log.e(TAG, "getStudentAtttendanceList: " + json);
        apiService.getStudentAtttendanceList(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void getCircularAndHomeWork(String json, DataListener dataListener) {
        Log.e(TAG, "getCircularAndHomeWork: " + json);
        apiService.getCircularAndHomeWork(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void getAttachment_id_data(String json, DataListener dataListener) {
        Log.e(TAG, "getAttachment_id_data: " + json);

        apiService.getAttachment_id_data1(json).flatMap((Function<Response<ResultResponse>, ObservableSource<?>>) response -> {

            switch (response.code()) {

                case 200:

                    return Observable.just(response.body());
                case 202:
                    //need to Repeat
//                        return Observable.error(new Status202Exception());
                    return Observable.error(new Exception("unknown error"));
                default:
                    return Observable.error(new Exception("unknown error"));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void getStandard_fee_collection(String json, DataListener dataListener) {
        Log.e(TAG, "getAttachment_id_data: " + json);
        apiService.getStandard_fee_collection(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void get_Standard_HeartRate_list(String json, DataListener dataListener) {
        Log.e(TAG, "get_Standard_HeartRate_list: " + json);
        apiService.get_Standard_heartrate_list(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void putDeviceToken(String json, DataListener dataListener) {

        Log.e(TAG, "get_Standard_HeartRate_list: " + json);
        apiService.put_Device_Token(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));

    }

    @Override
    public void whereisstudent(String json, DataListener dataListener) {

        Log.e(TAG, "get_Standard_HeartRate_list: " + json);
        apiService.whereisstudent(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }


    private static <T> Observer<T> observer(final DataListener dataListener) {

        return new Observer<T>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(T value) {

                dataListener.onSuccess(value);

            }

            @Override
            public void onError(Throwable e) {


                Log.e(TAG, "onError: Handle for testing purpose " + e.getMessage());


                dataListener.onFail(e);
            }

            @Override
            public void onComplete() {

//                Log.e(TAG, "onComplete:  qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");

            }
        };

    }


}
