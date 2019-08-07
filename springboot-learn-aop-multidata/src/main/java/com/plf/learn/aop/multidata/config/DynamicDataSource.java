package com.plf.learn.aop.multidata.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源的实现类
 * @author Panlf
 * @date 2019/8/7
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 	数据源路由，此方用于产生要选取的数据源逻辑名称
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }
}

