package com.mavis.dao;

import com.alibaba.fastjson.JSON;
import com.mavis.entity.User;
import com.mavis.utils.jdbc.MyJdbc;
import jdk.nashorn.internal.parser.JSONParser;

import java.sql.Connection;
import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-08-26 13:46
 **/

public class UserDAO {

    //更新用户信息
    public boolean updateUser(int uid,User user){
        boolean result = false;
        try {
            result = false;
            Connection conn = MyJdbc.getConn4druid();
            String sql = "update user set username = ?,email = ?,name = ?,tel = ? where uid = ?;";
            int i = MyJdbc.exUpdate(conn, sql, user.getUsername(),user.getEmail(),user.getName(),user.getTel(),uid);
            if (i>0){
                result = true;
            }
        } catch (Exception e) {
            return false;
        }
        return result;
    }

    //根据uid获取用户
    public List<User> selectByUid(int uid){
        List<User> Users = null;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "select * from user where uid = ?;";
            Users = MyJdbc.exQuery4class(conn, User.class, sql,uid);
            conn.close();
        } catch (Exception e) {
            return null;
        }
        return Users;
    }

    //根据uid删除用户
    public boolean deleteUser(int uid) throws Exception {
        boolean result = false;
        Connection conn = MyJdbc.getConn4druid();
        String sql = "delete from user where uid = ?;";
        int i = MyJdbc.exUpdate(conn, sql, uid);
        if (i>0){
            result = true;
        }
        return result;
    }

    //添加用户
    public boolean addUser(User user) throws Exception {
        boolean result = false;
        Connection conn = MyJdbc.getConn4druid();
        String sql = "insert into user (username,password,email,name,tel) values (?,?,?,?,?);";
        int i = MyJdbc.exUpdate(conn, sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), user.getTel());
        if (i>0){
            result = true;
        }
        return result;
    }

    //获取所有用户
    public List<User> getAllUser(){
        List<User> Users = null;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "select * from user;";
            Users = MyJdbc.exQuery4class(conn, User.class, sql);
            conn.close();
        } catch (Exception e) {
            return null;
        }
        return Users;
    }
}