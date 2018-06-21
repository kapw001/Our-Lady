package com.pappayaed.ui.studentprofile;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;

import java.io.Serializable;

/**
 * Created by yasar on 11/4/18.
 */

public class StudentDetails implements Serializable,ViewLayout {

    private String mTitle;
    private String mTitleValue;
    private int imgID;
    private boolean isBold = false;

    public StudentDetails() {

    }

    public StudentDetails(String mTitle, String mTitleValue, int imgID) {
        this.mTitle = mTitle;
        this.mTitleValue = mTitleValue;
        this.imgID = imgID;
    }

    public StudentDetails(String mTitle, String mTitleValue, int imgID, boolean isBold) {
        this.mTitle = mTitle;
        this.mTitleValue = mTitleValue;
        this.imgID = imgID;
        this.isBold = isBold;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.custom_student_profile_row;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTitleValue() {
        return mTitleValue;
    }

    public void setmTitleValue(String mTitleValue) {
        this.mTitleValue = mTitleValue;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }
}
