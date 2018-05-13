package com.example.holidaystatistics.entity;

import com.example.holidaystatistics.model.HolidayPlanModel;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 学生提交的离校表
 *
 * @author 马小生
 */
@Entity
public class HolidayPlan {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Student student;

    @Column(nullable = false, length = 32)
    private String whereToGo;

    @Column
    private Date leaveTime;

    @Column
    private Date backTime;

    @Column(length = 128)
    private String ip;

    @Column
    private Timestamp submitTime;

    @ManyToOne
    private HolidayInfo holidayInfo;

    public HolidayPlan() {

    }

    public HolidayPlan(HolidayPlanModel holidayPlanModel) {
        whereToGo = holidayPlanModel.getWhereToGo();
        leaveTime = holidayPlanModel.getLeaveTime();
        backTime = holidayPlanModel.getBackTime();
    }

    public void setHolidayPlanFormModel(HolidayPlanModel holidayPlanModel) {
        whereToGo = holidayPlanModel.getWhereToGo();
        leaveTime = holidayPlanModel.getLeaveTime();
        backTime = holidayPlanModel.getBackTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getWhereToGo() {
        return whereToGo;
    }

    public void setWhereToGo(String whereToGo) {
        this.whereToGo = whereToGo;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSubmitTime() {
        return submitTime.toString().substring(0,19);
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    public HolidayInfo getHolidayInfo() {
        return holidayInfo;
    }

    public void setHolidayInfo(HolidayInfo holidayInfo) {
        this.holidayInfo = holidayInfo;
    }
}
