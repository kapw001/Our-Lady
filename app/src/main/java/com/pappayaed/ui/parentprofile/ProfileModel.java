package com.pappayaed.ui.parentprofile;

import android.arch.lifecycle.ViewModel;

/**
 * Created by yasar on 10/4/18.
 */

public class ProfileModel {

    private String title;

    private String titleValue;

    private int imgId;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleValue() {
        return titleValue;
    }

    public void setTitleValue(String titleValue) {
        this.titleValue = titleValue;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
