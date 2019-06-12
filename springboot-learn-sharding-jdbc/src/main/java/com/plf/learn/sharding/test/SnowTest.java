package com.plf.learn.sharding.test;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

/**
 * @author Panlf
 * @date 2019/6/12
 */
public class SnowTest {
    public static void main(String[] args) {
        SnowflakeShardingKeyGenerator keyGenerator = new SnowflakeShardingKeyGenerator();
        System.out.println(keyGenerator.generateKey());
        System.out.println(keyGenerator.generateKey());
        System.out.println(keyGenerator.generateKey());

    }
}
