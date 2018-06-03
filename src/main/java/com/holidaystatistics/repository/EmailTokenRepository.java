package com.holidaystatistics.repository;

import com.holidaystatistics.entity.EmailToken;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author nitmali@126.com
 * @date 2018/5/9 14:31
 */
public interface EmailTokenRepository extends CrudRepository<EmailToken,Long> {
    EmailToken findEmailTokenByEmail(String email);

    EmailToken findEmailTokenByEmailAndAndEmailType(String email, EmailToken.EmailType emailType);

    List<EmailToken> findAllByEmailAndAndEmailType(String email, EmailToken.EmailType emailType);
}
