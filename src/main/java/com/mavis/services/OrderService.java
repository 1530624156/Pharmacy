package com.mavis.services;

import com.mavis.dao.OrdersDAO;
import com.mavis.entity.Orders;

import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-09-07 15:09
 **/

public class OrderService {
    OrdersDAO orderDAO = new OrdersDAO();

    //根据oid获取订单
    public List<Orders> getOrderByOid(String oid){
        return orderDAO.getOrderByOid(oid);
    }

    //根据oid删除订单
    public boolean deleteOrderByOid(String oid){
        return orderDAO.deleteOrderByOid(oid);
    }

    //获取所有订单
    public List<Orders> getAllOrders(){
        return orderDAO.getAllOrders();
    }

    //添加订单
    public boolean addOrder(Orders orders){
        return orderDAO.addOrder(orders);
    }
}