<%--
  Created by IntelliJ IDEA.
  User: Mavis
  Date: 2022/8/26
  Time: 17:40
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
        <button class="layui-btn layui-btn-sm" lay-event="addUser">添加用户</button>

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
        layer.msg('所有用户信息', {
            closeBtn: 0,
            icon: 6,
            time: 800,
            offset: '21px'
        });

        // 创建渲染实例
        table.render({
            elem: '#test'
            ,url:'user?method=getAllUser' // 获取数据接口
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
                ,{field:'uid', fixed: 'left', width:80, title: 'ID', sort: true, totalRowText: '合计：'}
                ,{field:'name', title: '姓名'}
                ,{field:'email', title:'邮箱', hide: 0, }
                ,{field:'tel',  title: '手机', sort: true}
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
                        title: '添加用户',
                        shadeClose: true,
                        shade: false,
                        maxmin: true, //开启最大化最小化按钮
                        area: ['50%', '80%'],
                        content: './adduser.html'
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
                    deleteuser(data.uid);
                });
            } else if(obj.event === 'edit'){
				setSession('uid',data.uid,()=>{
					layer.open({
					    type: 2,
					    title: '编辑用户信息',
					    shadeClose: true,
					    shade: false,
					    maxmin: true, //开启最大化最小化按钮
					    area: ['50%', '80%'],
					    content: 'updateuser.html'
					});
				})
            }
        });
    });

    function deleteuser(id) {
		$.post('user',{method:"deleteUserByUid",uid:id},(result)=>{
			alert(result);
			console.log(result);
			window.location="./alluser.jsp"
		})
    }

</script>

</body>
</html>

