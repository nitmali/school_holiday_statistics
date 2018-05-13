package com.example.holidaystatistics.model;

import com.example.holidaystatistics.entity.HolidayInfo;
import com.example.holidaystatistics.entity.HolidayPlan;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author 马小生
 */
@Component
public class HolidayPlanModel {

    @NotNull
    private  String holidayName;

    @NotNull
    private Date holidayStartTime;

    @NotNull
    private Date holidayEndTime;

    private Date leaveTime;

    private Date backTime;

    @NotNull
    private String whereToGo;

    public HolidayPlanModel(){}

    public HolidayPlanModel(HolidayInfo holidayInfo){
        holidayName = holidayInfo.getHolidayName();
        holidayStartTime = holidayInfo.getHolidayStartTime();
        holidayEndTime = holidayInfo.getHolidayEndTime();
    }

    public void setHolidayPlan (HolidayPlan holidayPlan){
        leaveTime = holidayPlan.getLeaveTime();
        backTime = holidayPlan.getBackTime();
        whereToGo = holidayPlan.getWhereToGo();
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public Date getHolidayStartTime() {
        return holidayStartTime;
    }

    public void setHolidayStartTime(Date holidaystartTime) {
        this.holidayStartTime = holidaystartTime;
    }

    public Date getHolidayEndTime() {
        return holidayEndTime;
    }

    public void setHolidayEndTime(Date holidayEndTime) {
        this.holidayEndTime = holidayEndTime;
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
