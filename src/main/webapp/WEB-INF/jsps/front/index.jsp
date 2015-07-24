<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>

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


<%@include file="/WEB-INF/jsps/front/common/header.jsp" %>


<!-- Content
================================================== -->

<!-- 960 Container -->
<div class="container">

	<!-- Flexslider -->
	<div class="sixteen columns">
		<section class="slider">
			<div class="flexslider home">
				<ul class="slides">
				  <c:forEach items="${carousels }" var="carousel">
					<li>
					<a href="${ carousel.title}" >
						<img src="<c:url value='${ carousel.imageUrl}'/>" alt="${ carousel.title}" />
						</a>
						<div class="slide-caption">
							<h3>${ carousel.title}</h3>
							<p>${ carousel.des}</p>
						</div>
					</li>
					</c:forEach>
				</ul>
			</div>
		</section>
  	</div>
	<!-- Flexslider / End -->
	
</div>
<!-- 960 Container / End -->
<div class="copyrights">Collect from <a href="http://blog.sina.com.cn/dodotoya"  title="新浪博客">新浪博客</a></div>

<!-- 960 Container -->
<div class="container">

	<!-- Icon Boxes -->
	<div class="icon-box-container">

		<!-- Icon Box Start -->
		<div class="one-third column">
		<div class="headline no-margin"><h3>公司简介</h3></div>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;红提葡萄又名红地球，欧亚种。原产于美国加州，是由美国加利福尼亚州立大学研究人员于70年代进行杂交试验，培育而成的一个葡萄品种。自我国1987年引入该品种以来，华北及西北大部分地区栽培表现极好，果实品质优，晚熟，耐贮运，丰产，是发展葡萄的首选优质高效品种。目前来说，中国的产区主要分布在：陕西渭南，新疆，云南，甘肃，宁夏，黄河故道，昌黎，胶东半岛，江苏宝应，沈阳 。其中，三贤故里的渭南临渭区种植地，因红提葡萄品质好，面积大，最为有名。</p>
	</div>
		<!-- Icon Box End -->
		
		<!-- Icon Box Start -->
		<div class="one-third column">
		<div style="float:right;"> <a href="<c:url value='/news/list_2_1.shtml'/>" style="cursor:pointer;">更多</a></div>
		<div class="headline no-margin"><h3>新闻中心</h3></div>
		
		<ol class="">
		<c:forEach items="${news }" var="n">
		
			<li><a href="<c:url value='/news/info_${n.id}.shtml'/>">${n.title }</a></li>
		</c:forEach>
		</ol>
	</div>
		<!-- Icon Box End -->
		
		<!-- Icon Box Start -->
		<div class="one-third column">
		<div style="float:right;"> <a href="<c:url value='/dynamic/list_1_1.shtml'/>" style="cursor:pointer;">更多</a></div>
		<div class="headline no-margin"><h3>最新动态</h3></div>
		
		<ul class="star_list">
		<c:forEach items="${articles }" var="article">
			<li> <a href="<c:url value='/dynamic/info_${article.id}.shtml'/>">${article.title }</a></li>
			</c:forEach>
		</ul>
	</div>
		<!-- Icon Box End -->
		
	</div>
	<!-- Icon Boxes / End -->
	
</div>
<!-- 960 Container / End -->

<!-- 960 Container -->
<div class="container">

	<div class="sixteen columns">
	<div style="float:right;"> <a href="<c:url value='/product/list_1_1.shtml'/>" style="cursor:pointer;">更多</a></div>
		<!-- Headline -->
		<div class="headline no-margin"><h3>产品展示</h3></div>
	</div>
	<c:forEach items="${products}" var="product">
	<!-- Project -->
	<div class="four columns">
		<div class="picture"><a href="<c:url value='/product/info_${product.type }_${product.id}.shtml'/>"><img src="<c:url value='${product.image }'/>" alt="${product.title }"/><div class="image-overlay-link"></div></a></div>
		<div class="item-description">
			<h4><a href="<c:url value='/product/info_${product.type }_${product.id}.shtml'/>">${product.title }</a></h4>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;${product.remark }...</p>
			<span class="span12 text-right" style="float: right;"><a class="text-right" href="<c:url value='/product/info_${product.type }_${product.id}.shtml'/>" style="width: 100%;cursor:pointer;">更多详情</a></span>
		</div>
			
	</div>
	</c:forEach>
</div>
<!-- 960 Container / End -->


<%@include file="/WEB-INF/jsps/front/common/client.jsp" %>

</div>
<!-- Wrapper / End -->

<!-- footer -->
<%@include file="/WEB-INF/jsps/front/common/footer.jsp" %>
<!-- 切换样式 -->
<%@include file="/WEB-INF/jsps/front/common/switch.jsp" %>
</body>
</html>