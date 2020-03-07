<%@page import="java.sql.ResultSet"%>
<%@ page import="java.util.*,javabean.*" language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>汇报提交</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>  
<title>查看汇报</title>
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

a{
	color:#444444;
}
</style>
</head>
<body style="">
<div class="panel admin-panel">
<div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 查看以往汇报</strong></div>
<div class="info1">
	<table border=2 align="center" class="imagetable">
	
		<%
		           
			int current = 1;
			int m=8;
			if (request.getParameter("page") != null)
				current = Integer.parseInt(request.getParameter("page"));
			HttpSession session2 = request.getSession(); 
			String sno=((Student) session.getAttribute("student")).getSno();
			
			ReportDAO report=new ReportDAO();
			List<Report> list=report.getResults(sno);
			int totalPages = ((list.size()+1)%m==0)?(list.size()+1)/m:(list.size()+1)/m+1;
			int j=(current-1)*m;
			int n=0;
			out.print("<br>");
			out.print("<tr>");
			out.print("<td>文件名</td>");
			out.print("<td>操作</td>");
			out.print("<td>查看</td>");
			out.print("<tr>");
			
			for(int i=j; i<list.size(); i++) {
				Report rs=(Report)list.get(i);
				out.print("<tr>");
				out.print("<td>" + rs.getReport_name() + "</td>");
				out.print("<td>" + "<a href=StudentDownloadServlet1?report_name="+rs.getReport_name()+">"+"下载" +"</a>"+"</td>");
				//out.print("<td>" + "<a href=index.jsp?report_name="+rs.getReport_name()+"&report_file="+rs.getReport_file()+">"+"下载" +"</a>"+"</td>");
				out.print("<td>" + "<a href=StudentQueryCheck?report_id="+rs.getReport_id()+">"+"查看批语" +"</a>"+"</td>");
				out.print("</tr>");
				n++;
				if(n%m==0)
					break;
			}
		%>
	</table>
	<%
		for (int i = 1; i <= totalPages; i++) {
	%>
	<div align="center">
	<a  href="studentlookreport.jsp?page=<%=i%>"><%=i%></a>
	</div>
	<%
		}
	%>
</div>
</div>
</body>