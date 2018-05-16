package com.example.holidaystatistics.service.EmailService;

import com.example.holidaystatistics.entity.EmailToken;
import com.example.holidaystatistics.entity.Student;
import com.example.holidaystatistics.repository.StudentRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author nitmali@126.com
 * @date 2018/5/9 13:52
 */

@Service
public class SendEmail {
    @Resource
    private JavaMailSender mailSender;

    @Resource
    private StudentRepository studentRepository;

    @Resource
    private EmailTemplate emailTemplate;

    @Resource
    private AddEmailToken addEmailToken;

    @Resource
    private Md5Token md5Token;


    public String sendRestPasswordEmail(String email, EmailToken.EmailType emailType){
        Student student = studentRepository.findStudentByEmail(email);
        if (student != null) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);

                String messageText;

                String url = "http://holiday.nitmali.com/public/reset_password?email=" + email + "&token=";

                Date thisTime = new Date();

                String getTime = String.valueOf(thisTime.getTime());

                String address = url
                        + md5Token.getMD5(student.getStudentId() + email + getTime);

                messageText = emailTemplate.getEmailTemplate(address);

                helper.setFrom("holiday@nitmali.com");
                helper.setTo(email);
                helper.setSubject("Holiday System重置密码");

                helper.setText(messageText, true);

                if (addEmailToken.addEmailToke(email, student.getStudentId(), getTime, emailType)){
                    mailSender.send(message);
                }

            } catch (Exception exception) {
                System.err.println("异常(SendEmail 69)："+exception.getMessage());
                return "error";
            }
            return "success";
        } else {
            return "not find email";
        }
    }


    /***
     *token 为账号+时间MD5加密后14到20位。
     */
    public String sendBindEmail(String studentId, String email, EmailToken.EmailType emailType){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            String messageText;

            String token;

            Date thisTime = new Date();

            String getTime = String.valueOf(thisTime.getTime());

            token = md5Token.getMD5(studentId + getTime).toUpperCase().substring(14, 20);

            messageText = emailTemplate.getBindEmailTemplate(token);

            helper.setFrom("holiday@nitmali.com");
            helper.setTo(email);
            helper.setSubject("Holiday System邮箱绑定");

            helper.setText(messageText, true);

            if (addEmailToken.addEmailToke(email, studentId, getTime, emailType)){
                mailSender.send(message);
            }

        } catch (Exception exception) {
            System.err.println("异常(SendEmail 110)："+exception.getMessage());
            return "error";
        }
        return "success";
    }
}
