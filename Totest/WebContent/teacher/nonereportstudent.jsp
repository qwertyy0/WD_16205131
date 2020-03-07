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
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
<meta charset="utf-8">
<title>未提交学生名单</title>
<!-- <script type="text/javascript">
    /* 获取session中的allmyclass列表值赋值 */
	List list = (List)session.getAttribute("allmyclass"); 
	for(var i=0;i<list.size();i++){
		$("#allclass").append("<option value='"+list.get(i)+"'>"+list.get(i)+"</option>");
	}
</script> -->
<script type="text/javascript">
<!--数据回显-->
	String oldclass = request.getAtribute("olclass");
	$("#allclass option[value='"+oldclass+"']").attr("selected",true);
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
			<strong><span class="icon-pencil-square-o"></span>未提交名单</strong>
		</div>
		<div class="body-content">
			<form action="NoneReport" method="post">
				<div class="form-group">
					<div class="label">
						<label>对应班级：</label>
					</div>
					<div class="select">
						<select name="allclass" id="allclass">
							<c:forEach items="${sessionScope.allmyclass}" var="myclass">
								<option value='${myclass}' >${myclass}</option>
							</c:forEach>
						</select>
					</div>
					<input type="submit" value="提交">
				</div>
			</form>
			<c:if test="${!empty sessionScope.page1 }" >
				<%
					int currentPage = 1;
					if (request.getParameter("currentPage") != null) {
						currentPage = Integer.parseInt(request.getParameter("currentPage"));
					}
					forPage newPage = new forPage(currentPage,
							((forPage) request.getSession().getAttribute("page1")).getStudentList());
					session.removeAttribute("page1");
					session.setAttribute("page1", newPage);
				%>
				<table border="2" cellspacing="2" class="t3"
					style="width: 600px; text-align: center;empty-cells: show; border-collapse: collapse;">
					<tr>
						<td>序号</td>
						<td>学号</td>
						<td>班级号</td>
						<td>姓名</td>
					</tr>
					<c:forEach items="${sessionScope.page1.studentList}" var="mystudent"
						begin="${sessionScope.page1.pageSize*(sessionScope.page1.currentPage-1) }"
						end="${sessionScope.page1.pageSize*(sessionScope.page1.currentPage-1)+sessionScope.page1.pageSize-1 }"
						varStatus="status">
						<tr>
							<td>${status.count+sessionScope.page1.pageSize*(sessionScope.page1.currentPage-1) }</td>
							<td>${mystudent.sno }</td>
							<td>${mystudent.class_id}</td>
							<td>${mystudent.sname}</td>
						</tr>
					</c:forEach>
				</table>	
				<p style="left:20%">
					<c:forEach begin="${sessionScope.page1.beginIndex }"
						end="${sessionScope.page1.endIndex }" varStatus="status">
						<a  class="selectby" href="teacher/nonereportstudent.jsp?currentPage=${status.index }">${status.index }</a>
					</c:forEach>
				</p>
			</c:if>
			<c:if test="${ requestScope.Nullmes ne 无未提交汇报学生 }">
				<h3 style="color:red; left:30%;">无未提交学生</h3>
			</c:if>
		</div>
	</div>
</body>
</html>