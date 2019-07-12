package com.plf.learn.monitor.service;

import com.plf.learn.monitor.bean.Person;
import com.plf.learn.monitor.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Panlf
 * @date 2019/7/11
 */
@Service
public class PersonService {
    @Autowired
    private PersonMapper personMapper;

    public PageInfo<Person> findPerson(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Person> list = personMapper.findPerson();
        return PageInfo.of(list);
    }
}
