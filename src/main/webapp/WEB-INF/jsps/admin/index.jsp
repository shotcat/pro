<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/easyui/themes/default/default.css'/>">
<%@include file="/WEB-INF/jsps/common/taglib.jsp" %>

</head>
<body class="easyui-layout">
	<c:url value="/" var="basePath"/>

	<div data-options="region:'north',border:false" style="height:50px;padding:10px;background-image: url('${basePath}resources/easyui/header_bg.png');">

	 <div style="float: left;width:250px; padding:10px; color:white;"><strong>欢迎使用后台管理系统</strong></div>
	 <div style="float: right;padding:10px; color:white;">欢迎你,超级管理员&nbsp;&nbsp;&nbsp;<a style="color: white;" href="<c:url value='/j_spring_cas_security_logout'/>">注销</a></div>
	</div>
	<div data-options="region:'west',title:'菜单列表',border:false" style="width:150px;">
	
		<div id="aa" class="easyui-accordion" data-options="fit:true" style="width:150px;">
		<c:forEach items="${ systemMenus}" var="parentMenu">
		
		<div title="${ parentMenu.resourceName}" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
		
		<c:if test="${ not empty parentMenu.childrenMenus  }">
		<ul>
		<c:forEach items="${ parentMenu.childrenMenus}" var="childMenu">
		<li>
		<div>
		<a href="javascript:void(0)" id="_menu_${childMenu.resourceId }" title="${childMenu.resourceName }" url="${childMenu.url}"   style="width:100%; margin: 5px;height:25px;">
		<span class="icon ${childMenu.icon }">&nbsp;</span>
		<span class="nav">${childMenu.resourceName}</span>
		</a>
		</div></li>
		</c:forEach>
		  </ul>
			</c:if>
		</div>
		</c:forEach>
		
	</div>
	
	</div>
	<div data-options="region:'south',border:false" style="height:30px;padding:10px;background-image: url('${basePath}resources/easyui/header_bg.png');">
	
	
	</div>
	<div data-options="region:'center',title:'工作平台'">
	
	
	<div class="easyui-tabs" id="_content" data-options="fit:true" style="width:100%;height:auto">
		<div title="欢迎使用" style="padding:10px">
			<p style="font-size:14px">后台管理系统</p>
			<ul>
				<li>欢迎使用后台管理系统${basePath }</li>
				<li>JQuery easyui 框架,快速开发jquery 框架</li>
				<li>SpringSecurity3.2.5.RELEASE 安全认证框架(权限控制到方法(按钮级别)基本)</li>
				<li>MyBatis ORM 框架</li>
				<li>SpringMVC3.2.9 	WEB框架</li>
				<li>Mysql 数据库</li>
				<li>Redis 分布式缓存</li>
				<li>Jasig CAS 单点登录,对应用统一进行验证</li>
				<li>Apache Thrift </li>
			</ul>
		</div>
	</div></div>

	<script type="text/javascript">
	 
	var basePath = "<c:url value='/'/>"
	function addTab(title,url){
		
		
		if ($('#_content').tabs('exists', title)){ 
	         $('#_content').tabs('select', title); 
	     } else {
	      /**添加一个tab标签**/
			$('#_content').tabs('add',{
				title: title,
				content: "<iframe scrolling=\"no\" frameborder=\"0\"  src=\""+basePath + url+"\" style=\"width:100%;height:100%;\"></iframe>",
				closable: true
			});
	      
	     }
	}
	
	 $(function(){
		 
		 $("a[id^='_menu_']").click(function(){
			 var url = $(this).attr("url");
			 var title = $(this).attr("title");
			 addTab(title,url);
			 $('.easyui-accordion li div').removeClass("selected");
			 $(this).parent().addClass("selected"); 
			 return false;
		 }).hover(function(){
			 $(this).parent().addClass("hover");
		 },function(){
		 	$(this).parent().removeClass("hover");
		 });
	 });
	</script>
</body>
</html>