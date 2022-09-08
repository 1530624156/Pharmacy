package com.mavis.controller;

import com.mavis.entity.Admin;
import com.mavis.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: Pharmacy
 * @description:
 * @author: Mavis
 * @create: 2022-08-26 09:17
 **/
@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet {
    AdminService adminService = new AdminService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Admin admin = new Admin(username, password);
        try {
            boolean result = adminService.login(admin);
            //设置编码格式
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            if (result == true){
                HttpSession session = req.getSession();
                session.setAttribute("admin",admin);
                writer.print("管理员登录成功");
            }else{
                writer.print("管理员登录失败");
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}