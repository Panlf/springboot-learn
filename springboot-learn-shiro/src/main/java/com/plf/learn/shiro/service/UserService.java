package com.plf.learn.shiro.service;

import com.plf.learn.shiro.bean.User;
import com.plf.learn.shiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Panlf
 * @date 2019/6/6
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username){
        return userRepository.findDistinctByUsername(username);
    }
}
