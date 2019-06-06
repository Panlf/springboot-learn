package com.plf.learn.shiro.controller;

import com.plf.learn.shiro.service.AdminActionService;
import com.plf.learn.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Panlf
 * @date 2019/6/5
 */
@Controller
@Slf4j
public class UserController {

    @Autowired
    private AdminActionService adminActionService;

    /**
     * 我不是放在static的文件夹下，需要controller跳转
     * @return
     */
    @GetMapping("index")
    public String toLogin(){
        return "login";
    }

    @GetMapping("fail")
    public String toFail(){
        return "fail";
    }

    @GetMapping("success")
    public String toSuccess(){
        return "success";
    }

    @GetMapping("user")
    public String toUser(){
        return "user";
    }

    @GetMapping("admin")
    public String toAdmin(){
        return "admin";
    }

    @GetMapping("testService")
    public String testService(){
        adminActionService.logVisitService();
        return "success";
    }

    @PostMapping("login")
    public String Login(@RequestParam String username
            ,@RequestParam String password){
        Subject currentUser = SecurityUtils.getSubject();

        log.info("Login currentUser - {}",currentUser);

        if(!currentUser.isAuthenticated()){
            //把用户名和密码封装为UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            try{
                currentUser.login(token);
            }catch(AuthenticationException e){
                System.out.println("登录失败："+e.getMessage());
                return "fail";
            }
        }
        return "success";
    }
}
