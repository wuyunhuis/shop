package com.shop.mapper;

import com.shop.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface OrdersMapper {


    int insertOrders(Orders orders);

    @Select("select * from orders where uid=#{uid}")
    List<Orders> findOrderByUid(@Param(value = "uid") Integer uid);

    @Update("update orders set state=#{state} where oid=#{oid}")
    void updateOrderState( @Param(value = "state")Integer state,@Param(value = "oid")String oid);

    @Select("select * from orders where bid=#{bid}")
    List<Orders> findOrderListByBid(@Param(value = "bid")Integer bid);
}