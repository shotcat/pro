<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>最新动态-动态详情</title>

<!-- Mobile Specific
================================================== -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS
================================================== -->
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/front/css/style.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/front/css/boxed.css'/>" id="layout">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/front/css/colors/green.css'/>" id="colors">

<!-- Java Script
================================================== -->
<script src="<c:url value='/resources/front/js/jquery.min.js'/>"></script>
<script src="<c:url value='/resources/front/js/custom.js'/>"></script>
<script src="<c:url value='/resources/front/js/selectnav.js'/>"></script>
<script src="<c:url value='/resources/front/js/flexslider.js'/>"></script>
<script src="<c:url value='/resources/front/js/twitter.js'/>"></script>
<script src="<c:url value='/resources/front/js/tooltip.js'/>"></script>
<script src="<c:url value='/resources/front/js/effects.js'/>"></script>
<script src="<c:url value='/resources/front/js/fancybox.js'/>"></script>
<script src="<c:url value='/resources/front/js/carousel.js'/>"></script>
<script src="<c:url value='/resources/front/js/isotope.js'/>"></script>

<!-- Styles Switcher
================================================== -->
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/front/css/switcher.css'/>">
<script src="<c:url value='/resources/front/js/switcher.js'/>"></script>
</head>
<body>


<!-- Wrapper Start -->
<div id="wrapper">


<!-- Header
================================================== -->

<%@include file="/WEB-INF/jsps/front/common/header.jsp" %>


<!-- Content
================================================== -->


<!-- 960 Container / End -->
<div class="copyrights">Collect from <a href="http://blog.sina.com.cn/dodotoya"  title="新浪博客">新浪博客</a></div>
<!-- 960 Container -->
<div class="container">

	<div class="sixteen columns">
	
		<!-- Page Title -->
		<div id="page-title">
			<h2><a href="<c:url value='/news/list_1_1.shtml'/>">最新动态</a></h2>
			<div id="bolded-line"></div>
		</div>
		<!-- Page Title / End -->

	</div>
</div>

<!-- 960 Container -->
<div class="container">
		
	<!-- 16 Columns -->
	<div class="sixteen columns">
	
	<!-- Post -->
	<div class="post">
		<div class="post-content" >
			<div class="post-title" style="text-align: center;"><h2><a href="blog_post.html">${dynamic.title }</a></h2></div>
			<div class="post-meta" style="text-align: center;"><span><i class="mini-ico-calendar"></i><fmt:formatDate value="${dynamic.updateTime }" pattern="yyyy-MM-dd hh:mm:ss"/></span> <span><i class="mini-ico-user"></i>发布人: <a href="#">管理员</a></span> <span><i class="mini-ico-comment"></i>共 <a href="#">12 评论</a></span></div>
			<div class="post-description">
				<p>${dynamic.content }</p>
			</div>
		</div>
	</div>
	</div>
	<!-- 16 Columns End -->
	
</div>
<!-- 960 Container End -->


<%@include file="/WEB-INF/jsps/front/common/client.jsp" %>

</div>
<!-- Wrapper / End -->

<!-- footer -->
<%@include file="/WEB-INF/jsps/front/common/footer.jsp" %>
<!-- 切换样式 -->
<%@include file="/WEB-INF/jsps/front/common/switch.jsp" %>


</body>
</html>