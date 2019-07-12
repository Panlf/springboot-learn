package com.plf.learn.monitor.controller;

import com.github.pagehelper.PageInfo;
import com.plf.learn.monitor.bean.Person;
import com.plf.learn.monitor.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Panlf
 * @date 2019/7/11
 */
@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("getPerson/{pagenum}/{pagesize}")
    public PageInfo<Person> getPerson(@PathVariable("pagenum") Integer pagenum, @PathVariable("pagesize") Integer pagesize){
        return personService.findPerson(pagenum, pagesize);
    }
}
