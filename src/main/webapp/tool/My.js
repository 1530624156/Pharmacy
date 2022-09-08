function setSession(key, data, f) {
	$.post('SetSession', {
		key: key,
		data: data
	}, function(d) {
		f(d);
	});
}

function getSession(key, f) {
	$.post('GetSession?key=' + key, function(data) {
		f(data);
	});
}

function setSessionJSON(key, data, f) {
	$.post('SetSession', {
		key: key,
		data: JSON.stringify(data)
	}, function(d) {
		f(d);
	});
}

function getSessionJSON(key, f) {
	$.post('GetSession?key=' + key, function(data) {
		data = JSON.parse(data);
		f(data);
	});
}

/**
 * 异步上传文件,fileinputid为input的id选择器
 * @param {Object} posturl
 * @param {Object} key
 * @param {Object} fileinputid
 * @param {Object} callback
 */
function ajaxUpload(posturl, key, fileinputid, callback) {
	var formData = new FormData();
	var files = document.getElementById(fileinputid).files;
	if (files.length == 0) {
		alert('还未选择文件');
		return;
	}
	formData.append(key, files[0]);
	$.ajax({
		url: posturl,
		type: "post",
		data: formData,
		contentType: false,
		processData: false,
		success: function(data) {
			callback(data);
		},
		error: function(data) {
			callback(data);
		}
	});
}

/**
 * 异步上传文件,file为文件对象
 * @param {Object} posturl
 * @param {Object} key
 * @param {Object} file
 * @param {Object} callback
 */
function ajaxUploadFile(posturl, key, file, callback) {
	var formData = new FormData();
	if (file == null) {
		alert('还未选择文件');
		return;
	}
	formData.append(key, file);
	$.ajax({
		url: posturl,
		type: "post",
		data: formData,
		contentType: false,
		processData: false,
		success: function(data) {
			callback(data);
		},
		error: function(data) {
			callback(data);
		}
	});
}

/**
 * 快速点击事件
 * @param {Object} select
 * @param {Object} f
 */
function ck(select, f) {
	$(select).on('click', f);
}
/**
 * 事件委托点击
 * @param {Object} father
 * @param {Object} child
 * @param {Object} f
 */
function ckwt(father, child, f) {
	$(father).on('click', child, f);
}

/**
 * 判断data,如果为true,则弹窗+刷新
 * @param {Object} data
 * @param {Object} alerttext
 * @param {Object} falsetext
 */
function ifDataReload(data,alerttext,falsetext) {
	if (data) {
		alert(alerttext);
		window.location.reload();
	}else {
		alert(falsetext);
	}
}

/**
 * 判断data,如果为true,则弹窗
 * @param {Object} data
 * @param {Object} truetext
 * @param {Object} falsetext
 */
function ifDataAlert(data, truetext, falsetext) {
	if (data) {
		alert(truetext);
	} else {
		alert(falsetext);
	}
}

/**
 * 解析vue对象，防止不显示
 * @param {Object} arr
 */
function parseVueArr(arr) {
	return JSON.parse(JSON.stringify(arr));
}

// 模拟getsession
function fakeGetSession(data, f) {
	f(data);
}

/**
 * 显示时间到div中
 * @param {Object} div
 */
function showTime(div) {
	// 加载当前时间
	var date = new Date();
	var time = date.getUTCFullYear() + '年' + (date.getUTCMonth() + 1) + '月' + date.getUTCDate() + '日  ' + date.getHours() +
		'时' + date.getUTCMinutes() + '分' + date.getUTCSeconds() + '秒';
	$(div).text(time);

	setInterval(() => {
		var date = new Date();
		var time = date.getUTCFullYear() + '年' + (date.getUTCMonth() + 1) + '月' + date.getUTCDate() + '日  ' + date.getHours() +
			'时' + date.getUTCMinutes() + '分' + date.getUTCSeconds() + '秒';
		$(div).text(time);
	}, 1000);
}

