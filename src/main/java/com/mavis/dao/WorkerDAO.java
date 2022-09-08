package com.mavis.dao;

import com.mavis.entity.Worker;
import com.mavis.utils.jdbc.MyJdbc;

import java.sql.Connection;
import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-08-26 10:03
 **/

public class WorkerDAO {

    //更新员工操作
    public boolean updateWorker(Worker worker,int wid){
        boolean result = false;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "update worker set wname = ?,wpassword = ?,wtel = ? where wid = ?;";
            int i = MyJdbc.exUpdate(conn, sql, worker.getWname(), worker.getWpassword(), worker.getWtel(), wid);
            if (i > 0){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    //根据工号查询员工
    public List<Worker> getWorkerByWid(int wid){
        List<Worker> worker;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "select * from worker where wid = ?;";
            worker = MyJdbc.exQuery4class(conn, Worker.class, sql, wid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return worker;
    }

    //获取所有员工
    public List<Worker> getAllWorker(){
        List<Worker> workers;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "select * from worker;";
            workers = MyJdbc.exQuery4class(conn, Worker.class, sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return workers;
    }

    //根据工号删除员工
    public boolean deleteWorkerByWid(int wid) throws Exception {
        boolean result = false;
        Connection conn = MyJdbc.getConn4druid();
        String sql = "delete from worker where wid = ?;";
        int i = MyJdbc.exUpdate(conn, sql, wid);
        if (i>0){
            result = true;
        }
        return result;
    }

    //添加员工
    public boolean addWorker(Worker worker) throws Exception {
        boolean result = false;
        Connection conn = MyJdbc.getConn4druid();
        String sql = "insert into worker values(?,?,?,?);";
        int i = MyJdbc.exUpdate(conn, sql, worker.getWid(), worker.getWpassword(), worker.getWname(), worker.getWtel());
        conn.close();
        if (i>0){
            result = true;
        }
        return result;
    }

    //员工登录
    public Worker login(Worker worker)  {
        Worker result;
        try {
            Connection conn = MyJdbc.getConn4druid();
            String sql = "select * from worker where wid = ? and wpassword = ?";
            List<Worker> objects = MyJdbc.exQuery4class(conn, Worker.class, sql, worker.getWid(), worker.getWpassword());
            conn.close();
            if (objects.size()>0){
                result = objects.get(0);
            }else{
                result = null;
            }
        } catch (Exception e) {
            return null;
        }
        return result;
    }
}