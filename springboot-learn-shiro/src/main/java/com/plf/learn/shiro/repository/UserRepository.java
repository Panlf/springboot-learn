package com.plf.learn.shiro.repository;

import com.plf.learn.shiro.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Panlf
 * @date 2019/6/6
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findDistinctByUsername(String username);
}
