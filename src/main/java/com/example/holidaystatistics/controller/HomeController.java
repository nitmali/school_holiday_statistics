package com.example.holidaystatistics.controller;


import com.example.holidaystatistics.model.MessageFromModel;
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
        MessageFromModel messageFromModel = new MessageFromModel();
        modelAndView.setViewName("student/studentlogin");
        modelAndView.addObject("message", messageFromModel);
        return modelAndView;
    }

    @GetMapping("/student_home")
    public ModelAndView home(ModelAndView modelAndView) {
        MessageFromModel messageFromModel = new MessageFromModel();
        modelAndView.addObject("message", messageFromModel);
        modelAndView.setViewName("student/student_home");
        return modelAndView;
    }

    @GetMapping("/admin_home")
    public ModelAndView admin(ModelAndView modelAndView) {
        modelAndView.setViewName("manage/admin_home");

        return modelAndView;
    }

    @GetMapping("/studenlogin")
    public ModelAndView studenlogin(ModelAndView modelAndView) {
        modelAndView.setViewName("student/studentlogin");
        return modelAndView;
    }

    @GetMapping("/managelogin")
    public ModelAndView managelogin(ModelAndView modelAndView) {
        modelAndView.setViewName("manage/managelogin");
        return modelAndView;
    }
}

