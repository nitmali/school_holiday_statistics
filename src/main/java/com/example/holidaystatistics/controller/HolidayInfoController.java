package com.example.holidaystatistics.controller;

import com.example.holidaystatistics.entity.HolidayInfo;
import com.example.holidaystatistics.model.HolidayInfoFromModel;
import com.example.holidaystatistics.repository.HolidayInfoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author 马小生
 */
@Controller
public class HolidayInfoController {
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
}
