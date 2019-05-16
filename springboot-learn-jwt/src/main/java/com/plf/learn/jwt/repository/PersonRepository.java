package com.plf.learn.jwt.repository;

import com.plf.learn.jwt.bean.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Panlf
 * @date 2019/5/16
 */
public interface PersonRepository extends CrudRepository<Person,Integer> {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public Person findPersonByUsername(String username);
}
