package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yasar on 31/7/17.
 */

public class AttachmentDetail implements Serializable
{

    @SerializedName("attach_id")
    @Expose
    private Integer attachId;
    @SerializedName("mimetype")
    @Expose
    private String mimetype;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("attachment_data")
    @Expose
    private String attachmentData;
    private final static long serialVersionUID = -207066105559105006L;

    public Integer getAttachId() {
        return attachId;
    }

    public void setAttachId(Integer attachId) {
        this.attachId = attachId;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttachmentData() {
        return attachmentData;
    }

    public void setAttachmentData(String attachmentData) {
        this.attachmentData = attachmentData;
    }

}