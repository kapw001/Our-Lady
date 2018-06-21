package com.pappayaed.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;

import java.io.Serializable;

/**
 * Created by yasar on 20/4/18.
 */

public class FeesStructureIdO2m implements Serializable, Parcelable, ViewLayout {

    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("actual_amount")
    @Expose
    private Double actualAmount;
    @SerializedName("fees_name")
    @Expose
    private String feesName;
    @SerializedName("concession_amount")
    @Expose
    private Double concessionAmount;
    public final static Parcelable.Creator<FeesStructureIdO2m> CREATOR = new Creator<FeesStructureIdO2m>() {


        @SuppressWarnings({
                "unchecked"
        })
        public FeesStructureIdO2m createFromParcel(Parcel in) {
            return new FeesStructureIdO2m(in);
        }

        public FeesStructureIdO2m[] newArray(int size) {
            return (new FeesStructureIdO2m[size]);
        }

    };
    private final static long serialVersionUID = 6456559186501950408L;

    protected FeesStructureIdO2m(Parcel in) {
        this.description = ((Object) in.readValue((Object.class.getClassLoader())));
        this.id = ((Long) in.readValue((Long.class.getClassLoader())));
        this.amount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.actualAmount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.feesName = ((String) in.readValue((String.class.getClassLoader())));
        this.concessionAmount = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public FeesStructureIdO2m() {
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Boolean description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getFeesName() {
        return feesName;
    }

    public void setFeesName(String feesName) {
        this.feesName = feesName;
    }

    public Double getConcessionAmount() {
        return concessionAmount;
    }

    public void setConcessionAmount(Double concessionAmount) {
        this.concessionAmount = concessionAmount;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(description);
        dest.writeValue(id);
        dest.writeValue(amount);
        dest.writeValue(actualAmount);
        dest.writeValue(feesName);
        dest.writeValue(concessionAmount);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fees_row;
    }
}