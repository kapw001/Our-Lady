package com.pappayaed.interfaces;

import com.pappayaed.ui.showprofile.StudentList;

/**
 * Created by yasar on 17/4/18.
 */

public interface CallBackStudentID {

    void loadStudentID(StudentList studentList);

    void onFail(Throwable throwable);

    void onNetworkFailure();

    void showLoading();

    void hideLoading();

}
