package com.mavis.services;

import com.mavis.dao.TypeDAO;
import com.mavis.entity.Type;

import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-08-27 09:09
 **/

public class TypeService {
    TypeDAO typeDAO = new TypeDAO();

    //根据tid删除类别
    public boolean deleteType(int tid) throws Exception {
        return typeDAO.deleteType(tid);
    }

    //更新类别信息
    public boolean updateType(int tid,Type type) throws Exception {
        return typeDAO.updateType(tid,type);
    }

    //根据tid获取类型
    public List<Type> selectByTid(int id) {
        return typeDAO.selectByTid(id);
    }

    //添加类别
    public boolean addType(Type type) throws Exception {
        return typeDAO.addType(type);
    }

    //获取所有类型
    public List<Type> getAllType() throws Exception {
        return typeDAO.getAllType();
    }
}