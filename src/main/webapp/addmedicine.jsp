<%--
  Created by IntelliJ IDEA.
  User: Mavis
  Date: 2022/9/1
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>

</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>添加药品</legend>
</fieldset>

<form return="false" class="layui-form" action="">

    <div class="layui-form-item">
        <label class="layui-form-label">药名：</label>
        <div class="layui-input-block">
            <input type="text" name="mname" lay-verify="required" lay-reqtext="药名是必填项，岂能为空？" placeholder="请输入药名" autocomplete="off" class="layui-input mname">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">选择框</label>
        <div class="layui-input-block">
<%--            渲染选择框--%>
            <select name="type" lay-verify="required" class="type">
                <c:forEach items="${types}" var="types">
                    <option value="${types.tname}">${types.tname}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">厂家：</label>
        <div class="layui-input-block">
            <input type="text" name="facturer" lay-verify="required" lay-reqtext="厂家是必填项，岂能为空？" placeholder="请输入厂家" autocomplete="off" class="layui-input facturer">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">说明：</label>
        <div class="layui-input-block">
            <input type="text" name="note" lay-verify="required" lay-reqtext="说明是必填项，岂能为空？" placeholder="请输入说明" autocomplete="off" class="layui-input note">
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">治疗症状：</label>
        <div class="layui-input-block">
            <input type="text" name="symptom" lay-verify="required" lay-reqtext="症状是必填项，岂能为空？" placeholder="请输入症状" autocomplete="off" class="layui-input symptom">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">用法用量：</label>
        <div class="layui-input-block">
            <input type="text" name="used" lay-verify="required" lay-reqtext="用法用量是必填项，岂能为空？" placeholder="请输入用法用量" autocomplete="off" class="layui-input used">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">禁忌：</label>
        <div class="layui-input-block">
            <input type="text" name="taboo" lay-verify="required" lay-reqtext="禁忌是必填项，岂能为空？" placeholder="请输入禁忌" autocomplete="off" class="layui-input taboo">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">成本：</label>
        <div class="layui-input-block">
            <input type="text" name="cost" lay-verify="required" lay-reqtext="成本是必填项，岂能为空？" placeholder="请输入成本" autocomplete="off" class="layui-input cost">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">单价：</label>
        <div class="layui-input-block">
            <input type="text" name="price" lay-verify="required" lay-reqtext="单价是必填项，岂能为空？" placeholder="请输入单价" autocomplete="off" class="layui-input price">
        </div>
    </div>

    <button type="button" class="layui-btn" id="test1" style="margin-left: 20px">
        <i class="layui-icon">&#xe67c;</i>上传图片
    </button>

    <div align="center">
        <button class="layui-btn layui-btn-normal addmedicine_btn">确认添加</button>
        <button style="margin-left: 20px" class="layui-btn layui-btn-normal close_btn">关闭</button>
    </div>

</form>

<script src="layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery-3.6.0.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
    //点击关闭按钮关闭弹出层
    $(".close_btn").on('click',function (){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })

    //上传图片并且获取图片文件名
    var fileName;

    layui.use(['upload','layer','jquery'], function(){
        var upload = layui.upload;
        var layer = layui.layer;
        var $ = layui.jquery;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: 'file' //上传接口
            ,field:"mimg"
            ,auto:true /*开启自动上传*/
            ,accept:"img"
            ,data:{
                method: "uploadImg"
            }
            ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。

            }
            ,done: function(res, index, upload){
                console.log(res.data);
                fileName = res.data;
                layer.msg("上传完成")
            }
            ,error: function(index, upload){
                alert("请求异常")
            }
        });
    });

    //点击按钮提交
    $('.addmedicine_btn').on('click', function () {
        var mname = $(".mname").val();
        var type = $(".type").val();
        var note = $(".note").val();
        var symptom = $(".symptom").val();
        var used = $(".used").val();
        var taboo = $(".taboo").val();
        var price = $(".price").val();
        var cost = $(".cost").val();
        var facturer = $(".facturer").val();
        var ming = fileName;
        $.ajax({
            type: 'post',
            url: 'medicine',
            data: {
                method: "addMedicine",
                mname: mname,
                type: type,
                note: note,
                symptom: symptom,
                used: used,
                taboo: taboo,
                price: price,
                ming: ming,
                cost: cost,
                facturer: facturer
            },
            success: function (result) {
                alert(result);
                console.log(result);
                //执行完成后关闭弹窗并且刷新父窗口
                window.parent.location.reload();
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        });
        return false;
    })
</script>
</body>
</html>
