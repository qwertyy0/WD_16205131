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
<title>登录</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
<script type="text/javascript">
	 function reload(){
		document.getElementById("image").src="<%=request.getContextPath()%>/ImageServlet?date="+ new Date().getTime();
		$("#passcode").val(""); // 将验证码输入值清空
	}

	/* 	 function verificationcode(){
	 var text=$.trim($("#checkcode").val());//获取#checkcode的标签的值（多个值时默认为第一个）
	 $.post("${pageContext.request.contextPath}/verificationServlet",{code:text},function(data){
	 data=parseInt($.trim(data));
	 if(data>0){
	 $("#span").text("验证成功!").css("color","green");
	 }else{
	 $("#span").text("验证失败!").css("color","red");
	 reload();  //验证失败后需要更换验证码
	 }
	 });
	 $("#passcode").val(""); // 将验证码清空
	 } */
</script>
<script type="text/javascript">
	if (window != top)
		top.location.href = "LoginServlet";
</script>
</head>
<body>
	<div class="bg"></div>
	<div class="container">
		<div class="line bouncein">
			<div class="xs6 xm4 xs3-move xm4-move">
				<div style="height: 150px;"></div>
				<div class="media media-y margin-big-bottom"></div>
				<form action="LoginServlet" method="post" id="frm">
					<div class="panel loginbox">
						<div class="text-center margin-big padding-big-top">
							<h1>实习实训管理系统</h1>
							<h2 style="color: red;">${message}</h2>
						</div>
						<div class="panel-body"
							style="padding: 30px; padding-bottom: 10px; padding-top: 10px;">
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="text" class="input input-big" name="username"
										placeholder="登录账号" data-validate="required:请填写账号" /> <span
										class="icon icon-user margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="password" class="input input-big" name="password"
										placeholder="登录密码" data-validate="required:请填写密码" /> <span
										class="icon icon-key margin-small"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<select class="input input-big" name="loginType" size="1">
										<option value="0">管理员</option>
										<option value="1">教师</option>
										<option value="2">学生</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="field">
									<input type="text" class="input input-big" name="code"
										id="passcode" placeholder="填写下方的验证码"
										data-validate="required:请填写右侧的验证码" />
									<div class="passimage"
										style="padding-left: 70%; padding-top: 3%">
										<a href="javascript:reload();"><img
											src="<%=request.getContextPath()%>/ImageServlet" alt="验证码"
											id="image" /></a><br>

									</div>
								</div>
							</div>
						</div>
						<div style="padding: 30px;">
							<input type="submit"
								class="button button-block bg-main text-big input-big"
								value="登录">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>