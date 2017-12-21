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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author 马小生
 */
@RestController
public class HolidayController {
    @Resource
    private StudentRepository studentRepository;

    @Resource
    private HolidayPlanRepository holidayPlanRepository;

    @Resource
    private HolidayInfoRepository holidayInfoRepository;

    @GetMapping("/holiday_info")
    public ModelAndView holidayInfo(ModelAndView modelAndView,HolidayInfoFromModel holidayInfoFromModel) {
        HolidayInfo holidayInfo;
        holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.START);
        holidayInfoFromModel = new HolidayInfoFromModel(holidayInfo);
        modelAndView.setViewName("manage/holiday_info");
        modelAndView.addObject("nationalDayInfoFormModel", holidayInfoFromModel);
        return modelAndView;
    }

    @PostMapping("/holiday_info")
    public ModelAndView setholidayInfo(ModelAndView modelAndView, @Valid HolidayInfoFromModel holidayInfoFromModel) {

        HolidayInfo holidayInfo = holidayInfoRepository.findByHolidayName(holidayInfoFromModel.getHolidayName());
        if (holidayInfo == null) {
            holidayInfo = new HolidayInfo(holidayInfoFromModel);
        } else {
            holidayInfo.setholidayInfoFromModel(holidayInfoFromModel);
        }
        holidayInfoRepository.save(holidayInfo);
        modelAndView.setViewName("manage/success");
        return modelAndView;
    }

    @GetMapping("/holiday_all")
    public ModelAndView holidayAll(ModelAndView modelAndView) {
        modelAndView.setViewName("manage/holiday_all");
        return modelAndView;
    }

    @GetMapping("/holiday_plan")
    public ModelAndView holidayPlan(ModelAndView modelAndView, HolidayPlanFormModel holidayPlanFormModel) {
        HolidayInfo holidayInfo;
        holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.START);
        if(holidayInfo == null)
        {
            holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.ACTIVATION);
        }
        if (holidayInfo != null) {
            Date startTime = holidayInfo.getHolidayStartTime();
            Date endTime = holidayInfo.getHolidayEndTime();
            holidayPlanFormModel.setHolidayName(holidayInfo.getHolidayName());
            holidayPlanFormModel.setLeaveTime(startTime);
            holidayPlanFormModel.setBackTime(endTime);
            modelAndView.setViewName("student/holiday_plan");
            modelAndView.addObject("nationalDayPlanFormModel", holidayPlanFormModel);
            return modelAndView;
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
       HolidayInfo holidayInfo;
        holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.START);
        if(holidayInfo == null)
        {
            holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.ACTIVATION);
        }
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
                holidayPlan.setStudent(student);
            }

            holidayPlan.setIp(httpServletRequest.getRemoteAddr());
            SimpleDateFormat sdf = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss秒");
            holidayPlan.setSubmitTime(new Timestamp(System.currentTimeMillis()));
            holidayPlan.setHolidayInfo(holidayInfo);
            holidayPlan.setHolidayPlanFormModel(holidayPlanFormModel);
            
            holidayPlanRepository.save(holidayPlan);
            modelAndView.setViewName("student/success");
            return modelAndView;
        }
    }

    @GetMapping("/finished")
    public ModelAndView finished(ModelAndView modelAndView, HttpServletRequest httpServletRequest) {
        String studentId = httpServletRequest.getSession().getAttribute("studentid").toString();
        HolidayInfo holidayInfo;
        holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.START);
        if(holidayInfo == null)
        {
            holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.ACTIVATION);
        }
        Student student = studentRepository.findBystudentId(studentId);
        HolidayPlan holidayPlan = holidayPlanRepository.findAllByHolidayInfoAndStudent(holidayInfo, student);
        MessageFromModel messageFromModel = new MessageFromModel();
        modelAndView.setViewName("student/finished");

        if (holidayPlan == null) {
            messageFromModel.setGetmessage(true);
        }else {
            modelAndView.addObject("holidayPlan",holidayPlan);
        }
        messageFromModel.setWarning("没有提交记录");
        modelAndView.addObject("message", messageFromModel);
        return modelAndView;
    }

    @GetMapping("/get_holidayInfo_of_Status")
    public HolidayInfo holidayInfo(HolidayInfo.holidayStatus holidayStatus) {
        return holidayInfoRepository.findAllByholidayStatus(holidayStatus);
    }

    @GetMapping("get_holidayInfo_all")
    public List<HolidayInfo> holidayInfoList(HolidayInfo.holidayStatus holidayStatus) {
        if (holidayStatus == null) {
            return (List<HolidayInfo>) holidayInfoRepository.findAll();
        } else {
            return holidayInfoRepository.findAllByHolidayStatus(holidayStatus);
        }
    }

    @PostMapping("/updated_holidayInfo")
    public void updatedHolidayInfo(HolidayInfoFromModel holidayInfoFromModel)
    {
        HolidayInfo holidayInfo = holidayInfoRepository.findOne(holidayInfoFromModel.getHolidayId());
        holidayInfo.setholidayInfoFromModel(holidayInfoFromModel);
        holidayInfoRepository.save(holidayInfo);
    }
}
