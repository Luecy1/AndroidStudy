package com.example.you.myapplication.zipcode;

/**
 * Created by you on 2017/11/24.
 */

public class ZipcodeEntity {

    private String message;
    private String status;

    private ResultEntity[] results;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResultEntity[] getResults() {
        return results;
    }

    public void setResults(ResultEntity[] results) {
        this.results = results;
    }
}
