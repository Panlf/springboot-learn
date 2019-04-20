package com.plf.learn.mail.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author Panlf
 * @date 2019/4/20
 */
@Service
@Slf4j
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendSimpleMail(String to,String subject,String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            javaMailSender.send(message);
            log.info("简单邮件已经发送了");
        } catch (Exception e) {
            log.error("简单邮件发送出现了异常，发送失败");
        }
    }

    public void sendHtmlMail(String to,String subject,String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            javaMailSender.send(message);
            log.info("HTML邮件已经发送了");
        } catch (Exception e) {
            log.error("HTML邮件发送出现了异常，发送失败");
        }
    }

    public void sendMailWithFile(String to,String subject,String content,String filepath) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            FileSystemResource file = new FileSystemResource(new File(filepath));
            helper.addAttachment(file.getFilename(),file);
            javaMailSender.send(message);

            log.info("文件邮件已经发送了");
        } catch (Exception e) {
            log.error("文件邮件发送出现了异常，发送失败");
        }
    }

    public void sendMailWithImg(String to,String subject,String content,String imgPath,String imgId) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            FileSystemResource file = new FileSystemResource(new File(imgPath));
            log.info("filename:{}",file.getFilename());
            helper.addInline(imgId,file);
            javaMailSender.send(message);
            log.info("图片邮件已经发送了");
        } catch (Exception e) {
            log.error("图片邮件发送出现了异常，发送失败，原因是：{}",e.getMessage());
        }
    }
}
