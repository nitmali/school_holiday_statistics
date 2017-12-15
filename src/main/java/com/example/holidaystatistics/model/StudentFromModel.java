package com.example.holidaystatistics.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author 马小生
 */
@Component
public class StudentFromModel {
    @NotNull
    private String studentId;

    @NotNull
    private String password;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
