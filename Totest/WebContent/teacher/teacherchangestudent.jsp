<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<title>修改学生信息</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
	<link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <script type="text/javascript">
    	function submitupinfo(){
    		if(document.getElementById("smajor").value.length==0||document.getElementById("sphone").value.length==0||document.getElementById("sqq").value.length==0){
    			alert("学生信息不能为空");
    			return false;
    		}else{
    			if(checkphone(document.getElementById("phone").value)){
					document.getElementById("upstuinfo").submit();
				}else{
					alert("请输入有效的电话号码！");
					return false;
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
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>修改学生信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" id="upstuinfo" action="<%=request.getContextPath() %>/UpStuInfo">
      <div class="form-group">
        <div class="label">
          <label>学号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="sno"  readonly="readonly" value="${requestScope.selectbyid.sno} " />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>班级：</label>
        </div>
        <div class="field">
          <input type="text" id="url1" name="class_is" class="input tips" readonly="readonly" value="${requestScope.selectbyid.class_id}" />
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="sname" readonly="readonly" value="${requestScope.selectbyid.sname}"/>
        </div>
      </div>
      <div class="form-group" >
        <div class="label">
          <label>专业：</label>
        </div>
        <div class="field">
          <input type="text" class="input" id="smajor" name="smajor"  value="${requestScope.selectbyid.smajor}" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>手机号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" id="sphone" name="sphone" id="phone" value="${requestScope.selectbyid.sphone}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>QQ号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" id="sqq" name="sqq"  value="${requestScope.selectbyid.sqq}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"/>
          <div class="tips"></div>
        </div>
      </div>             
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit" onclick=" return submitupinfo()"> 修改</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>