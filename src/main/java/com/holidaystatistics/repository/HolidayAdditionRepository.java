package com.holidaystatistics.repository;

import com.holidaystatistics.entity.HolidayAddition;
import com.holidaystatistics.entity.HolidayPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 马小生
 */
@Repository
public interface HolidayAdditionRepository extends CrudRepository<HolidayAddition, Long> {
    /**
     * 根据假期提交查询附加信息
     *
     * @param holidayPlan
     * @return HolidayAddition
     */
    HolidayAddition findByHolidayPlan(HolidayPlan holidayPlan);
}
