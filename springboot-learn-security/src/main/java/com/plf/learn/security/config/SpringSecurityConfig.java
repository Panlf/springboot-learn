package com.plf.learn.security.config;

import com.plf.learn.security.handler.MyAuthenticationFailtureHandler;
import com.plf.learn.security.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SpringSecurity配置类
 * 
 * @author Panlf
 */
@Configuration
@EnableWebSecurity //启动SpringSecurity过滤器链
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)//基于方法的授权
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{


	@Autowired
	private MyAuthenticationFailtureHandler myAuthenticationFailtureHandler;

	/**
	 * 替代之前配置
	 * 		<security:http>
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			//多权限控制
			//.antMatchers("/product/add").access("hasAnyAuthority('ROLE_ADD_PRODUCT)' and  hasAnyAuthority('ROLE_UPDATE_PRODUCT')")
			.antMatchers("/product/add").hasAnyAuthority("ROLE_ADD_PRODUCT")
			.antMatchers("/product/update").hasAnyAuthority("ROLE_UPDATE_PRODUCT")
			.antMatchers("/product/list").hasAnyAuthority("ROLE_LIST_PRODUCT")
			.antMatchers("/product/delete").hasAnyAuthority("ROLE_DELETE_PRODUCT")
			.antMatchers("/login").permitAll()
			.antMatchers("/**")
			.fullyAuthenticated()
			.and()
			.formLogin().loginPage("/login")
			.loginProcessingUrl("/securityLogin").failureHandler(myAuthenticationFailtureHandler)
			.and()
			//Session控制
			//1、always 如果没有session存在就创建一个
			//2、ifRequired	如果需要就创建一个session登录时(默认)
			//3、never	SpringSecurity将不会创建Session，但是如果应用中其他地方创建了Session，那么SpringSecurity将会使用它
			//4、stateless SpringSecurity将绝对不会创建Session，也不使用Session
					//并且暗示不使用cookie，所以每个请求都需要重新进行身份验证。这种无状态架构适用于Rest API及其无状态认证机制
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and().rememberMe().and()
			.csrf().disable();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService myUserDetailService() {
		return new MyUserDetailService();
	}
	
	/**
	 * 替代之前配置
	 * 		<security:authentication-manager>
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//动态分配
		auth.userDetailsService(myUserDetailService()).passwordEncoder(bCryptPasswordEncoder());
		
		//硬编码
		/*auth.inMemoryAuthentication()
			.passwordEncoder(new BCryptPasswordEncoder())
			.withUser("zs")
			.password(new BCryptPasswordEncoder().encode("123"))
			.authorities("PRODUCT_ADD");*/
		
	}
}
