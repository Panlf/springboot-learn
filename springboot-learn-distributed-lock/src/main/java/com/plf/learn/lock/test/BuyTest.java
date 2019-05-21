package com.plf.learn.lock.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;


/**
 *  测试并发
 * @author Panlf
 * @date 2019/5/20
 */
@Slf4j
public class BuyTest {

    private RestTemplate restTemplate = new RestTemplate();

    private String url = "http://localhost:8004/lock/buyRedis";

    @Test
    public void ticketTest() {
        Buy tr = new Buy();
        Thread t1 = new Thread(tr, "A");
        Thread t2 = new Thread(tr, "B");
        Thread t3 = new Thread(tr, "C");
        Thread t4 = new Thread(tr, "D");
        t1.start();
        //t2.start();
        //t3.start();
       // t4.start();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class Buy implements  Runnable{
        @Override
        public void run() {
            while(true){
                Integer num = restTemplate.getForObject(url,Integer.class);
                if(num<=0){
                    break;
                }
            }
        }
    }
}

