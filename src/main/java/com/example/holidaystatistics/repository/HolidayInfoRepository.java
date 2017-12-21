package com.example.holidaystatistics.repository;

import com.example.holidaystatistics.entity.HolidayInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 马小生
 */
@Repository
public interface HolidayInfoRepository extends CrudRepository<HolidayInfo,Long> {

    /**
     * 现阶段态查询
     *
     * @param holidayStatus
     * @return HolidayInfo
     */
    HolidayInfo findAllByholidayStatus(HolidayInfo.holidayStatus holidayStatus);

    /**
     * 根据假期的状态查询所有假期
     *
     * @param holidayStatus
     * @return HolidayInfo
     */
    List<HolidayInfo> findAllByHolidayStatus(HolidayInfo.holidayStatus holidayStatus);

    /**
     * 假期名查询
     *
     * @param holidayName
     * @return HolidayInfo
     */
    HolidayInfo findByHolidayName(String holidayName);
}
