package com.plf.learn.aop.multidata.config;

/**
 * 动态数据源的增加获取删除
 * @author Panlf
 * @date 2019/8/7
 */
public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void putDataSource(String name) {
        THREAD_LOCAL.set(name);
    }

    public static String getDataSource() {
        return THREAD_LOCAL.get();
    }

    public static void removeDataSource() {
        THREAD_LOCAL.remove();
    }
}
