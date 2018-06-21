package com.pappayaed.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yasar on 14/6/18.
 */

public class Subject implements Parcelable
{

    @SerializedName("academic_year")
    @Expose
    private String academicYear;
    @SerializedName("grade")
    @Expose
    private String grade;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("edu_structure")
    @Expose
    private String eduStructure;
    @SerializedName("sub_primary_tutor")
    @Expose
    private String subPrimaryTutor;
    @SerializedName("subject")
    @Expose
    private String subject;
    public final static Parcelable.Creator<Subject> CREATOR = new Creator<Subject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Subject createFromParcel(Parcel in) {
            return new Subject(in);
        }

        public Subject[] newArray(int size) {
            return (new Subject[size]);
        }

    }
            ;

    protected Subject(Parcel in) {
        this.academicYear = ((String) in.readValue((String.class.getClassLoader())));
        this.grade = ((String) in.readValue((String.class.getClassLoader())));
        this.section = ((String) in.readValue((String.class.getClassLoader())));
        this.eduStructure = ((String) in.readValue((String.class.getClassLoader())));
        this.subPrimaryTutor = ((String) in.readValue((String.class.getClassLoader())));
        this.subject = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Subject() {
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getEduStructure() {
        return eduStructure;
    }

    public void setEduStructure(String eduStructure) {
        this.eduStructure = eduStructure;
    }

    public String getSubPrimaryTutor() {
        return subPrimaryTutor;
    }

    public void setSubPrimaryTutor(String subPrimaryTutor) {
        this.subPrimaryTutor = subPrimaryTutor;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(academicYear);
        dest.writeValue(grade);
        dest.writeValue(section);
        dest.writeValue(eduStructure);
        dest.writeValue(subPrimaryTutor);
        dest.writeValue(subject);
    }

    public int describeContents() {
        return 0;
    }

}

