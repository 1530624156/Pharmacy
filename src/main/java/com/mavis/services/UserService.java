package com.mavis.services;

import com.mavis.dao.UserDAO;
import com.mavis.entity.User;

import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-08-26 13:46
 **/

public class UserService {
    UserDAO userDAO = new UserDAO();

    //更新用户信息
    public boolean updateUser(int uid,User user){
        return userDAO.updateUser(uid,user);
    }

    //根据uid获取用户
    public List<User> selectByUid(int uid){
        return userDAO.selectByUid(uid);
    }

    //根据uid删除用户
    public boolean deleteUser(int uid) throws Exception {
        return userDAO.deleteUser(uid);
    }

    //添加用户
    public boolean addUser(User user) throws Exception {
        return userDAO.addUser(user);
    }

    //获取所有用户
    public List<User> getAllUser(){
        return userDAO.getAllUser();
    }
}