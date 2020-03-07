<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>  
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>个人信息</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/city.js"></script>
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script> 
    <script type="text/javascript">
	checkTime = function() {
		var firm_id = document.getElementById("firm_id").value;
		var firm_name = document.getElementById("firm_name").value;
		var firm_linkman = document.getElementById("firm_linkman").value;
		var firm_linkman_phone = document.getElementById("firm_linkman_phone").value;
		var startTime = document.getElementById("startTime").value;
		var endTime = document.getElementById("endTime").value;
		var firm_linkman_phone = document.getElementById('firm_linkman_phone').value;
		var arys1 = new Array();
		var arys2 = new Array();
		if(firm_id.length<1||firm_name.length<1||firm_linkman.length<1||firm_linkman_phone.length<1||startTime.length<1||endTime.length<1||firm_linkman_phone.length<1){
			alert("有数据未填写");
			return false;
		}
			else{
			if (startTime != null && endTime != null) {
			arys1 = startTime.split('-');
			var sdate = new Date(arys1[0], parseInt(arys1[1] - 1), arys1[2]);
			arys2 = endTime.split('-');
			var edate = new Date(arys2[0], parseInt(arys2[1] - 1), arys2[2]);
			if (sdate >= edate) {
				alert("日期开始时间必须小于结束时间");
				return false;
			} else {
				     if(!(/^1[34578]\d{9}$/.test(firm_linkman_phone))){ 
				            alert("必须填写有效手机号码");  
				            return false; 
				        } else {
				        	return true;
				        }
				  
			}
		}
		}
	}
</script> 

</head>
<body>

	<%
	if ((String)request.getAttribute("str") == "true") {
	%>
	<script>
	alert("操作成功！");
	</script>
	<%
	}
	%>
	<%
	if ((String)request.getAttribute("str") == "false") {
	%>
	<script>
	alert("操作失败！");
	</script>
	<%
	}
	%>
<div class="panel admin-panel">

  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>登记单位信息</strong></div>
  <div class="body-content">
    <form method="post"  action="Student_Insert_firm" class="form-x">
      <div class="form-group">
        <div class="label">
          <label>公司编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="firm_id" id="firm_id" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" data-validate="required:请输入公司编号,length#<=10:公司编号不能大于10位"  >
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>公司名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="firm_name" id="firm_name" data-validate="required:请输入公司名称" />
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>公司所在省：</label>
        </div>
        <div class="field">
          <select  class="input w50" name="firm_address_sheng" id="province"></select>
        </div>
      </div>
      <div class="form-group" >
        <div class="label">
          <label>公司所在市：</label>
        </div>
        <div class="field">
          <select class="input w50" name="firm_address_shi" id="city" ></select>
        </div>
      </div>
        <script>
    (function(){
        var pHtmlStr ='';
        for(var name in pc){
            pHtmlStr = pHtmlStr + '<option>'+name+'</option>';
        }
        $("#province").html(pHtmlStr);
        $("#province").change(function(){
            var pname = $("#province option:selected").text();
            var pHtmlStr = '';
            var cityList = pc[pname];
            for(var index in cityList){
                pHtmlStr = pHtmlStr + '<option>'+cityList[index]+'</option>';
            }
            $("#city").html(pHtmlStr);
        });
        $("#province").change();
    })();
</script>
  	
      <div class="form-group">
        <div class="label">
          <label>联系人：</label>
        </div>
        <div class="field">
        <input type="text" class="input w50" name="firm_linkman" id="firm_linkman" data-validate="required:请输入联系人,length#>3:联系人名字不得小于两个汉字"  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>联系人电话：</label>
        </div>
        
         <div class="field">
        <input type="text" class="input w50" name="firm_linkman_phone" id="firm_linkman_phone" data-validate="required:请输入手机号码,length#<=11:手机号码不能大于11位" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>开始实习时间：</label>
        </div>
         <div class="field">
        <input type="date" class="input w50" name="work_start_time"  id="startTime" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>结束实习时间：</label>
        </div>
        
        <div class="field">
          <input type="date" class="input w50" name="work_end_time"  id="endTime"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit" onclick="return checkTime()"> 提交</button>
  
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>