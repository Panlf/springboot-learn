package com.plf.learn.jetcache.service;

import com.plf.learn.jetcache.bean.Person;

/**
 * @author Panlf
 * @date 2019/5/24
 */
public interface PersonService {
    /**
     * 根据Id获取用户
     * @param id
     * @return
     */
    Person getPersonById(Integer id);

    /**
     * 更新用户
     * @param person
     */
    void updatePerson(Person person);

    /**
     * 删除用户
     * @param id
     */
    void deletePerson(Integer id);
}
