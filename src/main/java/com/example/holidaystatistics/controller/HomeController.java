package com.example.holidaystatistics.controller;


import com.example.holidaystatistics.model.HolidayInfoModel;
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

    @GetMapping("/student/home")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("student/student_home");
        return modelAndView;
    }

    @GetMapping("/manager/home")
    public ModelAndView manager(ModelAndView modelAndView) {
        modelAndView.setViewName("manager/manager_home");
        return modelAndView;
    }

    @GetMapping("/student/holiday_plan")
    public ModelAndView holidayPlan(ModelAndView modelAndView) {
        modelAndView.setViewName("student/holiday_plan");
        return modelAndView;
    }

    @GetMapping("/student/login")
    public ModelAndView studentLogin(ModelAndView modelAndView) {
        modelAndView.setViewName("student/student_login");
        return modelAndView;
    }

    @GetMapping("/manager/login")
    public ModelAndView managerLogin(ModelAndView modelAndView) {
        modelAndView.setViewName("manager/manager_login");
        return modelAndView;
    }

    @GetMapping("/public/change_password")
    public ModelAndView change(ModelAndView modelAndView) {
        modelAndView.setViewName("public/change_password");
        return modelAndView;
    }

    @GetMapping("/student/success")
    public ModelAndView success(ModelAndView modelAndView) {
        modelAndView.setViewName("student/success");
        return modelAndView;
    }

    @GetMapping("/holiday_plan_of_student")
    public ModelAndView studentPlan(ModelAndView modelAndView) {
        modelAndView.setViewName("manager/holiday_plan_of_student");
        return modelAndView;
    }

    @GetMapping("/student/finished")
    public ModelAndView finished(ModelAndView modelAndView) {
        modelAndView.setViewName("student/finished");
        return modelAndView;
    }

    @GetMapping("/student/holiday_addition")
    public ModelAndView holidayAddition(ModelAndView modelAndView) {
        modelAndView.setViewName("student/holiday_addition");
        return modelAndView;
    }

    @GetMapping("/manager/holiday_info")
    public ModelAndView holidayInfo(ModelAndView modelAndView, HolidayInfoModel holidayInfoModel) {
        modelAndView.setViewName("manager/holiday_info");
        modelAndView.addObject("nationalDayInfoFormModel", holidayInfoModel);
        return modelAndView;
    }

    @GetMapping("/manager/holiday_all")
    public ModelAndView holidayAll(ModelAndView modelAndView) {
        modelAndView.setViewName("manager/holiday_all");
        return modelAndView;
    }

    @GetMapping("/public/forget_password")
    public ModelAndView forgetPassword(ModelAndView modelAndView) {
        modelAndView.setViewName("public/forget_password");
        return modelAndView;
    }

    @GetMapping("/student/student_personal")
    public ModelAndView studnetPersonal(ModelAndView modelAndView){
        modelAndView.setViewName("student/student_personal");
        return modelAndView;
    }
}

