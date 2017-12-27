package com.example.holidaystatistics.model;

import com.example.holidaystatistics.entity.HolidayPlan;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 马小生
 */
@Component
public class HolidayAdditionFromModel {
    @NotNull
    private HolidayPlan holidayPlan;

    @NotNull
    private String addition;

    @NotNull
    private Date expectedBackTime;

    public HolidayPlan getHolidayPlan() {
        return holidayPlan;
    }

    public void setHolidayPlan(HolidayPlan holidayPlan) {
        this.holidayPlan = holidayPlan;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public Date getExpectedBackTime() {
        return expectedBackTime;
    }

    public void setExpectedBackTime(Date expectedBackTime) {
        this.expectedBackTime = expectedBackTime;
    }
}
