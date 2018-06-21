package com.pappayaed.ui.studentfee;

import com.pappayaed.data.DataSource;
import com.pappayaed.ui.showprofile.StudentList;

import java.util.List;

/**
 * Created by yasar on 26/3/18.
 */

public interface IStudentIntractor {


    interface OnFinishedListener {

        void onSuccss(List<StudentList> studentLists);

        void onFail(Throwable throwable);

        void onNetworkFailure();
    }

    void getStudentProfile(OnFinishedListener onFinishedListener);

    DataSource getDataSource();

}
