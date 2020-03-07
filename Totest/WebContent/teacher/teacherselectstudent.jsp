<%@page import="entity.forPage"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <title>查询学生信息</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
	<style type="text/css">
	body, table {
		font-size: 12px;
	}
	
	form {
		padding:2% 1% 2%;
		
	}
	table {
		table-layout: automatic;
		empty-cells: show;
		border-collapse: collapse;
		margin: 0 auto;
	}
	
	td {
		white-space: nowrap;
		height: 20px;
	}
	
	table.t3 {
		border: 1px solid #fc58a6;
		color: #720337;
	}
	
	table.t3 th {
		background-color: #CCE8EB;
		background-repeat: :repeat-x;
		height: 30px;
		color: #35031b;
	}
	
	table.t3 td {
		border: 1px dashed #feb8d9;
		padding: 0 1.5em 0;
	}
	
	table.t3 th {
		border: 1px solid #b9f9dc;
		padding: 0 7px 0;
	}
	
	table.t3 tr.a1 {
		background-color: #fbd8e8;
	}
	
	.myclass{
		padding-left:30%;
	}
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
<script type="text/javascript">
    /* 获取session中的allmyclass列表值赋值 */
	List list = (List)session.getAttribute("allmyclass"); 
	for(var i=0;i<list.size();i++){
		$("#allclass").append("<option value='"+list.get(i)+"'>"+list.get(i)+"</option>");
	}
</script>

</head>
<body>
	
<div class="panel admin-panel">
	<div class="panel-head" ><strong><span class="icon-pencil-square-o"></span> 查询学生信息</strong></div>

	<!-- 查询所有学生信息 -->
	<div class="first">
			
		<form action="SelectMineStudent" method="post"> 
			<div class="myclass">
				<h3>查询选择班级：</h3>
				<div class="select" >
					<select name="allclass" id="allclass">
					<c:forEach items="${sessionScope.allmyclass}" var="myclass" >
						<option value='${myclass}'>${myclass}</option>
					</c:forEach>
					</select>
				</div>
				<input type="submit" value="提交">
			</div>
		</form>
		
		
		<form action="" method="post">
			<div>
				<%
					int currentPage = 1;
					if (request.getParameter("currentPage") != null) {
						currentPage = Integer.parseInt(request.getParameter("currentPage"));
					}
					forPage newPage = new forPage(currentPage,
							((forPage) request.getSession().getAttribute("page")).getStudentList());
					request.getSession().removeAttribute("page");
					request.getSession().setAttribute("page", newPage);
				%>
				<table border="2" cellspacing="0" class="t3"
					style="width: 600px; text-align: center;">
					<tr>
						<td>序号</td>
						<td>学号</td>
						<td>班级号</td>
						<td>姓名</td>
						<td>专业</td>
						<td>电话</td>
						<td>QQ</td>
					</tr>
					<c:forEach items="${sessionScope.page.studentList}" var="mystudent"
						begin="${sessionScope.page.pageSize*(sessionScope.page.currentPage-1) }"
						end="${sessionScope.page.pageSize*(sessionScope.page.currentPage-1)+sessionScope.page.pageSize-1 }"
						varStatus="status">
						<tr>
							<td>${status.count+sessionScope.page.pageSize*(sessionScope.page.currentPage-1) }</td>
							<td>${mystudent.sno }</td>
							<td>${mystudent.class_id}</td>
							<td>${mystudent.sname}</td>
							<td>${mystudent.smajor}</td>
							<td>${mystudent.sphone}</td>
							<td>${mystudent.sqq}</td>
						</tr>
					</c:forEach>
				</table>
				<p style="text-align: center;">
					<c:forEach begin="${sessionScope.page.beginIndex }"
						end="${sessionScope.page.endIndex }" varStatus="status">
						<a href="teacher/teacherselectstudent.jsp?currentPage=${status.index }">${status.index }</a>
					</c:forEach>
				</p>
			</div>

			<div id="div1" style="display: none;">
				<table width="80%" id="mytab" border="1" class="t3">
					<thead>
						<th width="8%">学号</th>
						<th width="8%">班级号</th>
						<th width="8%">姓名</th>
						<th width="8%">专业</th>
						<th width="8%">电话</th>
						<th width="8%">QQ</th>
					</thead>

					<tr class="a1">
						<td>wallimn</td>
						<td>http://blog.csdn.net/wallimn</td>
						<td>wallimn@tom.com</td>
						<td>http://wallimn.ys168.com</td>
						<td>54871876</td>
					</tr>
				</table>
			</div>


		</form>
	</div>
		
</div>
</body>
</html>