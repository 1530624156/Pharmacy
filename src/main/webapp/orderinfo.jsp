<%--
  Created by IntelliJ IDEA.
  User: Mavis
  Date: 2022/9/8
  Time: 2:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
    <script src="js/jquery-3.6.0.js" type="text/javascript" charset="utf-8"></script>
    <script src="layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <script src="tool/My.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<p align="center">
    <font size="10" color="green" face="幼圆">药 单 详 情</font>
</p>
<hr class="layui-border-green">
<div class="layui-row layui-col-space15">
    <div class="layui-col-md12">
        <div class="layui-panel">
            <div style="padding: 10px 10px;">
                <p style="margin-left: 15%;">
                    <font size="2" color="black" face="幼圆">订单号:</font>
                    <font size="2" color="black" face="楷体">${sessionScope.orderinfo.oid}</font>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <font size="2" color="black" face="幼圆">时间</font>
                    <font size="2" color="black" face="楷体">${sessionScope.orderinfo.time}</font>
                </p>

                <p style="margin-left: 15%;">
                    <font size="4" color="black" face="幼圆">购药人：</font>
                    <font size="4" color="green" face="楷体">${sessionScope.orderinfo.uname}</font>
                </p>
            </div>
        </div>
        <hr class="layui-border-green">
        <%--        药品详细信息--%>
        <c:forEach items="${medicineList}" var="medicineList">
            <div class="layui-col-md12" style="margin-bottom: 10px;">
                <div class="layui-panel">
                    <p align="center">
                        <font size="4" color="black" face="幼圆">药名:</font>
                        <font size="4" color="green" face="楷体">${medicineList.mname}</font>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <font size="4" color="black" face="幼圆">用法用量:</font>
                        <font size="4" color="green" face="楷体">${medicineList.used}</font>
                        <br>
                        <font size="4" color="black" face="幼圆">价格:</font>
                        <font size="4" color="green" face="楷体">${medicineList.price}元</font>
                    </p>
                </div>
            </div>
        </c:forEach>
        <hr class="layui-border-green">
<%--        合计--%>
        <p style=" float: right; margin-right: 20%" >
            <font size="4" color="black" face="幼圆">总价:</font>
            <font size="4" color="green" face="楷体">${sessionScope.orderinfo.totle}元</font>
        </p>
    </div>
</div>


</body>
</html>
