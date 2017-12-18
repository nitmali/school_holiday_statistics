package com.example.holidaystatistics.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author 马小生
 */
@Component
public class UserFromModel {

    @NotNull
    private String userId;

    @NotNull
    private String password;

    private String userName;

    private String userType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
