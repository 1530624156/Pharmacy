package com.mavis.dao;

import com.mavis.entity.Admin;
import com.mavis.utils.jdbc.MyJdbc;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-08-26 09:44
 **/

public class AdminDAO {

    public boolean login(Admin admin) throws Exception {
        Connection conn = MyJdbc.getConn4druid();
        String sql = "select * from admin where username = ? and password = ?;";
        List<HashMap> hashMaps = MyJdbc.exQuery(conn, sql, admin.getUsername(), admin.getPassword());
        conn.close();
        if (hashMaps.size()>0){
            return true;
        }else{
            return false;
        }
    }
}