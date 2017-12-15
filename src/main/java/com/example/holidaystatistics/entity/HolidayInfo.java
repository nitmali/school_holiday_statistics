package com.example.holidaystatistics.entity;

import com.example.holidaystatistics.model.HolidayInfoFromModel;

import javax.persistence.*;
import java.sql.Date;

/**
 * 假期信息表
 *
 * @author 马小生
 */
@Entity
public class HolidayInfo {
    @Id
    @GeneratedValue
    private Long holidayId;

    @Column(nullable = false)
    private String holidayName;

    @Column(nullable = false)
    private Date holidayStartTime;

    @Column(nullable = false)
    private Date holidayEndTime;

    @Column(length = 500)
    private String moreInfo;

    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private holiStatus holiStatus;

    public HolidayInfo() {
    }

    public HolidayInfo(HolidayInfoFromModel holidayInfoFromModel)
    {
        holidayName = holidayInfoFromModel.getHolidayName();
        holidayStartTime = holidayInfoFromModel.getHolidayStartTime();
        holidayEndTime = holidayInfoFromModel.getHolidayEndTime();
        moreInfo = holidayInfoFromModel.getMoreInfo();
        holiStatus = holidayInfoFromModel.getHoliStatus();
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

    public holiStatus getHoliStatus() {
        return holiStatus;
    }

    public void setHoliStatus(holiStatus holiStatus) {
        this.holiStatus = holiStatus;
    }

    public enum holiStatus {
        //假期开始
        START,
        //假期结束
        OVER
    }
}
