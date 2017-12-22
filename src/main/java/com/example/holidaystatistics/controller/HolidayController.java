package com.example.holidaystatistics.controller;

import com.example.holidaystatistics.entity.HolidayInfo;
import com.example.holidaystatistics.entity.HolidayPlan;
import com.example.holidaystatistics.entity.Student;
import com.example.holidaystatistics.model.HolidayInfoFromModel;
import com.example.holidaystatistics.model.HolidayPlanFormModel;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

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

    @GetMapping("/get_holiday_plan")
    public HolidayPlanFormModel holidayPlan(HttpServletRequest httpServletRequest) {
        HolidayInfo holidayInfo;
        String studentId = httpServletRequest.getSession().getAttribute("studentid").toString();
        Student student = studentRepository.findBystudentId(studentId);
        holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.START);
        if (holidayInfo == null) {
            return null;
        }

        HolidayPlanFormModel holidayPlanFormModel = new HolidayPlanFormModel(holidayInfo);
        HolidayPlan holidayPlan = holidayPlanRepository.findAllByHolidayInfoAndStudent(holidayInfo, student);
        if (holidayPlan == null)
        {
            return holidayPlanFormModel;
        }
        //        Date startTime = holidayInfo.getHolidayStartTime();
//        Date endTime = holidayInfo.getHolidayEndTime();
//        holidayPlanFormModel.setHolidayName(holidayInfo.getHolidayName());
//        holidayPlanFormModel.setLeaveTime(startTime);
//        holidayPlanFormModel.setBackTime(endTime);

        holidayPlanFormModel.setHolidayPlan(holidayPlan);
        return holidayPlanFormModel;
    }


    @PostMapping("/set_holiday_plan")
    public String holidayPlan(HolidayPlanFormModel holidayPlanFormModel, HttpServletRequest httpServletRequest) {
        String stayAtSchool = "留校";
        if (Objects.equals(holidayPlanFormModel.getWhereToGo(), "")) {
            return "error";
        }
        String studentId = httpServletRequest.getSession().getAttribute("studentid").toString();
        Student student = studentRepository.findBystudentId(studentId);
        HolidayInfo  holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.START);
//        if(holidayInfo == null)
//        {
//            holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.ACTIVATION);
//        }
        HolidayPlan holidayPlan = holidayPlanRepository.findAllByHolidayInfoAndStudent(holidayInfo, student);

        if (!Objects.equals(holidayPlanFormModel.getWhereToGo(), stayAtSchool)) {
            if (holidayPlanFormModel.getBackTime().getTime() - holidayPlanFormModel.getLeaveTime().getTime() < 0
                    || holidayPlanFormModel.getBackTime().getTime() > holidayInfo.getHolidayEndTime().getTime()
                    || holidayPlanFormModel.getLeaveTime().getTime() < holidayInfo.getHolidayStartTime().getTime()) {
                return "Time Error";
            }
        }

        if (holidayPlan == null) {
            holidayPlan = new HolidayPlan(holidayPlanFormModel);
            holidayPlan.setStudent(student);
        }
        holidayPlan.setIp(httpServletRequest.getRemoteAddr());
        holidayPlan.setSubmitTime(new Timestamp(System.currentTimeMillis()));
        holidayPlan.setHolidayInfo(holidayInfo);
        holidayPlan.setHolidayPlanFormModel(holidayPlanFormModel);

        holidayPlanRepository.save(holidayPlan);
        return "success";
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
        modelAndView.setViewName("student/finished");
        modelAndView.addObject("holidayPlan", holidayPlan);
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

    @PostMapping("/updated_holiday_info")
    public void updatedHolidayInfo(HolidayInfoFromModel holidayInfoFromModel)
    {
        HolidayInfo holidayInfo = holidayInfoRepository.findOne(holidayInfoFromModel.getHolidayId());
        holidayInfo.setholidayInfoFromModel(holidayInfoFromModel);
        holidayInfoRepository.save(holidayInfo);
    }
}
