package com.holidaystatistics.model;

import com.holidaystatistics.entity.HolidayPlan;
import com.holidaystatistics.entity.Student;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 马小生
 */
@Component
public class HolidayPlanOfStudentModel {
    @NotNull
    private String studentName;

    @NotNull
    private String studentId;

    private String phone;

    @NotNull
    private String whereToGo;

    private Date leaveTime;

    private Date backTime;

    public HolidayPlanOfStudentModel() {
    }

    public HolidayPlanOfStudentModel(HolidayPlan holidayPlan, Student student) {
        if (holidayPlan != null)
        {
            studentName = holidayPlan.getStudent().getStudentName();
            studentId = holidayPlan.getStudent().getStudentId();
            phone = holidayPlan.getStudent().getPhone();
            whereToGo = holidayPlan.getWhereToGo();
            leaveTime = holidayPlan.getLeaveTime();
            backTime = holidayPlan.getBackTime();
        }else {
            studentName = student.getStudentName();
            studentId = student.getStudentId();
            phone = student.getPhone();
        }
    }


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
