<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="entity.forPage"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="utf-8">
<title>班级查询</title>

</head>
<body>
<div class="panel admin-panel">  
	<div class="panel-head"><strong><span class="icon-pencil-square-o"></span>学生信息</strong></div>
		<div style="padding-left:30%">
			<%
				int currentPage = 1;
				if (request.getParameter("currentPage") != null) {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				forPage newPage = new forPage(currentPage,
						((forPage) request.getSession().getAttribute("page")).getStudentList());
				request.removeAttribute("page");
				request.setAttribute("page", newPage);
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
				<c:forEach items="${requestScope.page.studentList}" var="mystudent"
					begin="${requestScope.page.pageSize*(requestScope.page.currentPage-1) }"
					end="${requestScope.page.pageSize*(requestScope.page.currentPage-1)+requestScope.page.pageSize-1 }"
					varStatus="status">
					<tr>
						<td>${status.count+requestScope.page.pageSize*(requestScope.page.currentPage-1) }</td>
						<td>${mystudent.sno }</td>
						<td>${mystudent.class_id}</td>
						<td>${mystudent.sname}</td>
						<td>${mystudent.smajor}</td>
						<td>${mystudent.sphone}</td>
						<td>${mystudent.sqq}</td>	
						<td><a href="ChangeInfo?student_id=${mystudent.sno}" >修改信息</a></td>
					</tr>
				</c:forEach>
			</table>
			<p style="padding-left:25%;">
				<c:forEach begin="${requestScope.page.beginIndex }"
					end="${requestScope.page.endIndex }" varStatus="status">
					<a  class="selectby" href="teacher/selectbyclass_id.jsp?currentPage=${status.index }">${status.index }</a>
				</c:forEach>
			</p>
		</div>
	</div>
</body>
</html>