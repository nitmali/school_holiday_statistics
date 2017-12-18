package com.example.holidaystatistics.entity;

import javax.persistence.*;

/**
 * 学生信息表
 *
 * @author 马小生
 */
@Entity
public class Student {
    @Id
    @Column(nullable = false, length = 10)
    private String studentId;

    @Column(nullable = false,unique = true)
    private String studentName;

    @Column(nullable = false)
    private String password;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
