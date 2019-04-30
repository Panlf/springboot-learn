package com.plf.learn.mybatisplus.service;

import java.util.List;

import com.plf.learn.mybatisplus.entity.User;

public interface UserService {
	public List<User> listUser(String username);
}
