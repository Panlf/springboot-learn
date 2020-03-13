package com.plf.learn.security.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class User implements UserDetails{

	private Integer id;
	private String username;
	private String realname;
	private String password;
	/**
	 * 创建日期
	 */
	private Date createDate; 
	
	/**
	 * 最后登录时间
	 */
	private Date lastLogintime;
	
	/**
	 * 是否可用
	 */
	private boolean enabled;
	/**
	 * 是否过期
	 */
	private boolean accountNonExpired;
	/**
	 * 是否锁定
	 */
	private boolean accountNonLocked;
	
	/**
	 * 证书是否过期
	 */
	private boolean credentialsNonExpired;

	/**
	 * 用户拥有的所有权限
	 */
	public List<GrantedAuthority> authorities = new ArrayList<>();

}
