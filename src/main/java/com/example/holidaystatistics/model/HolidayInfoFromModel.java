package com.example.holidaystatistics.model;

import com.example.holidaystatistics.entity.HolidayInfo;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author 马小生
 */
public class HolidayInfoFromModel {

    private Long holidayId;

    @NotNull
    private String holidayName;

    @NotNull
    private Date holidayStartTime;

    @NotNull
    private Date holidayEndTime;

    private String moreInfo;

    @NotNull
    private HolidayInfo.holidayStatus holidayStatus;

    public HolidayInfoFromModel(){}

    public HolidayInfoFromModel(HolidayInfo holidayInfo)
    {
        holidayId = holidayInfo.getHolidayId();
        holidayName = holidayInfo.getHolidayName();
        holidayStartTime = holidayInfo.getHolidayStartTime();
        holidayEndTime = holidayInfo.getHolidayEndTime();
        moreInfo = holidayInfo.getMoreInfo();
        holidayStatus = holidayInfo.getHolidayStatus();
    }

    public Long getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Long holidayId) {
        this.holidayId = holidayId;
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

    public void setHolidayStartTime(Date holidayStartTime) {
        this.holidayStartTime = holidayStartTime;
    }

    public Date getHolidayEndTime() {
        return holidayEndTime;
    }

    public void setHolidayEndTime(Date holidayEndTime) {
        this.holidayEndTime = holidayEndTime;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public HolidayInfo.holidayStatus getHolidayStatus() {
        return holidayStatus;
    }

    public void setHolidayStatus(HolidayInfo.holidayStatus holidayStatus) {
        this.holidayStatus = holidayStatus;
    }
}
