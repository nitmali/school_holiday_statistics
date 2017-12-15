package com.example.holidaystatistics.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 信息类Model
 *
 * @author 马小生
 */
@Component
public class MessageFromModel {
    @NotNull
    private boolean getmessage;

    private String success;

    private String warning;

    private String error;

    private String danger;

    private String other;

    public MessageFromModel() {
        this.getmessage = false;
        this.success = "success";
        this.warning = "warning";
        this.error = "error";
        this.danger = "danger";
        this.other = "other";
    }

    public boolean getGetmessage() {
        return getmessage;
    }

    public void setGetmessage(boolean getmessage) {
        this.getmessage = getmessage;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDanger() {
        return danger;
    }

    public void setDanger(String danger) {
        this.danger = danger;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
