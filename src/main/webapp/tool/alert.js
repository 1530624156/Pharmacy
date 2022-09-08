
//自定义的alert效果
  function myAlert(mes,time,isF5){
	  $(".myAlert").remove();
	  var html = "<div class='myAlert' style='display:inline-block;width:220px;position:fixed;left:50%;top:50%;margin:-25px 0 0 -110px;z-index:999; text-align:center; background:rgba(0,0,0,.7); border-radius:5px; display:none;' ><a href=\"javascript:\" style='width:100%; min-height:50px; padding:10px;display:-webkit-box;-webkit-box-pack:center; -webkit-box-align:center; color:#FFF !important;'>"+mes+"</a></div>";
      
      $("body").append("\r\n"+html+"\r\n");
      $(".myAlert").fadeIn();
	  function hide(){
		$(".myAlert").fadeOut();
		if(isF5){
			window.location.reload();
		}
	  }
	  setTimeout(hide, time)
	  
  }
      
