package com.example.holidaystatistics.service.DownExcel;

import com.example.holidaystatistics.entity.HolidayInfo;
import com.example.holidaystatistics.entity.HolidayPlan;
import com.example.holidaystatistics.entity.Student;
import com.example.holidaystatistics.model.HolidayPlanOfStudentFromModel;
import com.example.holidaystatistics.repository.HolidayInfoRepository;
import com.example.holidaystatistics.repository.HolidayPlanRepository;
import com.example.holidaystatistics.repository.StudentRepository;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
    private ExcelConfig excelConfig;

    @Resource
    private HolidayPlanRepository holidayPlanRepository;

    @Resource
    private HolidayInfoRepository holidayInfoRepository;

    @Resource
    private StudentRepository studentRepository;


    public void getExcel(HttpServletResponse response, Long holidayId) {
        try {
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
                        = new HolidayPlanOfStudentFromModel(holidayPlan, studentList.get(i));
                holidayPlanOfStudentFromModelList.add(holidayPlanOfStudentFromModel);
            }

            String fileName = excelConfig.getClassName() + "_"
                    + holidayInfo.getHolidayName() + "_" + excelConfig.getExcelName();

            File fi = new File(excelConfig.getTemplatePath());
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
            HSSFWorkbook workbook = new HSSFWorkbook(fs);

            HSSFSheet sheet = workbook.getSheetAt(0);

            int atSchool = 0;
            int goHome = 0;
            int travel = 0;

            for (int i = 0; i <= studentList.size() - 1; i++) {
                HolidayPlanOfStudentFromModel holidayPlanOfStudentFromModel
                        = holidayPlanOfStudentFromModelList.get(i);
                HSSFRow row = sheet.createRow(i + 4);
                row.createCell(0).setCellValue(holidayPlanOfStudentFromModel.getStudentId());
                row.createCell(1).setCellValue(holidayPlanOfStudentFromModel.getStudentName());
                if (holidayPlanOfStudentFromModel.getLeaveTime() != null
                        && holidayPlanOfStudentFromModel.getBackTime() != null) {
                    row.createCell(2).setCellValue(holidayPlanOfStudentFromModel
                            .getLeaveTime().toString().substring(5, 10)
                            + " - " + holidayPlanOfStudentFromModel
                            .getBackTime().toString().substring(5, 10));
                }
                if ("留校".equals(holidayPlanOfStudentFromModel.getWhereToGo())) {
                    row.createCell(3).setCellValue("1");
                    atSchool++;
                } else if (holidayPlanOfStudentFromModel.getWhereToGo().contains("回家")) {
                    row.createCell(4).setCellValue("1");
                    goHome++;
                } else {
                    row.createCell(5).setCellValue(holidayPlanOfStudentFromModel.getWhereToGo());
                    travel++;
                }
                row.createCell(6).setCellValue(holidayPlanOfStudentFromModel.getPhone());
            }
            CellRangeAddress atSchool1 = new CellRangeAddress(43, 43, 0, 2);
            CellRangeAddress atSchool2 = new CellRangeAddress(43, 43, 3, 6);
            CellRangeAddress goHome1 = new CellRangeAddress(44, 44, 0, 2);
            CellRangeAddress goHome2 = new CellRangeAddress(44, 44, 3, 6);
            CellRangeAddress travel1 = new CellRangeAddress(45, 45, 0, 2);
            CellRangeAddress travel2 = new CellRangeAddress(45, 45, 3, 6);
            sheet.addMergedRegion(atSchool1);
            sheet.addMergedRegion(atSchool2);
            sheet.addMergedRegion(goHome1);
            sheet.addMergedRegion(goHome2);
            sheet.addMergedRegion(travel1);
            sheet.addMergedRegion(travel2);
            HSSFRow atSchoolRow = sheet.createRow(43);
            atSchoolRow.createCell(0).setCellValue("在校人数");
            atSchoolRow.createCell(3).setCellValue(atSchool);
            HSSFRow goHomeRow = sheet.createRow(44);
            goHomeRow.createCell(0).setCellValue("回家人数");
            goHomeRow.createCell(3).setCellValue(goHome);
            HSSFRow travelRow = sheet.createRow(45);
            travelRow.createCell(0).setCellValue("旅游人数");
            travelRow.createCell(3).setCellValue(travel);


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
