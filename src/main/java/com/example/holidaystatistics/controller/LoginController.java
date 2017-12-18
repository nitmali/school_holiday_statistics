package com.example.holidaystatistics.controller;

import com.example.holidaystatistics.entity.Student;
import com.example.holidaystatistics.model.UserFromModel;
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

    @PostMapping("/api/login")
    public UserFromModel login(UserFromModel userFromModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String adminUserName = "907777849";
        if (Objects.equals(userFromModel.getUserId(), adminUserName)) {
            String adminPassword = "86fe9e821fb48f55a9ca0f33c1e27d88";
            if (Objects.equals(userFromModel.getPassword(), adminPassword)) {
                session.setAttribute("usertype", "admin");
                userFromModel.setUserType("admin");
                userFromModel.setUserName("管理员");
                return userFromModel;
            } else {
                return userFromModel;
            }
        }

        Student student = studentRepository.findBystudentId(userFromModel.getUserId());
        if (Objects.equals(student.getPassword(), userFromModel.getPassword())) {
            session.setAttribute("usertype", "student");
            session.setAttribute("studentid", userFromModel.getUserId());
            userFromModel.setUserType("student");
            userFromModel.setUserName(student.getStudentName());
            return userFromModel;
        } else {
            return userFromModel;
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

    @RequestMapping(value = "/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpServletRequest request) {
        request.getSession().invalidate();
        modelAndView.setViewName("student/studentlogin");
        return modelAndView;
    }

}
