package com.plf.learn.document.service;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 解析文档，将文档数据保存到数据库
 * @author Panlf
 * @date 2020/2/13
 */
@Slf4j
@Service
public class AnalysisFileService {

	@Async
	public void insertdb() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i=0;i<10;i++) {
			log.info("正在处理+++{}",i);
		}
	}
}
