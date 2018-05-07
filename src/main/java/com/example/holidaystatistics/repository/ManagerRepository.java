package com.example.holidaystatistics.repository;

import com.example.holidaystatistics.entity.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nitmali@126.com
 * @date 2018/5/7 20:53
 */

@Repository
public interface ManagerRepository extends CrudRepository<Manager, String> {
    Manager findManagerByManagerId(String managerId);
}
