<%--
  Created by IntelliJ IDEA.
  User: Mavis
  Date: 2022/9/4
  Time: 8:33
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
    <button class="layui-btn layui-btn-sm" lay-event="record_btn">查看记录</button>
  </div>
</script>

<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="into">入库</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="output">出库</a>
</script>

<script>
  layui.use(['table', 'dropdown'], function(){
    var table = layui.table;
    var dropdown = layui.dropdown;
    layer.msg('所有药品库存信息', {
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
        ,{field:'mname', width:120, title: '品名'}
        ,{field:'type', title:'类型', hide: 0, width:80}
        ,{field:'note',  title: '说明'}
        ,{field:'cost', title:'进价', hide: 0, width:80}
        ,{field:'price', title:'单价', hide: 0, width:80}
        ,{field:'profit', title:'利润', hide: 0, width:80}
        ,{field:'number', title:'库存数', hide: 0, width:100}
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
        case 'record_btn':
          layer.open({
            type: 2,
            title: '出入库记录',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['100%', '80%'],
            content: './record.html'
          });
          break;
      };
    });

    //触发单元格工具事件
    table.on('tool(test)', function(obj){ // 双击 toolDouble
      var data = obj.data;
      // console.log(data)
      if(obj.event === 'output'){
        setSession('mid',data.mid,()=>{
          layer.open({
            type: 2,
            title: '药品出库',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['50%', '80%'],
            content: './output.jsp'
          });
        })
      } else if(obj.event === 'into'){
        setSession('mid',data.mid,()=>{
          layer.open({
            type: 2,
            title: '药品入库',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['50%', '80%'],
            content: './into.jsp'
          });
        })
      }
    });
  });


</script>

</body>
</html>
