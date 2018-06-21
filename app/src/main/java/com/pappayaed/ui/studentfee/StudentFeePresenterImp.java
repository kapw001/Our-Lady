package com.pappayaed.ui.studentfee;

import com.pappayaed.base.BasePresenter;
import com.pappayaed.ui.showprofile.StudentList;

import java.util.List;

/**
 * Created by yasar on 26/3/18.
 */

public class StudentFeePresenterImp<V extends IStudentFeeView> extends BasePresenter<V> implements IStudentFeePresenter<V>, IStudentIntractor.OnFinishedListener {

    private IStudentIntractor iStudentIntractor;

    private List<StudentList> lists;

    public StudentFeePresenterImp(IStudentIntractor iStudentIntractor) {
        super(iStudentIntractor.getDataSource());
        this.iStudentIntractor = iStudentIntractor;
    }

    @Override
    public void getStudentProfile() {

        if (this.lists == null) {
            getMvpView().showProgress();
            iStudentIntractor.getStudentProfile(this);
        } else {

            onSuccss(this.lists);
        }
    }

    @Override
    public void onSuccss(List<StudentList> studentLists) {

        this.lists = studentLists;

        getMvpView().hideProgress();
        if (studentLists.size() > 0) getMvpView().setData(studentLists);
        else getMvpView().emptyData();


    }

    @Override
    public void onFail(Throwable throwable) {
        getMvpView().hideProgress();
        getMvpView().onFail(throwable);
    }

    @Override
    public void onNetworkFailure() {
        getMvpView().hideProgress();
        getMvpView().onNetworkFailure();
    }
}
