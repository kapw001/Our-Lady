package com.pappayaed.ui.studentprofile;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;

import java.io.Serializable;

/**
 * Created by yasar on 11/4/18.
 */

public class Header implements Serializable, ViewLayout {

    private String title;

    public Header(String title) {
        this.title = title;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.row_header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
