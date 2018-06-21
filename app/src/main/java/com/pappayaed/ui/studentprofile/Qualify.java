package com.pappayaed.ui.studentprofile;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;

import java.io.Serializable;

/**
 * Created by yasar on 14/6/18.
 */

public class Qualify implements Serializable, ViewLayout {

    private String Degree;
    private String Year_of_Completion;
    private String Institute_of_Study;
    private String University;


    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getYear_of_Completion() {
        return Year_of_Completion;
    }

    public void setYear_of_Completion(String year_of_Completion) {
        Year_of_Completion = year_of_Completion;
    }

    public String getInstitute_of_Study() {
        return Institute_of_Study;
    }

    public void setInstitute_of_Study(String institute_of_Study) {
        Institute_of_Study = institute_of_Study;
    }

    public String getUniversity() {
        return University;
    }

    public void setUniversity(String university) {
        University = university;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.qualify_row;
    }
}
