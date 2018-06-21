package com.pappayaed.data;

import android.content.Context;

import com.pappayaed.data.helper.NetworkHelper;
import com.pappayaed.data.parser.Parser;
import com.pappayaed.data.listener.DataListener;
import com.pappayaed.data.pref.Pref;
import com.pappayaed.data.remote.RemoteDataSource;

import java.util.Map;

/**
 * Created by yasar on 6/3/18.
 */

public class DataRepository implements DataSource {

    private static final String TAG = "DataRepository";
    private Context context;
    private RemoteDataSource remoteDataSource;
    private Pref preferences;

    private TypeRequest typeRequest;

    private enum TypeRequest {

        LOGIN, PROFILE, ALL_PROFILE, OTHERS;

    }


    public DataRepository(Context context, RemoteDataSource remoteDataSource, Pref preferences) {
        this.context = context;
        this.remoteDataSource = remoteDataSource;
        this.preferences = preferences;
    }


    public void login(String json, final DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.LOGIN;
            remoteDataSource.login(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void getStudentProfile(String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.PROFILE;
            remoteDataSource.getStudentProfile(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void getAllProfile(String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.ALL_PROFILE;
            remoteDataSource.getAllProfile(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void getDailyAttendanceList(String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getDailyAttendanceList(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void updateAttendanceList(String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.updateAttendanceList(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void getDailyHomeWorkList(String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getDailyHomeWorkList(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void updateHomeWorkBySubject(String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.updateHomeWorkBySubject(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void getStudentFeeList(String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getStudentFeeList(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void getStudentAtttendanceList(String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getStudentAtttendanceList(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void getCircularAndHomeWork(String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getCircularAndHomeWork(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void getAttachment_id_data(String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getAttachment_id_data(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void getStandard_fee_collection(String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getStandard_fee_collection(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void get_Standard_HeartRate_list(String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.get_Standard_HeartRate_list(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void putDeviceToken(String json, DataListener dataListener) {


        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.putDeviceToken(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void whereisstudent(String json, DataListener dataListener) {


        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.whereisstudent(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }


    private DataListener setDataListener(final DataListener dataListener, final TypeRequest typeRequest) {

        return new DataListener() {
            @Override
            public void onSuccess(Object object) {


                parserData(object, dataListener, typeRequest);


            }

            @Override
            public void onFail(Throwable s) {
                dataListener.onFail(s);
            }

            @Override
            public void onNetworkFailure() {
                dataListener.onNetworkFailure();
            }
        };
    }


    private <T> void parserData(T o, DataListener dataListener, TypeRequest typeRequest) {

//        switch (typeRequest) {
//
//            case LOGIN:
//
//                Parser.loginParse(o.toString(), dataListener, this);
//
//                break;
//
//            case PROFILE:
//
//                Parser.profileParse(o.toString(), dataListener, this);
//
//                break;
//
//            case ALL_PROFILE:
//
//                Parser.allProfileParse(o.toString(), dataListener, this);
//
//                break;
//
//            case OTHERS:

        dataListener.onSuccess(o);

//                break;
//        }


    }


    @Override
    public boolean isLoggedIn() {

        return preferences.isLoggedIn();

    }

    @Override
    public void saveSession(String email, String password, String username, String userid, String usertype, boolean status, String userimage) {

        preferences.saveSession(email, password, username, userid, usertype, status, userimage);

    }

    @Override
    public String getUserType() {
        return preferences.getUserType();
    }

    @Override
    public Map getUserDetails() {
        return preferences.getUserDetails();
    }

    @Override
    public String getEmailOrUsername() {
        return preferences.getEmailOrUsername();
    }

    @Override
    public String getPassword() {
        return preferences.getPassword();
    }

    @Override
    public String getProfileName() {
        return preferences.getProfileName();
    }

    @Override
    public String getProfileImage() {
        return preferences.getProfileImage();
    }

    @Override
    public void clear() {
        preferences.clear();
    }

    @Override
    public void saveHomeWork(String circular_homework) {
        preferences.saveHomeWork(circular_homework);
    }

    @Override
    public String getHomeWork() {
        return preferences.getHomeWork();
    }

    @Override
    public void saveDownloadedfile(String key, String state) {
        preferences.saveDownloadedfile(key, state);
    }

    @Override
    public String getState(String key) {
        return preferences.getState(key);
    }
}

