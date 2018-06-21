package com.pappayaed.ui.circular;

import com.pappayaed.data.DataRepository;
import com.pappayaed.data.DataSource;
import com.pappayaed.data.model.Circular;
import com.pappayaed.data.model.CircularAndHomeWork;
import com.pappayaed.data.model.ResultResponse;
import com.pappayaed.ui.showprofile.StudentList;
import com.pappayaed.ui.studentfee.IStudentIntractor;

import java.util.List;

/**
 * Created by yasar on 27/3/18.
 */

public interface ICircularIntractor {


    interface OnFinishedListener {

        void onSuccss(ResultResponse resultResponse);

        void onFail(Throwable throwable);

        void onNetworkFailure();
    }

    void getCircularAndHomework(OnFinishedListener onFinishedListener);

    void getCircularAndHomeworkFromLocal(OnFinishedListener onFinishedListener);

    DataSource getDataSource();


}
