package com.example.holidaystatistics.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author 马小生
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("student/student_login");
        return modelAndView;
    }

    @GetMapping("/student_home")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("student/student_home");
        return modelAndView;
    }

    @GetMapping("/admin_home")
    public ModelAndView admin(ModelAndView modelAndView) {
        modelAndView.setViewName("manage/admin_home");
        return modelAndView;
    }

    @GetMapping("/holiday_plan")
    public ModelAndView holidayPlan(ModelAndView modelAndView) {
        modelAndView.setViewName("student/holiday_plan");
        return modelAndView;
    }

    @GetMapping("/student_login")
    public ModelAndView studentLogin(ModelAndView modelAndView) {
        modelAndView.setViewName("student/student_login");
        return modelAndView;
    }

    @GetMapping("/admin_login")
    public ModelAndView adminLogin(ModelAndView modelAndView) {
        modelAndView.setViewName("manage/admin_login");
        return modelAndView;
    }

    @GetMapping("/change_password")
    public ModelAndView change(ModelAndView modelAndView) {
        modelAndView.setViewName("public/change_password");
        return modelAndView;
    }

    @GetMapping("/success")
    public ModelAndView success(ModelAndView modelAndView) {
        modelAndView.setViewName("student/success");
        return modelAndView;
    }

    @GetMapping("/holiday_addition")
    public ModelAndView holidayAddition(ModelAndView modelAndView) {
        modelAndView.setViewName("student/holiday_addition");
        return modelAndView;
    }
}

