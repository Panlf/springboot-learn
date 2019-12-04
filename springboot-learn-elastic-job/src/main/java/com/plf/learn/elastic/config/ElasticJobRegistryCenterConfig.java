package com.plf.learn.elastic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

@Configuration
public class ElasticJobRegistryCenterConfig {

	//zookeeper端口
	private static final int ZOOKEEPER_PORT = 2181;
	//zookeeper链接
	private static final String ZOOKEEPER_CONNECTION_STRING="localhost:"+ZOOKEEPER_PORT;
	//定时任务命名空间
	private static final String JOB_NAMESPACE="elastic-job-example-java";
	
	
	//zk配置及创建注册中心
	@Bean(initMethod="init")
	private static CoordinatorRegistryCenter setUpRegitryCenter() {
		//zk配置
		ZookeeperConfiguration zookeeperConfiguration=new ZookeeperConfiguration(ZOOKEEPER_CONNECTION_STRING,JOB_NAMESPACE);
		//减少zk超时时间
		zookeeperConfiguration.setBaseSleepTimeMilliseconds(100);
		
		//创建注册中心
		CoordinatorRegistryCenter zookeeperRegistryCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
		
		return zookeeperRegistryCenter;
	}
	
}
