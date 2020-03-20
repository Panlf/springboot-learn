package com.plf.learn.thread.controller;

import com.plf.learn.thread.base.Message;
import com.plf.learn.thread.entity.User;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author Panlf
 * @date 2020/3/20
 */
@RestController
@RequestMapping("user")
public class ThreadController {


    @GetMapping("sign")
    public Message sign(){
        return Message.fail("讲道理你需要登录啊!访问/thread/user/login",null);
    }

    @GetMapping("login")
    public Message login(String username, String password, HttpSession httpSession){
        User user = new User(UUID.randomUUID().toString(),
                username,
                DigestUtils.md5DigestAsHex(password.getBytes()));
        httpSession.setAttribute("user",user);
        return Message.success("登录成功!可访问/thread/user/index",null);
    }

    @GetMapping("/index")
    public Message index(){
        return Message.success("主页内容来了!",null);
    }

    @GetMapping("/error")
    public Message error(){
        return Message.fail("尴尬!出错了!",null);
    }

    @GetMapping("loginout")
    public Message loginout(HttpSession httpSession){
        httpSession.invalidate();
        return Message.success("登出",null);
    }
}
