package com.example.holidaystatistics.controller;

import com.example.holidaystatistics.entity.HolidayAddition;
import com.example.holidaystatistics.entity.HolidayInfo;
import com.example.holidaystatistics.entity.HolidayPlan;
import com.example.holidaystatistics.entity.Student;
import com.example.holidaystatistics.model.HolidayAdditionFromModel;
import com.example.holidaystatistics.model.HolidayInfoFromModel;
import com.example.holidaystatistics.model.HolidayPlanFormModel;
import com.example.holidaystatistics.model.HolidayPlanOfStudentFromModel;
import com.example.holidaystatistics.repository.HolidayAdditionRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Resource
    private HolidayAdditionRepository holidayAdditionRepository;

    @PostMapping("/holiday_info")
    public ModelAndView setHolidayInfo(ModelAndView modelAndView, @Valid HolidayInfoFromModel holidayInfoFromModel) {

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

    @GetMapping("/get_holidayPlan_of_student")
    public List<HolidayPlan> holidayPlans(HttpServletRequest httpServletRequest) {
        String studentId = httpServletRequest.getSession().getAttribute("studentid").toString();
        Student student = studentRepository.findBystudentId(studentId);

        return holidayPlanRepository.findAllByStudent(student);
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
    public void updatedHolidayInfo(HolidayInfoFromModel holidayInfoFromModel) {
        HolidayInfo holidayInfo = holidayInfoRepository.findOne(holidayInfoFromModel.getHolidayId());
        holidayInfo.setholidayInfoFromModel(holidayInfoFromModel);
        holidayInfoRepository.save(holidayInfo);
    }

    @PostMapping("/set_holiday_addition")
    public String holidayAddition(HolidayAdditionFromModel holidayAdditionFromModel, HttpServletRequest httpServletRequest){
        String studentId = httpServletRequest.getSession().getAttribute("studentid").toString();
        Student student = studentRepository.findBystudentId(studentId);
        HolidayInfo  holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.START);
        HolidayPlan holidayPlan = holidayPlanRepository.findAllByHolidayInfoAndStudent(holidayInfo, student);
        if (holidayPlan == null)
        {
            return "undefine holiday";
        }else {
            holidayAdditionFromModel.setHolidayPlan(holidayPlan);
            HolidayAddition holidayAddition;
            holidayAddition = holidayAdditionRepository.findByHolidayPlan(holidayPlan);
            if(holidayAddition == null)
            {
                holidayAddition = new HolidayAddition();
            }
            holidayAddition.setHolidayAdditionFromModel(holidayAdditionFromModel);
            holidayAdditionRepository.save(holidayAddition);
            return "success";
        }
    }

//    @GetMapping("/get_holiday_plan_of_student")
//    public List<HolidayPlanOfStudentFromModel> getHolidayPlanOfStudent(Long holidayId) {
//        HolidayInfo holidayInfo = holidayInfoRepository.findOne(holidayId);
//        List<HolidayPlanOfStudentFromModel> holidayPlanOfStudentFromModelList = new ArrayList<>();
//        List<Student> studentList = (List<Student>) studentRepository.findAll();
//        studentList = studentList
//                .stream()
//                .filter(student -> student.getStudentId().length() == 10)
//                .collect(Collectors.toList());
//        for (int i = 0; i <= studentList.size() - 1; i++) {
//            HolidayPlan holidayPlan = holidayPlanRepository
//                    .findAllByHolidayInfoAndStudent(holidayInfo, studentList.get(i));
//            HolidayPlanOfStudentFromModel holidayPlanOfStudentFromModel
//                    = new HolidayPlanOfStudentFromModel(holidayPlan,studentList.get(i));
//            holidayPlanOfStudentFromModelList.add(holidayPlanOfStudentFromModel);
//        }
//        return holidayPlanOfStudentFromModelList;
//    }

    @GetMapping("/get_holiday_plan_of_student")
    public ModelAndView getHolidayPlanOfStudent(Long holidayId,ModelAndView modelAndView) {
        System.out.println("get holiday:"+holidayId);
        HolidayInfo holidayInfo = holidayInfoRepository.findOne(holidayId);
        List<HolidayPlanOfStudentFromModel> holidayPlanOfStudentFromModelList = new ArrayList<>();
        List<Student> studentList = (List<Student>) studentRepository.findAll();
        studentList = studentList
                .stream()
                .filter(student -> student.getStudentId().charAt(0) == '3')
                .collect(Collectors.toList());
        for (int i = 0; i <= studentList.size() - 1; i++) {
            HolidayPlan holidayPlan = holidayPlanRepository
                    .findAllByHolidayInfoAndStudent(holidayInfo, studentList.get(i));
            HolidayPlanOfStudentFromModel holidayPlanOfStudentFromModel
                    = new HolidayPlanOfStudentFromModel(holidayPlan,studentList.get(i));
            holidayPlanOfStudentFromModelList.add(holidayPlanOfStudentFromModel);
        }
        modelAndView.setViewName("manage/holiday_plan_of_student");
        modelAndView.addObject("holidayPlanOfStudentFromModelList",holidayPlanOfStudentFromModelList);
        return modelAndView;
    }
}
