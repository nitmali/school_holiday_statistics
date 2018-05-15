package com.example.holidaystatistics.service.DownExcelService;

import com.example.holidaystatistics.entity.HolidayInfo;
import com.example.holidaystatistics.entity.HolidayPlan;
import com.example.holidaystatistics.entity.Student;
import com.example.holidaystatistics.model.HolidayPlanOfStudentModel;
import com.example.holidaystatistics.repository.HolidayInfoRepository;
import com.example.holidaystatistics.repository.HolidayPlanRepository;
import com.example.holidaystatistics.repository.StudentRepository;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nitmali@126.com
 * @date 2018/5/3 20:57
 */
@Service
public class DownExcelService {

    @Resource
    private ExcelConfig.HolidayExcelConfig holidayExcelConfig;

    @Resource
    private HolidayPlanRepository holidayPlanRepository;

    @Resource
    private HolidayInfoRepository holidayInfoRepository;

    @Resource
    private StudentRepository studentRepository;

    @Resource
    private ExcelTemplate excelTemplate;


    public void getHolidayExcel(HttpServletResponse response, Long holidayId) {
        try {
            HolidayInfo holidayInfo = holidayInfoRepository.findOne(holidayId);
            List<HolidayPlanOfStudentModel> holidayPlanOfStudentModelList = new ArrayList<>();
            List<Student> studentList = (List<Student>) studentRepository.findAll();
            studentList = studentList
                    .stream()
                    .filter(student -> student.getStudentId().charAt(0) == '3')
                    .collect(Collectors.toList());
            for (int i = 0; i <= studentList.size() - 1; i++) {
                HolidayPlan holidayPlan = holidayPlanRepository
                        .findAllByHolidayInfoAndStudent(holidayInfo, studentList.get(i));
                HolidayPlanOfStudentModel holidayPlanOfStudentModel
                        = new HolidayPlanOfStudentModel(holidayPlan, studentList.get(i));
                holidayPlanOfStudentModelList.add(holidayPlanOfStudentModel);
            }

            String fileName = holidayExcelConfig.getClassName() + "_"
                    + holidayInfo.getHolidayName() + "_" + holidayExcelConfig.getExcelName();

            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(holidayExcelConfig.getTemplatePath()));

            excelTemplate.getHolidayExcelTemplate(workbook,holidayPlanOfStudentModelList);

            response.setContentType("application/octet-stream");

            response.setHeader("Content-disposition", "attachment;filename="
                    + java.net.URLEncoder.encode(fileName, "UTF-8"));
            response.flushBuffer();

            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
