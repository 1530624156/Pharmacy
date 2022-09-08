package com.mavis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mavis.entity.User;
import com.mavis.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//用户控制层
@WebServlet("/user")
public class UserController extends BaseServlet{

    private UserService userService = new UserService();

    /**
     * 添加用户
     * @param request
     * @param response
     */
    public void addUser(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setTel(tel);
        try {
            boolean b = userService.addUser(user);
            if (b == true){
                writeToPage(response,"添加成功");
            }else{
                writeToPage(response,"添加失败，请检查网络是否通畅和数据是否正常");
            }
        } catch (Exception e) {
            e.printStackTrace();
            writeToPage(response,"添加失败，请检查网络是否通畅和数据是否正常");
        }
    }

    /**
     * 获取所有用户Json对象
     * @param req
     * @param resp
     */
    public void getAllUser(HttpServletRequest req, HttpServletResponse resp){
            List<User> allUser = userService.getAllUser();
            int count = allUser.size();
            //将对象进行json格式化
            JSONObject res = new JSONObject();
            res.put("code",0);
            res.put("msg","");
            res.put("count",count);
            res.put("data",allUser);
            writeToPage(resp,res.toJSONString());
    }

    /**
     * 根据用户id删除用户
     * @param req
     * @param resp
     */
    public void deleteUserByUid(HttpServletRequest req, HttpServletResponse resp){
        int uid = Integer.parseInt(req.getParameter("uid"));
        try {
            boolean b = userService.deleteUser(uid);
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
     * 根据用户id获取用户
     * @param request
     * @param response
     */
    public void getUserByUid(HttpServletRequest request, HttpServletResponse response){
        Integer uid = getIntegerParams(request, "uid");
        if(uid != null){
            //如果有参数
            List<User> users = userService.selectByUid(uid);
            if(users != null && users.size() != 0){
                //如果有用户
                User user = users.get(0);
                writeToPage(response, JSON.toJSONString(user));
            }
        }
        writeToPage(response,null);
    }

    /**
     * 更新接口
     * @param req
     * @param resp
     */
    public void updateUser(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        int uid = Integer.parseInt(req.getParameter("uid"));
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String tel = req.getParameter("tel");
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setName(name);
        user.setTel(tel);
        boolean b = userService.updateUser(uid, user);
        System.out.println(b);
        writeToPage(resp, b+"");
    }
}
