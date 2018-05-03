package com.example.holidaystatistics.service.DownExcel;

import com.example.holidaystatistics.entity.HolidayInfo;
import com.example.holidaystatistics.entity.HolidayPlan;
import com.example.holidaystatistics.entity.Student;
import com.example.holidaystatistics.model.HolidayPlanOfStudentFromModel;
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

            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(excelConfig.getTemplatePath()));

            HSSFSheet sheet = workbook.getSheetAt(0);

            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            sheet.setDefaultColumnWidth(44);

            int atSchool = 0;
            int goHome = 0;
            int other = 0;

            for (int i = 0; i <= studentList.size() - 1; i++) {
                HolidayPlanOfStudentFromModel holidayPlanOfStudentFromModel
                        = holidayPlanOfStudentFromModelList.get(i);

                HSSFRow row = sheet.createRow(i + 4);

                HSSFCell cell0 = row.createCell(0);
                HSSFCell cell1 = row.createCell(1);
                HSSFCell cell2 = row.createCell(2);
                HSSFCell cell3 = row.createCell(3);
                HSSFCell cell4 = row.createCell(4);
                HSSFCell cell5 = row.createCell(5);
                HSSFCell cell6 = row.createCell(6);
                HSSFCell cell7 = row.createCell(7);

                cell0.setCellValue(holidayPlanOfStudentFromModel.getStudentId());
                cell1.setCellValue(holidayPlanOfStudentFromModel.getStudentName());
                if (holidayPlanOfStudentFromModel.getLeaveTime() != null
                        && holidayPlanOfStudentFromModel.getBackTime() != null) {
                    cell2.setCellValue(holidayPlanOfStudentFromModel
                            .getLeaveTime().toString().substring(5, 10)
                            + " - " + holidayPlanOfStudentFromModel
                            .getBackTime().toString().substring(5, 10));
                }
                if ("留校".equals(holidayPlanOfStudentFromModel.getWhereToGo())) {
                    cell3.setCellValue("√");
                    atSchool++;
                } else if (holidayPlanOfStudentFromModel.getWhereToGo().contains("回家")) {
                    cell4.setCellValue("√");
                    goHome++;
                } else {
                    cell5.setCellValue(holidayPlanOfStudentFromModel.getWhereToGo());
                    other++;
                }
                cell6.setCellValue(holidayPlanOfStudentFromModel.getPhone());

                cell0.setCellStyle(cellStyle);
                cell1.setCellStyle(cellStyle);
                cell2.setCellStyle(cellStyle);
                cell3.setCellStyle(cellStyle);
                cell4.setCellStyle(cellStyle);
                cell5.setCellStyle(cellStyle);
                cell6.setCellStyle(cellStyle);
                cell7.setCellStyle(cellStyle);
            }

            HSSFFont font = workbook.createFont();
            font.setBold(true);

            HSSFCellStyle footCellStyle = workbook.createCellStyle();
            footCellStyle.setBorderBottom(BorderStyle.THIN);
            footCellStyle.setBorderLeft(BorderStyle.THIN);
            footCellStyle.setBorderTop(BorderStyle.THIN);
            footCellStyle.setBorderRight(BorderStyle.THIN);
            footCellStyle.setAlignment(HorizontalAlignment.CENTER);
            footCellStyle.setFont(font);



            HSSFRow atSchoolRow = sheet.createRow(43);
            HSSFCell atSchoolCell0 = atSchoolRow.createCell(0);
            HSSFCell atSchoolCell1 = atSchoolRow.createCell(1);
            HSSFCell atSchoolCell2 = atSchoolRow.createCell(2);
            HSSFCell atSchoolCell3 = atSchoolRow.createCell(3);
            HSSFCell atSchoolCell4 = atSchoolRow.createCell(4);
            HSSFCell atSchoolCell5 = atSchoolRow.createCell(5);
            HSSFCell atSchoolCell6 = atSchoolRow.createCell(6);
            HSSFCell atSchoolCell7 = atSchoolRow.createCell(7);
            atSchoolCell0.setCellValue("在校人数");
            atSchoolCell3.setCellValue(atSchool+"人");

            HSSFRow goHomeRow = sheet.createRow(44);
            HSSFCell goHomeCell0 = goHomeRow.createCell(0);
            HSSFCell goHomeCell1 = goHomeRow.createCell(1);
            HSSFCell goHomeCell2 = goHomeRow.createCell(2);
            HSSFCell goHomeCell3 = goHomeRow.createCell(3);
            HSSFCell goHomeCell4 = goHomeRow.createCell(4);
            HSSFCell goHomeCell5 = goHomeRow.createCell(5);
            HSSFCell goHomeCell6 = goHomeRow.createCell(6);
            HSSFCell goHomeCell7 = goHomeRow.createCell(7);
            goHomeCell0.setCellValue("回家人数");
            goHomeCell3.setCellValue(goHome+"人");

            HSSFRow otherRow = sheet.createRow(45);
            HSSFCell otherCell0 = otherRow.createCell(0);
            HSSFCell otherCell1 = otherRow.createCell(1);
            HSSFCell otherCell2 = otherRow.createCell(2);
            HSSFCell otherCell3 = otherRow.createCell(3);
            HSSFCell otherCell4 = otherRow.createCell(4);
            HSSFCell otherCell5 = otherRow.createCell(5);
            HSSFCell otherCell6 = otherRow.createCell(6);
            HSSFCell otherCell7 = otherRow.createCell(7);
            otherCell0.setCellValue("有其他安排人数");
            otherCell3.setCellValue(other+"人");

            atSchoolCell0.setCellStyle(footCellStyle);
            atSchoolCell1.setCellStyle(footCellStyle);
            atSchoolCell2.setCellStyle(footCellStyle);
            atSchoolCell3.setCellStyle(footCellStyle);
            atSchoolCell4.setCellStyle(footCellStyle);
            atSchoolCell5.setCellStyle(footCellStyle);
            atSchoolCell6.setCellStyle(footCellStyle);
            atSchoolCell7.setCellStyle(footCellStyle);

            goHomeCell0.setCellStyle(footCellStyle);
            goHomeCell1.setCellStyle(footCellStyle);
            goHomeCell2.setCellStyle(footCellStyle);
            goHomeCell3.setCellStyle(footCellStyle);
            goHomeCell4.setCellStyle(footCellStyle);
            goHomeCell5.setCellStyle(footCellStyle);
            goHomeCell6.setCellStyle(footCellStyle);
            goHomeCell7.setCellStyle(footCellStyle);

            otherCell0.setCellStyle(footCellStyle);
            otherCell1.setCellStyle(footCellStyle);
            otherCell2.setCellStyle(footCellStyle);
            otherCell3.setCellStyle(footCellStyle);
            otherCell4.setCellStyle(footCellStyle);
            otherCell5.setCellStyle(footCellStyle);
            otherCell6.setCellStyle(footCellStyle);
            otherCell7.setCellStyle(footCellStyle);

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
