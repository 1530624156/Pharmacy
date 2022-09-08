<%--
  Created by IntelliJ IDEA.
  User: Mavis
  Date: 2022/9/4
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
    <script src="tool/My.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>药品入库</legend>
</fieldset>


<form class="layui-form" action="" id="f1">

    <div class="layui-form-item">
        <label class="layui-form-label">ID：</label>
        <div class="layui-input-block">
            <input disabled type="text" name="mid" lay-verify="required"
                   autocomplete="off" class="layui-input mid">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">药名：</label>
        <div class="layui-input-block">
            <input disabled type="text" name="mname" lay-verify="required" lay-reqtext="药名是必填项，岂能为空？"
                   autocomplete="off" class="layui-input mname">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">入库数量：</label>
        <div class="layui-input-block">
            <input type="text" name="addnum" lay-verify="required" lay-reqtext="入库数量是必填项，岂能为空？"
                   autocomplete="off" class="layui-input addnum">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">备注：</label>
        <div class="layui-input-block">
            <input type="text" name="note" lay-verify="required" lay-reqtext="备注是必填项，岂能为空？"
                   autocomplete="off" class="layui-input note">
        </div>
    </div>

    <div align="center">
        <button class="layui-btn layui-btn-normal add_btn">确认入库</button>
        <button style="margin-left: 20px" class="layui-btn layui-btn-normal close_btn">关闭</button>
    </div>

</form>

<script src="layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery-3.6.0.js" type="text/javascript" charset="utf-8"></script>


<script type="text/javascript">

    //点击关闭按钮关闭弹出层
    $(".close_btn").on('click', function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })

    $(() => {
        getSession('mid', (mid) => {
            console.log(mid);
            $.post('medicine?method=getMedicineByMid&mid=' + mid, function (data) {
                data = JSON.parse(data);
                console.log(data);
                $('input[name=mid]').val(mid);
                $('input[name=mname]').val(data.mname);
            })

            // 点击入库
            $('.add_btn').on('click', () => {
                var mid = $(".mid").val();
                var mname = $(".mname").val()
                var addnum = $(".addnum").val()
                var note = $(".note").val()
                $.ajax({
                    type: 'post',
                    url: 'inventory',
                    data: {
                        method: "into",
                        mid: mid,
                        mname: mname,
                        addnum: addnum,
                        note: note
                    },

                    success: function (result) {
                        if (result == 'true'){
                            alert("入库成功");
                            console.log("入库成功");
                        }else{
                            alert("入库失败")
                        }
                        //执行完成后关闭弹窗并且刷新父窗口
                        window.parent.location.reload();
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }
                })
                return false;
            })
        })
    })
</script>
</body>
</html>
