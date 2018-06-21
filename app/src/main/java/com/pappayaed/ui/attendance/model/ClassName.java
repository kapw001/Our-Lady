package com.pappayaed.ui.attendance.model;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;

import java.io.Serializable;

/**
 * Created by yasar on 17/6/18.
 */

public class ClassName implements Serializable, ViewLayout {

    private String name;

    public ClassName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_row_classname;
    }
}
