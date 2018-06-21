package com.pappayaed.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yasar on 14/6/18.
 */

public class Attendance implements Parcelable
{

    @SerializedName("out_time")
    @Expose
    private String outTime;
    @SerializedName("employee_id")
    @Expose
    private String employeeId;
    @SerializedName("attendance_status")
    @Expose
    private String attendanceStatus;
    @SerializedName("in_time")
    @Expose
    private String inTime;
    @SerializedName("attendance_date")
    @Expose
    private String attendanceDate;
    @SerializedName("time_diff")
    @Expose
    private String timeDiff;
    public final static Parcelable.Creator<Attendance> CREATOR = new Creator<Attendance>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Attendance createFromParcel(Parcel in) {
            return new Attendance(in);
        }

        public Attendance[] newArray(int size) {
            return (new Attendance[size]);
        }

    }
            ;

    protected Attendance(Parcel in) {
        this.outTime = ((String) in.readValue((String.class.getClassLoader())));
        this.employeeId = ((String) in.readValue((String.class.getClassLoader())));
        this.attendanceStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.inTime = ((String) in.readValue((String.class.getClassLoader())));
        this.attendanceDate = ((String) in.readValue((String.class.getClassLoader())));
        this.timeDiff = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Attendance() {
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getTimeDiff() {
        return timeDiff;
    }

    public void setTimeDiff(String timeDiff) {
        this.timeDiff = timeDiff;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(outTime);
        dest.writeValue(employeeId);
        dest.writeValue(attendanceStatus);
        dest.writeValue(inTime);
        dest.writeValue(attendanceDate);
        dest.writeValue(timeDiff);
    }

    public int describeContents() {
        return 0;
    }

}