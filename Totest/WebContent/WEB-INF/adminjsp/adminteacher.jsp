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
<script type="text/javascript">
	function checkform(o) {
		var f_content = form1.file.value;
		var fileext = f_content.substring(f_content.lastIndexOf("."),
				f_content.length)
		fileext = fileext.toLowerCase()
		if (fileext != '.xlsx') {
			alert("对不起，导入数据格式必须是xlsx格式文件哦，请您调整格式后重新上传，谢谢 ！");
			o.file.focus();
			return false;
		}
		return true;
	}
</script>
<script type="text/javascript">
	function checkUploadFileNull() {
		if (document.getElementById("uploadfile").value == null
				|| document.getElementById("uploadfile").value == "") {
			alert("请选择上传文件！");
			return false;
		} else {
			return true;
		}
	}
</script>
<style type="text/css">
.uploadfile {
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

.uploadfile input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
}

.uploadfile:hover {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
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
			<strong><span class="icon-pencil-square-o"></span>增加教师信息</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x"
				action="InsertTeacherByXlsxServlet" enctype="multipart/form-data">
				<div class="form-group">
					<div>
						<!-- 如果上传文件失败 -->
						<c:if test="${requestScope.uploadSucceed eq 'uploadFailed' }">
							<h2 style="color: red;">操作失败，请重试！</h2>
							<br>
						</c:if>
						<!-- 如果上传文件成功 -->
						<c:if test="${requestScope.uploadSucceed eq 'uploadSucceed' }">
							<!-- 如果插入教师信息成功 -->
							<c:if test="${requestScope.insertSucceed eq 'insertSucceed' }">
								<h2>导入教师信息成功！</h2>
								<br>
							</c:if>
							<c:if test="${requestScope.insertSucceed eq 'insertFailed' }">
								<h2 style="color: red;">导入教师信息失败！请重试！(原因可能是表格中的信息已存在于数据库中)</h2>
								<br>
							</c:if>
						</c:if>
					</div>
					<div class="label">
						<label>请选择文件：</label>
					</div>
					<div class="field">
						<a href="javascript" class="uploadfile">选择Excel文件 <input
							type="file" name="uploadfile" id="uploadfile" onclick="">
						</a>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" type="submit"
							onclick="return checkUploadFileNull();">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>