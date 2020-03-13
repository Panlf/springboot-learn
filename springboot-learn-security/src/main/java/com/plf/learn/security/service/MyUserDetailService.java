package com.plf.learn.security.service;

import com.plf.learn.security.entity.Permission;
import com.plf.learn.security.entity.User;
import com.plf.learn.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Panlf
 * @date 2020/3/13
 */
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);

        //配置权限或者角色
		if (user != null) {
			List<Permission> permList = userMapper.findPermissionByUsername(username);
			List<GrantedAuthority> authorities = new ArrayList<>();

			for (Permission perm : permList) {
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(perm.getPermTag());
				authorities.add(grantedAuthority);
			}

			user.setAuthorities(authorities);
		}
        //List<GrantedAuthority> authoritys = new ArrayList<>();
        //authoritys.add(new SimpleGrantedAuthority("PRODUCT_ADD"));
        return user;
    }
}
