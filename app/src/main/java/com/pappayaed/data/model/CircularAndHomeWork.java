package com.pappayaed.data.model;

import java.util.List;

/**
 * Created by yasar on 28/3/18.
 */

public class CircularAndHomeWork {

    private List<Circular> circularList;

    private List<AssignmentSubLine> homeCircularList;

    public List<Circular> getCircularList() {
        return circularList;
    }

    public void setCircularList(List<Circular> circularList) {
        this.circularList = circularList;
    }


    public List<AssignmentSubLine> getHomeCircularList() {
        return homeCircularList;
    }

    public void setHomeCircularList(List<AssignmentSubLine> homeCircularList) {
        this.homeCircularList = homeCircularList;
    }
}
