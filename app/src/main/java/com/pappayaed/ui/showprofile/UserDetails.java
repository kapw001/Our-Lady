package com.pappayaed.ui.showprofile;

import com.pappayaed.data.model.AttachmentFileId;

import java.util.List;

/**
 * Created by yasar on 5/5/17.
 */

public class UserDetails {

    public static final int HEADER = 0;
    public static final int ROW = 1;

    private String mName;
    private String mTitle;
    private String attachID;

    private int imgID;


    public List<AttachmentFileId> getAttachmentFileIdList() {
        return attachmentFileIdList;
    }

    public void setAttachmentFileIdList(List<AttachmentFileId> attachmentFileIdList) {
        this.attachmentFileIdList = attachmentFileIdList;
    }

    private String profileImage;
    private int mType;
    private StudentList studentList;
    private List<AttachmentFileId> attachmentFileIdList;

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String mimetype;
    private String fileName;

    public String getAttachID() {
        return attachID;
    }

    public void setAttachID(String attachID) {
        this.attachID = attachID;
    }

    public UserDetails(String mTitle, String mName, String profileImage, int mType) {
        this.mName = mName;
        this.mTitle = mTitle;
        this.profileImage = profileImage;
        this.mType = mType;

    }

    public UserDetails(String mTitle, String mName, String profileImage, int mType, int img) {
        this.mName = mName;
        this.mTitle = mTitle;
        this.profileImage = profileImage;
        this.mType = mType;
        this.imgID = img;

    }

    public UserDetails(String mTitle, String mName, String attachID, String att, int mType) {
        this.mName = mName;
        this.mTitle = mTitle;
        this.attachID = attachID;
        this.mType = mType;

    }

    public UserDetails(String mTitle, String mName, String profileImage, String mimetype, String fileName, int mType) {
        this.mName = mName;
        this.mTitle = mTitle;
        this.profileImage = profileImage;
        this.mType = mType;
        this.mimetype = mimetype;
        this.fileName = fileName;

    }

    public UserDetails(String mTitle, String mName, String profileImage, StudentList studentList, int mType) {
        this.mName = mName;
        this.mTitle = mTitle;
        this.profileImage = profileImage;
        this.mType = mType;
        this.studentList = studentList;
    }

    public UserDetails(StudentList studentList, int mType) {
        this.studentList = studentList;
        this.mType = mType;
    }

    public UserDetails(String mTitle, String mName, int mType, List<AttachmentFileId> attachmentFileIdList) {
        this.attachmentFileIdList = attachmentFileIdList;
        this.mType = mType;
        this.mName = mName;
        this.mTitle = mTitle;

    }

    public StudentList getStudentList() {
        return studentList;
    }

    public void setStudentList(StudentList studentList) {
        this.studentList = studentList;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getmType() {
        return mType;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "mName='" + mName + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", mType=" + mType +
                ", studentList=" + studentList +
                ", mimetype='" + mimetype + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}
