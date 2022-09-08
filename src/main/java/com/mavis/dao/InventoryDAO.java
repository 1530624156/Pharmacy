package com.mavis.dao;

import com.mavis.entity.Inventory;
import com.mavis.utils.jdbc.MyJdbc;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-09-04 15:58
 **/

public class InventoryDAO {

    //获取所有记录
    public List<HashMap> getAllRecord(){
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "select i.id,m.mname,i.addnum,i.note,i.type,i.time from inventory i,medicine m where i.mid = m.mid;";
            List<HashMap> records = MyJdbc.exQuery(conn, sql);
            return records;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //增加一行记录
    public boolean addRecord(Inventory inventory){
        boolean result = false;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "insert into Inventory values (null,?,?,?,?,?);";
            int i = MyJdbc.exUpdate(conn, sql, inventory.getMid(), inventory.getAddnum(), inventory.getNote(),inventory.getType(),inventory.getTime());
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