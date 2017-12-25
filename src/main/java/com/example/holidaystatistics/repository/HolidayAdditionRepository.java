package com.example.holidaystatistics.repository;

import com.example.holidaystatistics.entity.HolidayAddition;
import com.example.holidaystatistics.entity.HolidayInfo;
import com.example.holidaystatistics.entity.HolidayPlan;
import com.example.holidaystatistics.entity.Student;
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
