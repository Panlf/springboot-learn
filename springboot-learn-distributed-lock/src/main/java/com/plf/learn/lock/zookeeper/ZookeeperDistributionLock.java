package com.plf.learn.lock.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author Panlf
 * @date 2019/5/21
 */
@Component
@Slf4j
public class ZookeeperDistributionLock implements InitializingBean {

    @Resource
    private CuratorFramework curatorFramework;

    private final static String ROOT_PATH_LOCK = "rootlock";

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 获取分布式锁
     * @param path
     */
    public void acquireLock(String path){
        String lockPath = "/"+ROOT_PATH_LOCK+"/"+path;
        while (true) {
            try {
                curatorFramework
                        .create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(lockPath);
                log.info("成功获取锁,锁为:{}", lockPath);
                break;
        } catch (Exception e) {
                log.error("获取锁失败,异常为:{},锁为:{},", e.getMessage(),lockPath);
                try {
                    if (countDownLatch.getCount() <= 0) {
                        countDownLatch = new CountDownLatch(1);
                    }
                    countDownLatch.await();
                } catch (InterruptedException e1) {
                    log.error("发生异常,异常原因:{}",e1.getMessage());
                }
            }
        }
    }

    /**
     * 释放锁
     * @param path
     * @return
     */
    public boolean releaseLock(String path){
        try {
            String lockPath = "/" + ROOT_PATH_LOCK + "/" + path;
            if (curatorFramework.checkExists().forPath(lockPath) != null) {
                curatorFramework.delete().forPath(lockPath);
            }
        } catch (Exception e) {
            log.error("发生异常,异常原因:{}",e.getMessage());
            return false;
        }
        return true;
    }

    private void addWatcher(String path) throws Exception {
        String lockPath;
        if (path.equals(ROOT_PATH_LOCK)) {
            lockPath = "/" + path;
        } else {
            lockPath = "/" + ROOT_PATH_LOCK + "/" + path;
        }

        final PathChildrenCache cache = new PathChildrenCache(curatorFramework, lockPath, false);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener((client, event) -> {
            if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                String oldPath = event.getData().getPath();
                log.info("成功释放锁,oldPath:{}", oldPath);
                if (oldPath.contains(path)) {
                    //释放计数器，让当前的请求获取锁
                    countDownLatch.countDown();
                }
            }
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        curatorFramework = curatorFramework.usingNamespace("lock-namespace");
        String path = "/" + ROOT_PATH_LOCK;
        try {
            if (curatorFramework.checkExists().forPath(path) == null) {
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(path);
            }
            addWatcher(ROOT_PATH_LOCK);
            log.info("root path 的 watcher 事件创建成功");
        } catch (Exception e) {
            log.error("连接Zookeeper失败,异常原因:{}", e.getMessage());
        }
    }
}
