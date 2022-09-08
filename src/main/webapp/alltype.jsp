<%--
  Created by IntelliJ IDEA.
  User: Mavis
  Date: 2022/8/27
  Time: 14:39
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
        <button class="layui-btn layui-btn-sm" lay-event="addType">添加类别</button>

    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use(['table', 'dropdown'], function(){
        var table = layui.table;
        var dropdown = layui.dropdown;
        layer.msg('所有药品类别信息', {
            closeBtn: 0,
            icon: 6,
            time: 800,
            offset: '21px'
        });

        // 创建渲染实例
        table.render({
            elem: '#test'
            ,url:'type?method=getAllType' // 获取数据接口
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
                ,{field:'tid', fixed: 'left', width:"5%", title: 'ID', sort: true, totalRowText: '合计：'}
                ,{field:'tname', width:"10%", title: '类名'}
                ,{field:'note', width:"72%", title:'说明', hide: 0, }
                ,{fixed: 'right', title:'操作', width: "8%", minWidth: 180, toolbar: '#barDemo'}
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
                case 'addType':
                    layer.open({
                        type: 2,
                        title: '添加类别',
                        shadeClose: true,
                        shade: false,
                        maxmin: true, //开启最大化最小化按钮
                        area: ['50%', '80%'],
                        content: './addtype.html'
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
                    deletetype(data.tid);
                });
            } else if(obj.event === 'edit'){
                setSession('tid',data.tid,()=>{
                    layer.open({
                        type: 2,
                        title: '编辑类别信息',
                        shadeClose: true,
                        shade: false,
                        maxmin: true, //开启最大化最小化按钮
                        area: ['50%', '80%'],
                        content: 'updatetype.html'
                    });
                })
            }
        });
    });

    function deletetype(id) {
        $.ajax({
            type:'post',
            url:'type',
            data:{
                method: "deleteTypeByTid",
                tid:id
            },
            success:function(result){
                alert(result);
                console.log(result);
                window.location="alltype.jsp"
            }
        });
    }

</script>

</body>
</html>

