package com.holidaystatistics.service.DownExcelService;

import com.holidaystatistics.entity.HolidayInfo;
import com.holidaystatistics.entity.HolidayPlan;
import com.holidaystatistics.entity.Manager;
import com.holidaystatistics.entity.Student;
import com.holidaystatistics.model.HolidayPlanOfStudentModel;
import com.holidaystatistics.repository.HolidayInfoRepository;
import com.holidaystatistics.repository.HolidayPlanRepository;
import com.holidaystatistics.repository.StudentRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

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


    public void getHolidayExcel(HttpServletResponse response, Long holidayId, Manager manager) {
        try {
            HolidayInfo holidayInfo = holidayInfoRepository.findOne(holidayId);
            List<HolidayPlanOfStudentModel> holidayPlanOfStudentModelList = new ArrayList<>();
            List<Student> studentList = studentRepository
                    .findAllByProfessionalClass_Id(manager.getProfessionalClass().getId());
            for (int i = 0; i <= studentList.size() - 1; i++) {
                HolidayPlan holidayPlan = holidayPlanRepository
                        .findAllByHolidayInfoAndStudent(holidayInfo, studentList.get(i));
                HolidayPlanOfStudentModel holidayPlanOfStudentModel
                        = new HolidayPlanOfStudentModel(holidayPlan, studentList.get(i));
                holidayPlanOfStudentModelList.add(holidayPlanOfStudentModel);
            }

            String fileName = manager.getProfessionalClass().getName() + "_"
                    + holidayInfo.getHolidayName() + "_" + holidayExcelConfig.getExcelName();

            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(holidayExcelConfig.getTemplatePath()));

            excelTemplate.getHolidayExcelTemplate(workbook, holidayPlanOfStudentModelList);

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
