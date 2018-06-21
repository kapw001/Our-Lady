package com.pappayaed.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yasar on 14/6/17.
 */

public class AttachmentFileId implements Serializable {

    @SerializedName("attach_id")
    @Expose
    private Integer attachId;
    @SerializedName("mimetype")
    @Expose
    private String mimetype;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = 8065460399334957976L;

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
}
