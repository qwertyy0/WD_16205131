<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>个人信息</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>个人信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">
      <div class="form-group">
        <div class="label">
          <label>工号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="teacher_id" readonly="readonly" value="${sessionScope.teacher.teacher_id}" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>专业：</label>
        </div>
        <div class="field">
          <input type="text" id="url1" name="teacher_major" class="input"  readonly="readonly" value="${sessionScope.teacher.teacher_major}"   />
          <!-- data-toggle="hover" data-place="right" data-image="" -->
<!--      <input type="button" class="button bg-blue margin-left" id="image1" value="+ 浏览上传" >-->        
		</div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="teacher_name" readonly="readonly" value="${sessionScope.teacher.teacher_name}" />
        </div>
      </div>
      <div class="form-group" >
        <div class="label">
          <label>手机号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="teacher_phone" readonly="readonly" value="${sessionScope.teacher.teacher_phone}" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>QQ号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="teacher_qq" readonly="readonly" value="${sessionScope.teacher.teacher_qq}" />
          <div class="tips"></div>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>