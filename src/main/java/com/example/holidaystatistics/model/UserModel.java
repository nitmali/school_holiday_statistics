package com.example.holidaystatistics.model;

import com.example.holidaystatistics.entity.Student;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author 马小生
 */
@Component
public class UserModel {

    @NotNull
    private String userId;

    @NotNull
    private String password;

    private String userName;

    private String email;

    private String phone;

    private String userType;

    public UserModel() {
    }

    public UserModel(Student student) {
        this.userId = student.getStudentId();
        this.password = student.getPassword();
        this.userName = student.getStudentName();
        this.email = student.getEmail();
        this.phone = student.getPhone();
        this.userType = null;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
