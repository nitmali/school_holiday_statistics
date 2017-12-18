package com.example.holidaystatistics.controller;

import com.example.holidaystatistics.entity.HolidayInfo;
import com.example.holidaystatistics.entity.HolidayPlan;
import com.example.holidaystatistics.entity.Student;
import com.example.holidaystatistics.model.HolidayPlanFormModel;
import com.example.holidaystatistics.model.MessageFromModel;
import com.example.holidaystatistics.repository.HolidayInfoRepository;
import com.example.holidaystatistics.repository.HolidayPlanRepository;
import com.example.holidaystatistics.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author 马小生
 */
@Controller
public class HomeController {
    @Resource
    private StudentRepository studentRepository;

    @Resource
    private HolidayPlanRepository holidayPlanRepository;

    @Resource
    private HolidayInfoRepository holidayInfoRepository;

    @RequestMapping(value = "/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpServletRequest request) {
        request.getSession().invalidate();
        modelAndView.setViewName("student/studentlogin");
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        MessageFromModel messageFromModel = new MessageFromModel();
        modelAndView.setViewName("student/studentlogin");
        modelAndView.addObject("message", messageFromModel);
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView) {
        MessageFromModel messageFromModel = new MessageFromModel();
        modelAndView.addObject("message", messageFromModel);
        modelAndView.setViewName("student/home");
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView admin(ModelAndView modelAndView) {
        modelAndView.setViewName("manage/admin");
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

    @GetMapping("/holiday_plan")
    public ModelAndView holidayPlan(ModelAndView modelAndView, HolidayPlanFormModel holidayPlanFormModel) {
        HolidayInfo holidayInfo = holidayInfoRepository.findByholiStatus(HolidayInfo.holiStatus.START);
        if (holidayInfo != null) {
            Date starttime = holidayInfo.getHolidayStartTime();
            Date endtime = holidayInfo.getHolidayEndTime();
            holidayPlanFormModel.setHolidayName(holidayInfo.getHolidayName());
            holidayPlanFormModel.setLeaveTime(starttime);
            holidayPlanFormModel.setBackTime(endtime);
            modelAndView.setViewName("student/holiday_plan");
            return modelAndView.addObject("nationalDayPlanFormModel", holidayPlanFormModel);
        } else {
            MessageFromModel messageFromModel = new MessageFromModel();
            messageFromModel.setGetmessage(true);
            messageFromModel.setWarning("当前无假期");
            modelAndView.setViewName("student/home");
            modelAndView.addObject("nationalDayPlanFormModel", holidayPlanFormModel);
            modelAndView.addObject("message", messageFromModel);
            return modelAndView;
        }
    }

    @PostMapping("/holiday_plan")
    public ModelAndView holidayPlan(ModelAndView modelAndView,
                                    @Valid HolidayPlanFormModel holidayPlanFormModel, BindingResult bindingResult,
                                    HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("student/holiday_plan");
            return modelAndView.addObject("nationalDayPlanFormModel", holidayPlanFormModel);
        }

        String studentid = httpServletRequest.getSession().getAttribute("studentid").toString();
        Student student = studentRepository.findBystudentId(studentid);
        HolidayInfo holidayInfo = holidayInfoRepository.findByholiStatus(HolidayInfo.holiStatus.START);

        if (holidayInfo == null) {
            MessageFromModel messageFromModel = new MessageFromModel();
            messageFromModel.setGetmessage(true);
            modelAndView.setViewName("student/holiday_plan");
            modelAndView.addObject("message", messageFromModel);
            return modelAndView;
        } else {
            HolidayPlan holidayPlan = holidayPlanRepository.findAllByHolidayInfoAndStudent(holidayInfo, student);

            if (holidayPlan == null) {
                holidayPlan = new HolidayPlan(holidayPlanFormModel);
            }

            holidayPlan.setIp(httpServletRequest.getRemoteAddr());
            holidayPlan.setSubmitTime(new Timestamp(System.currentTimeMillis()));
            holidayPlan.setHolidayInfo(holidayInfo);
            holidayPlan.setStudent(student);

            holidayPlanRepository.save(holidayPlan);
            modelAndView.setViewName("student/success");
            return modelAndView;
        }
    }

    @GetMapping("/finished")
    public ModelAndView finished(ModelAndView modelAndView, HttpServletRequest httpServletRequest) {
        String studentid = httpServletRequest.getSession().getAttribute("studentid").toString();
        HolidayInfo holidayInfo = holidayInfoRepository.findByholiStatus(HolidayInfo.holiStatus.START);
        Student student = studentRepository.findBystudentId(studentid);
        HolidayPlan holidayPlan = holidayPlanRepository.findAllByHolidayInfoAndStudent(holidayInfo, student);
        MessageFromModel messageFromModel = new MessageFromModel();
        modelAndView.setViewName("/student/finished");

        if (holidayPlan == null) {
            messageFromModel.setGetmessage(true);
        }else {
            modelAndView.addObject("holidayPlan",holidayPlan);
        }
        messageFromModel.setWarning("没有提交记录");
        return modelAndView.addObject("message", messageFromModel);
    }
}

