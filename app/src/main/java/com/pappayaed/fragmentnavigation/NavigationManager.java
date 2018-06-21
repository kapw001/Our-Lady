package com.pappayaed.fragmentnavigation;

import android.support.v4.app.Fragment;

/**
 * Created by yasar on 20/7/17.
 */

public interface NavigationManager {

    void AttendanceFragment(String title);

    void StudentFeeFragment(String title);

//    void StudentAttedanceFragment(String title, boolean isfee);

    void LeaveTapFragment(String title);

    void MoreFragment(String title);

    void AssignmentFragmentBackup(String title);

    void StudentTableFragment(String title);

    void StudentExamFragment(String title);

    void StudentExamParentFragment(String title);

    void StudentSubmissionFragment(String title);

    void Circular(String title);

    Fragment getFragment();
}
