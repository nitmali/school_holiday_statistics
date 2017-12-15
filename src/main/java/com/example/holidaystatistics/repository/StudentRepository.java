package com.example.holidaystatistics.repository;

import com.example.holidaystatistics.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 马小生
 */
@Repository
public interface StudentRepository extends CrudRepository<Student,String> {
    /**
     * 根据学生学号查询学生信息
     *
     * @param studentid
     * @return Student
     */
    Student findBystudentId(String studentid);
}
