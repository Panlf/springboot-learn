package com.plf.learn.share.session.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Slf4j
public class ShareSessionController {

    @GetMapping("/create/{name}")
    public ResponseEntity<Void> createSession(HttpServletRequest request, @PathVariable String name){
        HttpSession session=request.getSession();
        session.setAttribute("name",name);
        log.info("当前项目的session.id:{},name为{}已成功存入session",session.getId(),name);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/get")
    public ResponseEntity<Void> getSession(HttpServletRequest request){
        HttpSession session=request.getSession();
        log.info("当前项目的session.id:{},获取name为{}",session.getId(),session.getAttribute("name"));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
