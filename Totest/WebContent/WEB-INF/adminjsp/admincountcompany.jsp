<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="js/city.js"></script>
<script src="js/jquery-1.11.1.min.js"></script>
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
<title>学生所在实习单位信息</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
<script type="text/javascript">
	function check() {
		if (document.getElementById("province").value == "---请选择省/市---"
				&& document.getElementById("city").value == "---请选择地区---") {
			alert("请选择省/市");
			return false;
		}
	}
</script>
</head>
<body>
	<br>
	<br>
	<div style="" align="center">
		<form action="AdminFirmServlet" method="post">
			<input type="hidden" id="type" name="type" value="search">
			<div>
				<label>公司所在省/市：</label><select class="" name="firm_address_sheng"
					id="province"></select>
			</div>
			<br>
			<div>
				<label>公司所在地区：</label><select class="" name="firm_address_shi"
					id="city"></select>
			</div>
			<script>
				(function() {
					var pHtmlStr = '';
					for ( var name in pc) {
						pHtmlStr = pHtmlStr + '<option>' + name + '</option>';
					}
					$("#province").html(pHtmlStr);
					$("#province").change(
							function() {
								var pname = $("#province option:selected")
										.text();
								var pHtmlStr = '';
								var cityList = pc[pname];
								for ( var index in cityList) {
									pHtmlStr = pHtmlStr + '<option>'
											+ cityList[index] + '</option>';
								}
								$("#city").html(pHtmlStr);
							});
					$("#province").change();
				})();
			</script>
			<br>
			<button type="submit" style="width: 100px" onclick="return check();">查询</button>
		</form>
	</div>
	<br>
	<c:if test="${requestScope.type eq null }">
		<div class="body-content" align="center" style="margin: 0">
			<div class="cloudTableAuto">
				<!-- 第一个table -->
				<table class="cloudTable cloudMtxTableHead">
					<colgroup>
						<col width="60">
						<col width="220px">
						<col width="230px">
						<col width="90px">
						<col width="90px">
						<col width="160px">
						<col width="">
						<col width="16px">
					</colgroup>
					<thead class="cloudTable_header">
						<tr>
							<th>序号</th>
							<th>公司编号</th>
							<th>公司名称</th>
							<th>所在省/市</th>
							<th>所在地区</th>
							<th>联系人</th>
							<th>联系电话</th>
							<!-- <th>序号</th>
						<th>美途秀</th>
						<th>旅游机构</th>
						<th>Pv</th>
						<th>Uv</th>
						<th>活跃产品数(Uv≥20)</th>
						<th>分享率</th>
						<th style="width: 16px;"></th> -->
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
							<col width="90px">
							<col width="90px">
							<col width="160px">
							<col width="">
						</colgroup>
						<tbody class="cloudTable_body ">
							<c:forEach items="${sessionScope.firmPages.resultList}"
								begin="${sessionScope.firmPages.pageSize*(sessionScope.firmPages.currentPage-1) }"
								end="${sessionScope.firmPages.pageSize*(sessionScope.firmPages.currentPage-1)+sessionScope.firmPages.pageSize-1 }"
								var="firmList" varStatus="status">
								<tr>
									<td>${status.count+sessionScope.firmPages.pageSize*(sessionScope.firmPages.currentPage-1) }</td>
									<td class="data-cell"><span>${firmList.firm_id }</span></td>
									<td class="data-cell">${firmList.firm_name }</td>
									<td>${firmList.firm_address_sheng }</td>
									<td>${firmList.firm_address_shi }</td>
									<td>${firmList.firm_linkman }</td>
									<td colspan="2">${firmList.firm_linkman_phone }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<h4 style="position: absolute; left: 43%; top: 70%;">
				<c:if test="${sessionScope.firmPages.currentPage eq 1 }">上一页</c:if>
				<c:if test="${sessionScope.firmPages.currentPage > 1 }">
					<a
						href="AdminFirmServlet?operation=showFirmInfo&currentPage=${sessionScope.firmPages.currentPage-1 }">[上一页]</a>
				</c:if>
				<c:forEach begin="${sessionScope.firmPages.beginIndex }"
					end="${sessionScope.firmPages.endIndex }" varStatus="status">
					<c:if test="${sessionScope.firmPages.currentPage eq status.count}">${status.count }</c:if>
					<c:if test="${sessionScope.firmPages.currentPage ne status.count }">
						<a
							href="AdminFirmServlet?operation=showFirmInfo&currentPage=${status.count }">${status.count }</a>
					</c:if>
				</c:forEach>
				<c:if
					test="${sessionScope.firmPages.currentPage < sessionScope.firmPages.totalPage} ">
					<a
						href="AdminFirmServlet?operation=showFirmInfo&currentPage=${sessionScope.firmPages.currentPage+1 }">[下一页]</a>
				</c:if>
				<c:if
					test="${sessionScope.firmPages.currentPage eq sessionScope.firmPages.totalPage}">下一页</c:if>
			</h4>
		</div>
	</c:if>
	<c:if test="${requestScope.type eq 'search' }">
		<c:if test="${requestScope.firm.firm_id eq null }">
			<br>
			<br>
			<h1 style="" align="center">该地区没有学生实习！</h1>
		</c:if>
		<c:if test="${requestScope.firm.firm_id ne null }">
			<div class="body-content" align="center" style="margin: 0">
				<div class="cloudTableAuto">
					<!-- 第一个table -->
					<table class="cloudTable cloudMtxTableHead">
						<colgroup>
							<col width="60">
							<col width="220px">
							<col width="230px">
							<col width="90px">
							<col width="90px">
							<col width="160px">
							<col width="160px">
							<col width="">
							<col width="16px">
						</colgroup>
						<thead class="cloudTable_header">
							<tr>
								<th>序号</th>
								<th>公司编号</th>
								<th>公司名称</th>
								<th>所在省/市</th>
								<th>所在地区</th>
								<th>法人</th>
								<th>联系电话</th>
								<th>实习学生数</th>
								<!-- <th>序号</th>
						<th>美途秀</th>
						<th>旅游机构</th>
						<th>Pv</th>
						<th>Uv</th>
						<th>活跃产品数(Uv≥20)</th>
						<th>分享率</th>
						<th style="width: 16px;"></th> -->
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
								<col width="90px">
								<col width="90px">
								<col width="160px">
								<col width="160px">
								<col width="">
							</colgroup>
							<tbody class="cloudTable_body ">
								<tr>
									<td>1</td>
									<td class="data-cell"><span>${requestScope.firm.firm_id }</span></td>
									<td class="data-cell">${requestScope.firm.firm_name }</td>
									<td>${requestScope.firm.firm_address_sheng }</td>
									<td>${requestScope.firm.firm_address_shi }</td>
									<td>${requestScope.firm.firm_linkman }</td>
									<td>${requestScope.firm.firm_linkman_phone }</td>
									<td colspan="2">${requestScope.firm.stuNum }</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</c:if>
	</c:if>
	<script type="text/javascript">
		
	</script>
</body>
</html>