package com.plf.learn.redis.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Panlf
 * @date 2019/12/26
 */
public interface TokenService {
    /**
     * 创建token
     * @param username 用户名
     * @return
     */
    public String createToken(String username);

    /**
     * 检验Token
     * @param request
     * @return
     * @throws Exception
     */
    public boolean checkToken(HttpServletRequest request) throws Exception;
}
