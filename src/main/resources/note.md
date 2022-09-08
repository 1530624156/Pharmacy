### 弹出层执行ajax完成刷新父窗口，关闭弹窗

```js
//执行完成后关闭弹窗并且刷新父窗口
window.parent.location.reload();
var index = parent.layer.getFrameIndex(window.name);
parent.layer.close(index);
```



### 在layui的数据表格中显示图片

1、layui模板

```html
<script type="text/html" id="medicineImgTemp">
    <img src="/img/{{d.mimg}}" style="height: 100%;">
</script>
```

2、在field单元格中加入属性templet:"#medicineImgTemp"

```js
{field:'mimg',title: "照片",width: 100 ,align:"center",templet:"#medicineImgTemp"}
```



### 上传文件时将资源库中的文件名与数据库中的字段同步

1、首先通过上传文件接口上传文件，并且拿到回调的数据

```js
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
            }
            ,error: function(index, upload){
                alert("请求异常")
            }
        });
    });
```

2、再将拿到的数据一起提交给添加到数据库的接口

```js
//点击按钮提交
    $('.addmedicine_btn').on('click', function () {
        var mname = $(".mname").val();
        var type = $(".type").val();
        var note = $(".note").val();
        var symptom = $(".symptom").val();
        var used = $(".used").val();
        var taboo = $(".taboo").val();
        var price = $(".price").val();
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
                ming: ming
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
```

### Layui选择框的数据与数据库同步

```js
//1、首先将option标签内的数据与数据库同步(将目标选择栏添加selected属性)
//获取类别设置默认选择
var arr = document.getElementsByTagName("option");
for (var i=0;i<arr.length;i++){
    if (arr[i].value == data.type){
        console.log(arr[i]);
        arr[i].selected = true;
        //2、选择框的展示内容渲染
        $(".typebox input").val(data.type)
    }
}
```

### Layui上传文件是总是提示接口异常

因为layui上产文件的回调数据必须是对应的Json格式数据

```java
public void uploadImg(HttpServletRequest req, HttpServletResponse resp){
    try {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Part part = req.getPart("mimg");
        String id = UUID.randomUUID().toString();
        String fileName = id+part.getSubmittedFileName();
        part.write(this.getInitParameter("imgPath")+ File.separator+fileName);
        System.out.println("文件的名称是:"+fileName);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("data",fileName);
        writeToPage(resp,json.toJSONString());
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ServletException e) {
        e.printStackTrace();
    }
}
```

### SQL数据库中的datetime字段在Java后台中对象的timestamp类在进行json转化时会变成时间戳格式

在Java后台中可以用Date类对象获取当前时间戳然后转化成 yyyy-MM-dd HH:mm:ss 格式 再存入sql数据库中

```java
//获取当前时间格式的时间方法
public static String getNowTime(){
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
        Date date;
        try {
            date = sdf.parse(sdf.format(time));
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
```

