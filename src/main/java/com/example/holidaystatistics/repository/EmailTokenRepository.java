package com.example.holidaystatistics.repository;

import com.example.holidaystatistics.entity.EmailToken;
import org.springframework.data.repository.CrudRepository;

/**
 * @author nitmali@126.com
 * @date 2018/5/9 14:31
 */
public interface EmailTokenRepository extends CrudRepository<EmailToken,Long> {
    EmailToken findEmailTokenByEmail(String email);
}
