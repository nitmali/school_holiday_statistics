package com.example.holidaystatistics.model;

import com.example.holidaystatistics.entity.HolidayInfo;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author 马小生
 */
public class HolidayInfoFromModel {

    @NotNull
    private String holidayName;

    @NotNull
    private Date holidayStartTime;

    @NotNull
    private Date holidayEndTime;

    private String moreInfo;

    @NotNull
    private HolidayInfo.holiStatus holiStatus;

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

    public HolidayInfo.holiStatus getHoliStatus() {
        return holiStatus;
    }

    public void setHoliStatus(HolidayInfo.holiStatus holiStatus) {
        this.holiStatus = holiStatus;
    }
}
