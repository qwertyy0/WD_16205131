<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*,javabean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>汇报提交</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>  
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
	<%
		HttpSession session1 = request.getSession(); 
		String sname=(String) session1.getAttribute("sname");
		
		if(request.getAttribute("rs")!=null){
			ResultSet rs=(ResultSet)request.getAttribute("rs");
			while(rs.next()) {
			String weeknum = rs.getString(1);
			String post_start_time=rs.getString(2);                                                                                                                                                                                                                                                      
			String post_end_time=rs.getString(3);
	
	%>
	<script type="text/javascript">
	<%
	Date d = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String now = df.format(d);
	System.out.print(now);
	%>
	checkTime = function() {
		var startTime = "<%=now%>";
		var endTime = document.getElementById("endTime").value;
		var fileField=document.getElementById("fileField").value;
		var arys1 = new Array();
		var arys2 = new Array();
		
			//包含时间，日期  
			if(fileField.length<1){
				alert("未选择文件");
				return false;
			}
			else{
			if (startTime.length > 0 && endTime.length > 0) {
			var startDateTemp = startTime.split(" ");
			var endDateTemp = endTime.split(" ");
			var arrStartDate = startDateTemp[0].split("-");
			var arrEndDate = endDateTemp[0].split("-");
			var arrStartTime = startDateTemp[1].split(":");
			var arrEndTime = endDateTemp[1].split(":");
			var allStartDate = new Date(arrStartDate[0], arrStartDate[1],
					arrStartDate[2], arrStartTime[0], arrStartTime[1],
					arrStartTime[2]);
			var allEndDate = new Date(arrEndDate[0], arrEndDate[1],
					arrEndDate[2], arrEndTime[0], arrEndTime[1], arrEndTime[2]);
			if (allStartDate.getTime() < allEndDate.getTime()) {
				return true;
			} else {
				alert("超时无法提交");
				return false;
			}
			}
		} 
	}
	
</script>
<div class="panel admin-panel">
	<div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 提交汇报</strong></div>
  <div class="body-content">
  <form method="post" action="Student_UploadServlet" enctype="multipart/form-data" class="form-x">
   		
   		 <div class="form-group">
        <div class="field">
          <label>
           	最新公告 ：第<%=weeknum %>周
          </label>
        </div>
      </div>      
   		
   		<div class="form-group">
        <div class="label">
          <label>选择文件：</label>
        </div>
        <div class="field">
         <input type="file" class="input w50" name="stu_file" id="fileField"  data-validate="required:文件大小不能超过100MB" onchange="document.getElementById('textfield').value=this.files[0].name" >
          <div class="tips"></div>
        </div>
     	</div>
     	
     	<div class="form-group">
        <div class="label">
          <label>第<%=weeknum %>周开始时间：</label>
        </div>
        <div class="field">
       <input type="datetime" class="input w50"  name="submit_time" value="<%=post_start_time%>" id="startTime" readonly="readonly">
          <div class="tips"></div>
        </div>
     	</div>
     	
     	
     	<div class="form-group">
        <div class="label">
          <label>第<%=weeknum %>周结束时间：</label>
        </div>
        <div class="field">
       <input type="datetime" class="input w50"  name="submit_time" value="<%=post_end_time%>" id="endTime"  readonly="readonly">
          <div class="tips"></div>
        </div>
     	</div>
     	
     	<div class="form-group">
        <div class="label">
          <label>上传者：</label>
        </div>
        <div class="field">
         <input type="text" class="input w50" name="upPerson" value="<%=sname%>" readonly="readonly">
          <div class="tips"></div>
        </div>
     	</div>
     	
     	<div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
        <button class="button bg-main icon-check-square-o" onclick="return checkTime()" type="submit">开始上传</button>
        </div>
      </div>
    </form>
    </div>
    </div>
<%
			}
	rs.absolute(0);
	}
			%>
</body>
</html>