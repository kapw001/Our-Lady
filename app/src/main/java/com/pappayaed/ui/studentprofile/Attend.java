package com.pappayaed.ui.studentprofile;

import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;

import java.io.Serializable;

/**
 * Created by yasar on 14/6/18.
 */

public class Attend implements Serializable, ViewLayout {

    private String Attendance_Date;
    private String In_time;
    private String Out_time;
    private String Worked_Hours;
    private String Attendance_Status;

    public boolean isNodata() {
        return isNodata;
    }

    public void setNodata(boolean nodata) {
        isNodata = nodata;
    }

    private boolean isNodata = false;

    public String getAttendance_Date() {
        return Attendance_Date;
    }

    public void setAttendance_Date(String attendance_Date) {
        Attendance_Date = attendance_Date;
    }

    public String getIn_time() {
        return In_time;
    }

    public void setIn_time(String in_time) {
        In_time = in_time;
    }

    public String getOut_time() {
        return Out_time;
    }

    public void setOut_time(String out_time) {
        Out_time = out_time;
    }

    public String getWorked_Hours() {
        return Worked_Hours;
    }

    public void setWorked_Hours(String worked_Hours) {
        Worked_Hours = worked_Hours;
    }

    public String getAttendance_Status() {
        return Attendance_Status;
    }

    public void setAttendance_Status(String attendance_Status) {
        Attendance_Status = attendance_Status;
    }

    @Override
    public int getLayoutRes() {

        return R.layout.attend_row;
    }
}
