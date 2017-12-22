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

    @PostMapping("/login")
    public UserFromModel login(UserFromModel userFromModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String adminUserName = "管理员";
        Student student = studentRepository.findBystudentId(userFromModel.getUserId());
        if(student == null)
        {
            return userFromModel;
        }
        if (Objects.equals(student.getPassword(), userFromModel.getPassword())) {
            if (Objects.equals(student.getStudentName(), adminUserName)) {
                userFromModel.setUserType("admin");
                session.setAttribute("usertype", "admin");
            } else {
                userFromModel.setUserType("student");
                session.setAttribute("usertype", "student");
            }
            session.setAttribute("studentid", userFromModel.getUserId());
            userFromModel.setUserName(student.getStudentName());
            return userFromModel;

        } else {
            return userFromModel;
        }
    }

    @GetMapping("/getlogin")
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

    @PostMapping("/change_password")
    public String change( String oldPassword,String newPassword,HttpServletRequest request) {
        String studentId = (String) request.getSession().getAttribute("studentid");
        Student student = studentRepository.findBystudentId(studentId);
        if(!Objects.equals(student.getPassword(), oldPassword))
        {
            return "old error";
        }else
        {
            student.setPassword(newPassword);
            studentRepository.save(student);
            return "success";
        }
    }

}
