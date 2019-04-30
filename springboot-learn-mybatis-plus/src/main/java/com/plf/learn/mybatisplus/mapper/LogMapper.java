package com.plf.learn.mybatisplus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plf.learn.mybatisplus.dto.LogDto;
import com.plf.learn.mybatisplus.entity.Log;

@Repository
public interface LogMapper extends BaseMapper<Log>{

	@Select("select l.content,u.username,l.createtime from log l"
			+ " left join user u on l.user_id=u.id"
			+ " where u.username=#{username}")
	public List<LogDto> getLogAndName(Page<LogDto> page, @Param("username") String username);
}
 