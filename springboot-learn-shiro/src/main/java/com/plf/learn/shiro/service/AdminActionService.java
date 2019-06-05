package com.plf.learn.shiro.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

/**
 * admin 用户权限测试
 * @author Panlf
 * @date 2019/6/5
 */
@Service
@Slf4j
public class AdminActionService {

    @RequiresRoles("admin")
    public void logVisitService(){
        log.info("AdminActionService Admin Role Visit--");
    }
}
