package com.example.holidaystatistics.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author 马小生
 */
@Component
public class HolidayPlanFormModel {

    @NotNull
    private String whereToGo;

    private Date leaveTime;

    private Date backTime;

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
