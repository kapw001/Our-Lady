package com.pappayaed.ui.showprofile;

import com.pappayaed.ui.parentprofile.PersonalInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by yasar on 26/3/18.
 */

public interface IProfileView {


    void displayProfile(String profileName, String userType, String profileImage);

    void gotoStudentProfileActivity();

    void setData(List list);

    void setData(Map<Object, List> map, PersonalInfo personalInfo);

    void setError(String msg);

    void setEmptyData();

}
