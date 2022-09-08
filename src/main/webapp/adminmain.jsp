<%--
  Created by IntelliJ IDEA.
  User: Mavis
  Date: 2022/9/8
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>药店管理系统-管理员界面</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo layui-hide-xs layui-bg-black">药店管理系统</div>
        <!-- 头部区域（可配合layui 已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <!-- 移动端显示 -->
            <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
                <i class="layui-icon layui-icon-spread-left"></i>
            </li>
            <li class="layui-nav-item layui-hide-xs sysintroduced"><a href="javascript:;">系 统 介 绍</a></li>


        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item layui-hide layui-show-md-inline-block">
                <a href="javascript:;">
                    <img src="/img/header.jpg"
                         class="layui-nav-img">
                    管理员
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="./">Worker/员工登录</a></dd>
                    <dd><a href="" class="logout">Sign out/退出登录</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item usermanager"><a href="javascript:;">客户[用户]管理</a></li>
                <li class="layui-nav-item "><a class="inventory" href="javascript:;">库存管理</a></li>
                <li class="layui-nav-item "><a class="ordermanager" href="javascript:;">订单管理</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">药品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a class="medicinemanager" href="javascript:;">药品管理</a></dd>
                        <dd><a class="typemanager" href="javascript:;">类别管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item "><a class="workermanager" href="javascript:;">员工管理</a></li>

            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="padding: 15px;">
            <iframe src="./alluser.jsp" frameborder="0" id="mainView" style="width: 100%; height: 100%; border-radius: 2px;"></iframe>
        </div>
    </div>

    <div class="layui-footer" align="center">
        <!-- 底部固定区域 -->
        Copyright © 2022 武汉东湖学院 20级计算机应用技术6班-郭逸轩、刘翔、陈杰、夏梓豪
    </div>


</div>


<script src="layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery-3.6.0.js" type="text/javascript" charset="utf-8"></script>

<script>
    //获取所有分类
    $.post('type?method=getAllType', function (data) {
    })
</script>

<script>

    //系统介绍页面显示事件
    $(".sysintroduced").on('click',function(){
        var mainView = $('#mainView')[0];
        mainView.src = "./introduced.html";
    });

    //用户管理页面显示事件
    $(".usermanager").on('click',function(){
        var mainView = $('#mainView')[0];
        mainView.src = "./alluser.jsp";
    });

    //药品管理页面显示事件
    $(".medicinemanager").on('click',function (){
        var mainView = $('#mainView')[0];
        mainView.src = "./allmedicine.jsp";
    })

    //类别管理页面显示事件
    $(".typemanager").on('click',function(){
        var mainView = $('#mainView')[0];
        mainView.src = "./alltype.jsp";
    });

    //库存管理页面显示事件
    $(".inventory").on('click',function (){
        var mainView = $('#mainView')[0];
        mainView.src = "./inventory.jsp";

    })

    //订单管理页面显示操作
    $(".ordermanager").on('click',function (){
        var mainView = $('#mainView')[0];
        mainView.src = "./allorders.jsp";
    })

    //员工管理页面现实操作
    $(".workermanager").on('click',function (){
        var mainView = $('#mainView')[0];
        mainView.src = "./allworker.jsp";
    })



    //退出登录事件
    $(".logout").on('click',function(){
        $.ajax({
            type:'post',
            url:'worker',
            data:{
                method: "workerLogout"
            },
            success:function(result){
                alert(result);
                console.log(result);
                window.location="./";
            }
        });
    })
</script>
</body>
</html>
