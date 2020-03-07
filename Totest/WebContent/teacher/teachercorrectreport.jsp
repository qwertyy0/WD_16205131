<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="entity.forPage"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>批阅汇报</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
<style type="text/css">
table.imagetable {
font-family: verdana,arial,sans-serif;
font-size:18px;
color:#444444;
border-width: 2px;
border-color: #999999;
border-collapse: collapse;
}
table.imagetable td {
border-width: 2px;
padding: 8px;
border-style: solid;
border-color: #999999;
}
</style>
<script type="text/javascript">
	function submitremark(){
		if(document.getElementById("correct").value.length==0||document.getElementById("correct").value==""){
			alert("汇报评语不能为空");
		}else{
			document.getElementById("remark").submit();
		}
	}
</script>
</head>
<body>
	<div class="panel admin-panel margin-top">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>学生汇报</strong>
		</div>
		<form action="">
			<div style="padding-top:5%;">
				<table border="2" align="center" class="imagetable">
					<%
						//第一页
						int currentpage = 1;
						ResultSet rs = (ResultSet)request.getSession().getAttribute("allstudentreport");
						
						if (request.getParameter("page") != null && !("".equals(request.getParameter("page")))) {
							currentpage = Integer.parseInt(request.getParameter("page"));
						} else {
							currentpage = 1;
						}
						//每页数据条数 
						int pagenum = 3;
						int totalrow = 0;
						int totalpage = 0;

						/*if(request.getParameter("select")==null||request.getParameter("inselect")==null)
						{
							 rs = musicdao.selectallmusic();
						}*/
						rs.last();
						totalrow = rs.getRow();
						totalpage = totalrow % pagenum == 0 ? totalrow / pagenum : totalrow / pagenum + 1;

						rs.absolute(pagenum * (currentpage - 1));
						int count = 0;
						out.print("<tr>");
						out.print("<td>序号</td>");
						out.print("<td>学号</td>");
						out.print("<td>文件名</td>");
						out.print("<td>下载</td>");
						out.print("<td>批阅</td>");
						out.print("<tr>");

						while (rs.next()) {
							//if(++count>m)
							//	break;
							out.print("<tr>");
							out.print("<td>"+ rs.getRow() +"</td>");
							out.print("<td>" + rs.getString(1) + "</td>");
							out.print("<td>" + rs.getString(3) + "</td>");
							out.print("<td>" + "<a href=StudentDownloadServlet1?report_name="+rs.getString(3)+">下载</a>" + "</td>");
							out.print("<td>" + "<a href=CorrectReoprt?sno="+rs.getString(1)+"&report_id="+rs.getString(4)+">批阅</a>" + "</td>");
							out.print("</tr>");
							count++;
							if (count > pagenum)
								break;
						}
					%>
				</table>
				<p style="text-align:center;">
				<%
					for (int i = 1; i <= totalpage; i++) {
						out.print("<a href='teacher/teachercorrectreport.jsp?page=" + i + "'>" + i + "</a>");
						out.print("&nbsp;&nbsp;");
					}
				%>
				</p>
			</div>
		</form>
		<c:if test="${sno ne null && reportid ne null}">
			<div class="body-content">
				<form action="CorrectReoprt" id="remark" method="post">
					<div class="form-group">
						<div class="label">
							<label>批阅评语：</label>
						</div>
						<div class="field">
							<textarea type="text" class="input"  maxlength="120" id="correct" name="note"
								style="height: 120px;" value=""></textarea>
							<div class="tips"></div>
						</div>
					</div>
					<input type="hidden"  name="needsno" value="${ sno}">
					<input type="hidden" name="needreportid" value="${ reportid}">
					<h3 align="left" style="color: red">${checkmes}</h3>
					<div class="form-group">
						<div class="label">
							<label></label>
						</div>
						<div class="field">
							<button class="button bg-main icon-check-square-o" type="button"
								id="changeinfo" onclick="submitremark()">提交</button>
						</div>
					</div>
				</form>	
			</div>
		</c:if>
			
	</div>
</body>
</html>