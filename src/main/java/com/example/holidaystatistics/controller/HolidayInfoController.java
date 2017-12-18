package com.example.holidaystatistics.controller;

import com.example.holidaystatistics.entity.HolidayInfo;
import com.example.holidaystatistics.entity.HolidayPlan;
import com.example.holidaystatistics.entity.Student;
import com.example.holidaystatistics.model.HolidayInfoFromModel;
import com.example.holidaystatistics.model.HolidayPlanFormModel;
import com.example.holidaystatistics.model.MessageFromModel;
import com.example.holidaystatistics.repository.HolidayInfoRepository;
import com.example.holidaystatistics.repository.HolidayPlanRepository;
import com.example.holidaystatistics.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author 马小生
 */
@RestController
public class HolidayInfoController {
    @Resource
    private StudentRepository studentRepository;

    @Resource
    private HolidayPlanRepository holidayPlanRepository;

    @Resource
    private HolidayInfoRepository holidayInfoRepository;

    @GetMapping("/holiday_info")
    public ModelAndView holidayInfo(ModelAndView modelAndView,HolidayInfoFromModel holidayInfoFromModel) {
        modelAndView.setViewName("manage/holiday_info");
        return modelAndView.addObject("nationalDayInfoFormModel",holidayInfoFromModel);
    }

    @PostMapping("/holiday_info")
    public ModelAndView setholidayInfo(ModelAndView modelAndView, @Valid HolidayInfoFromModel holidayInfoFromModel) {

        HolidayInfo holidayInfo = new HolidayInfo(holidayInfoFromModel);

        holidayInfoRepository.save(holidayInfo);
        modelAndView.setViewName("manage/success");
        return modelAndView;
    }

    @GetMapping("/holiday_all")
    public ModelAndView holidayAll(ModelAndView modelAndView) {
        modelAndView.setViewName("manage/holiday_all");
        return modelAndView.addObject("nationalDayInfoFormModel",holidayInfoRepository.findAll());
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
            System.out.println("holiday start time :"+holidayPlanFormModel.getLeaveTime());
            modelAndView.addObject("nationalDayPlanFormModel", holidayPlanFormModel);
            modelAndView.addObject("message", messageFromModel);
            return modelAndView;
        }
    }

    @PostMapping("/holiday_plan")
    public ModelAndView holidayPlan(ModelAndView modelAndView,
                                    @Valid HolidayPlanFormModel holidayPlanFormModel, HttpServletRequest httpServletRequest) {


        String studentId = httpServletRequest.getSession().getAttribute("studentid").toString();
        Student student = studentRepository.findBystudentId(studentId);
        HolidayInfo holidayInfo = holidayInfoRepository.findByholiStatus(HolidayInfo.holiStatus.START);
        System.out.println("come in ");
        if (holidayInfo == null) {
            System.out.println("undefind holiday info!");
            MessageFromModel messageFromModel = new MessageFromModel();
            messageFromModel.setGetmessage(true);
            modelAndView.setViewName("student/holiday_plan");
            modelAndView.addObject("message", messageFromModel);
            return modelAndView;
        } else {
            HolidayPlan holidayPlan = holidayPlanRepository.findAllByHolidayInfoAndStudent(holidayInfo, student);

            if (holidayPlan == null) {
                holidayPlan = new HolidayPlan(holidayPlanFormModel);
                holidayPlan.setStudent(student);
            }

            holidayPlan.setIp(httpServletRequest.getRemoteAddr());
            holidayPlan.setSubmitTime(new Timestamp(System.currentTimeMillis()));
            holidayPlan.setHolidayInfo(holidayInfo);
            holidayPlan.setHolidayPlanFormModel(holidayPlanFormModel);

            System.out.println("get holidayplan success!");
            holidayPlanRepository.save(holidayPlan);
            modelAndView.setViewName("student/success");
            return modelAndView;
        }
    }

    @GetMapping("/finished")
    public ModelAndView finished(ModelAndView modelAndView, HttpServletRequest httpServletRequest) {
        String studentId = httpServletRequest.getSession().getAttribute("studentid").toString();
        HolidayInfo holidayInfo = holidayInfoRepository.findByholiStatus(HolidayInfo.holiStatus.START);
        Student student = studentRepository.findBystudentId(studentId);
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
