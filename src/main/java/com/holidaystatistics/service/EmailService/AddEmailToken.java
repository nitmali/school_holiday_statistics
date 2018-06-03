package com.holidaystatistics.service.EmailService;

import com.holidaystatistics.entity.EmailToken;
import com.holidaystatistics.repository.EmailTokenRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nitmali@126.com
 * @date 2018/5/9 14:35
 */

@Service
public class AddEmailToken {
    @Resource
    private EmailTokenRepository emailTokenRepository;

    Boolean addEmailToke(String email, String userId, String sendTime, EmailToken.EmailType emailType) {

        EmailToken emailToken;

        try {
            emailToken = emailTokenRepository.findEmailTokenByEmailAndAndEmailType(email, emailType);
            if (emailToken != null && emailToken.getEmailType() == emailType) {
                emailTokenRepository.delete(emailToken);
            }
        } catch (Exception e) {
            List<EmailToken> emailTokenList =
                    emailTokenRepository.findAllByEmailAndAndEmailType(email, emailType);
            emailTokenRepository.delete(emailTokenList);
        }

        emailToken = new EmailToken(email, userId, sendTime, emailType);
        emailTokenRepository.save(emailToken);

        return true;
    }

}
