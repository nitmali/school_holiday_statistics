package com.holidaystatistics.entity;

import com.holidaystatistics.model.HolidayInfoModel;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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
    private holidayStatus holidayStatus;

    @OneToMany(cascade={CascadeType.REMOVE},mappedBy="holidayInfo")
    private Set<HolidayPlan> holidayPlanSet = new HashSet<HolidayPlan>();

    public HolidayInfo() {
    }

    public HolidayInfo(HolidayInfoModel holidayInfoModel)
    {
        holidayName = holidayInfoModel.getHolidayName();
        holidayStartTime = holidayInfoModel.getHolidayStartTime();
        holidayEndTime = holidayInfoModel.getHolidayEndTime();
        moreInfo = holidayInfoModel.getMoreInfo();
        holidayStatus = holidayInfoModel.getHolidayStatus();
    }

    public void setholidayInfoFromModel(HolidayInfoModel holidayInfoModel)
    {
        holidayName = holidayInfoModel.getHolidayName();
        holidayStartTime = holidayInfoModel.getHolidayStartTime();
        holidayEndTime = holidayInfoModel.getHolidayEndTime();
        moreInfo = holidayInfoModel.getMoreInfo();
        holidayStatus = holidayInfoModel.getHolidayStatus();
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

    public holidayStatus getHolidayStatus() {
        return holidayStatus;
    }

    public void setHolidayStatus(holidayStatus holidayStatus) {
        this.holidayStatus = holidayStatus;
    }

    public enum holidayStatus {
        //假期开始
        START,
        //假期结束
        OVER,
        //假期激活
        ACTIVATION
    }
}
