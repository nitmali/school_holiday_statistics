package com.example.holidaystatistics.service.EmailService;

import com.example.holidaystatistics.entity.Student;
import com.example.holidaystatistics.repository.StudentRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
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
    private ForgetPasswordEmailTemplate forgetPasswordEmailTemplate;

    @Resource
    private AddEmailToken addEmailToken;

    @Resource
    private Md5Token md5Token;
    public String sendForgetPasswordMail(String email) throws MessagingException {
        Student student = studentRepository.findStudentByEmail(email);
        if (student != null) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);

                String messageText;

                String url = "http://holiday.nitmali.com/public/reset_password?email="+email+"&token=";

                Date thisTime = new Date();

                String getTime = String.valueOf(thisTime.getTime());

                String address = url
                        + md5Token.getMD5(student.getStudentId() + email + getTime);

                messageText = forgetPasswordEmailTemplate.getEmailTemplate(address);

                helper.setFrom("holiday@nitmali.com");
                helper.setTo(email);
                helper.setSubject("Holiday System重置密码");

                helper.setText(messageText,true);
                mailSender.send(message);

                addEmailToken.addEmailToke(email,student.getStudentId(),getTime);

            }catch (Exception exception){
                System.err.println(exception.getMessage());
                return "error";
            }
            return "success";
        } else {
            return "not find email";
        }
    }
}
