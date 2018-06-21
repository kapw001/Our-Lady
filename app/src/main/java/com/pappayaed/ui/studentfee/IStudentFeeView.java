package com.pappayaed.ui.studentfee;

import com.pappayaed.base.BaseView;
import com.pappayaed.ui.showprofile.StudentList;

import java.util.List;

/**
 * Created by yasar on 26/3/18.
 */

public interface IStudentFeeView extends BaseView {


    void setData(List<StudentList> studentLists);

    void emptyData();

    void setError(String msg);

    void showProgress();

    void hideProgress();

}
