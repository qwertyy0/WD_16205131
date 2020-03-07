<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>导入教师信息</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>

<style type="text/css">
table {
	border-collapse: collapse;
	border-spacing: 0;
}

.cloudTableAuto {
	width: 1200px;
	margin: auto;
}

.cloudTable {
	border: 1px solid #e0e6ed;
	font-size: 16px;
	width: 100%;
}

.cloudTableTitle {
	padding: 10px 20px;
}

.cloudTable td {
	height: 40px;
	min-width: 0px;
	box-sizing: border-box;
	vertical-align: middle;
	position: relative;
	border-bottom: 1px solid #e0e6ed;
}
/*  表头 */
.cloudTable_header tr {
	background-color: #efeff1;
}

.cloudTable_header th {
	min-width: 0;
	box-sizing: border-box;
	text-overflow: ellipsis;
	vertical-align: middle;
	position: relative;
	border-bottom: 1px solid #e0e6ed;
	border-right: 1px solid #e0e6ed;
	padding: 10px 0px;
	white-space: nowrap;
	overflow: hidden;
	font-weight: bold;
	text-align: -internal-center;
}

/* 表身体 */
.cloudTable_body tr td {
	height: 30px;
	text-align: center;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	text-align: center;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	font-family: 'Lato', 'Helvetica Neue', Arial, Helvetica, sans-serif;
}

.cloudTable_body tr td {
	box-sizing: border-box;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	text-align: center;
	border-right: 1px solid #e0e6ed;
}

.cloudMtxTable_body {
	/*height:102px;*/
	height: 402px;
	width: 100%;
	overflow-y: auto;
	border-bottom: 1px solid #e0e6ed;
}

/* 公用样式 */
.data-cell {
	padding-left: 15px;
	padding-right: 15px;
	font-family: 'Lato', 'Helvetica Neue', Arial, Helvetica, sans-serif;
}

.cloudTable_body tr:hover {
	background-color: #c5e8fe;
}

.file_1 {
	position: relative;
	display: inline-block;
	background: #D0EEFF;
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 4px 12px;
	overflow: hidden;
	color: #1E88C7;
	text-decoration: none;
	text-indent: 0;
	line-height: 20px;
}

.file_1 input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
}

.file_1:hover {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>教师信息</strong>
		</div>
		<div class="body-content" align="center" style="margin: 0">
			<div class="cloudTableAuto">
				<!-- 第一个table -->
				<table class="cloudTable cloudMtxTableHead">
					<colgroup>
						<col width="60">
						<col width="220px">
						<col width="230px">
						<col width="200px">
						<col width="200px">
						<col width="">
						<col width="60px">
					</colgroup>
					<thead class="cloudTable_header">
						<tr>
							<th>序号</th>
							<th>工号</th>
							<th>姓名</th>
							<th>专业</th>
							<th>联系电话</th>
							<th>QQ</th>
						</tr>
					</thead>
				</table>
				<!-- 第二个table 外层包裹div并设置高度，超出出现滚动条 -->
				<div class="cloudMtxTable_body">
					<table class="cloudTable" style="table-layout: fixed">
						<colgroup>
							<col width="60">
							<col width="220px">
							<col width="230px">
							<col width="200px">
							<col width="200px">
							<col width="">
							<col width="60px">
						</colgroup>
						<tbody class="cloudTable_body ">
							<c:forEach items="${sessionScope.pages.resultList}"
								begin="${sessionScope.pages.pageSize*(sessionScope.pages.currentPage-1) }"
								end="${sessionScope.pages.pageSize*(sessionScope.pages.currentPage-1)+sessionScope.pages.pageSize-1 }"
								var="teacherList" varStatus="status">
								<tr>
									<td>${status.count+sessionScope.pages.pageSize*(sessionScope.pages.currentPage-1) }</td>
									<td class="data-cell"><span>${teacherList.teacher_id }</span></td>
									<td class="data-cell">${teacherList.teacher_name }</td>
									<td>${teacherList.teacher_major }</td>
									<td>${teacherList.teacher_phone }</td>
									<td colspan="2">${teacherList.teacher_qq }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<h4 style="position: absolute; left: 43%; top: 60%;">
				<c:if test="${sessionScope.pages.currentPage eq 1 }">上一页</c:if>
				<c:if test="${sessionScope.pages.currentPage > 1 }">
					<a
						href="AdminTeacherServlet?operation=showTeacherInfo&currentPage=${sessionScope.pages.currentPage-1 }">[上一页]</a>
				</c:if>
				<c:forEach begin="${sessionScope.pages.beginIndex }"
					end="${sessionScope.pages.endIndex }" varStatus="status">
					<c:if test="${sessionScope.pages.currentPage eq status.count}">${status.count }</c:if>
					<c:if test="${sessionScope.pages.currentPage ne status.count }">
						<a
							href="AdminTeacherServlet?operation=showTeacherInfo&currentPage=${status.count }">${status.count }</a>
					</c:if>
				</c:forEach>
				<c:if
					test="${sessionScope.pages.currentPage < sessionScope.pages.totalPage} ">
					<a
						href="AdminTeacherServlet?operation=showTeacherInfo&currentPage=${sessionScope.pages.currentPage+1 }">[下一页]</a>
				</c:if>
				<c:if
					test="${sessionScope.pages.currentPage eq sessionScope.pages.totalPage}">下一页</c:if>
			</h4>
		</div>
	</div>
</body>
</html>