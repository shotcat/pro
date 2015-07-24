<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品列表</title>

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

<!-- 960 Container -->
<%@include file="/WEB-INF/jsps/front/common/header.jsp" %>
<!-- 960 Container / End -->


<!-- Content
================================================== -->


<!-- 960 Container / End -->
<div class="copyrights">Collect from <a href="http://blog.sina.com.cn/dodotoya"  title="新浪博客">新浪博客</a></div>
<!-- 960 Container -->
<div class="container">

	<div class="sixteen columns">
	
		<!-- Page Title -->
		<div id="page-title">
			<h2><a href="<c:url value='/product/list_1_1.shtml'/>">产品中心</a></h2>
			<div id="bolded-line"></div>
		</div>
		<!-- Page Title / End -->

	</div>
</div>

 <div class="container">
 	<div class="sixteen columns">
  <div style="float:right;"> 
	<c:forEach items="${types}" var="productType">
	<c:choose>
	 	<c:when test="${ productType.id == type}">
	 	<a class="button color" href="javascript:void(0)" style="cursor:pointer;">${productType.typeName }</a> &nbsp;&nbsp;
	 	</c:when>
	 	
	 	<c:otherwise>
	 	<a class="button light" href="<c:url value='/product/list_${productType.id }_1.shtml'/>" style="cursor:pointer;">${productType.typeName }</a> &nbsp;&nbsp;
	 	</c:otherwise>
	</c:choose>
	
	</c:forEach>
	</div>
	</div>
</div>
<!-- 960 Container -->
<div class="container">
		<div class="sixteen columns">
		
	
		<!-- Headline -->
		<div class="headline no-margin"><h3>产品展示</h3></div>
	</div>
	
	<c:forEach var="product" items="${pager.list }">
	<div class="four columns">
		<div class="picture"><a href="<c:url value='/product/info_${product.type }_${product.id }.shtml'/>"><img src="<c:url value='${product.image }'/>" alt="${product.title }"/><div class="image-overlay-link"></div></a></div>
		<div class="item-description">
			<h4><a href="#">${product.title }</a></h4>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;${product.remark }...</p>
			<span class="span12 text-right" style="float: right;"><a class="text-right" href="<c:url value='/product/info_${product.type }_${product.id }.shtml'/>" style="width: 100%">更多详情</a></span>
		</div>
			
	</div>
	</c:forEach>
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