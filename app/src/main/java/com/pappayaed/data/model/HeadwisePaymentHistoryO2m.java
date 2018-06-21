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
 * Created by yasar on 20/4/18.
 */

public class HeadwisePaymentHistoryO2m implements Serializable, Parcelable, ViewLayout {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("bank_name")
    @Expose
    private Boolean bankName;
    @SerializedName("balance_amount")
    @Expose
    private Double balanceAmount;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("paid_amount")
    @Expose
    private Double paidAmount;
    @SerializedName("pay_amount")
    @Expose
    private Double payAmount;
    @SerializedName("cheque_dd_date")
    @Expose
    private Boolean chequeDdDate;
    @SerializedName("ifsc")
    @Expose
    private Boolean ifsc;
    @SerializedName("from_excess")
    @Expose
    private Long fromExcess;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("fees_id")
    @Expose
    private String feesId;
    @SerializedName("paid_date")
    @Expose
    private String paidDate;
    @SerializedName("neft_date")
    @Expose
    private Boolean neftDate;
    @SerializedName("narration")
    @Expose
    private String narration;
    @SerializedName("receipt_no")
    @Expose
    private String receiptNo;
    @SerializedName("account_no")
    @Expose
    private Boolean accountNo;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("payment_ref")
    @Expose
    private Boolean paymentRef;
    public final static Parcelable.Creator<HeadwisePaymentHistoryO2m> CREATOR = new Creator<HeadwisePaymentHistoryO2m>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HeadwisePaymentHistoryO2m createFromParcel(Parcel in) {
            return new HeadwisePaymentHistoryO2m(in);
        }

        public HeadwisePaymentHistoryO2m[] newArray(int size) {
            return (new HeadwisePaymentHistoryO2m[size]);
        }

    };
    private final static long serialVersionUID = -1777727430308286296L;

    protected HeadwisePaymentHistoryO2m(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.bankName = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.balanceAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.description = ((Object) in.readValue((Object.class.getClassLoader())));
        this.paidAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.payAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.chequeDdDate = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.ifsc = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.fromExcess = ((Long) in.readValue((Long.class.getClassLoader())));
        this.paymentMode = ((String) in.readValue((String.class.getClassLoader())));
        this.feesId = ((String) in.readValue((String.class.getClassLoader())));
        this.paidDate = ((String) in.readValue((String.class.getClassLoader())));
        this.neftDate = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.narration = ((String) in.readValue((String.class.getClassLoader())));
        this.receiptNo = ((String) in.readValue((String.class.getClassLoader())));
        this.accountNo = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        this.paymentRef = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public HeadwisePaymentHistoryO2m() {
    }

    public String getStatus() {
        return Utils.capitalizeFirstLetter(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getBankName() {
        return bankName;
    }

    public void setBankName(Boolean bankName) {
        this.bankName = bankName;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public Boolean getChequeDdDate() {
        return chequeDdDate;
    }

    public void setChequeDdDate(Boolean chequeDdDate) {
        this.chequeDdDate = chequeDdDate;
    }

    public Boolean getIfsc() {
        return ifsc;
    }

    public void setIfsc(Boolean ifsc) {
        this.ifsc = ifsc;
    }

    public Long getFromExcess() {
        return fromExcess;
    }

    public void setFromExcess(Long fromExcess) {
        this.fromExcess = fromExcess;
    }

    public String getPaymentMode() {
        return Utils.capitalizeFirstLetter(paymentMode);
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getFeesId() {
        return feesId;
    }

    public void setFeesId(String feesId) {
        this.feesId = feesId;
    }

    public String getPaidDate() {

        if (paidDate != null && paidDate.length() > 0)
            return Utils.convertDateToString(Utils.convertStringToDate(paidDate, "yyyy-MM-dd"), "dd/MM/yyyy");
        else return "";

    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public Boolean getNeftDate() {
        return neftDate;
    }

    public void setNeftDate(Boolean neftDate) {
        this.neftDate = neftDate;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Boolean getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Boolean accountNo) {
        this.accountNo = accountNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(Boolean paymentRef) {
        this.paymentRef = paymentRef;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(bankName);
        dest.writeValue(balanceAmount);
        dest.writeValue(description);
        dest.writeValue(paidAmount);
        dest.writeValue(payAmount);
        dest.writeValue(chequeDdDate);
        dest.writeValue(ifsc);
        dest.writeValue(fromExcess);
        dest.writeValue(paymentMode);
        dest.writeValue(feesId);
        dest.writeValue(paidDate);
        dest.writeValue(neftDate);
        dest.writeValue(narration);
        dest.writeValue(receiptNo);
        dest.writeValue(accountNo);
        dest.writeValue(id);
        dest.writeValue(paymentRef);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.history_row;
    }
}