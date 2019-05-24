package com.plf.learn.jetcache.controller;

import com.plf.learn.jetcache.bean.Person;
import com.plf.learn.jetcache.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Panlf
 * @date 2019/5/24
 */
@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("get")
    public Person getById(Integer id){
        return personService.getPersonById(id);
    }

    @PostMapping("update")
    public String updatePerson(Person person){
        personService.updatePerson(person);
        return "success";
    }

    @GetMapping("delete")
    public String deleteById(Integer id){
        personService.deletePerson(id);
        return "success";
    }
}