// 日期转换成yyyy-MM-dd
function formatDateToYYYY(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	m = m < 10 ? '0' + m : m;
	var d = date.getDate();
	d = d < 10 ? ('0' + d) : d;
	return y + '-' + m + '-' + d;
}

// 解析日期成年月日时分秒字符串
function formData(date) {
	return date.getUTCFullYear() + '年' + (date.getUTCMonth() + 1) + '月' + date.getUTCDate() + '日  ' + date.getHours() +
		'时' + date.getUTCMinutes() + '分' + date.getUTCSeconds() + '秒';
}


// 判断传进来的数组是否有空,如果没有空的，则返回true
function checkNoNullReturn(arr) {
	var flag = true;
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == '' || arr[i] == null) {
			flag = false;
			break;
		}
	}
	return flag;
}

// 数组去重
function distinct(arr) {
	var arr2 = [];

	for (var i = 0; i < arr.length; i++) {
		if (arr2.indexOf(arr[i]) == -1) {
			arr2.push(arr[i]);
		}
	}
	return arr2;
}

// 将接收的数据存为文件
function saveDataAsFile() {
	var eleLink = document.createElement('a');
	eleLink.download = 'hhh.xls';
	eleLink.style.display = 'none';
	var blob = new Blob([data]);
	eleLink.href = URL.createObjectURL(blob);
	document.body.appendChild(eleLink);
	eleLink.click();
	// 然后移除 
	document.body.removeChild(eleLink);
}

// 发送请求下载二进制文件
function sendPostSaveFile(url, formData, fname, type) {
	var xhr = new XMLHttpRequest();
	xhr.open('POST', url, true);
	xhr.responseType = 'blob';
	xhr.onload = function(e) {
		if (this.status == 200) {
			var blob = this.response;
			var filename = fname;

			var a = document.createElement('a');
			blob.type = type;
			var url = createObjectURL(blob);

			a.href = url;
			a.download = filename;
			a.click();
			window.URL.revokeObjectURL(url);

		}
	};
	xhr.send(formData);
	//文件类型对照：
	// ‘doc’ => ‘application/msword’,
	// ‘bin’ => ‘application/octet-stream’,
	// ‘exe’ => ‘application/octet-stream’,
	// ‘so’ => ‘application/octet-stream’,
	// ‘dll’ => ‘application/octet-stream’,
	// ‘pdf’ => ‘application/pdf’,
	// ‘ai’ => ‘application/postscript’,
	// ‘xls’ => ‘application/vnd.ms-excel’,
	// ‘ppt’ => ‘application/vnd.ms-powerpoint’,
	// ‘dir’ => ‘application/x-director’,
	// ‘js’ => ‘application/x-javascript’,
	// ‘swf’ => ‘application/x-shockwave-flash’,
	// ‘xhtml’ => ‘application/xhtml+xml’,
	// ‘xht’ => ‘application/xhtml+xml’,
	// ‘zip’ => ‘application/zip’,
	// ‘mid’ => ‘audio/midi’,
	// ‘midi’ => ‘audio/midi’,
	// ‘mp3’ => ‘audio/mpeg’,
	// ‘rm’ => ‘audio/x-pn-realaudio’,
	// ‘rpm’ => ‘audio/x-pn-realaudio-plugin’,
	// ‘wav’ => ‘audio/x-wav’,
	// ‘bmp’ => ‘image/bmp’,
	// ‘gif’ => ‘image/gif’,
	// ‘jpeg’ => ‘image/jpeg’,
	// ‘jpg’ => ‘image/jpeg’,
	// ‘png’ => ‘image/png’,
	// ‘css’ => ‘text/css’,
	// ‘html’ => ‘text/html’,
	// ‘txt’ => ‘text/plain’,
	// ‘xsl’ => ‘text/xml’,
	// ‘xml’ => ‘text/xml’,
	// ‘mpeg’ => ‘video/mpeg’,
	// ‘mpg’ => ‘video/mpeg’,
	// ‘avi’ => ‘video/x-msvideo’,
	// ‘movie’ => ‘video/x-sgi-movie’,
}

