<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<style>
p {
	margin: 0px;
	padding: 0;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>管理员个人信息</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-pencil-square-o"></span> 个人信息</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" action="">
				<div class="form-group">
					<div class="" style="margin-left: 6.5%; float: left;">
						<p>工号：</p>
					</div>
					<div class="" style="float: left;">
						<p>${admin.adminId }</p>
					</div>
				</div>
				<div class="form-group">
					<div class="" style="margin-left: 6.5%; float: left;">
						<p>用户名：</p>
					</div>
					<div class="" style="float: left;">
						<p>${admin.adminName }</p>
					</div>
				</div>
				<div class="form-group">
					<div class="" style="margin-left: 6.5%; float: left;">
						<p>联系电话：</p>
					</div>
					<div class="" style="float: left;">
						<p>${admin.adminPhone }</p>
					</div>
				</div>
				<div class="form-group">
					<div class="" style="margin-left: 6.5%; float: left;">
						<p>QQ：</p>
					</div>
					<div class="field" style="float: left;">
						<p>${admin.adminqq }</p>
					</div>
				</div>
				<div class="form-group">
					<div class="" style="margin-left: 6.5%; float: left;">
						<p>备注：</p>
					</div>
					<div class="" style="float: left;">
						<p>qaqaqa！好男人凯少至高无上！qaqaqa</p>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>