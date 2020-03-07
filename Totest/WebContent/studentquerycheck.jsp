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
</head>
<body>
<% 
	if(request.getAttribute("rs")!=null){
	ResultSet rs=(ResultSet)request.getAttribute("rs");
	while(rs.next()) {
	String teacher_check = rs.getString(4);
	if(teacher_check==null){
		teacher_check="";
	}
%>
	
<div class="panel admin-panel">

  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 教师批语</strong></div>
  <div class="body-content">
   
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <input  type="text" class="input"  name="teaacher_check" id="teaacher_check" value="<%=teacher_check%>" readonly="readonly">
          <div class="tips"></div>
        </div>
      </div>
  </div>
</div>
			<%
			}
	rs.absolute(0);
	}
			%>
</body>
</html>