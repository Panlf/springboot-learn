package com.plf.learn.caffeine.controller;

import com.plf.learn.caffeine.service.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2021/8/16
 */
@RestController
public class CaffeineController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("add")
    public String add(Integer id,String userInfo){
        userInfoService.addUserInfo(id,userInfo);
        return "success";
    }

    @GetMapping("get")
    public String get(Integer id){
        String userInfo = userInfoService.getUserById(id);
        return userInfo;
    }

    @GetMapping("update")
    public String update(Integer id,String userInfo){
        userInfoService.updateUserInfo(id,userInfo);
        return "success";
    }

    @GetMapping("delete")
    public String delete(Integer id){
        userInfoService.deleteUserById(id);
        return "success";
    }

}
