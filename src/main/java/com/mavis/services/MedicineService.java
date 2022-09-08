package com.mavis.services;

import com.mavis.dao.MedicineDAO;
import com.mavis.entity.Medicine;

import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-09-01 18:46
 **/

public class MedicineService {

    MedicineDAO medicineDAO = new MedicineDAO();

    //库存减少
    public boolean reduceNumber(int mid,int reducenum){
        return medicineDAO.reduceNumber(mid,reducenum);
    }

    //库存增加
    public boolean addNumber(int mid,int addnum){
        return medicineDAO.addNumber(mid,addnum);
    }

    //更新药品信息
    public boolean updateMedicine(Medicine medicine,int mid){
        return medicineDAO.updateMedicine(medicine,mid);
    }

    //根据mid获取药品
    public List<Medicine> getMedicineByMid(int mid){
        return medicineDAO.getMedicineByMid(mid);
    }

    //删除药品
    public boolean deleteMedicineByMid(int mid){
        return medicineDAO.deleteMedicineByMid(mid);
    }

    //添加药品
    public boolean addMedicine(Medicine medicine){
        return medicineDAO.addMedicine(medicine);
    }

    //获取所有药品
    public List<Medicine> getAllMedicine(){
        return medicineDAO.getAllMedicine();
    }
}