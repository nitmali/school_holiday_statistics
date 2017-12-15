package com.example.holidaystatistics.repository;

import com.example.holidaystatistics.entity.HolidayInfo;
import com.example.holidaystatistics.entity.HolidayPlan;
import com.example.holidaystatistics.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayPlanRepository extends CrudRepository<HolidayPlan, String> {
    /**
     * 匹配唯一学生和假期组合
     *
     * @param holidayInfo
     * @param student
     * @return HolidayPlan
     */
    HolidayPlan findAllByHolidayInfoAndStudent(HolidayInfo holidayInfo, Student student);
}
