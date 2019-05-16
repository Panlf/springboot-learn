package com.plf.learn.jwt.controller;

import com.plf.learn.jwt.bean.Person;
import com.plf.learn.jwt.common.JwtResult;
import com.plf.learn.jwt.common.ResponseData;
import com.plf.learn.jwt.service.PersonService;
import com.plf.learn.jwt.utils.JwtUtils;
import com.plf.learn.jwt.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Panlf
 * @date 2019/5/14
 */
@RestController
@Slf4j
public class HelloController {

    @Autowired
    private PersonService personService;

    @Resource
    public RedisUtils redisUtils;

    @GetMapping("/hello")
    public Object hello(HttpServletRequest request, HttpServletResponse response){
        //获取Token
        String token = request.getHeader("Authorization");
        //验证Token
        JwtResult result= JwtUtils.validateJWT(token);

        Object data  = null;
        Integer code = 200;
        String msg = "";

        if(result.isSuccess()){
            msg = "授权通过，返回数据";
            //重新生成token，就是为了重置token有效期
            token = JwtUtils.createJWT(result.getClaims().getId()
                    , result.getClaims().getIssuer()
                    , result.getClaims().getSubject()
                    , 1*60*1000);
            data = "Hello,JWT Data!";
        }else{
            code = 500;
            msg =  "授权不通过，请重新登录";
        }
        return ResponseData.result(code,data,msg,token);
    }

    @PostMapping("/login")
    public ResponseData login(String username,String password){
        log.info("登录授权");
        Person person = personService.findPersonByUsername(username);
        Object data  = null;
        String jwtToken = null;
        Integer code = 200;
        String msg = "";
        if(person == null){
            code = 404;
            msg = "无此用户";
        }else{
            if (!person.getPassword().equals(password)){
                code = 500;
                msg = "密码错误";
            }else{
                // 因为JWT不能存储敏感信息，所以这里使用Redis存储敏感数据，同时设置过期时间
                //Redis这里也可防多登陆等进一步功能
                redisUtils.insertStr(person.getId()+"-"+person.getUsername(),person.getPassword(),1*60);
                msg = "登陆成功";
                //用户标识ID
                jwtToken = JwtUtils.createJWT(UUID.randomUUID().toString()
                        //签发者
                        , "learn-jwt"
                        //用户
                        , JwtUtils.generalSubject(username)
                        //过期时间
                        , 1*60*1000);
            }
        }
        return ResponseData.result(code,data,msg,jwtToken);
    }
}
