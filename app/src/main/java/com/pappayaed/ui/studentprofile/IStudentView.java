package com.pappayaed.ui.studentprofile;

import com.pappayaed.base.BaseView;
import com.pappayaed.ui.showprofile.StudentList;

import java.util.List;

/**
 * Created by yasar on 21/5/18.
 */

public interface IStudentView extends BaseView {


    void displayNameAndID(String name, String id);

    void showStudentDetails(List studentList);

}
