package com.example.administrator.penapp.GSON_Class;

import java.util.List;

public class gupiao_get {
    private String resultcode;
    private String reason;
    private List<gupiao_result> result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<gupiao_result> getResult() {
        return result;
    }

    public void setResult(List<gupiao_result> result) {
        this.result = result;
    }
}
