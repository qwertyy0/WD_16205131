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
<title>个人信息修改</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-pencil-square-o"></span> 修改个人信息</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" action="AccountServlet">
				<input type="hidden" value="changeInfoNow" name="operation">
				<div class="form-group">
					<div class="label">
						<label style="padding: 10px 8px"> ${message }工号： </label>
					</div>
					<div>
						<label style="line-height: 33px;">&nbsp;&nbsp;${admin.adminId }</label>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>用户名：</label>
					</div>
					<div class="field">
						<input type="text" class="input" name="username" maxlength="20"
							value="${admin.adminName }" style="width: 300px" size="30"
							data-validate="required:请输入用户名" />
					</div>
				</div>
				<!-- <div class="form-group">
					<div class="label">
						<label>修改头像：</label>
					</div>
					<div class="field">
						<input type="text" id="url1" name="adminlogo" class="input tips"
							style="width: 25%; float: left;" value="" data-toggle="hover"
							data-place="right" data-image="" /> <input type="button"
							class="button bg-blue margin-left" id="image1" value="+ 浏览上传">
					</div>
				</div> -->
				<div class="form-group">
					<div class="label">
						<label>联系电话：</label>
					</div>
					<div class="field">
						<input type="text" class="input" name="telephone"
							value="${admin.adminPhone }" style="width: 400px" maxlength="11" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>QQ：</label>
					</div>
					<div class="field">
						<input type="text" class="input" name="qq"
							value="${admin.adminqq }" style="width: 400px" maxlength="11" />
					</div>
				</div>
				<!-- <div class="form-group">
					<div class="label">
						<label>备注：</label>
					</div>
					<div class="field">
						<textarea name="scopyright" class="input"
							style="width: 700px; height: 100px;"></textarea>
						<div class="tips"></div>
					</div>
				</div> -->
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="submit">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>