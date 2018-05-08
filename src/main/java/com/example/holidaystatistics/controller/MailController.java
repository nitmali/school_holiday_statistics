package com.example.holidaystatistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author nitmali@126.com
 * @date 2018/5/8 21:29
 */
@RestController
public class MailController {
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping("/openApi/sendEmail")
    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("holiday@nitmali.com");
        message.setTo("nitmali@126.com");
        message.setSubject("主题：测试邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }
}