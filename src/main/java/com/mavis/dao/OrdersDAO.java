package com.mavis.dao;

import com.mavis.entity.Orders;
import com.mavis.utils.jdbc.MyJdbc;

import java.sql.Connection;
import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-09-07 15:09
 **/

public class OrdersDAO {

    //根据oid获取订单
    public List<Orders> getOrderByOid(String oid){
        List<Orders> orders;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "select * from orders where oid = ?;";
            orders = MyJdbc.exQuery4class(conn,Orders.class,sql,oid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return orders;
    }

    //根据oid删除订单
    public boolean deleteOrderByOid(String oid){
        boolean result = false;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "delete from orders where oid = ?;";
            int i = MyJdbc.exUpdate(conn, sql, oid);
            if (i > 0){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    //获取所有订单
    public List<Orders> getAllOrders(){
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "select * from orders;";
            List<Orders> orders = MyJdbc.exQuery4class(conn, Orders.class, sql);
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //添加订单
    public boolean addOrder(Orders orders){
        boolean result = false;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "insert into orders (oid,uname,mid,time) values(?,?,?,?);";
            int i = MyJdbc.exUpdate(conn, sql, orders.getOid(), orders.getUname(), orders.getMid(), orders.getTime());
            if (i > 0){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}