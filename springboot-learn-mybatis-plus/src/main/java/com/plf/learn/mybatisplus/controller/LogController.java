package com.plf.learn.mybatisplus.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plf.learn.mybatisplus.dto.LogDto;
import com.plf.learn.mybatisplus.entity.Log;
import com.plf.learn.mybatisplus.service.LogService;

@RestController
@RequestMapping("/log")
public class LogController {

	@Autowired
	private LogService logService; 
	
	@RequestMapping("/add")
	public String addLog(Log log){
		log.setCreatetime(new Date());
		logService.insertLog(log);
		return "success";
	} 
	
	@RequestMapping("/delete")
	public String deleteLog(Integer id){
		logService.deleteLog(id);
		return "success";
	} 
	
	@RequestMapping("/listPage")
	public Page<Log> listPageLog(String starttime){
		return logService.getPageLog(new Page<Log>(1,2),starttime);
	} 
	
	@RequestMapping("/listLogName")
	public Page<LogDto> listLogAndName(String username){
		return logService.getLogAndName(new Page<LogDto>(1,2),username);
	} 
}
