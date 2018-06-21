package com.pappayaed.ui.calendarandlistview;

import com.pappayaed.data.model.AttendanceList;
import com.pappayaed.data.model.Circular;
import com.pappayaed.data.model.HomeWork;

import java.util.List;

import calendar.android.com.customcalendar.EventHighlight;
import calendar.android.com.customcalendar.EventObjects;

/**
 * Created by yasar on 27/4/18.
 */

public interface ICalendarListView {


    void setEventHighlight(EventHighlight eventHighlight);

    void setDataCircular(List<Circular> circularList, List<EventObjects> eventList);

    void updateDataCircular(List<Circular> circularList);

    void setDataHome(List<HomeWork> homeList, List<EventObjects> eventList);

    void updateDataHome(List<HomeWork> homeList);

    void setDataAttendance(List<AttendanceList> attendanceLists, List<EventObjects> eventList);

    void updateAttendance(List<AttendanceList> attendanceLists);

    void updateEventList(List<EventObjects> eventList);

    void showErrorOrEmptyView(String msg, boolean isContentOrEventNotFound);


}
