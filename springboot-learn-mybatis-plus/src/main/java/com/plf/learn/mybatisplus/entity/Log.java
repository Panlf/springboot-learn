package com.plf.learn.mybatisplus.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName
@Data
public class Log {
	@TableId
	private Integer id;
	
	@TableId
	private Integer userId;
	
	@TableField
	private String content;
	
	@TableField
	private Date createtime;
	
	@TableField
	@TableLogic
	private Integer delstatus;
}
