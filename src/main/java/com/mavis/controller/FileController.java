package com.mavis.controller;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @program: Pharmacy
 * @description: 文件控制层
 * @author: Mavis
 * @create: 2022-09-02 09:45
 **/
@MultipartConfig(
        location = ""  /*文件保存的缓存区*/
        ,fileSizeThreshold = 1024
)

@WebServlet(value = "/file",initParams = @WebInitParam(name = "imgPath", value = "D:\\JAVA_Project\\ProjectImg\\pharmacy"))
public class FileController extends BaseServlet{

    /**
     * 上传药品图片
     * @param req
     * @param resp
     */
    public void uploadImg(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=utf-8");
            Part part = req.getPart("mimg");
            String id = UUID.randomUUID().toString();
            String fileName = id+part.getSubmittedFileName();
            part.write(this.getInitParameter("imgPath")+ File.separator+fileName);
            System.out.println("文件的名称是:"+fileName);
            JSONObject json = new JSONObject();
            json.put("code",0);
            json.put("msg","");
            json.put("data",fileName);
            writeToPage(resp,json.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}