package com.plf.learn.mybatisplus.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName
@Data
public class User {
	@TableId
	private Integer id;
	@TableField
	private String username;
	@TableField
	private Date birthday;
	@TableField
	private Integer sex;
	@TableField
	private String address;

}
