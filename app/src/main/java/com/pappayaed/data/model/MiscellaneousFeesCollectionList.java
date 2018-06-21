package com.pappayaed.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;
import com.pappayaed.common.Utils;

import java.io.Serializable;

/**
 * Created by yasar on 23/4/18.
 */

public class MiscellaneousFeesCollectionList implements Serializable, Parcelable, ViewLayout {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("discount_amount")
    @Expose
    private Double discountAmount;
    @SerializedName("parent_name")
    @Expose
    private String parentName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("student_id")
    @Expose
    private Long studentId;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("grade_id")
    @Expose
    private Long gradeId;
    @SerializedName("fees_id")
    @Expose
    private Long feesId;
    @SerializedName("grade_name")
    @Expose
    private String gradeName;
    @SerializedName("actual_amount")
    @Expose
    private Double actualAmount;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("fees_name")
    @Expose
    private String feesName;
    @SerializedName("school_id")
    @Expose
    private Long schoolId;
    public final static Parcelable.Creator<MiscellaneousFeesCollectionList> CREATOR = new Creator<MiscellaneousFeesCollectionList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MiscellaneousFeesCollectionList createFromParcel(Parcel in) {
            return new MiscellaneousFeesCollectionList(in);
        }

        public MiscellaneousFeesCollectionList[] newArray(int size) {
            return (new MiscellaneousFeesCollectionList[size]);
        }

    };
    private final static long serialVersionUID = -9123103056252125754L;

    protected MiscellaneousFeesCollectionList(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.schoolName = ((String) in.readValue((String.class.getClassLoader())));
        this.discountAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.parentName = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.studentId = ((Long) in.readValue((Long.class.getClassLoader())));
        this.amount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.gradeId = ((Long) in.readValue((Long.class.getClassLoader())));
        this.feesId = ((Long) in.readValue((Long.class.getClassLoader())));
        this.gradeName = ((String) in.readValue((String.class.getClassLoader())));
        this.actualAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.studentName = ((String) in.readValue((String.class.getClassLoader())));
        this.feesName = ((String) in.readValue((String.class.getClassLoader())));
        this.schoolId = ((Long) in.readValue((Long.class.getClassLoader())));
    }

    public MiscellaneousFeesCollectionList() {
    }

    public String getStatus() {
        return Utils.capitalizeFirstLetter(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getFeesId() {
        return feesId;
    }

    public void setFeesId(Long feesId) {
        this.feesId = feesId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFeesName() {
        return feesName;
    }

    public void setFeesName(String feesName) {
        this.feesName = feesName;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(schoolName);
        dest.writeValue(discountAmount);
        dest.writeValue(parentName);
        dest.writeValue(name);
        dest.writeValue(studentId);
        dest.writeValue(amount);
        dest.writeValue(gradeId);
        dest.writeValue(feesId);
        dest.writeValue(gradeName);
        dest.writeValue(actualAmount);
        dest.writeValue(studentName);
        dest.writeValue(feesName);
        dest.writeValue(schoolId);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.standard_fees_row;
    }
}