package com.plf.learn.elastic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobRootConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;

@Configuration
public class ElasticJobConfig {

	@Autowired
	SimpleJob fileBackupJob;
	
	@Autowired
	CoordinatorRegistryCenter coordinatorRegistryCenter;
	
	@Bean(initMethod="init")
	public SpringJobScheduler initSimpleElasticJob() {
		SpringJobScheduler springJobScheduler=new SpringJobScheduler(fileBackupJob, coordinatorRegistryCenter, createJobConfiguration(fileBackupJob.getClass()
				,"0/3 * * * * ?",4,"0=text,1=image,2=video"));
		return springJobScheduler;
	}
	
	/**
	 * 配置任务详细信息
	 * @param jobClass	任务执行类
	 * @param cron	执行策略
	 * @param shardingTotalCount	分片数量
	 * @param shardingItemParameters	分片个性化参数
	 * @return
	 */
	@SuppressWarnings("unused")
	private LiteJobConfiguration createJobConfiguration(final Class<? extends SimpleJob> jobClass,
			final String cron,
			final int shardingTotalCount,
			final String shardingItemParameters) {
		JobCoreConfiguration.Builder jobCoreConfigurationBuilder = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount);
		if(!org.springframework.util.StringUtils.isEmpty(shardingItemParameters)) {
			jobCoreConfigurationBuilder.shardingItemParameters(shardingItemParameters);
		}
		JobCoreConfiguration jobCoreConfiguration = jobCoreConfigurationBuilder.build();
		
		SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(jobCoreConfiguration,jobClass.getCanonicalName());
	
		//定义Lite作业跟配置
		JobRootConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true).build();
		
		return (LiteJobConfiguration) simpleJobRootConfig;
		
	}
	
	
	//创建支持dataFlow类型作业的配置信息
	private LiteJobConfiguration createFlowJobConfiguration(final Class<? extends ElasticJob> jobClass,
			final String cron,
			final int shardingTotalCount,
			final String shardingItemParameters) {
		JobCoreConfiguration.Builder jobCoreConfigurationBuilder = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount);
		if(!org.springframework.util.StringUtils.isEmpty(shardingItemParameters)) {
			jobCoreConfigurationBuilder.shardingItemParameters(shardingItemParameters);
		}
		JobCoreConfiguration jobCoreConfiguration = jobCoreConfigurationBuilder.build();
		
		//SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(jobCoreConfiguration,jobClass.getCanonicalName());
	
		//定义数据流类型任务配置
		DataflowJobConfiguration jobConfig = new DataflowJobConfiguration(jobCoreConfiguration,jobClass.getCanonicalName(),true);
		
		//定义Lite作业跟配置
		JobRootConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(jobConfig).overwrite(true).build();
		
		return (LiteJobConfiguration) simpleJobRootConfig;

	}
}
