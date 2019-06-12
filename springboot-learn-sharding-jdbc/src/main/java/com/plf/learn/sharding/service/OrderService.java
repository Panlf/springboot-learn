package com.plf.learn.sharding.service;

import com.plf.learn.sharding.bean.Order;
import com.plf.learn.sharding.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Panlf
 * @date 2019/6/12
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public void save(Order order){
        orderMapper.insertOrder(order);
    }

    public Order findById(Long id){
        return orderMapper.findById(id);
    }

}