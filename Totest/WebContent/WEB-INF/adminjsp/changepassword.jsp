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
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-key"></span> 修改密码</strong>
		</div>
		<div class="body-content">
			<form method="get" class="form-x" action="AccountServlet">
				<input type="hidden" name="operation" value="changePSWNow">
				<div class="form-group">
					<div>
						<h1 align="left" style="color: red">${message }</h1>
					</div>
					<div class="label">
						<label for="sitename">管理员工号：</label>
					</div>
					<div class="field">
						<label style="line-height: 33px;">${sessionScope.admin.adminId }</label>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="sitename">旧密码：</label>
					</div>
					<div class="field">
						<input type="password" class="input w50" id="mpass" name="mpass"
							size="50" placeholder="请输入原始密码"  maxlength="6"
							data-validate="required:请输入原始密码" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="sitename">新密码：</label>
					</div>
					<div class="field">
						<input type="password" class="input w50" name="newpass" size="50"
							placeholder="请输入新密码" maxlength="6"
							data-validate="required:请输入新密码" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="sitename">确认新密码：</label>
					</div>
					<div class="field">
						<input type="password" class="input w50" name="renewpass"
							size="50" placeholder="请再次输入新密码" maxlength="6"
							data-validate="required:请再次输入新密码,repeat#newpass:两次输入的密码不一致" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="submit">
							提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>