function createObjectURL(object) {
	return (window.URL) ? window.URL.createObjectURL(object) : window.webkitURL.createObjectURL(object);
}

// 向后端传递对象
function uploadClass(path, obj, f) {
	$.ajax({
		type: "POST",
		url: path,
		contentType: "application/json",
		dataType: "JSON",
		data: JSON.stringify(obj),

		success: function(data) {
			f(data)
		},
		error: function(data) {
			f(data)
		}
	});
}

// 获取表单f1
function getf1() {
	return $('#f1').serialize();
}

//表单序列化添加字段
function addf1(f, key, data) {
	return (f += ('&' + key + '=' + data));
}

//删除table的tr
function deleteByTrId(attr, path, cb) {
	ckwt('table', 'button.shanchu', function() {
		var id = $(this).parents('tr').attr(attr);
		var obj = {};
		obj[attr] = id;
		console.log(obj);
		$.post(path, obj, cb);
	})
}

// 自动根据id,class更新
// 用法：
// <td class="update" update="字段名">{{o.sname}}</td>
// autoUpdate('tr主键属性名','更新接口');
function autoUpdate(idattr, updatepath) {
	ckwt('table', 'td.update', function() {
		var shuxing = $(this).attr('update');
		var id = $(this).parents('tr').attr(idattr);
		var data = prompt('请输入新数据',$(this).text());
		if (checkNoNullReturn([data])) {
			var obj = {};
			obj[idattr] = id;
			obj[shuxing] = data;
			$.post(updatepath, obj, (data) => {
				ifDataReload(data, '更新成功');
			})
		}
	})
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) {
	var o = {
		"M+": this.getMonth() + 1, //月份 
		"d+": this.getDate(), //日 
		"H+": this.getHours(), //小时 
		"m+": this.getMinutes(), //分 
		"s+": this.getSeconds(), //秒 
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
		"S": this.getMilliseconds() //毫秒 
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[
			k]).substr(("" + o[k]).length)));
	return fmt;
}
// 调用： 
// var time1 = new Date().Format("yyyy-MM-dd");
// var time2 = new Date().Format("yyyy-MM-dd HH:mm:ss");

function getTime() {
	return new Date().Format("yyyy-MM-dd HH:mm:ss");
}

function parseTime(time) {
	return new Date(time).Format("yyyy-MM-dd HH:mm:ss");
}


//生成从minNum到maxNum的随机数
function randomNum(minNum, maxNum) {
	switch (arguments.length) {
		case 1:
			return parseInt(Math.random() * minNum + 1, 10);
			break;
		case 2:
			return parseInt(Math.random() * (maxNum - minNum + 1) + minNum, 10);
			break;
		default:
			return 0;
			break;
	}
}

// 转换文件大小
function changeSize(limit) {
	var size = "";
	if (limit < 0.1 * 1024) { //小于0.1KB，则转化成B
		size = limit.toFixed(2) + "B"
	} else if (limit < 0.1 * 1024 * 1024) { //小于0.1MB，则转化成KB
		size = (limit / 1024).toFixed(2) + "KB"
	} else if (limit < 0.1 * 1024 * 1024 * 1024) { //小于0.1GB，则转化成MB
		size = (limit / (1024 * 1024)).toFixed(2) + "MB"
	} else { //其他转化成GB
		size = (limit / (1024 * 1024 * 1024)).toFixed(2) + "GB"
	}

	var sizeStr = size + ""; //转成字符串
	var index = sizeStr.indexOf("."); //获取小数点处的索引
	var dou = sizeStr.substr(index + 1, 2) //获取小数点后两位的值
	if (dou == "00") { //判断后两位是否为00，如果是则删除00                
		return sizeStr.substring(0, index) + sizeStr.substr(index + 3, 2)
	}
	return size;
}



