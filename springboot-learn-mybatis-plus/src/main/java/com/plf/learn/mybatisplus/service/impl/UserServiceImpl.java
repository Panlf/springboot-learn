package com.plf.learn.mybatisplus.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plf.learn.mybatisplus.entity.User;
import com.plf.learn.mybatisplus.mapper.UserMapper;
import com.plf.learn.mybatisplus.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService {

	@Override
	public List<User> listUser(String username) {
		return baseMapper.selectList(new QueryWrapper<User>().like("username", username));
	}

}
