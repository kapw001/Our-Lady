package com.pappayaed.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;

import java.io.Serializable;

/**
 * Created by yasar on 19/12/17.
 */

public class AttendanceList implements Serializable, Parcelable, ViewLayout {

    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("present_morning")
    @Expose
    private Boolean presentMorning;
    @SerializedName("attendance_date")
    @Expose
    private String attendanceDate;

    private int color;

    public final static Parcelable.Creator<AttendanceList> CREATOR = new Creator<AttendanceList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AttendanceList createFromParcel(Parcel in) {
            return new AttendanceList(in);
        }

        public AttendanceList[] newArray(int size) {
            return (new AttendanceList[size]);
        }

    };
    private final static long serialVersionUID = 5666167396705791940L;

    protected AttendanceList(Parcel in) {
        this.remark = ((String) in.readValue((Boolean.class.getClassLoader())));
        this.presentMorning = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.attendanceDate = ((String) in.readValue((String.class.getClassLoader())));
        this.color = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public AttendanceList() {
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getPresentMorning() {
        return presentMorning;
    }

    public void setPresentMorning(Boolean presentMorning) {
        this.presentMorning = presentMorning;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(remark);
        dest.writeValue(presentMorning);
        dest.writeValue(attendanceDate);
        dest.writeValue(color);
    }

    public int describeContents() {
        return 0;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.attendance_row;
    }
}
