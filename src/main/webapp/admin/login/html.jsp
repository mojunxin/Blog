<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
<title>网站系统登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">


<!--可无视-->
<link rel="stylesheet" href="bootstrap.min.css">

<!--主要样式-->
<link type="text/css" href="style.css" rel="stylesheet" />

<script>
if(self!=top)
{
	parent.location.href='index.html';
}
function CheckLogin(obj){
	if(obj.username.value=='')
	{
		alert('请输入用户名');
		obj.username.focus();
		return false;
	}
	if(obj.password.value=='')
	{
		alert('请输入登录密码');
		obj.password.focus();
		return false;
	}
	return true;
}
</script>
</head>
<body>
<div class="container" align="center">
	<div class="col-md-6" style="margin-top: 20%;">
		<div class="inset">
			<form name="login" id="login" method="post" action='<c:url value="/userController/login"></c:url> ' onSubmit="return CheckLogin(document.login);">
				<input type="hidden" name="enews" value="login">
				<div>
					<h2>后台管理系统</h2>
					<span style="text-align: left;text-indent: 0.4em;"><label>用户名</label></span>
					<span><input type="text" name="account" class="textbox" ></span>
				</div>
				<div>
					<span style="text-align: left;text-indent: 0.4em;"><label>密码</label></span>
					<span><input  name="password" type="password" class="password"></span>
				</div>
				<div class="sign">
					<input type="reset"  class="submit" value="重置"/>
					<input type="submit" value="登录" class="submit" />
				</div>
				<p>${errMsg}</p>
			</form>
		</div>
	</div>
</div>

<script>
if(document.login.equestion.value==0)
{
	showanswer.style.display='none';
}
</script>
</body>
</html>