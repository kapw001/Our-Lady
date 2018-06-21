package com.pappayaed.ui.studentfee;

import com.pappayaed.base.MvpPresenter;

/**
 * Created by yasar on 26/3/18.
 */

public interface IStudentFeePresenter<V extends IStudentFeeView> extends MvpPresenter<V> {


    void getStudentProfile();

}
