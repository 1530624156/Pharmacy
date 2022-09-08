package com.mavis.controller;

import com.alibaba.fastjson.JSONObject;
import com.mavis.entity.Inventory;
import com.mavis.services.InventoryService;
import com.mavis.services.MedicineService;
import com.mavis.utils.date.MyDate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-09-04 15:55
 **/

@WebServlet("/inventory")
public class InventoryController extends BaseServlet{

    InventoryService inventoryService = new InventoryService();
    MedicineService medicineService = new MedicineService();

    /**
     * 获取所有记录
     * @param req
     * @param resp
     */
    public void getAllRecord(HttpServletRequest req,HttpServletResponse resp){
        List<HashMap> allRecord = inventoryService.getAllRecord();
        int count = allRecord.size();
        JSONObject res = new JSONObject();
        res.put("code",0);
        res.put("msg","");
        res.put("count",count);
        res.put("data",allRecord);
        //writeToPage(resp, JSONObject.toJSONString(String.valueOf(allRecord)));
        //System.out.println(res.toJSONString());
        writeToPage(resp, res.toJSONString());
    }

    /**
     * 出库操作
     * @param req
     * @param resp
     */
    public void output(HttpServletRequest req, HttpServletResponse resp){
        Integer mid = getIntegerParams(req,"mid");
        Integer reducenum = getIntegerParams(req, "reducenum");
        String note = getStringParams(req, "note");
        String type = "reduce";
        Inventory inventory = new Inventory();
        inventory.setMid(mid);
        inventory.setAddnum(reducenum);
        inventory.setNote(note);
        inventory.setType(type);
        boolean b = medicineService.reduceNumber(mid, reducenum);
        if (b == true){
            boolean b1 = inventoryService.addRecord(inventory);
            writeToPage(resp,b1+"");
        }else {
            writeToPage(resp,b+"");
        }
    }

    /**
     * 入库操作
     * @param req
     * @param resp
     */
    public void into(HttpServletRequest req, HttpServletResponse resp){
        Integer mid = getIntegerParams(req,"mid");
        Integer addnum = getIntegerParams(req, "addnum");
        String note = getStringParams(req, "note");
        String type = "add";
        String time = MyDate.getNowTime();
        Inventory inventory = new Inventory();
        inventory.setMid(mid);
        inventory.setAddnum(addnum);
        inventory.setNote(note);
        inventory.setType(type);
        inventory.setTime(time);
        boolean b = medicineService.addNumber(mid, addnum);
        if (b == true){
            boolean b1 = inventoryService.addRecord(inventory);
            writeToPage(resp,b1+"");
        }else {
            writeToPage(resp,b+"");
        }
    }
}