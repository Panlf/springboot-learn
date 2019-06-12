package com.plf.learn.sharding.controller;

import com.plf.learn.sharding.bean.Order;
import com.plf.learn.sharding.service.OrderService;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Panlf
 * @date 2019/6/12
 */
@RestController
@RequestMapping("order")
public class OrderController {

    final SnowflakeShardingKeyGenerator keyGenerator = new SnowflakeShardingKeyGenerator();

    @Autowired
    private OrderService orderService;

    @GetMapping("save")
    public String save(Order order){
        String keyId = keyGenerator.generateKey().toString();
        long id = Long.valueOf(keyId);
        order.setId(id);
        orderService.save(order);
        return "success";
    }

    @GetMapping("findById")
    public Order findById(Long id){
        return orderService.findById(id);
    }


}
