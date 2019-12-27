package com.plf.learn.redis.service.impl;

import com.plf.learn.redis.service.TokenService;
import com.plf.learn.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author Panlf
 * @date 2019/12/26
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public String createToken(String username) {
        //生成Token
        String token = UUID.randomUUID().toString();

        /**
         * key为用户名，UUID生成token，过期时间600s
         */
        redisUtils.set(username,token,600);

        return token;
    }

    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        String username = request.getParameter("username");

        if(username==null || username.trim().length()<=0){
            throw new Exception("未获取到用户，请重新登录");
        }

        if(token==null || token.trim().length()<=0){
            token = request.getParameter("token");
            if(token==null || token.trim().length()<=0){
                throw new Exception("无token,无法继续操作");
            }
        }

        if(!redisUtils.exits(username)){
            throw new Exception("token过期，请重新申请");
        }

        if(!redisUtils.get(username).equals(token)){
            throw new Exception("token不匹配，请重新访问");
        }

        //继续操作，删除无用token
        redisUtils.remove(token);

        return true;

    }
}
