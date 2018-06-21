package com.pappayaed.data.model;

/**
 * Created by yasar on 1/6/17.
 */

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultResponse implements Serializable {

    @SerializedName("jsonrpc")
    @Expose
    private String jsonrpc;
    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("result")
    @Expose
    private Result result;

    private final static long serialVersionUID = 3775751890722231610L;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}