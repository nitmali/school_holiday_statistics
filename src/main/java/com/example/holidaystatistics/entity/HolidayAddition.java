package com.example.holidaystatistics.entity;

import com.example.holidaystatistics.model.HolidayAdditionFromModel;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 马小生
 */
@Entity
@Table
public class HolidayAddition {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private HolidayPlan holidayPlan;

    @Column
    private String whereNow;

    @Column
    private String addition;

    @Column
    private Date expectedBackTime;

    public HolidayAddition() {
    }

    public void setHolidayAdditionFromModel(HolidayAdditionFromModel holidayAdditionFromModel) {
        holidayPlan = holidayAdditionFromModel.getHolidayPlan();
        whereNow= holidayAdditionFromModel.getWhereNow();
        addition = holidayAdditionFromModel.getAddition();
        expectedBackTime = holidayAdditionFromModel.getExpectedBackTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HolidayPlan getHolidayPlan() {
        return holidayPlan;
    }

    public void setHolidayPlan(HolidayPlan holidayPlan) {
        this.holidayPlan = holidayPlan;
    }

    public String getWhereNow() {
        return whereNow;
    }

    public void setWhereNow(String whereNow) {
        this.whereNow = whereNow;
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
