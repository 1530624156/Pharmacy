package com.mavis.dao;

import com.mavis.entity.Type;
import com.mavis.entity.User;
import com.mavis.utils.jdbc.MyJdbc;

import java.sql.Connection;
import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-08-27 09:09
 **/

public class TypeDAO {

    //根据tid删除类别
    public boolean deleteType(int tid) throws Exception {
        boolean result = false;
        Connection conn = MyJdbc.getConn4druid();
        String sql = "delete from type where tid = ?;";
        int i = MyJdbc.exUpdate(conn, sql, tid);
        conn.close();
        if (i>0){
            result = true;
        }
        return result;
    }

    //更新类别信息
    public boolean updateType(int tid,Type type) throws Exception {
        boolean result = false;
        Connection conn = MyJdbc.getConn4druid();
        String sql = "update type set tname = ?,note = ? where tid = ?;";
        int i = MyJdbc.exUpdate(conn, sql, type.getTname(), type.getNote(), tid);
        if (i>0){
            result = true;
        }
        return result;
    }

    //根据tid获取类型
    public List<Type> selectByTid(int id) {
        List<Type> types = null;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "select * from type where tid = ?;";
            types = MyJdbc.exQuery4class(conn, Type.class, sql, id);
            conn.close();
            return types;
        } catch (Exception e) {
            return null;
        }
    }

    //添加类别
    public boolean addType(Type type) throws Exception {
        boolean result = false;
        Connection conn = MyJdbc.getConn4druid();
        String sql = "insert into type (tname,note) values (?,?);";
        int i = MyJdbc.exUpdate(conn, sql, type.getTname(), type.getNote());
        if (i>0){
            result = true;
        }
        return result;
    }

    //获取所有类型
    public List<Type> getAllType() throws Exception {
        Connection conn = MyJdbc.getConn4druid();
        String sql = "select * from type;";
        List<Type> Types = MyJdbc.exQuery4class(conn, Type.class, sql);
        conn.close();
        return Types;
    }
}