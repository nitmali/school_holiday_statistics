package com.example.holidaystatistics.repository;

import com.example.holidaystatistics.entity.ProfessionalClass;
import com.example.holidaystatistics.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 马小生
 */
@Repository
public interface StudentRepository extends CrudRepository<Student,String> {
    /**
     * 根据学生学号查询学生信息
     *
     * @param studentId
     * @return Student
     */
    Student findBystudentId(String studentId);

    Student findBystudentName(String studentName);

    Student findStudentByEmail(String email);

    List<Student> findAllByProfessionalClass_Id(Long professionalClassId);
    
}
