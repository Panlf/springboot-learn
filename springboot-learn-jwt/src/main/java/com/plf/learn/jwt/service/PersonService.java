package com.plf.learn.jwt.service;

import com.plf.learn.jwt.bean.Person;
import com.plf.learn.jwt.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Panlf
 * @date 2019/5/16
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public Person findPersonByUsername(String username){
        return personRepository.findPersonByUsername(username);
    }
}
