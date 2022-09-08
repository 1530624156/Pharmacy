package com.mavis.services;

import com.mavis.dao.WorkerDAO;
import com.mavis.entity.Worker;

import java.util.List;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-08-26 10:03
 **/

public class WorkerService {
    WorkerDAO workerDAO = new WorkerDAO();

    //更新员工操作
    public boolean updateWorker(Worker worker,int wid){
        return workerDAO.updateWorker(worker,wid);
    }

    //根据工号查询员工
    public List<Worker> getWorkerByWid(int wid){
        return workerDAO.getWorkerByWid(wid);
    }

    //获取所有员工
    public List<Worker> getAllWorker(){
        return workerDAO.getAllWorker();
    }

    //根据工号删除员工
    public boolean deleteWorkerByWid(int wid) throws Exception {
        return workerDAO.deleteWorkerByWid(wid);
    }

    //添加员工
    public boolean addWorker(Worker worker) throws Exception {
        return workerDAO.addWorker(worker);
    }

    //员工登录
    public Worker login(Worker worker) {
        Worker result = workerDAO.login(worker);
        return result;
    }
}