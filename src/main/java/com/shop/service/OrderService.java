package com.shop.service;

import com.shop.entity.Orders;
import com.shop.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderService {
    @Autowired
    OrdersMapper ordersMapper;

    public void insertOrders(Orders orders){
        ordersMapper.insertOrders(orders);
    }

    public List<Orders> findOrderList(Integer uid) {
        return  ordersMapper.findOrderByUid(uid);
    }

    public void updateOrderState(Integer state,String oid) {
        ordersMapper.updateOrderState(state,oid);
    }

    public List<Orders> findOrderListByBid(Integer bid) {
        return ordersMapper.findOrderListByBid(bid);
    }
}
