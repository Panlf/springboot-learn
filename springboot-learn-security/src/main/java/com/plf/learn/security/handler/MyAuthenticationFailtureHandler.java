package com.plf.learn.security.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

/**
 * 自定义登录失败的逻辑
 * @author Panlf
 * @date 2020/3/13
 */
@Component
public class MyAuthenticationFailtureHandler implements AuthenticationFailureHandler {

	private ObjectMapper objectMapper = new  ObjectMapper();
	

	/**
	 * 返回JSON数据，配合ajax使用，直接弹框用户体验更好
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//返回字符串给前端
		Map<String,Object> map = new HashMap<>();
		map.put("success",false);
		map.put("errorMsg",exception.getMessage());
		String json = objectMapper.writeValueAsString(map);
		
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(json);
	}

}
