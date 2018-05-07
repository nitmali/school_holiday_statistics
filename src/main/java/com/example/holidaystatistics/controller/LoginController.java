package com.example.holidaystatistics.controller;

import com.example.holidaystatistics.entity.Manager;
import com.example.holidaystatistics.entity.Student;
import com.example.holidaystatistics.model.UserFromModel;
import com.example.holidaystatistics.repository.ManagerRepository;
import com.example.holidaystatistics.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    @Resource
    private ManagerRepository managerRepository;

    @PostMapping("/login")
    public UserFromModel login(UserFromModel userFromModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String studentType = "student";
        String managerType = "manager";
        if (studentType.equals(userFromModel.getUserType())) {
            Student student = studentRepository.findBystudentId(userFromModel.getUserId());
            if (student == null) {
                return userFromModel;
            }
            if (userFromModel.getPassword().equals(student.getPassword())) {
                userFromModel.setUserType("student");
                session.setAttribute("userType", "student");
                session.setAttribute("studentId", userFromModel.getUserId());
                userFromModel.setUserName(student.getStudentName());
                return userFromModel;
            } else {
                return userFromModel;
            }
        }
        if (managerType.equals(userFromModel.getUserType())) {
            Manager manager = managerRepository.findManagerByManagerId(userFromModel.getUserId());
            if (manager == null) {
                return userFromModel;
            }
            if (userFromModel.getPassword().equals(manager.getPassword())) {
                userFromModel.setUserType("manager");
                session.setAttribute("userType", "manager");
                session.setAttribute("studentId", userFromModel.getUserId());
                userFromModel.setUserName(manager.getManagerName());
                return userFromModel;
            } else {
                return userFromModel;
            }
        }
        userFromModel.setUserType(null);
        return userFromModel;
    }

    @GetMapping("/get_login")
    public String login(HttpServletRequest request) {
        String student = "student";
        String manager = "manager";
        String userType = (String) request.getSession().getAttribute("userType");
        if (userType != null) {
            if (userType.equals(student)) {
                return student;
            } else {
                return manager;
            }
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/public/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpServletRequest request) {
        request.getSession().invalidate();
        modelAndView.setViewName("student/student_login");
        return modelAndView;
    }

    @PostMapping("/public/change_password")
    public String change(String oldPassword, String newPassword, HttpServletRequest request) {
        String studentId = (String) request.getSession().getAttribute("studentId");
        Student student = studentRepository.findBystudentId(studentId);
        if (!Objects.equals(student.getPassword(), oldPassword)) {
            return "old error";
        } else {
            student.setPassword(newPassword);
            studentRepository.save(student);
            return "success";
        }
    }

}
