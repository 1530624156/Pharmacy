package com.mavis.services;

import com.mavis.dao.InventoryDAO;
import com.mavis.entity.Inventory;

import java.util.HashMap;
import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-09-04 15:58
 **/

public class InventoryService {
    InventoryDAO inventoryDAO = new InventoryDAO();

    //获取所有记录
    public List<HashMap> getAllRecord(){
        return inventoryDAO.getAllRecord();
    }

    //增加一行记录
    public boolean addRecord(Inventory inventory){
        return inventoryDAO.addRecord(inventory);
    }

    //测试方法
    public static void main(String[] args) {
        InventoryDAO inventoryDAO = new InventoryDAO();
        System.out.println(inventoryDAO.getAllRecord());
    }
}