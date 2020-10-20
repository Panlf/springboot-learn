package com.plf.learn.mybatisplus.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
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
	
	@TableField(fill = FieldFill.INSERT)
	private Date createtime;

	@TableField(fill=FieldFill.UPDATE)
	private Date updatetime;
	
	@TableField
	@TableLogic
	private Integer delstatus;
}
