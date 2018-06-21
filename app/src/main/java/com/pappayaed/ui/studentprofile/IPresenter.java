package com.pappayaed.ui.studentprofile;

import com.pappayaed.base.BasePresenter;
import com.pappayaed.base.BaseView;
import com.pappayaed.base.MvpPresenter;
import com.pappayaed.ui.showprofile.StudentList;

/**
 * Created by yasar on 21/5/18.
 */

public interface IPresenter<V extends BaseView> extends MvpPresenter<V> {

    void getStudentList(StudentList st);

}
