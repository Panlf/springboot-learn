package com.plf.learn.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {

	@RequestMapping("/add")
	public String add() {
		return "product/add";
	}
	
	
	@RequestMapping("/update")
	public String update() {
		return "product/update";
	}
	
	@RequestMapping("/list")
	public String list() {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal!=null) {
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				String username = userDetails.getUsername();
				log.info("当前登录用户:{}",username);
			}
		}
		
		return "product/list";
	}
	
	@RequestMapping("/delete")
	public String delete() {
		return "product/delete";
	}
}
