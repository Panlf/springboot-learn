package com.plf.learn.staticftl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Panlf
 * @date 2019/11/15
 */
@Controller
public class TestController {


    @RequestMapping("/index")
    public String toIndex(Model model) {
        model.addAttribute("name", "lisi");
        return "index";
    }
}
