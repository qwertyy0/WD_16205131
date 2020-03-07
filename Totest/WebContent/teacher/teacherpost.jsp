<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
<meta charset="utf-8">
<title>发布汇报</title>
<script type="text/javascript">
function checkdate(){
	if(document.getElementById("weeknum").value.length==0||document.getElementById("start_time").value.length==0||document.getElementById("end_time").value.length==0
			||document.getElementById("weeknum").value==""||document.getElementById("start_time").value==""||document.getElementById("end_time").value==""){
		alert("周数开始截止时间不能为空");
		return false;
	}else{
		//获取的时间字符串进行处理(datetime-local没有秒)
		var startTime0 = document.getElementById("start_time").value.toString().replace("T"," ");
		var startTime = startTime0+":00";
		
		var endTime0 = document.getElementById("end_time").value.toString().replace("T"," ");
		var endTime = endTime0+":00";
		//compareTime函数进行比较
		if(compareTime(startTime,endTime)){
			
			document.getElementById("outpost").submit();
		}else{
			alert("结束时间大于开始时间");
			return false;
		}	
	}	
}	
	function compareTime(startDate,endDate){
		//分割开始-结束时间
		var startDateTemp = startDate.split(" ");
		var endDateTemp = endDate.split(" ");
		//分割年月日
		var arrStartDate = startDateTemp[0].split("-");
		var arrEndDate = endDateTemp[0].split("-");
		//分割时分秒
		var arrStartTime = startDateTemp[1].split(":");
		var arrEndTime = endDateTemp[1].split(":");
		//转换为Date 时间戳比较
		var allStartDate = new Date(parseInt(arrStartDate[0]), parseInt(arrStartDate[1]),parseInt(arrStartDate[2]), parseInt(arrStartTime[0]), parseInt(arrStartTime[1]),parseInt("00"));
		var allEndDate = new Date(parseInt(arrEndDate[0]), parseInt(arrEndDate[1]),parseInt(arrEndDate[2]),parseInt(arrEndTime[0]), parseInt(arrEndTime[1]),parseInt("00"));

		if (allStartDate.getTime() >= allEndDate.getTime()) {
			return false;
		} else {
			return true;
		}
	}
</script>
<style type="text/css">
	.select {
		display: inline-block;
		width: 200px;
		position: relative;
		vertical-align: middle;
		margin:0 auto;
		padding-top: 0;
		overflow: hidden;
		background-color: #fff;
		color: #555;
		border: 1px solid #aaa;
		text-shadow: none;
		border-radius: 4px;	
		transition: box-shadow 0.25s ease;
		z-index: 2;
	}
	.select:hover {
		box-shadow: 0 1px 4px rgba(0, 0, 0, 0.15);
	}
 
	.select:before {
		content: "";
		position: absolute;
		width: 0;
		height: 0;
		border: 10px solid transparent;
		border-top-color: #ccc;
		top: 14px;
		right: 10px;
		cursor: pointer;
		z-index: -2;
	}
	.select select {
		cursor: pointer;
		padding: 10px;
		width: 100%;
		border: none;
		background: transparent;
		background-image: none;
		-webkit-appearance: none;
		-moz-appearance: none;
	}
 
	.select select:focus {
		outline: none;
	}
</style>
</head>
<body>
	<div class="panel admin-panel margin-top">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>汇报发布</strong>
		</div>
		<div class="body-content">
			<form method="post" id="outpost" class="form-x" action="TeacherPost">
				<div class="form-group">
					<div class="label">
						<label>对应班级：</label>
					</div>
					<div class="select">
						<select name="allclass" id="allclass">
							<c:forEach items="${sessionScope.allmyclass}" var="myclass">
								<option value='${myclass}'>${myclass}</option>
							</c:forEach>
						</select>
					</div>
					<div class="tips"></div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>周数：</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="weeknum" name="weeknum" maxlength="2"
							value=""
							onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"
							onblur="this.v();" style="width: 30%; left:30%" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>开始时间：</label>
					</div>
					<div class="field">
						<input type="datetime-local" class="input"  id="start_time" name="start_time"
							value="" style="width: 30%;"/>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>截止时间：</label>
					</div>
					<div class="field">
						<input type="datetime-local" class="input" id="end_time" name="end_time"
							value="" style="width: 30%;" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="button" onclick="return checkdate()">发布</button>
					</div>
				</div>
			</form>
		</div>
		<div>
			<h1 align="left" style="color: red">${postmes}</h1>
		</div>
	</div>
</body>
</html>