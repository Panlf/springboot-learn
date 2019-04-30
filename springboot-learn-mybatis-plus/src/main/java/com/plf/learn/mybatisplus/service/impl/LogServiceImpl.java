package com.plf.learn.mybatisplus.service.impl;

import java.util.List;

import com.plf.learn.mybatisplus.dto.LogDto;
import com.plf.learn.mybatisplus.entity.Log;
import com.plf.learn.mybatisplus.mapper.LogMapper;
import com.plf.learn.mybatisplus.service.LogService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

	@Override
	public void insertLog(Log log) {
		baseMapper.insert(log);
	}

	@Override
	public void deleteLog(Integer id) {
		baseMapper.deleteById(id);
	}

	@Override
	public Page<Log> getPageLog(Page<Log> page, String starttime) {
		return (Page<Log>) baseMapper.selectPage(page, new QueryWrapper<Log>().select("content,createtime,user_id").gt("createtime",starttime));
	}

	@Override
	public Page<LogDto> getLogAndName(Page<LogDto> page, String username) {
		List<LogDto> records = baseMapper.getLogAndName(page,username);
		page.setRecords(records);
		return page;
	}
}
