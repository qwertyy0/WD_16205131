<%@page import="java.sql.ResultSet"%>
<%@ page import="java.util.*,javabean.*,servlet.*" language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>个人信息修改</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>  
  	<script>
    function checkphone(){ 
        var sphone = document.getElementById('sphone').value;
        var sqq = document.getElementById('sqq').value;
        if(sphone.length<1||sqq.length<1)
        	{
        	alert("有数据未填写");  
            return false; 
        	}
        else{
             if(!(/^1[34578]\d{9}$/.test(sphone))){ 
            alert("必须填写有效手机号码");  
            return false; 
        } else {
        	return true;
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
<% 
	if(request.getAttribute("rs")!=null){
	ResultSet rs=(ResultSet)request.getAttribute("rs");
	while(rs.next()) {
	String sno=rs.getString(1);
	String sclass_id=rs.getString(2);                                                                                                                                                                                                                                                      
	String sna=rs.getString(3);
	String smajor = rs.getString(4);
	String sphone=rs.getString(5);
	String sQQ=rs.getString(6);
	
	String teacher_name = (String) session.getAttribute("teacher_name");
%>
	
<div class="panel admin-panel">

  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>个人信息修改</strong></div>
  <div class="body-content">
    <form method="post"  action="Student_Update1_info" class="form-x">
      <div class="form-group">
        <div class="label">
          <label>学号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="sno" value="<%=sno%>" readonly="readonly">
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>班级：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="class_id" value="<%=sclass_id%>" readonly="readonly"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="sname"  value="<%=sna%>" readonly="readonly"/>
        </div>
      </div>
      <div class="form-group" >
        <div class="label">
          <label>老师：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="user" value="<%=teacher_name%>" readonly="readonly"/>
        </div>
      </div>
      <div class="form-group" >
        <div class="label">
          <label>专业：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="smajor" value="<%=smajor%>" readonly="readonly"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>手机号：</label>
        </div>
        <div class="field">
        <input type="text" class="input w50" name="sphone"  id="sphone" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" data-validate="required:请输入手机号码,length#<=11:手机号码不能大于11位" value="<%=sphone%>"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>QQ号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="sqq" id="sqq" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" data-validate="required:请输入QQ号,length#>=6:QQ号大于5位"  value="<%=sQQ%>" />
          <div class="tips"></div>
        </div>
      </div>
      
       <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit" onclick="return checkphone()"> 提交</button>   
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