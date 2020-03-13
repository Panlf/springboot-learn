package com.plf.learn.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/403")
	public String forbidden() {
		return "403";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
