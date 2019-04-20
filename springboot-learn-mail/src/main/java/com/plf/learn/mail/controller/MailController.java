package com.plf.learn.mail.controller;

import com.plf.learn.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Panlf
 * @date 2019/4/20
 */
@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @RequestMapping("/sendSimpleMail")
    public String sendSimpleMail(){
        mailService.sendSimpleMail("", "SpringBoot简单邮件测试", "测试数据SpringBoot");
        return "success";
    }

    @RequestMapping("/sendHtmlMail")
    public String sendHtmlMail(){
        mailService.sendHtmlMail("", "SpringBootHTML邮件测试",
                "<html><body><h1>邮件标题</h1><font color='blue'>Hello</font></body><html>");
        return "success";
    }

    @RequestMapping("/sendMailWithFile")
    public String sendMailWithFile(){
        mailService.sendMailWithFile("", "SpringBoot附件邮件测试",
                "文件邮件","E:\\temp\\2.txt");
        return "success";
    }

    @RequestMapping("/sendMailWithImg")
    public String sendMailWithImg(){
        String imgId="2";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:"+imgId + "\'></body></html>";
        mailService.sendMailWithImg("", "SpringBoot图片邮件测试",
                content,"E:\\temp\\10.jpg",imgId);
        return "success";
    }
}
