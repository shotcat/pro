<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
    
    <!-- Header
================================================== -->

<!-- 960 Container -->
<div class="container ie-dropdown-fix">

	<!-- Header -->
	<div id="header">

		<!-- Logo -->
		<div class="eight columns">
			<div id="logo">
				<a href="#"><img src="<c:url value='/resources/front/images/logo.png'/>" alt="logo" /></a>
				<div id="tagline">欢迎你,水果忍者</div>
				<div class="clear"></div>
			</div>
		</div>

		<!-- Social / Contact -->
		<div class="eight columns">
			
			<!-- Social Icons -->
			<ul class="social-icons">
				<li class="facebook"><a href="#">Facebook</a></li>
				<li class="twitter"><a href="#">Twitter</a></li>
				<li class="dribbble"><a href="#">Dribbble</a></li>
				<li class="linkedin"><a href="#">LinkedIn</a></li>
				<li class="pintrest"><a href="#">Pintrest</a></li>
			</ul>
			
			<div class="clear"></div>
			
			<!-- Contact Details -->
			<div id="contact-details">
				<ul>
					<li><i class="mini-ico-envelope"></i><a href="#">466862016@qq.com</a></li>
					<li><i class="mini-ico-user"></i>+86 010 111 111</li>
				</ul>
			</div>

		</div>

	</div>
	<!-- Header / End -->
	
	<!-- Navigation -->
	<div class="sixteen columns">

		<div id="navigation">
			<ul id="nav">

				<li class="active"><a href="<c:url value='/index.shtml'/>">首页</a></li>

				<li><a href="<c:url value='/about/aboutUs.shtml'/>">关于我们</a></li>

				<li><a href="<c:url value='/news/list_2_1.shtml'/>">新闻中心</a></li>

				<li><a href="<c:url value='/product/list_1_1.shtml'/>">产品中心</a>
				<!-- 
					<ul>
						<li><a href="portfolio_2.html">2 Columns</a></li>
						<li><a href="portfolio_3.html">3 Columns</a></li>
						<li><a href="portfolio_4.html">4 Columns</a></li>
						<li><a href="single_project.html">Single Project</a></li>
					</ul>
					 -->
				</li>

				<li><a href="<c:url value='/job/joinUs.shtml'/>">人才招聘</a></li>
				<li><a href="<c:url value='/contact/contactUs.shtml'/>">联系我们</a></li>

			</ul>

			<!-- Search Form -->
			<div class="search-form">
				<form method="get" action="#">
					<input type="text" class="search-text-box" />
				</form>
			</div>

		</div> 
		<div class="clear"></div>
		
	</div>
	<!-- Navigation / End -->

</div>
<!-- 960 Container / End -->