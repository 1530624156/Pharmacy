package com.mavis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mavis.entity.Type;
import com.mavis.services.TypeService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: Pharmacy
 * @description: 类别控制层
 * @author: Mavis
 * @create: 2022-08-30 15:58
 **/

@WebServlet("/type")
public class TypeController extends BaseServlet{

    TypeService typeService = new TypeService();

    /**
     * 根据tid获取类别
     * @param req
     * @param resp
     */
    public void getTypeByTid(HttpServletRequest req, HttpServletResponse resp){
        Integer tid = Integer.parseInt(req.getParameter("tid"));
        if (tid != null){
            List<Type> types = typeService.selectByTid(tid);
            if (types != null){
                Type type = types.get(0);
                writeToPage(resp, JSON.toJSONString(type));
            }
        }
        writeToPage(resp,null);
    }

    /**
     * 更新类别操作
     * @param req
     * @param resp
     */
    public void updateType(HttpServletRequest req, HttpServletResponse resp){
        int tid = Integer.parseInt(req.getParameter("tid"));
        String tname = req.getParameter("tname");
        String note = req.getParameter("note");
        Type type = new Type();
        type.setTname(tname);
        type.setNote(note);
        try {
            boolean b = typeService.updateType(tid, type);
            writeToPage(resp,b+"");
        } catch (Exception e) {
            e.printStackTrace();
            writeToPage(resp,"操作异常");
        }
    }

    /**
     * 根据tid删除类别
     * @param req
     * @param resp
     */
    public void deleteTypeByTid(HttpServletRequest req, HttpServletResponse resp){
        int tid = Integer.parseInt(req.getParameter("tid"));
        try {
            boolean b = typeService.deleteType(tid);
            if (b == true){
                writeToPage(resp,"删除成功");
            }else{
                writeToPage(resp,"删除失败，请检查网络是否通畅和数据是否正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
            writeToPage(resp,"删除失败，请检查网络是否通畅和数据是否正确");
        }
    }

    /**
     * 获取所有类别json对象
     * @param req
     * @param resp
     */
    public void getAllType(HttpServletRequest req, HttpServletResponse resp){
        try {
            List<Type> types = typeService.getAllType();
            HttpSession session = req.getSession();
            session.setAttribute("types",types);
            int count = types.size();
            //将对象进行json格式化
            JSONObject res = new JSONObject();
            res.put("code",0);
            res.put("msg","");
            res.put("count",count);
            res.put("data",types);
            writeToPage(resp,res.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加类别
     * @param req
     * @param resp
     */
    public void addType(HttpServletRequest req, HttpServletResponse resp){
        String tname = req.getParameter("tname");
        String note = req.getParameter("note");
        Type type = new Type();
        type.setTname(tname);
        type.setNote(note);
        try {
            boolean b = typeService.addType(type);
            if (b == true){
                writeToPage(resp,"添加成功");
            }else{
                writeToPage(resp,"添加失败，请检查网络是否通畅或者数据是否正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
            writeToPage(resp,"添加失败，请检查网络是否通畅或者数据是否正确");
        }
    }

}