package com.plf.learn.share.session.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Panlf
 * @date 2019/12/4
 */
@RestController
public class ShareSessionController {


    @Value("${server.port}")
    public int port;

    @GetMapping("/create/{name}")
    public String createSession(HttpServletRequest request, @PathVariable String name){
        HttpSession session=request.getSession();
        session.setAttribute("name",name);
        return "当前端口"+port+"项目的session.id:"+session.getId()+",name为"+name+"已成功存入session";

    }

    @GetMapping("/get")
    public String getSession(HttpServletRequest request){
        HttpSession session=request.getSession();
        return  "当前端口"+port+"项目的session.id:"+session.getId()+",name为"+session.getAttribute("name");
    }
}
