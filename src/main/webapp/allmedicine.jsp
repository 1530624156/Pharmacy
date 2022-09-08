<%--
  Created by IntelliJ IDEA.
  User: Mavis
  Date: 2022/9/1
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
    <script src="tool/My.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>

<table class="layui-hide" id="test" lay-filter="test"></table>



<script src="js/jquery-3.6.0.js" type="text/javascript" charset="utf-8"></script>
<script src="layui/layui.js" type="text/javascript" charset="utf-8"></script>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addUser">添加药品</button>

    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/html" id="medicineImgTemp">
    <img src="/img/{{d.mimg}}" style="height: 100%;">
</script>

<script>
    layui.use(['table', 'dropdown'], function(){
        var table = layui.table;
        var dropdown = layui.dropdown;
        layer.msg('所有药品信息', {
            closeBtn: 0,
            icon: 6,
            time: 800,
            offset: '21px'
        });

        // 创建渲染实例
        table.render({
            elem: '#test'
            ,url:'medicine?method=getAllMedicine' // 获取数据接口
            ,toolbar: '#toolbarDemo'
            ,defaultToolbar: ['filter', 'exports', 'print', {
                title: '所有数据'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,height: 'full-200' // 最大高度减去其他容器已占有的高度差
            ,cellMinWidth: 80
            ,totalRow: false // 关闭合计行
            ,page: false //关闭分页
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'mid', fixed: 'left', width:60, title: 'ID', sort: true, totalRowText: '合计：'}
                ,{field:'mimg',title: "照片",width: 100 ,align:"center",templet:"#medicineImgTemp"}
                ,{field:'mname', width:120, title: '品名'}
                ,{field:'type', title:'类型', hide: 0, width:80}
                ,{field:'facturer', width:200, title: '生产厂家'}
                ,{field:'note', title:'说明', hide: 0, width:150}
                ,{field:'used', title:'用法用量', hide: 0, width:200}
                ,{field:'taboo', title:'禁忌', hide: 0, width:200}
                ,{field:'price', width:100, title: '单价'}
                ,{field:'profit', width:100, title: '利润'}
                ,{fixed: 'right', title:'操作', width: 125, minWidth: 180, toolbar: '#barDemo'}
            ]]
            ,done: function(){
                var id = this.id;
            }
            ,error: function(res, msg){
                console.log(res, msg)
            }
        });

        // 工具栏事件
        table.on('toolbar(test)', function(obj){
            var id = obj.config.id;
            var checkStatus = table.checkStatus(id);
            var othis = lay(this);
            switch(obj.event){
                case 'addUser':
                    layer.open({
                        type: 2,
                        title: '添加药品',
                        shadeClose: true,
                        shade: false,
                        maxmin: true, //开启最大化最小化按钮
                        area: ['50%', '80%'],
                        content: './addmedicine.jsp'
                    });
                    break;
            };
        });


        //触发单元格工具事件
        table.on('tool(test)', function(obj){ // 双击 toolDouble
            var data = obj.data;
            // console.log(data)
            if(obj.event === 'del'){
                layer.confirm('真的删除么', function(index){
                    // 删除方法
                    deleteuser(data.mid);
                });
            } else if(obj.event === 'edit'){
                setSession('mid',data.mid,()=>{
                    layer.open({
                        type: 2,
                        title: '编辑药品信息',
                        shadeClose: true,
                        shade: false,
                        maxmin: true, //开启最大化最小化按钮
                        area: ['50%', '80%'],
                        content: 'updatemedicine.jsp'
                    });
                })
            }
        });
    });

    function deleteuser(id) {
        $.post('medicine',{method:"deleteMedicineByMid",mid:id},(result)=>{
            alert(result);
            console.log(result);
            window.location="./allmedicine.jsp"
        })
    }

</script>

</body>
</html>

