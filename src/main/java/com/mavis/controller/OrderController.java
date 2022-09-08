package com.mavis.controller;

import com.alibaba.fastjson.JSONObject;
import com.mavis.entity.Medicine;
import com.mavis.entity.Orderinfo;
import com.mavis.entity.Orders;
import com.mavis.services.MedicineService;
import com.mavis.services.OrderService;
import com.mavis.utils.date.MyDate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: Pharmacy
 * @description: 订单控制层
 * @author: Mavis
 * @create: 2022-09-07 15:07
 **/

@WebServlet("/order")
public class OrderController extends BaseServlet{
    MedicineService medicineService = new MedicineService();
    OrderService orderService = new OrderService();

    public void getOrderByOid(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String oid = getStringParams(req, "oid");
        List<Orders> orders = orderService.getOrderByOid(oid);
        if (orders != null && orders.size() != 0){
            Orders orders1 = orders.get(0);
            String mid = orders1.getMid();
            String[] split = mid.split(",");
            Orderinfo orderinfo = new Orderinfo();
            orderinfo.setOid(oid);
            orderinfo.setTime(orders1.getTime());
            orderinfo.setUname(orders1.getUname());
            List<Medicine> medicineList = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                List<Medicine> medicines = medicineService.getMedicineByMid(Integer.parseInt(split[i]));
                if (medicines != null && medicines.size() != 0){
                    Medicine medicine = medicines.get(0);
                    medicineList.add(medicine);
                }
            }
            Double totle = 0.00;
            for (int i = 0; i < medicineList.size(); i++) {
                totle = totle + medicineList.get(i).getPrice();
            }
            orderinfo.setTotle(totle);
            HttpSession session = req.getSession();
            session.setAttribute("orderinfo",orderinfo);
            session.setAttribute("medicineList",medicineList);
            resp.sendRedirect("orderinfo.jsp");
            System.out.println(orderinfo);
            System.out.println(medicineList);
        }
    }

    /**
     * 根据oid删除订单
     * @param req
     * @param resp
     */
    public void deleteOrderByOid(HttpServletRequest req,HttpServletResponse resp){
        String oid = getStringParams(req, "oid");
        boolean b = orderService.deleteOrderByOid(oid);
        if (b == true){
            writeToPage(resp,"删除成功");
        }else{
            writeToPage(resp,"删除失败，请检查网络是否通畅和数据是否正确");
        }
    }

    /**
     * 获取所有订单列表
     * @param req
     * @param resp
     */
    public void getAllOrder(HttpServletRequest req, HttpServletResponse resp){
        List<Orders> allOrders = orderService.getAllOrders();
        int count = allOrders.size();
        JSONObject res = new JSONObject();
        res.put("code",0);
        res.put("msg","");
        res.put("count",count);
        res.put("data",allOrders);
        writeToPage(resp,res.toJSONString());
    }

    /**
     * 添加订单
     * @param req
     * @param resp
     */
    public void addOrder(HttpServletRequest req, HttpServletResponse resp){
        String uname = getStringParams(req, "uname");
        String mid = getStringParams(req, "mid");
        long time = new Date().getTime();
        String oid = String.valueOf(time);
        String nowTime = MyDate.getNowTime();
        Orders orders = new Orders(oid,uname,mid,nowTime);
        boolean b = orderService.addOrder(orders);
        if (b == true) {
            writeToPage(resp,"添加成功");
        }else{
            writeToPage(resp,"添加失败");
        }
     }


}