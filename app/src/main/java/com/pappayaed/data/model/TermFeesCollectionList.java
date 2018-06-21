package com.pappayaed.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pappayaed.common.Utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yasar on 19/12/17.
 */

public class TermFeesCollectionList implements Serializable, Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("term_name")
    @Expose
    private String termName;
    @SerializedName("fees_payment_history_list")
    @Expose
    private List<FeesPaymentHistoryList> feesPaymentHistoryList = null;
    @SerializedName("headwise_payment_history_o2m")
    @Expose
    private List<HeadwisePaymentHistoryO2m> headwisePaymentHistoryO2m = null;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("fees_structure_id_o2m")
    @Expose
    private List<FeesStructureIdO2m> feesStructureIdO2m = null;
    @SerializedName("parent_name")
    @Expose
    private String parentName;
    @SerializedName("student_id")
    @Expose
    private Long studentId;
    @SerializedName("grade_id")
    @Expose
    private Long gradeId;
    @SerializedName("amount")
    @Expose
    private Long amount;
    @SerializedName("grade_name")
    @Expose
    private String gradeName;
    @SerializedName("term_id")
    @Expose
    private Long termId;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    private final static long serialVersionUID = -9095139296465538489L;


    protected TermFeesCollectionList(Parcel in) {
        status = in.readString();
        dueDate = in.readString();
        termName = in.readString();
        feesPaymentHistoryList = in.createTypedArrayList(FeesPaymentHistoryList.CREATOR);
        headwisePaymentHistoryO2m = in.createTypedArrayList(HeadwisePaymentHistoryO2m.CREATOR);
        feesStructureIdO2m = in.createTypedArrayList(FeesStructureIdO2m.CREATOR);
        parentName = in.readString();
        studentId = in.readLong();
        amount = in.readLong();
        gradeName = in.readString();
        studentName = in.readString();


    }

    public static final Creator<TermFeesCollectionList> CREATOR = new Creator<TermFeesCollectionList>() {
        @Override
        public TermFeesCollectionList createFromParcel(Parcel in) {
            return new TermFeesCollectionList(in);
        }

        @Override
        public TermFeesCollectionList[] newArray(int size) {
            return new TermFeesCollectionList[size];
        }
    };

    public String getStatus() {
        return Utils.capitalizeFirstLetter(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        if (dueDate != null && dueDate.length() > 0)
            return Utils.convertDateToString(Utils.convertStringToDate(dueDate, "yyyy-MM-dd"), "dd/MM/yyyy");
        else return "";
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public List<FeesPaymentHistoryList> getFeesPaymentHistoryList() {
        return feesPaymentHistoryList;
    }

    public void setFeesPaymentHistoryList(List<FeesPaymentHistoryList> feesPaymentHistoryList) {
        this.feesPaymentHistoryList = feesPaymentHistoryList;
    }

    public List<HeadwisePaymentHistoryO2m> getHeadwisePaymentHistoryO2m() {
        return headwisePaymentHistoryO2m;
    }

    public void setHeadwisePaymentHistoryO2m(List<HeadwisePaymentHistoryO2m> headwisePaymentHistoryO2m) {
        this.headwisePaymentHistoryO2m = headwisePaymentHistoryO2m;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FeesStructureIdO2m> getFeesStructureIdO2m() {
        return feesStructureIdO2m;
    }

    public void setFeesStructureIdO2m(List<FeesStructureIdO2m> feesStructureIdO2m) {
        this.feesStructureIdO2m = feesStructureIdO2m;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(dueDate);
        dest.writeString(termName);
        dest.writeTypedList(feesPaymentHistoryList);
        dest.writeTypedList(headwisePaymentHistoryO2m);
        dest.writeTypedList(feesStructureIdO2m);
        dest.writeString(parentName);
        dest.writeLong(studentId);
        dest.writeLong(amount);
        dest.writeString(gradeName);
        dest.writeString(studentName);


    }
}