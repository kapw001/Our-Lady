package com.pappayaed.dowloadservices;

/**
 * Created by yasar on 18/5/18.
 */

public class QueueData {

    private int id;

    public QueueData(int id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

}
