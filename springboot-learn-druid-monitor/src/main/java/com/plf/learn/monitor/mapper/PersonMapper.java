package com.plf.learn.monitor.mapper;

import com.plf.learn.monitor.bean.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Panlf
 * @date 2019/7/11
 */
@Repository
public interface PersonMapper {
    public List<Person> findPerson();
}
