package com.plf.learn.sharding.bean;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author Panlf
 * @date 2019/6/12
 */
@Data
@Alias("t_order")
public class Order {
    private Long id;
    private Long orderId;
    private Long userId;
    private String username;
}
