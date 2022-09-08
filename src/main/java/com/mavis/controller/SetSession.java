package com.mavis.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//我的session工具，用于临时存储数据
@WebServlet("/SetSession")
public class SetSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = "demo" + req.getParameter("key");
        String data = req.getParameter("data");
        req.getSession().setAttribute(key,data);
        writeToPage(resp,"true");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }


    /**
     * 写数据到响应中
     * @param resp
     * @param data
     */
    protected void writeToPage(HttpServletResponse resp,String data){
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(data);
        writer.flush();
        writer.close();
    }
}
