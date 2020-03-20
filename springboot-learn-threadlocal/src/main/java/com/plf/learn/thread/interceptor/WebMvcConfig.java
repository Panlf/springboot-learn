package com.plf.learn.thread.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Autowired
	private AuthActionInterceptor authActionInterceptor;

	@Autowired
	private AuthUserInterceptor authUserInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authUserInterceptor).excludePathPatterns("/static/**").addPathPatterns("/**");
		registry.addInterceptor(authActionInterceptor).addPathPatterns("/user/index");
		super.addInterceptors(registry);
	}

	/**
	 * 放行静态资源
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}
}
