package com.pappayaed.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yasar on 14/6/18.
 */

public class Qualification implements Parcelable
{

    @SerializedName("university")
    @Expose
    private String university;
    @SerializedName("institute_of_study")
    @Expose
    private String instituteOfStudy;
    @SerializedName("year_of_completion")
    @Expose
    private String yearOfCompletion;
    @SerializedName("degree")
    @Expose
    private String degree;
    public final static Parcelable.Creator<Qualification> CREATOR = new Creator<Qualification>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Qualification createFromParcel(Parcel in) {
            return new Qualification(in);
        }

        public Qualification[] newArray(int size) {
            return (new Qualification[size]);
        }

    }
            ;

    protected Qualification(Parcel in) {
        this.university = ((String) in.readValue((String.class.getClassLoader())));
        this.instituteOfStudy = ((String) in.readValue((String.class.getClassLoader())));
        this.yearOfCompletion = ((String) in.readValue((String.class.getClassLoader())));
        this.degree = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Qualification() {
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getInstituteOfStudy() {
        return instituteOfStudy;
    }

    public void setInstituteOfStudy(String instituteOfStudy) {
        this.instituteOfStudy = instituteOfStudy;
    }

    public String getYearOfCompletion() {
        return yearOfCompletion;
    }

    public void setYearOfCompletion(String yearOfCompletion) {
        this.yearOfCompletion = yearOfCompletion;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(university);
        dest.writeValue(instituteOfStudy);
        dest.writeValue(yearOfCompletion);
        dest.writeValue(degree);
    }

    public int describeContents() {
        return 0;
    }

}
