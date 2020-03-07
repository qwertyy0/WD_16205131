<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>个人信息修改</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>  
    <script type="text/javascript">
    	function submitinfo(){
    		if(document.getElementById("teacher_phone").value.length==0||document.getElementById("teacher_qq").value==0){
    			alert("必须输入电话号码和qq号");
    			return false;
    		}else{
    			if(document.getElementById("teacher_phone").value==${sessionScope.teacher.teacher_phone}&&document.getElementById("teacher_qq").value==${sessionScope.teacher.teacher_qq})
        		{
        				alert("未修改数据");
    					return false;
    			}else{
    				if(checkphone(document.getElementById("teacher_phone").value)){
    					document.getElementById("Info").submit();
    				}else{
    					alert("请输入有效的电话号码！")
    					return false;
    				}
    			}
        			
    		}
    		
    	}
    	
    	function checkphone(phone){
    		if(!(/^1[34578]\d{9}$/.test(phone))){   
                return false; 
            } else {
            	return true;
            }

    	}
    </script>
</head>
<body>
	
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 修改个人信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" id="Info" action="<%=request.getContextPath() %>/ChangeInfo">
      <div class="form-group">
        <div class="label">
          <label>工号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="teacher_id" readonly="readonly" value="${sessionScope.teacher.teacher_id}" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>专业：</label>
        </div>
        <div class="field">
          <input type="text" id="url1" name="teacher_major" class="input" readonly="readonly" value="${sessionScope.teacher.teacher_major}"   />
          <!-- data-toggle="hover" data-place="right" data-image="" -->
<!--      <input type="button" class="button bg-blue margin-left" id="image1" value="+ 浏览上传" >-->        
		</div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="teacher_name" readonly="readonly" value="${sessionScope.teacher.teacher_name}" />
        </div>
      </div>
      <div class="form-group" >
        <div class="label">
          <label>手机号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="teacher_phone" id="teacher_phone" maxlength="11" value="${sessionScope.teacher.teacher_phone}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>QQ号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="teacher_qq"  id="teacher_qq" maxlength="13" value="${sessionScope.teacher.teacher_qq}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"
          />
          <div class="tips"></div>
        </div>
      </div>
	  <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="button" id="changeinfo" onclick="submitinfo()"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>