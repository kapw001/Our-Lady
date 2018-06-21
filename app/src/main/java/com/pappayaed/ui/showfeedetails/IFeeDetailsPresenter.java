package com.pappayaed.ui.showfeedetails;

/**
 * Created by yasar on 27/3/18.
 */

public interface IFeeDetailsPresenter {


    void getStudentFeeList(long id);

    void parseFeeDetails();

    void setTermName(String termName);

    void getTermName();

}
