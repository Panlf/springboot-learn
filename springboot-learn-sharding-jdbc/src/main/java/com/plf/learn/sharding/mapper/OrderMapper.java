package com.plf.learn.sharding.mapper;

import com.plf.learn.sharding.bean.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Panlf
 * @date 2019/6/12
 */
@Mapper
public interface OrderMapper {

    @Insert("insert into t_order values(#{id},#{orderId},#{userId},#{username})")
    public void insertOrder(Order order);

    @Select("select * from t_order where id=#{id}")
    public Order findById(@Param("id") long id);
}
