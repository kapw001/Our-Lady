package com.pappayaed.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yasar on 22/12/17.
 */

public class FeesPaymentHistoryList implements Serializable, Parcelable {

    @SerializedName("bill_no")
    @Expose
    private String billNo;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("paid_date")
    @Expose
    private String paidDate;
    @SerializedName("paid_amount")
    @Expose
    private Double paidAmount;
    @SerializedName("return_amount")
    @Expose
    private Double returnAmount;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("cheque_dd_date")
    @Expose
    private String chequeDdDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("payment_ref")
    @Expose
    private String paymentRef;
    public final static Parcelable.Creator<FeesPaymentHistoryList> CREATOR = new Creator<FeesPaymentHistoryList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public FeesPaymentHistoryList createFromParcel(Parcel in) {
            return new FeesPaymentHistoryList(in);
        }

        public FeesPaymentHistoryList[] newArray(int size) {
            return (new FeesPaymentHistoryList[size]);
        }

    };
    private final static long serialVersionUID = 624391941181578018L;

    protected FeesPaymentHistoryList(Parcel in) {
        this.billNo = ((String) in.readValue((String.class.getClassLoader())));
        this.bankName = ((String) in.readValue((Boolean.class.getClassLoader())));
        this.paidDate = ((String) in.readValue((String.class.getClassLoader())));
        this.paidAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.returnAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.paymentMode = ((String) in.readValue((String.class.getClassLoader())));
        this.chequeDdDate = ((String) in.readValue((Boolean.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentRef = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Long) in.readValue((Long.class.getClassLoader())));
    }

    public FeesPaymentHistoryList() {
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(Double returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getChequeDdDate() {
        return chequeDdDate;
    }

    public void setChequeDdDate(String chequeDdDate) {
        this.chequeDdDate = chequeDdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(billNo);
        dest.writeValue(bankName);
        dest.writeValue(paidDate);
        dest.writeValue(paidAmount);
        dest.writeValue(returnAmount);
        dest.writeValue(paymentMode);
        dest.writeValue(chequeDdDate);
        dest.writeValue(status);
        dest.writeValue(paymentRef);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}