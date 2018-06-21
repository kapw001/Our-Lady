package com.pappayaed.ui.circular;

import com.pappayaed.base.BaseView;
import com.pappayaed.data.model.AssignmentSubLine;
import com.pappayaed.data.model.Circular;
import com.pappayaed.data.model.HomeWork;

import java.util.List;

import calendar.android.com.customcalendar.EventObjects;

/**
 * Created by yasar on 27/3/18.
 */

public interface ICircularView extends BaseView {


    void setDataCircular(List<Circular> circularList);

    void setDataHome(List<HomeWork> homeList);

    void setEventList(List<EventObjects> eventList);

    void setEmptyData();

    void emptyData();

}
