<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
    <!-- Styles Switcher
================================================== -->
<div id="style-switcher">
	<h2>样式切换 <a href="#"></a></h2>
	
	<div><h3>颜色</h3>
		<ul class="colors" id="color1">
			<li><a href="#" class="green" title="绿色"></a></li>
			<li><a href="#" class="blue" title="蓝色"></a></li>
			<li><a href="#" class="orange" title="橙色"></a></li>
			<li><a href="#" class="navy" title="深蓝色"></a></li>
			<li><a href="#" class="yellow" title="黄色"></a></li>
			<li><a href="#" class="peach" title="桃红色"></a></li>
			<li><a href="#" class="beige" title="米黄色"></a></li>
			<li><a href="#" class="purple" title=紫色></a></li>
			<li><a href="#" class="red" title="红色"></a></li>
			<li><a href="#" class="pink" title="粉红色"></a></li>
			<li><a href="#" class="celadon" title="灰绿色"></a></li>
			<li><a href="#" class="brown" title="棕色"></a></li>
			<li><a href="#" class="cherry" title="鲜红色"></a></li>
			<li><a href="#" class="gray" title="灰色"></a></li>
			<li><a href="#" class="dark" title="深黑色"></a></li>
			<li><a href="#" class="cyan" title="蓝绿色"></a></li>
			<li><a href="#" class="olive" title="橄榄色"></a></li>
			<li><a href="#" class="dirty-green" title="青绿色"></a></li>
		</ul>
		
	<h3>布局风格</h3>
	<div class="layout-style">
		<select id="layout-switcher">
			<option value="css/boxed">有栏线的排版</option>
			<option value="css/wide">宽布局</option>
		</select>
	</div>
	
	<h3>背景图片</h3>
		 <ul class="colors bg" id="bg">
			<li><a href="#" class="bg1"></a></li>
			<li><a href="#" class="bg2"></a></li>
			<li><a href="#" class="bg3"></a></li>
			<li><a href="#" class="bg4"></a></li>
			<li><a href="#" class="bg5"></a></li>
			<li><a href="#" class="bg6"></a></li>
			<li><a href="#" class="bg7"></a></li>
			<li><a href="#" class="bg8"></a></li>
			<li><a href="#" class="bg9"></a></li>
			<li><a href="#" class="bg10"></a></li>
			<li><a href="#" class="bg11"></a></li>
			<li><a href="#" class="bg12"></a></li>
			<li><a href="#" class="bg13"></a></li>
			<li><a href="#" class="bg14"></a></li>
			<li><a href="#" class="bg15"></a></li>
			<li><a href="#" class="bg16"></a></li>
			<li><a href="#" class="bg17"></a></li>
			<li><a href="#" class="bg18"></a></li>
		</ul>
		
	<h3>背景颜色</h3>
		<ul class="colors bgsolid" id="bgsolid">
			<li><a href="#" class="green-bg" title="Green"></a></li>
			<li><a href="#" class="blue-bg" title="Blue"></a></li>
			<li><a href="#" class="orange-bg" title="Orange"></a></li>
			<li><a href="#" class="navy-bg" title="Navy"></a></li>
			<li><a href="#" class="yellow-bg" title="Yellow"></a></li>
			<li><a href="#" class="peach-bg" title="Peach"></a></li>
			<li><a href="#" class="beige-bg" title="Beige"></a></li>
			<li><a href="#" class="purple-bg" title="Purple"></a></li>
			<li><a href="#" class="red-bg" title="Red"></a></li>
			<li><a href="#" class="pink-bg" title="Pink"></a></li>
			<li><a href="#" class="celadon-bg" title="Celadon"></a></li>
			<li><a href="#" class="brown-bg" title="Brown"></a></li>
			<li><a href="#" class="cherry-bg" title="Cherry"></a></li>
			<li><a href="#" class="gray-bg" title="Gray"></a></li>
			<li><a href="#" class="dark-bg" title="Dark"></a></li>
			<li><a href="#" class="cyan-bg" title="Cyan"></a></li>
			<li><a href="#" class="olive-bg" title="Olive"></a></li>
			<li><a href="#" class="dirty-green-bg" title="Dirty Green"></a></li>
		</ul></div>
	
		<div id="reset"><a href="#" class="button color green boxed">重置</a></div>
		
</div>