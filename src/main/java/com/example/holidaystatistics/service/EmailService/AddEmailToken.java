package com.example.holidaystatistics.service.EmailService;

import com.example.holidaystatistics.entity.EmailToken;
import com.example.holidaystatistics.repository.EmailTokenRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author nitmali@126.com
 * @date 2018/5/9 14:35
 */

@Service
public class AddEmailToken {
    @Resource
    private EmailTokenRepository emailTokenRepository;

    void addEmailToke(String email,String userId,String sendTime,EmailToken.EmailType emailType){
        EmailToken emailToken = emailTokenRepository.findEmailTokenByEmail(email);
        if (emailToken != null && emailToken.getEmailType() == emailType){
            emailTokenRepository.delete(emailToken);
        }
        emailToken = new EmailToken(email,userId,sendTime, emailType);
        emailTokenRepository.save(emailToken);
    }

}
