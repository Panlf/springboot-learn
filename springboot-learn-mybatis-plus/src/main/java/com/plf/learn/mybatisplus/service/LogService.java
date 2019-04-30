package com.plf.learn.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.plf.learn.mybatisplus.dto.LogDto;
import com.plf.learn.mybatisplus.entity.Log;

public interface LogService extends IService<Log>{
	
	public void insertLog(Log log);
	
	public void deleteLog(Integer id);
	
	public Page<Log> getPageLog(Page<Log> page, String strattime);

	public Page<LogDto> getLogAndName(Page<LogDto> page, String username);
}
