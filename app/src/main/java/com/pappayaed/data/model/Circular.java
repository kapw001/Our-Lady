package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pappayaed.R;
import com.pappayaed.RecyclerViewCommon.ViewLayout;
import com.pappayaed.common.Utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yasar on 20/3/18.
 */

public class Circular implements Serializable,ViewLayout {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("attachment_file_ids")
    @Expose
    private List<AttachmentFileId> attachmentFileIds = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("subject")
    @Expose
    private String subject;
    private final static long serialVersionUID = -1003447513153706132L;

    public String getDate() {
        String d = "";
        if (date != null && date.length() > 0) {

            d = Utils.convertDateToString(date, "yyyy-MM-dd", "dd/MM/yyyy");

        }

        return d;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<AttachmentFileId> getAttachmentFileIds() {
        return attachmentFileIds;
    }

    public void setAttachmentFileIds(List<AttachmentFileId> attachmentFileIds) {
        this.attachmentFileIds = attachmentFileIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.circular_row;
    }
}