// 传入原生节点复制文字
function copy(node, cb) {
	const range = document.createRange();
	range.selectNode(node);
	const selection = window.getSelection();
	if (selection.rangeCount > 0) selection.removeAllRanges();
	selection.addRange(range);
	document.execCommand('copy');
	cb();
}

// 下滑到select执行事件
function endload(select, cb) {
	$(select).unbind("scroll").bind("scroll", function(e) {
		var sum = this.scrollHeight;
		if (sum <= $(this).scrollTop() + $(this).height()) {
			cb();
		}
	});
}

function loading(status) {
	if (status) {
		var html = '<div class="loading">\n' +
			'        <img src="tool/images/loading.gif" >\n' +
			'    </div>';
		$('body').append(html);
	} else {
		$('div.loading').remove();
	}
}


function passwordLevel(password) {
	var Modes = 0;
	for (i = 0; i < password.length; i++) {
		Modes |= CharMode(password.charCodeAt(i));
	}
	return bitTotal(Modes);
	//CharMode函数
	function CharMode(iN) {
		if (iN >= 48 && iN <= 57) //数字
			return 1;
		if (iN >= 65 && iN <= 90) //大写字母
			return 2;
		if ((iN >= 97 && iN <= 122) || (iN >= 65 && iN <= 90))
			//大小写
			return 4;
		else
			return 8; //特殊字符
	}
	//bitTotal函数
	function bitTotal(num) {
		modes = 0;
		for (i = 0; i < 4; i++) {
			if (num & 1) modes++;
			num >>>= 1;
		}
		return modes;
	}
}


// 获取URL上的参数
function getParam(variable) {
	var query = window.location.search.substring(1);
	var vars = query.split("&");
	for (var i = 0; i < vars.length; i++) {
		var pair = vars[i].split("=");
		if (pair[0] == variable) {
			return pair[1];
		}
	}
	return (false);
}

/**
 * 带参数跳转url
 * @param {string} url-跳转地址
 * @param {object} paramsMap-参数键值对
 */
function jumpWithParams(url, paramsMap) {
	url = url + '?';
	for (var key in paramsMap) {
		url += key + '=' + paramsMap[key] + '&';
	}
	console.log(url);
	window.location=url;
}

/**
 * 判断是否是数字
 * @param {Object} val
 */
function Number(val) {
　　if (parseFloat(val).toString() == "NaN") {
　　　　
　　　　return false;
　　} else {
　　　　return true;
　　}
}


function dataURLtoBlob(dataurl) {
	var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
		bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
	while (n--) {
		u8arr[n] = bstr.charCodeAt(n);
	}
	return new Blob([u8arr], { type: mime });
}

function downloadFile(url,name='What\'s the fuvk'){
	var a = document.createElement("a")
	a.setAttribute("href",url)
	a.setAttribute("download",name)
	a.setAttribute("target","_blank")
	let clickEvent = document.createEvent("MouseEvents");
	clickEvent.initEvent("click", true, true);  
	a.dispatchEvent(clickEvent);
}

/**
 * 保存base64图片到本地
 * @param {Object} base64
 * @param {Object} name
 */ 
function downloadFileByBase64(base64,name){
	var myBlob = dataURLtoBlob(base64)
	var myUrl = URL.createObjectURL(myBlob)
	downloadFile(myUrl,name)
}

/**
 * 防抖
 * @param {*} fn 回调函数
 * @param {*} delay 延迟 ms
 * @param {*} immediate 立即执行true/false
 * @returns 
 */
function debounce(fn, delay, immediate) {
	var timer = null;
	delay = delay || 500;
	return function () {
		var context = this;
		var args = arguments;

		if (timer) {
			clearTimeout(timer);
		}

		if (immediate) {
			if (!timer) {
				fn.apply(context, args);
			}

			timer = setTimeout(function () {
				timer = null;
			}, delay);
		} else {
			timer = setTimeout(function () {
				timer = fn.apply(context, args);
			}, delay);
		}
	};
};