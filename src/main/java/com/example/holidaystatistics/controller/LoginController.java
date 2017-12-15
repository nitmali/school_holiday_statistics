package com.example.holidaystatistics.controller;

import com.example.holidaystatistics.model.StudentFromModel;
import com.example.holidaystatistics.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author 马小生
 */
@RestController
public class LoginController {

    @Resource
    private StudentRepository studentRepository;

    @PostMapping("/api/login")
    public String login(StudentFromModel studentFromModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String adminUserName = "907777849";
        if (Objects.equals(studentFromModel.getStudentId(), adminUserName)) {
            String adminPassword = "maliloveqlt1314+";
            if (Objects.equals(studentFromModel.getPassword(), adminPassword)) {
                session.setAttribute("usertype", "admin");
                return "{\"msg\":\"admin\"}";
            } else {
                return "{\"msg\":\"password error\"}";
            }
        }

        if (studentRepository.findBystudentId(studentFromModel.getStudentId()) != null) {
            if (Objects.equals(studentRepository.findBystudentId(studentFromModel.getStudentId()).getPassword(),
                    studentFromModel.getPassword())) {
                session.setAttribute("usertype", "student");
                session.setAttribute("studentid", studentFromModel.getStudentId());
                return "{\"msg\":\"success\"}";
            } else {
                return "{\"msg\":\"password error\"}";
            }
        } else {
            return "{\"msg\":\"not find student\"}";
        }
    }

    @GetMapping("/api/getlogin")
    public String login(HttpServletRequest request) {
        String student = "student";
        String admin = "admin";
        String userType = (String) request.getSession().getAttribute("usertype");
        if (userType != null) {
            if (userType.equals(student)) {
                return student;
            } else {
                return admin;
            }
        } else {
            return "error";
        }
    }

}
