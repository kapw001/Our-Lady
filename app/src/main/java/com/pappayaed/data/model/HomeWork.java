package com.pappayaed.data.model;

import android.view.View;

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

public class HomeWork implements Serializable, ViewLayout {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("attachment_file_ids")
    @Expose
    private List<AttachmentFileId> attachmentFileIds = null;
    @SerializedName("subject_id")
    @Expose
    private Long subjectId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("subject_name")
    @Expose
    private String subjectName;
    private final static long serialVersionUID = 6628099335142101504L;

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

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.homework_row;
    }
}
