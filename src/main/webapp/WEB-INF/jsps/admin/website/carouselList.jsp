<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站前台轮播图列表</title>
<%@include file="/WEB-INF/jsps/common/taglib.jsp" %>
<script type="text/javascript" src="<c:url value='/resources/js/admin/carousel/carouselList.js'/>"></script>
</head>
<body>
	<table id="_carouseList"></table>
	
	
	<div id="tb" style="padding:2px 5px;">
<sec:authorize url="/admin/carousel/saveCarousel.mvc">
<a href="javascript:void(0);" class="easyui-linkbutton" id="_addCarouselBtn" iconCls="icon-add">新增</a>
</sec:authorize>
<sec:authorize url="/admin/carousel/updateCarousel.mvc">
<a href="javascript:void(0);" class="easyui-linkbutton" id="_editCarouselBtn" iconCls="icon-edit">修改</a>
</sec:authorize>

&nbsp;标题(模糊查询):
<input class="easyui-validatebox" id="_title" style="width:110px">
		创建时间: <input class="easyui-datebox" id="_startTime" data-options="editable:false" style="width:110px">
		到: <input class="easyui-datebox" id="_endTime" data-options="editable:false" style="width:110px">
		状态: 
		<select class="easyui-combobox" id="_state" panelHeight="auto" style="width:100px">
			<option value="">全部</option>
			<option value="0">有效</option>
			<option value="1">无效</option>
		</select>
		<a href="#" class="easyui-linkbutton" id="_searchBtn" iconCls="icon-search">查询</a>
	</div>
	
	<!-- 新增轮播图 -->
	
	
<div id="newCarouselWindow" class="easyui-window" title="新增轮播图" style="width:500px" data-options="closed:true,iconCls:'icon-add',collapsible:false,minimizable:false,maximizable:false,resizable:false,footer:'#footer'">
		<div id="content_div" style="padding:10px 30px 20px 30px">
	    <form id="newForm" class="easyui-form" method="post" data-options="novalidate:false">
	    	<table cellpadding="5" style="width:98%;">
	    		<tr>
	    			<td align="right" width="20%;">标题:</td>
	    			<td><input class="easyui-validatebox"  name="title" data-options="required:true"></input></td>
                   
	    		</tr>
	    		<tr>
	    			<td align="right">URL链接:</td>
	    			<td><input class="easyui-validatebox"  name="url" data-options="required:true"></input></td>
                   
	    		</tr>
                <tr>
	    			<td align="right" width="20%;" >轮播图片:</td>
	    			<td>
                    <input class="easyui-validatebox" id="imageUrl"  name="imageUrl" data-options="required:true"/> 
                   	<a class="easyui-linkbutton" id="uploadFileBtn">上传</a>&nbsp;
                    <a class="easyui-linkbutton" id="lookFile">查看</a>
                    </td>
                    
	    		</tr>
	    		<tr>
	    			<td align="right">排序:</td>
	    			<td><input class="easyui-numberbox"  name="sort" data-options="required:true"></input></td>
                   
	    		</tr>
                <tr>
	    			<td align="right">状态:</td>
	    			<td>
	    				<input  type="radio" id="state_normal" name="state" value="0" checked/> 正常 &nbsp;&nbsp;&nbsp;<input  type="radio" id="state_disable" name="state" value="1"/>禁用
	    			</td>
                  
	    		</tr>
	    		<tr>
	    			<td align="right">描述:</td>
	    			<td><input class="easyui-textbox" name="des" data-options="multiline:true" style="height:60px"></input></td>
                   
	    		</tr>
	    		
	    	</table>
	    </form>
	   
	    <div id="footer" style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="CarouselAdmin.ctrl.saveCarousel()">提交</a>&nbsp;&nbsp;&nbsp;
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#newCarouselWindow').window('close')">取消</a>
	    </div>
	   
	    </div>
	</div>
	
<div id="editCarouselWindow" class="easyui-window" title="更新轮播图" style="width:500px" data-options="closed:true,iconCls:'icon-add',collapsible:false,minimizable:false,maximizable:false,resizable:false,footer:'#_editfooter'">
		<div  style="padding:10px 30px 20px 30px">
	    <form id="editForm" class="easyui-form" method="post" data-options="novalidate:false">
	    <input type="hidden" name="id" id="_editId">
	    	<table cellpadding="5" style="width:98%;">
	    		<tr>
	    			<td align="right" width="20%;">标题:</td>
	    			<td><input class="easyui-validatebox" id="_editTitle"  name="title" data-options="required:true"></input></td>
                   
	    		</tr>
	    		<tr>
	    			<td align="right">URL链接:</td>
	    			<td><input class="easyui-validatebox" id="_editUrl"  name="url" data-options="required:true"></input></td>
                   
	    		</tr>
                <tr>
	    			<td align="right" width="20%;" >轮播图片:</td>
	    			<td>
                    <input class="easyui-validatebox" id="_editImageUrl"   name="imageUrl" data-options="required:true"/> 
                   	<a class="easyui-linkbutton" id="_editIUploadFileBtn">上传</a>&nbsp;
                    <a class="easyui-linkbutton" id="_editlookFile">查看</a>
                    </td>
                    
	    		</tr>
	    		<tr>
	    			<td align="right">排序:</td>
	    			<td><input class="easyui-numberbox" id="_editSort"  name="sort" data-options="required:true"></input></td>
                   
	    		</tr>
                <tr>
	    			<td align="right">状态:</td>
	    			<td>
	    				<input  type="radio" id="_edit_state_normal" name="state" value="0" checked/> 正常 &nbsp;&nbsp;&nbsp;<input  type="radio" id="edit_state_disable" name="state" value="1"/>禁用
	    			</td>
                  
	    		</tr>
	    		<tr>
	    			<td align="right">描述:</td>
	    			<td><input class="easyui-textbox" id="_editDes" name="des" data-options="multiline:true" style="height:60px"></input></td>
                   
	    		</tr>
	    		
	    	</table>
	    </form>
	   
	    <div id="_editfooter" style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="CarouselAdmin.ctrl.updateCarousel()">更新</a>&nbsp;&nbsp;&nbsp;
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#newCarouselWindow').window('close')">取消</a>
	    </div>
	   
	    </div>
	</div>
	  <input  type="file" id="uploadFile"  name="file" onchange="CarouselAdmin.ctrl.uploadFile();" style="width:0px;height:0px;"/>
	  <input  type="file" id="_editUploadFile"  name="file" onchange="CarouselAdmin.ctrl.editUploadFile();" style="width:0px;height:0px;"/>
	
</body>
</html>