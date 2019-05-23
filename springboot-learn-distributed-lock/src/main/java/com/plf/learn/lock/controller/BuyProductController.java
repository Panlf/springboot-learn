package com.plf.learn.lock.controller;


import com.plf.learn.lock.redis.RedisDistributionLock;
import com.plf.learn.lock.redis.RedisUtils;
import com.plf.learn.lock.redis.RedissonDistributionLock;
import com.plf.learn.lock.zookeeper.ZookeeperDistributionLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Panlf
 * @date 2019/5/20
 */
@RestController
@Slf4j
public class BuyProductController {

    @Resource
    private RedisDistributionLock redisDistributionLock;

    @Resource
    private RedissonDistributionLock redissonDistributionLock;

    @Resource
    private ZookeeperDistributionLock zookeeperDistributionLock;

    @Resource
    private RedisUtils redisUtils;

    @GetMapping("buyRedis")
    public Integer buyRedis() {
        // 10s 有效时间
        long currentTime = System.currentTimeMillis() + 10*1000L;
        try{
            boolean flag = redisDistributionLock.tryLock("lock", String.valueOf(currentTime));
            if (flag) {
                Integer num = buy();
                return num;
            }
            return 0;
        }finally {
            redisDistributionLock.unlock("lock",String.valueOf(currentTime));
        }

    }

    @GetMapping("buyRedisson")
    public Integer buyRedisson() {
        String uuid = UUID.randomUUID().toString();
        log.info("生成uuid:{}", uuid);
        try {
            redissonDistributionLock.lock("lock",30);

            Integer num = buy();
            return num;

        } finally {
            redissonDistributionLock.unlock("lock");
        }
    }

    @GetMapping("buyZookeeper")
    public Integer buyZookeeper() {
        try {
            zookeeperDistributionLock.acquireLock("zookeeperLock");
            Integer num = buy();
            return num;
        } finally {
            zookeeperDistributionLock.releaseLock("zookeeperLock");
        }
    }

    public Integer buy(){
        Integer num = Integer.valueOf(redisUtils.getStr("number"));
        if(num>0){
            redisUtils.insertStr("number",String.valueOf(num-1));
            log.info("购买成功,库存:{}",(num-1));
            return (num-1);
        }else{
            log.info("购买失败，库存为0");
            return 0;
        }
    }
}
