package com.mavis.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mavis.entity.Worker;
import com.mavis.services.WorkerService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: Pharmacy
 * @description: 工人控制层
 * @author: Mavis
 * @create: 2022-08-28 01:31
 **/

@WebServlet("/worker")
public class WorkerController extends BaseServlet {
    //工作人员业务层
    WorkerService workerService = new WorkerService();

    //获取所有工作人员
    public void getAllWorker(HttpServletRequest request,HttpServletResponse response){
        List<Worker> allWorker = workerService.getAllWorker();
        int count = allWorker.size();
        //将对象进行json格式化
        JSONObject res = new JSONObject();
        res.put("code",0);
        res.put("msg","");
        res.put("count",count);
        res.put("data",allWorker);
        writeToPage(response,res.toJSONString());
    }

    //工作人员新增操作
    public void addWorker(HttpServletRequest request,HttpServletResponse response){
        int wid = Integer.parseInt(request.getParameter("wid"));
        String wpassword = request.getParameter("wpassword");
        String wname = request.getParameter("wname");
        String wtel = request.getParameter("wtel");
        Worker worker = new Worker(wid,wpassword,wname,wtel);
        try {
            boolean b = workerService.addWorker(worker);
            if (b == true){
                writeToPage(response,"添加成功");
            }else{
                writeToPage(response,"添加失败，请检查网络和数据是否正常");
            }
        } catch (Exception e) {
            e.printStackTrace();
            writeToPage(response,"添加失败，请检查网络和数据是否正常");
        }
    }

    //工作人员删除操作
    public void deleteWorkerByWid(HttpServletRequest request, HttpServletResponse response){
        int wid = Integer.parseInt(request.getParameter("wid"));
        try {
            boolean b = workerService.deleteWorkerByWid(wid);
            if (b == true){
                writeToPage(response,"删除成功");
            }else{
                writeToPage(response,"删除失败，请检查网络和数据是否正常");
            }
        } catch (Exception e) {
            e.printStackTrace();
            writeToPage(response,"删除失败，请检查网络和数据是否正常");
        }
    }

    //工作人员查询操作
    public void getWorkerByWid(HttpServletRequest request, HttpServletResponse response){
        Integer wid = getIntegerParams(request, "wid");
        if (wid != null){
            List<Worker> workers = workerService.getWorkerByWid(wid);
            if (workers != null && workers.size() != 0){
                Worker worker = workers.get(0);
                writeToPage(response, JSON.toJSONString(worker));
            }
        }
        writeToPage(response,null);

    }

    //工作人员更新操作
    public void updateWorker(HttpServletRequest request, HttpServletResponse response){
        Integer wid = getIntegerParams(request, "wid");
        String wname = getStringParams(request, "wname");
        String wpassword = getStringParams(request, "wpassword");
        String wtel = getStringParams(request, "wtel");
        Worker worker = new Worker();
        worker.setWname(wname);
        worker.setWpassword(wpassword);
        worker.setWtel(wtel);
        boolean b = workerService.updateWorker(worker, wid);
        writeToPage(response,b+"");
    }


    //工作人员登出操作
    public void workerLogout(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        session.removeAttribute("worker");
        writeToPage(resp,"退出登录成功");
    }

    //工作人员登陆操作
    public void workerLogin(HttpServletRequest req, HttpServletResponse resp){
        String wid_str = req.getParameter("wid");
        String wpassword = req.getParameter("wpassword");
        if(StringUtils.isEmpty(wid_str) || StringUtils.isEmpty(wpassword)){
            writeToPage(resp,"登录失败");
            return;
        }
        int wid = Integer.parseInt(wid_str);
        Worker worker = new Worker();
        worker.setWid(wid);
        worker.setWpassword(wpassword);
        Worker result = workerService.login(worker);
        if (result != null){
            HttpSession session = req.getSession();
            session.setAttribute("worker",result);
            writeToPage(resp,"登录成功");
        }
        writeToPage(resp,"登录失败");
    }

}