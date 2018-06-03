package com.holidaystatistics.controller;

import com.holidaystatistics.entity.EmailToken;
import com.holidaystatistics.entity.Manager;
import com.holidaystatistics.entity.Student;
import com.holidaystatistics.model.UserModel;
import com.holidaystatistics.repository.EmailTokenRepository;
import com.holidaystatistics.repository.ManagerRepository;
import com.holidaystatistics.repository.StudentRepository;
import com.holidaystatistics.service.EmailService.Md5Token;
import com.holidaystatistics.service.EmailService.SendEmail;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

/**
 * @author 马小生
 */
@RestController
public class UserController {

    @Resource
    private StudentRepository studentRepository;

    @Resource
    private ManagerRepository managerRepository;

    @Resource
    private EmailTokenRepository emailTokenRepository;

    @Resource
    private Md5Token md5Token;

    @Resource
    private SendEmail sendEmail;

    @PostMapping("/login")
    public UserModel login(@RequestBody UserModel userModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String studentType = "student";
        String managerType = "manager";
        if (studentType.equals(userModel.getUserType())) {
            Student student = studentRepository.findBystudentId(userModel.getUserId());
            if (student == null) {
                return userModel;
            }
            if (userModel.getPassword().equals(student.getPassword())) {
                userModel.setUserType("student");
                session.setAttribute("userType", "student");
                session.setAttribute("studentId", userModel.getUserId());
                userModel.setUserName(student.getStudentName());
                return userModel;
            } else {
                return userModel;
            }
        }
        if (managerType.equals(userModel.getUserType())) {
            Manager manager = managerRepository.findManagerByManagerId(userModel.getUserId());
            if (manager == null) {
                return userModel;
            }
            if (userModel.getPassword().equals(manager.getPassword())) {
                userModel.setUserType("manager");
                session.setAttribute("userType", "manager");
                session.setAttribute("managerId", userModel.getUserId());
                userModel.setUserName(manager.getManagerName());
                return userModel;
            } else {
                return userModel;
            }
        }
        userModel.setUserType(null);
        return userModel;
    }

    @GetMapping("/openApi/get_login")
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

    @GetMapping("/student/get_student_personal")
    public UserModel getStudentPersonal(HttpServletRequest request){
        HttpSession session = request.getSession();
        Student student = studentRepository.findBystudentId((String) session.getAttribute("studentId"));

        if(student != null){
            UserModel userModel = new UserModel(student);
            userModel.setPassword(null);
            return userModel;
        }else {
            return null;
        }
    }

    @GetMapping("/student/set_student_phone")
    public String setStudentPersonal(String phone,HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("studentId");
        Student student = studentRepository.findBystudentId(userId);
        student.setPhone(phone);
        studentRepository.save(student);
        return "success";
    }

    //restPassword：重置密码邮件；bindEmail：绑定邮箱邮件
    @GetMapping("/openApi/send_email")
    public String sendForgetPasswordMail(HttpServletRequest request,String email, String emailType)
            throws MessagingException {
        String restPassword = "restPassword";
        String bindEmail = "bindEmail";
        if (restPassword.equals(emailType)) {
            return sendEmail.sendRestPasswordEmail(email, EmailToken.EmailType.REST_PASSWORD);
        } else if (bindEmail.equals(emailType)) {
            if (studentRepository.findStudentByEmail(email) != null) {
                return "emil is being use";
            } else {
                HttpSession session = request.getSession();
                String studentId = (String) session.getAttribute("studentId");
                if (studentId != null){
                    return sendEmail.sendBindEmail(studentId,email, EmailToken.EmailType.BIND_EMAIL);
                }else {
                    return "please login again";
                }

            }
        } else {
            return "error";
        }
    }

    @GetMapping("/public/reset_password")
    public ModelAndView resetPassword(ModelAndView modelAndView, String email, String token) {
        EmailToken emailToken;
        if (email != null && token != null) {
            emailToken = emailTokenRepository.findEmailTokenByEmail(email);
            if (emailToken == null) {
                System.err.println("令牌未找到");
                modelAndView.setViewName("public/reset_password_invalid");
                return modelAndView;
            }
            Date thisTime = new Date();
            Long timeDifference = 600000L;
            if (thisTime.getTime() - Long.parseLong(emailToken.getSendTime()) > timeDifference) {
                System.err.println("连接超时");
                emailTokenRepository.delete(emailToken);
                modelAndView.setViewName("public/reset_password_invalid");
                return modelAndView;
            }
            String getToken = md5Token
                    .getMD5(emailToken.getUserId() + emailToken.getEmail() + emailToken.getSendTime());
            if (token.equals(getToken)) {
                modelAndView.setViewName("public/reset_password");
                modelAndView.addObject("userName", studentRepository
                        .findBystudentId(emailToken.getUserId()).getStudentName());
                modelAndView.addObject("userId", studentRepository
                        .findBystudentId(emailToken.getUserId()).getStudentId());
            } else {
                System.err.println("令牌错误");
                modelAndView.setViewName("public/reset_password_invalid");
                return modelAndView;
            }
        } else {
            modelAndView.setViewName("public/reset_password_invalid");
            return modelAndView;
        }
        return modelAndView;
    }

    @GetMapping("/openApi/reset_password")
    public String resetPassword(String userId, String password) {
        Student student = studentRepository.findBystudentId(userId);
        EmailToken emailToken = emailTokenRepository.findEmailTokenByEmail(student.getEmail());
        if (emailToken != null) {
            student.setPassword(password);
            studentRepository.save(student);
            emailTokenRepository.delete(emailToken);
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping("/openApi/bind_email")
    public String bindEmail( HttpServletRequest request,String email, String token) {
        HttpSession session = request.getSession();
        Student student = studentRepository.findBystudentId((String) session.getAttribute("studentId"));
        EmailToken emailToken = emailTokenRepository.findEmailTokenByEmail(email);
        if (emailToken == null){
            return "time out";
        }else if(!emailToken.getUserId().equals(student.getStudentId())){
            return "error";
        }
        Date thisTime = new Date();
        Long timeDifference = 600000L;
        if (thisTime.getTime() - Long.parseLong(emailToken.getSendTime()) > timeDifference) {
            System.err.println("验证码超时");
            emailTokenRepository.delete(emailToken);
            return "time out";
        }
        String getToken =
                md5Token.getMD5(student.getStudentId() + emailToken.getSendTime()).substring(14, 20);

        token = token.toUpperCase();
        getToken = getToken.toUpperCase();
        if (getToken.equals(token)){
            student.setEmail(email);
            studentRepository.save(student);
            return "success";
        }else {
            System.err.println("error token:"+getToken);
            System.err.println("success token:"+token);
            return "token error";
        }
    }


}
