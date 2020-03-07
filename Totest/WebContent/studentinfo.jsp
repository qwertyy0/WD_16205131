<%@page import="java.sql.ResultSet"%>
<%@ page import="java.util.*,javabean.*" language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
 	
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>个人信息查询</title>  
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
	String sno=rs.getString(1);
	String sclass_id=rs.getString(2);                                                                                                                                                                                                                                                      
	String sname=rs.getString(3);
	String smajor = rs.getString(4);
	String sphone=rs.getString(5);
	String sQQ=rs.getString(6);
	
	String teacher_name = (String) session.getAttribute("teacher_name");
	
	HttpSession session0=request.getSession();
	session0.setAttribute("sno", sno);
	HttpSession session1=request.getSession();
	session1.setAttribute("sname", sname);
	HttpSession session2=request.getSession();
	session1.setAttribute("sclass_id", sclass_id);
%>
<div class="panel admin-panel">

  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 学生信息</strong></div>
  <div class="body-content">
  <div class="form-x" >
      <div class="form-group">
        <div class="label">
          <label>学号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="stitle" value="<%=sno%>" readonly="readonly">
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>班级：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name=class value="<%=sclass_id%>" readonly="readonly"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="surl"  value="<%=sname%>" readonly="readonly"/>
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
          <input type="text" class="input w50" name="user" value="<%=smajor%>" readonly="readonly"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>手机号：</label>
        </div>
        <div class="field">
        <input type="text" class="input w50" name="sentitle"  value="<%=sphone%>" readonly="readonly"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>QQ号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="sentitle" value="<%=sQQ%>" readonly="readonly" />
          <div class="tips"></div>
        </div>
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