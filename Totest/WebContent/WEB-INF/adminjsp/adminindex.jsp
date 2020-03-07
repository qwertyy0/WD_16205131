<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>实训管理中心</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
</head>
<body style="background-color: #f2f9fd;">
	<div class="header bg-main">
		<div class="logo margin-big-left fadein-top">
			<h1>
				<img src="adminHeadImg/timg.jpg" class="radius-circle rotate-hover"
					height="50" alt="" />实训管理中心
			</h1>
		</div>
		<div class="head-l">
			<a class="button button-little bg-green" target="_blank"><span
				class="icon-home"></span> 首页</a> &nbsp;&nbsp;<a
				class="button button-little bg-red" href="/toTest/LogoutServlet"><span
				class="icon-power-off"></span> 注销</a>
		</div>
	</div>
	<div class="leftnav">
		<div class="leftnav-title">
			<strong><span class="icon-list"></span>菜单列表</strong>
		</div>
		<h2>
			<span class="icon-user"></span>账号信息
		</h2>
		<ul style="display: block">
			<li><a href="AccountServlet?operation=showInfo" target="right"><span
					class="icon-caret-right"></span>个人信息</a></li>
			<li><a href="AccountServlet?operation=changeInfo" target="right"><span
					class="icon-caret-right"></span>修改个人信息</a></li>
			<li><a href="AccountServlet?operation=changePSW" target="right"><span
					class="icon-caret-right"></span>修改密码</a></li>
			<!-- 注释<li><a href="page.html" target="right"><span class="icon-caret-right"></span>单页管理</a></li>
    <li><a href="adv.html" target="right"><span class="icon-caret-right"></span>首页轮播</a></li>
    <li><a href="book.html" target="right"><span class="icon-caret-right"></span>留言管理</a></li>
    <li><a href="column.html" target="right"><span class="icon-caret-right"></span>栏目管理</a></li>注释 -->
		</ul>

		<h2>
			<span class="icon-pencil-square-o"></span>教师管理
		</h2>
		<ul>
			<li><a href="AdminTeacherServlet?operation=addTeacherInfo"
				target="right"><span class="icon-caret-right"></span>导入教师信息</a></li>
			<li><a href="AdminTeacherServlet?operation=showTeacherInfo"
				target="right"><span class="icon-caret-right"></span>教师信息浏览</a></li>
			<!-- 注释<li><a href="cate.html" target="right"><span class="icon-caret-right"></span>分类管理</a></li> -->
		</ul>

		<h2>
			<span class="icon-pencil-square-o"></span>统计汇报
		</h2>
		<ul>
			<li><a href="AdminStudentServlet?operation=unsubmitStudent"
				target="right"> <span class="icon-caret-right"></span>未交学生名单
			</a></li>
		</ul>

		<h2>
			<span class="icon-pencil-square-o"></span>统计单位信息
		</h2>
		<ul>
			<li><a href="AdminFirmServlet?operation=showFirmInfo"
				target="right"> <span class="icon-caret-right"></span>统计单位信息
			</a></li>
			<!-- <li><a href="admincountadress.jsp" target="right"><span
					class="icon-caret-right"></span>统计分布信息</a></li> -->
		</ul>
	</div>
	<script type="text/javascript">
		$(function() {
			$(".leftnav h2").click(function() {
				$(this).next().slideToggle(200);
				$(this).toggleClass("on");
			})
			$(".leftnav ul li a").click(function() {
				$("#a_leader_txt").text($(this).text());
				$(".leftnav ul li a").removeClass("on");
				$(this).addClass("on");
			})
		});
	</script>
	<ul class="bread">
		<li><a href="" target="right" class="icon-home"> 首页</a></li>
		<li><b>当前语言：</b><span style="color: red;">中文</span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：英文 &nbsp;&nbsp;<a href="##"></a></li>
	</ul>
	<div class="admin">
		<iframe scrolling="auto" src="" name="right" width="100%"
			height="100%"></iframe>
	</div>
</body>
</html>