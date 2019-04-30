package com.plf.learn.mybatisplus.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LogDto{
	private String content;
	
	private Date createtime;
	
	private String username;

}
