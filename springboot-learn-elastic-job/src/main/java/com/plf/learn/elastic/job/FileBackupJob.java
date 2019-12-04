package com.plf.learn.elastic.job;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.plf.learn.elastic.bean.FileCustom;
import com.plf.learn.elastic.service.FileCustomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;



@Component
@Slf4j
public class FileBackupJob implements SimpleJob{

	//每次任务执行要备份文件的数量
	private final int FETCH_SIZE=2;

	@Autowired
	private FileCustomService fileCustomService;

	@Override
	public void execute(ShardingContext shardingContext) {
		System.out.println("作业分片:"+shardingContext.getShardingItem());
		//分片参数 (0=text,1=image,2=radio,3=video) 参数就是text、image...
		String jobParameter=shardingContext.getShardingParameter();
		
		//获取未备份文件
		List<FileCustom> fileCustoms = fetchUnBackupFiles(jobParameter,FETCH_SIZE);
		//进行文件备份
		backupFiles(fileCustoms);
	}
	
	/**
	 * 获取未备份文件
	 * @param count
	 * @return
	 */
	public List<FileCustom> fetchUnBackupFiles(String jobParamter,int count){
		//要取的文件列表
		List<FileCustom> fetchList = new ArrayList<>();
		//从数据库查询根据type和count查询
		fetchList=fileCustomService.findFileCustomList(jobParamter,count);
		return fetchList;
	}
	
	/**
	 * 文件备份
	 * @param files
	 */
	public void backupFiles(List<FileCustom> files) {
		for (FileCustom fileCustom : files) {
			fileCustomService.updateFileCustom(fileCustom.getId());
			log.info("time:{},备份文件,名称:{},类型:{}", LocalDateTime.now(),fileCustom.getName(),fileCustom.getType());
		}
	}

}
