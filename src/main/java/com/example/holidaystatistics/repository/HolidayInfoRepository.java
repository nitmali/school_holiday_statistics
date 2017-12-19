package com.example.holidaystatistics.repository;

import com.example.holidaystatistics.entity.HolidayInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 马小生
 */
@Repository
public interface HolidayInfoRepository extends CrudRepository<HolidayInfo,Long> {

    /**
     * 根据假期的状态查询
     *
     * @param holidayStatus
     * @return HolidayInfo
     */
    HolidayInfo findByholidayStatus(HolidayInfo.holidayStatus holidayStatus);
}
