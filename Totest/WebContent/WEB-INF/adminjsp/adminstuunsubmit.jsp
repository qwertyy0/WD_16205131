<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
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
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>所有未提交名单</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
	<!-- 不存在未提交报告的学生，即全部学生已经提交报告 -->
	<c:if test="${requestScope.message eq 'studentNotExist'}">
		<br>
		<br>
		<h1 align="center">全部学生已经提交本周汇报！</h1>
	</c:if>
	<!-- 存在未提交报告的学生 -->
	<c:if test="${requestScope.message eq 'studentExist' }">
		<c:if test="${requestScope.updatemessage eq 'updateSucceed' }">
			<div class="body-content" align="center" style="margin: 0">
				<div class="cloudTableAuto">
					<!-- 第一个table -->
					<table class="cloudTable cloudMtxTableHead">
						<colgroup>
							<col width="300px">
							<col width="420px">
							<col width="">
							<col width="60px">
						</colgroup>
						<thead class="cloudTable_header">
							<tr>
								<th>序号</th>
								<th>学号</th>
								<th>姓名</th>
							</tr>
						</thead>
					</table>
					<!-- 第二个table 外层包裹div并设置高度，超出出现滚动条 -->
					<div class="cloudMtxTable_body">
						<table class="cloudTable" style="table-layout: fixed">
							<colgroup>
								<col width="300px">
								<col width="420px">
								<col width="">
								<col width="60px">
							</colgroup>
							<tbody class="cloudTable_body ">
								<c:forEach items="${sessionScope.unPages.resultList}"
									begin="${sessionScope.unPages.pageSize*(sessionScope.unPages.currentPage-1) }"
									end="${sessionScope.unPages.pageSize*(sessionScope.unPages.currentPage-1)+sessionScope.unPages.pageSize-1 }"
									var="studentList" varStatus="status">
									<tr>
										<td>${status.count+sessionScope.unPages.pageSize*(sessionScope.unPages.currentPage-1) }</td>
										<td class="data-cell"><span>${studentList.sno }</span></td>
										<td class="data-cell" colspan="2">${studentList.sna }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<h4 style="position: absolute; left: 39%; top: 60%;">
					<c:if test="${sessionScope.unPages.currentPage eq 1 }">&nbsp;上一页&nbsp;</c:if>
					<c:if test="${sessionScope.unPages.currentPage > 1 }">
						<a
							href="AdminStudentServlet?operation=unsubmitStudent&currentPage=${sessionScope.unPages.currentPage-1 }">&nbsp;[上一页]&nbsp;</a>
					</c:if>
					<c:forEach begin="1" end="${sessionScope.unPages.totalPage }"
						varStatus="status">
						<c:if test="${sessionScope.unPages.currentPage eq status.count}">&nbsp;${status.count }&nbsp;</c:if>
						<c:if test="${sessionScope.unPages.currentPage ne status.count }">
							<a
								href="AdminStudentServlet?operation=unsubmitStudent&currentPage=${status.count }">&nbsp;${status.count }&nbsp;</a>
						</c:if>
					</c:forEach>
					<c:if
						test="${sessionScope.unPages.currentPage < sessionScope.unPages.totalPage} ">
						<a
							href="AdminStudentServlet?operation=unsubmitStudent&currentPage=${sessionScope.unPages.currentPage+1 }">&nbsp;[下一页]&nbsp;</a>
					</c:if>
					<c:if
						test="${sessionScope.unPages.currentPage eq sessionScope.unPages.totalPage}">下一页</c:if>
				</h4>
			</div>
		</c:if>
		<c:if test="${requestScope.updatemessage eq 'updateFailed' }">
			<h1 align="center">操作失败，请重试！！</h1>
		</c:if>
	</c:if>
</body>
</html>