package com.holidaystatistics.controller;

import com.holidaystatistics.entity.Student;
import com.holidaystatistics.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nitmali@126.com
 * @date 2018/5/8 19:15
 */

@RestController
public class OtherController {
    @Resource
    private StudentRepository studentRepository;

    @GetMapping("/openApi/getStudentName")
    public Student getStudentInfoName(String studentName) {
        return studentRepository.findBystudentName(studentName);
    }

    @GetMapping("/openApi/getStudentId")
    public Student getStudentInfoId(String studentId) {
        return studentRepository.findBystudentId(studentId);
    }

    @GetMapping("/openApi/getStudentAll")
    public List<Student> getStudentInfoAll(){
        return (List<Student>) studentRepository.findAll();
    }
}
