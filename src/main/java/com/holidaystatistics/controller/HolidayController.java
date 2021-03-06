package com.holidaystatistics.controller;

import com.holidaystatistics.entity.*;
import com.holidaystatistics.model.HolidayAdditionModel;
import com.holidaystatistics.model.HolidayInfoModel;
import com.holidaystatistics.model.HolidayPlanModel;
import com.holidaystatistics.model.HolidayPlanOfStudentModel;
import com.holidaystatistics.service.DownExcelService.DownExcelService;
import com.holidaystatistics.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private ManagerRepository managerRepository;

    @Resource
    private HolidayPlanRepository holidayPlanRepository;

    @Resource
    private HolidayInfoRepository holidayInfoRepository;

    @Resource
    private HolidayAdditionRepository holidayAdditionRepository;

    @Resource
    private DownExcelService downExcelService;

    @PostMapping("/manager/holiday_info")
    public ModelAndView setHolidayInfo(ModelAndView modelAndView, @Valid HolidayInfoModel holidayInfoModel) {
        HolidayInfo holidayInfo = holidayInfoRepository.findByHolidayName(holidayInfoModel.getHolidayName());
        if(holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.START) == null){
            if (holidayInfo == null) {
                holidayInfo = new HolidayInfo(holidayInfoModel);
            } else {
                holidayInfo.setholidayInfoFromModel(holidayInfoModel);
            }
            holidayInfoRepository.save(holidayInfo);
            modelAndView.setViewName("manager/success");
        }else {
            modelAndView.setViewName("manager/error");
        }
        return modelAndView;
    }

    @Transactional
    @GetMapping("/manager/delete_holiday_info")
    public String deleteHoliday(Long holidayId){
        try {
            holidayInfoRepository.delete(holidayId);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/student/get_holiday_plan")
    public HolidayPlanModel holidayPlan(HttpServletRequest httpServletRequest) {
        HolidayInfo holidayInfo;
        String studentId = httpServletRequest.getSession().getAttribute("studentId").toString();
        Student student = studentRepository.findBystudentId(studentId);
        holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.START);
        if (holidayInfo == null) {
            return null;
        }

        HolidayPlanModel holidayPlanModel = new HolidayPlanModel(holidayInfo);
        HolidayPlan holidayPlan = holidayPlanRepository.findAllByHolidayInfoAndStudent(holidayInfo, student);
        if (holidayPlan == null) {
            return holidayPlanModel;
        }

        holidayPlanModel.setHolidayPlan(holidayPlan);
        return holidayPlanModel;
    }

    @PostMapping("/student/set_holiday_plan")
    public String holidayPlan(HolidayPlanModel holidayPlanModel, HttpServletRequest httpServletRequest) {
        String stayAtSchool = "留校";
        if (Objects.equals(holidayPlanModel.getWhereToGo(), "")) {
            return "error";
        }
        String studentId = httpServletRequest.getSession().getAttribute("studentId").toString();
        Student student = studentRepository.findBystudentId(studentId);
        HolidayInfo holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.START);
        HolidayPlan holidayPlan = holidayPlanRepository.findAllByHolidayInfoAndStudent(holidayInfo, student);

        if (!Objects.equals(holidayPlanModel.getWhereToGo(), stayAtSchool)) {
            if (holidayPlanModel.getBackTime().getTime() - holidayPlanModel.getLeaveTime().getTime() < 0
                    || holidayPlanModel.getBackTime().getTime() > holidayInfo.getHolidayEndTime().getTime()
                    || holidayPlanModel.getLeaveTime().getTime() < holidayInfo.getHolidayStartTime().getTime()) {
                return "Time Error";
            }
        }

        if (holidayPlan == null) {
            holidayPlan = new HolidayPlan(holidayPlanModel);
            holidayPlan.setStudent(student);
        }
        holidayPlan.setIp(httpServletRequest.getRemoteAddr());
        holidayPlan.setSubmitTime(new Timestamp(System.currentTimeMillis()));
        holidayPlan.setHolidayInfo(holidayInfo);
        holidayPlan.setHolidayPlanFormModel(holidayPlanModel);

        holidayPlanRepository.save(holidayPlan);
        return "success";
    }

    @GetMapping("/student/get_holidayPlan_all")
    public List<HolidayPlan> holidayPlanListofStudent(HttpServletRequest httpServletRequest) {
        String studentId = httpServletRequest.getSession().getAttribute("studentId").toString();
        Student student = studentRepository.findBystudentId(studentId);
        return holidayPlanRepository.findAllByStudent(student);
    }

    @GetMapping("/student/get_holidayPlan_one")
    public HolidayPlan holidayPlanOneOfStudent(HttpServletRequest httpServletRequest) {
        String studentId = httpServletRequest.getSession().getAttribute("studentId").toString();
        Student student = studentRepository.findBystudentId(studentId);
        HolidayInfo holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.START);
        return holidayPlanRepository.findAllByHolidayInfoAndStudent(holidayInfo, student);
    }

    @GetMapping("/public/get_holidayInfo_of_Status")
    public HolidayInfo holidayInfo(HolidayInfo.holidayStatus holidayStatus) {
        return holidayInfoRepository.findAllByholidayStatus(holidayStatus);
    }

    @GetMapping("/manager/get_holidayInfo_all")
    public List<HolidayInfo> holidayInfoList(HolidayInfo.holidayStatus holidayStatus) {
        if (holidayStatus == null) {
            return (List<HolidayInfo>) holidayInfoRepository.findAll();
        } else {
            return holidayInfoRepository.findAllByHolidayStatus(holidayStatus);
        }
    }

    @PostMapping("/manager/updated_holiday_info")
    public void updatedHolidayInfo(HolidayInfoModel holidayInfoModel) {
        HolidayInfo holidayInfo = holidayInfoRepository.findOne(holidayInfoModel.getHolidayId());
        holidayInfo.setholidayInfoFromModel(holidayInfoModel);
        holidayInfoRepository.save(holidayInfo);
    }

    @PostMapping("/student/set_holiday_addition")
    public String holidayAddition(HolidayAdditionModel holidayAdditionModel, HttpServletRequest httpServletRequest) {
        String studentId = httpServletRequest.getSession().getAttribute("studentId").toString();
        Student student = studentRepository.findBystudentId(studentId);
        HolidayInfo holidayInfo = holidayInfoRepository.findAllByholidayStatus(HolidayInfo.holidayStatus.START);
        HolidayPlan holidayPlan = holidayPlanRepository.findAllByHolidayInfoAndStudent(holidayInfo, student);
        if (holidayPlan == null) {
            return "undefine holiday";
        } else {
            holidayAdditionModel.setHolidayPlan(holidayPlan);
            HolidayAddition holidayAddition;
            holidayAddition = holidayAdditionRepository.findByHolidayPlan(holidayPlan);
            if (holidayAddition == null) {
                holidayAddition = new HolidayAddition();
            }
            holidayAddition.setHolidayAdditionFromModel(holidayAdditionModel);
            holidayAdditionRepository.save(holidayAddition);
            return "success";
        }
    }

    @GetMapping("/manager/get_holiday_plan_of_student")
    public ModelAndView getHolidayPlanOfStudent(Long holidayId, ModelAndView modelAndView, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Manager manager = managerRepository.findOne((String) session.getAttribute("managerId"));

        HolidayInfo holidayInfo = holidayInfoRepository.findOne(holidayId);
        List<HolidayPlanOfStudentModel> holidayPlanOfStudentModelList = new ArrayList<>();
        List<Student> studentList = studentRepository
                .findAllByProfessionalClass_Id(manager.getProfessionalClass().getId());

//        studentList = studentList
//                .stream()
//                .filter(student -> student.getStudentId().charAt(0) == '3')
//                .collect(Collectors.toList());

        for (int i = 0; i <= studentList.size() - 1; i++) {
            HolidayPlan holidayPlan = holidayPlanRepository
                    .findAllByHolidayInfoAndStudent(holidayInfo, studentList.get(i));
            HolidayPlanOfStudentModel holidayPlanOfStudentModel
                    = new HolidayPlanOfStudentModel(holidayPlan, studentList.get(i));
            holidayPlanOfStudentModelList.add(holidayPlanOfStudentModel);
        }
        modelAndView.setViewName("manager/holiday_plan_of_student");
        modelAndView.addObject("holidayId", holidayId);
        modelAndView.addObject("holidayName", holidayInfo.getHolidayName());
        modelAndView.addObject("holidayPlanOfStudentFromModelList", holidayPlanOfStudentModelList);
        return modelAndView;
    }

    @GetMapping("/manager/download_excel")
    public String  downloadExcelOfholidayPlan(HttpServletRequest request,HttpServletResponse response, Long holidayId) {

        HttpSession session = request.getSession();

        Manager manager = managerRepository.findOne((String) session.getAttribute("managerId"));

        return downExcelService.getHolidayExcel(response, holidayId,manager);
    }

}
