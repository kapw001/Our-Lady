package com.pappayaed.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pappayaed.common.Utils;

import java.io.Serializable;

/**
 * Created by yasar on 8/5/18.
 */

public class HeartrateList implements Serializable, Parcelable {

    @SerializedName("heartrate")
    @Expose
    private Long heartrate;
    @SerializedName("time")
    @Expose
    private String time;

    private String convertedTime;

    public final static Parcelable.Creator<HeartrateList> CREATOR = new Creator<HeartrateList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HeartrateList createFromParcel(Parcel in) {
            return new HeartrateList(in);
        }

        public HeartrateList[] newArray(int size) {
            return (new HeartrateList[size]);
        }

    };
    private final static long serialVersionUID = -6369865079168349013L;

    protected HeartrateList(Parcel in) {
        this.heartrate = ((Long) in.readValue((Long.class.getClassLoader())));
        this.time = ((String) in.readValue((String.class.getClassLoader())));
    }

    public HeartrateList() {
    }

    public Long getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(Long heartrate) {
        this.heartrate = heartrate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(heartrate);
        dest.writeValue(time);
    }

    public int describeContents() {
        return 0;
    }

    public String getConvertedTime() {

        return Utils.getTimeFromDate(time);
    }
}