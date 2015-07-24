<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理-用户登录</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/easyui/themes/default/easyui.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/easyui/themes/icon.css'/>">
	<script type="text/javascript" src="<c:url value='/resources/easyui/jquery.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/easyui/jquery.easyui.min.js'/>"></script>
</head>
<body>
<center style="margin-top:150px;">
		<form id="ff" action="https://www.cas.com:8443/cas/login" method="post">
		<div class="easyui-panel" title="用户登录" style="width:400px;height:200px;padding:20px 60px 20px 60px">
		<table cellpadding="5">
			<tr>
				<td>用户名:</td>
				<td><input class="easyui-textbox" value="admin" name="userName" data-options="required:true,validType:'length[3,10]',novalidate:true"></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input class="easyui-textbox"  value="123456" type="password" name="password" data-options="required:true,validType:'length[3,10]',novalidate:true"></td>
			</tr>
		</table>
		
		<div style="margin-top: 20px; text-align: center;padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">登录</a>&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
		
		</div>
	</div>
	</form>
	</center>
	<iframe src="https://sso.dongtian.com:8443/cas/login?http://sys.dongtian.com:8070/pro/index.shtml&get-lt=true&isAjax=true"></iframe>
	<script src="<c:url value='/resources/easyui/jquery.min.js'/>" type="text/javascript"></script>
	
	
	<script>
	document.domain="dongtian.com";
	function submitForm() {
		alert(ok);
	}
	$(function(){
		//flushLoginTicket();
	});
	 function flushLoginTicket(){  
	    var _services = 'service=' + encodeURIComponent('http://sys.dongtian.com/pro/');  
	    $.getScript('https://sso.dongtian.com:8443/cas/login?'+_services+'&get-lt=true&isAjax=true&n='   
	            + new Date().getTime(),   
	    function(data){  
	    	alert(data);
	    });
	}
	    
	</script>
</body>
</html>