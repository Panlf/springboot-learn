package com.plf.learn.caffeine.service;

import com.plf.learn.caffeine.dao.UserInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2021/8/16
 */
@Service
@CacheConfig(cacheNames = "userInfo")
@Slf4j
public class UserInfoService {

    @Resource
    UserInfoDao userInfoDao;

    @CachePut(key = "#id",value = "userInfo")
    public String addUserInfo(Integer id,String userInfo){
        log.info("UserInfoService.addUserInfo");
        userInfoDao.addUserInfo(id,userInfo);
        return userInfo;
    }

    @Cacheable(key = "#id",value = "userInfo")
    public String getUserById(Integer id){
        log.info("UserInfoService.getUserById");
        String userInfo = userInfoDao.getUserInfo(id);
        return userInfo;
    }

    @CachePut(key = "#id",value = "userInfo")
    public String updateUserInfo(Integer id,String userInfo){
        log.info("UserInfoService.updateUserInfo");
        userInfoDao.updateUserInfo(id,userInfo);
        return userInfo;
    }

    @CacheEvict(key = "#id")
    public void deleteUserById(Integer id) {
        log.info("UserInfoService.deleteById");
        userInfoDao.deleteUserInfo(id);
    }

}
