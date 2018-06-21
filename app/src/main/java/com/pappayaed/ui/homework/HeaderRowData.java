package com.pappayaed.ui.homework;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;

/**
 * Created by yasar on 19/6/18.
 */

public class HeaderRowData implements ViewLayout{


    private int image;

    private String title;

    private String title_name;

    public HeaderRowData(int image, String title, String title_name) {
        this.image = image;
        this.title = title;
        this.title_name = title_name;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.headerrow_row;
    }
}
