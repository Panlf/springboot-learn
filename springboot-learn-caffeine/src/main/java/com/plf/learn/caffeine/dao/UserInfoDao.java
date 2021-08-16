package com.plf.learn.caffeine.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author panlf
 * @date 2021/8/16
 */
@Repository
@Slf4j
public class UserInfoDao {
    public Map<Integer,String> map = new HashMap<>();


    public String addUserInfo(Integer id,String userInfo){
        log.info("数据库操作 - add");

        map.put(id,userInfo);

        return userInfo;
    }

    public String getUserInfo(Integer id){
        log.info("数据库操作 - get");

        return map.get(id);
    }

    public String updateUserInfo(Integer id,String userInfo){
        log.info("数据库操作 - update");

        map.put(id,userInfo);

        return userInfo;
    }

    public void deleteUserInfo(Integer id){
        log.info("数据库操作 - delete");

        map.remove(id);
    }
}
