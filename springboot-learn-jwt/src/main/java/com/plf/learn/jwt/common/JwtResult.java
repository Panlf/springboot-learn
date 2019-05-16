package com.plf.learn.jwt.common;

import io.jsonwebtoken.Claims;
import lombok.Data;

/**
 * @author Panlf
 * @date 2019/5/14
 */
@Data
public class JwtResult {
	
	/**
	 * 错误编码 在JwtUtils中定义常量
	 * 
	 * 200为正确
	 */
	private int errCode;
	
	/**
	 * 是否成功
	 */
	private boolean success;
	
	/**
	 * 验证过程中payload中的数据
	 */
	private Claims claims;

}
