package com.mavis.dao;

import com.mavis.entity.Medicine;
import com.mavis.entity.User;
import com.mavis.utils.jdbc.MyJdbc;

import java.sql.Connection;
import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-09-01 18:46
 **/

public class MedicineDAO {

    //库存减少
    public boolean reduceNumber(int mid,int reducenum){
        boolean result = false;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "update medicine set number = number - ? where mid = ?;";
            int i = MyJdbc.exUpdate(conn, sql,reducenum,mid);
            if (i > 0){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result =  false;
        }
        return result;
    }

    //库存增加
    public boolean addNumber(int mid,int addnum){
        boolean result = false;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "update medicine set number = number + ? where mid = ?;";
            int i = MyJdbc.exUpdate(conn, sql,addnum,mid);
            if (i > 0){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result =  false;
        }
        return result;
    }

    //更新药品信息
    public boolean updateMedicine(Medicine medicine,int mid){
        boolean result = false;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "update medicine set mname = ?,type = ?,facturer = ?,note = ?,symptom = ?,used = ?,mimg = ?,taboo = ?,cost = ?,price = ?,profit = ? where mid = ?;";
            int i = MyJdbc.exUpdate(conn, sql, medicine.getMname(), medicine.getType(), medicine.getFacturer(),medicine.getNote(), medicine.getSymptom(), medicine.getUsed(), medicine.getMimg(), medicine.getTaboo(), medicine.getCost(),medicine.getPrice(),medicine.getProfit(), mid);
            if (i > 0){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    //根据mid获取药品
    public List<Medicine> getMedicineByMid(int mid){
        List<Medicine> medicines = null;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "select * from medicine where mid = ?;";
            medicines = MyJdbc.exQuery4class(conn, Medicine.class, sql, mid);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return medicines;
    }

    //删除药品
    public boolean deleteMedicineByMid(int mid){
        boolean result = false;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "delete from medicine where mid = ?;";
            int i = MyJdbc.exUpdate(conn, sql, mid);
            if (i > 0){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //添加药品
    public boolean addMedicine(Medicine medicine){
        boolean result = false;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "insert into medicine (mname,type,note,symptom,used,mimg,taboo,cost,price,profit,facturer) values (?,?,?,?,?,?,?,?,?,?,?);";
            int i = MyJdbc.exUpdate(conn, sql, medicine.getMname(), medicine.getType(), medicine.getNote(), medicine.getSymptom(), medicine.getUsed(), medicine.getMimg(), medicine.getTaboo(), medicine.getCost(), medicine.getPrice(),medicine.getProfit(),medicine.getFacturer());
            if (i > 0){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //获取所有药品
    public List<Medicine> getAllMedicine(){
        List<Medicine> medicines = null;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "select * from medicine;";
            medicines = MyJdbc.exQuery4class(conn, Medicine.class, sql);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return medicines;
    }

    //测试方法
    public static void main(String[] args) {
        MedicineDAO medicineDAO = new MedicineDAO();
        System.out.println(medicineDAO.getAllMedicine());
    }
}