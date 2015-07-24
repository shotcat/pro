<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站前台文章管理</title>
<%@include file="/WEB-INF/jsps/common/taglib.jsp" %>
<script type="text/javascript" src="<c:url value='/resources/kindeditor-4.1.10/kindeditor.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/kindeditor-4.1.10/lang/zh_CN.js'/>"></script>
</head>
<body>
	<table id="_articleList"></table>
	
	
	<div id="tb" style="padding:2px 5px;">
<sec:authorize url="/admin/article/saveArticle.mvc">
<a href="javascript:void(0);" class="easyui-linkbutton" id="_addArticlelBtn" iconCls="icon-add">新增</a>
</sec:authorize>
<sec:authorize url="/admin/article/updateArticle.mvc">
<a href="javascript:void(0);" class="easyui-linkbutton" id="_editArticleBtn" iconCls="icon-edit">修改</a>
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
		文章类别:
		<select class="easyui-combobox" id="_type" panelHeight="auto" style="width:100px">
			<option value="">全部</option>
					<option value="1" selected>最新动态</option>
					<option value="2">新闻中心</option>
					<option value="3">关于我们</option>
					<option value="4">人才招聘</option>
					<option value="5">联系我们</option>
		</select> &nbsp;
		<a href="javascript:void(0);" class="easyui-linkbutton" id="_searchBtn" iconCls="icon-search">查询</a>
	</div>
	
	<!-- 新增文章 -->
	
	
<div id="newArticleWindow" class="easyui-window" title="新增文章" style="width:800px;height: 600px;"  data-options="closed:true,iconCls:'icon-add',collapsible:false,minimizable:false,maximizable:false,resizable:false,footer:'#footer'">
		<div id="content_div" style="padding:10px 20px 20px 20px">
	    <form id="newForm" class="easyui-form" method="post" data-options="novalidate:false">
	    	<center>
	    	  <table style="width: 100%;">
	    	    <tr >
	    	      <td align="right" style="width: 50px;">标题:</td>
	    	      <td colspan="2"><input class="easyui-validatebox" id="_title"  name="title" data-options="required:true" style="width: 500px;"></input></td>
	    	    </tr>
	    	    <tr >
	    	      <td align="right" style="width: 50px;">概述:</td>
	    	      <td colspan="2"><input class="easyui-validatebox" id="_remark"  name="remark" data-options="required:true" style="width: 500px;"></input></td>
	    	    </tr>
	    	    <tr >
	    	      <td align="right" style="width: 50px;">来源:</td>
	    	      <td colspan="2"><input class="easyui-validatebox" id="_source"  name="source" data-options="required:true" style="width: 500px;"></input></td>
	    	    </tr>
	    	    <tr >
	    	      <td align="right" style="width: 50px;">图片:</td>
	    	      <td><input class="easyui-validatebox" id="_image"  name="image" style="width: 420px;"></input> <a class="easyui-linkbutton" id="uploadFileBtn">上传</a>&nbsp;
                    <a class="easyui-linkbutton" id="lookFile">查看</a></td>
	    	    </tr>
	    	    <tr >
	    	      <td align="right" style="width: 50px;">排序:</td>
	    	      <td colspan="2"><input class="easyui-numberbox" id="_sort" value="0" name="sort" style="width: 500px;" data-options="required:true"></input></td>
	    	    </tr>
	    	    <tr >
	    	      <td align="right" style="width: 50px;">类型:</td>
	    	      <td colspan="2">
	    	      <select class="easyui-combobox" id="type" name="type" panelHeight="auto" style="width:500px">
					<option value="1" selected>最新动态</option>
					<option value="2">新闻中心</option>
					<option value="3">关于我们</option>
					<option value="4">人才招聘</option>
					<option value="5">联系我们</option>
				</select>
	    	      </td>
	    	    </tr>
	    	     <tr>
	    			<td align="right">状态:</td>
	    			<td>
	    				<input  type="radio" id="state_normal" name="state" value="0" checked/> 正常 &nbsp;&nbsp;&nbsp;<input  type="radio" id="state_disable" name="state" value="1"/>禁用
	    			</td>
                  
	    		</tr>
	    	     <tr>
	    			<td align="right">文章内容:</td>
	    			<td colspan="2">
	    				<textarea id="editor_id" name="content" style="width:500px;height:300px;">
			&lt;strong&gt;HTML内容&lt;/strong&gt;
				</textarea>
				
				<script>
				var newEditor;
			        KindEditor.ready(function(K) {
			        	newEditor = K.create('#editor_id',{
			                	uploadJson:basePath +"upload/kindEditorFileupload.mvc"
			                });
			        });
			</script>
	    			</td>
                  
	    		</tr>
	    	  </table>
	    	</center>
	    </form>
	   
	    <div id="footer" style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="ArticleAdmin.ctrl.saveArticle()">提交</a>&nbsp;&nbsp;&nbsp;
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#newArticleWindow').window('close')">取消</a>
	    </div>
	   
	    </div>
	</div>
	
<!-- 编辑文章 -->
	
<div id="editArticleWindow" class="easyui-window" title="编辑文章" style="width:800px;height: 600px;"  data-options="closed:true,iconCls:'icon-add',collapsible:false,minimizable:false,maximizable:false,resizable:false,footer:'#edit_footer'">
		<div id="content_div" style="padding:10px 20px 20px 20px">
	    <form id="editForm" class="easyui-form" method="post" data-options="novalidate:false">
	    <input type="hidden" name="id" id="_id">
	    	<center>
	    	  <table style="width: 100%;">
	    	    <tr >
	    	      <td align="right" style="width: 50px;">标题:</td>
	    	      <td colspan="2"><input class="easyui-validatebox" id="_editTitle"  name="title" data-options="required:true" style="width: 500px;"></input></td>
	    	    </tr>
	    	    <tr >
	    	      <td align="right" style="width: 50px;">概述:</td>
	    	      <td colspan="2"><input class="easyui-validatebox" id="_editRemark"  name="remark" data-options="required:true" style="width: 500px;"></input></td>
	    	    </tr>
	    	    <tr >
	    	      <td align="right" style="width: 50px;">来源:</td>
	    	      <td colspan="2"><input class="easyui-validatebox" id="_editSource"  name="source" data-options="required:true" style="width: 500px;"></input></td>
	    	    </tr>
	    	    <tr >
	    	      <td align="right" style="width: 50px;">图片:</td>
	    	      <td><input class="easyui-validatebox" id="_editImage"  name="image" style="width: 420px;"></input> <a class="easyui-linkbutton" id="_editIUploadFileBtn">上传</a>&nbsp;
                    <a class="easyui-linkbutton" id="lookFile">查看</a></td>
	    	    </tr>
	    	    <tr >
	    	      <td align="right" style="width: 50px;">排序:</td>
	    	      <td colspan="2"><input class="easyui-numberbox" id="_editSort" value="0" name="sort" style="width: 500px;" data-options="required:true"></input></td>
	    	    </tr>
	    	    <tr >
	    	      <td align="right" style="width: 50px;">类型:</td>
	    	      <td colspan="2">
	    	      <select class="easyui-combobox" id="_editType" name="type" panelHeight="auto" style="width:500px">
					<option value="1" selected>最新动态</option>
					<option value="2">新闻中心</option>
					<option value="3">关于我们</option>
					<option value="4">人才招聘</option>
					<option value="5">联系我们</option>
				</select>
	    	      </td>
	    	    </tr>
	    	     <tr>
	    			<td align="right">状态:</td>
	    			<td>
	    				<input  type="radio" id="_edit_state_normal" name="state" value="0" checked/> 正常 &nbsp;&nbsp;&nbsp;
	    				<input  type="radio" id="_edit_state_disable" name="state" value="1"/>禁用
	    			</td>
                  
	    		</tr>
	    	     <tr>
	    			<td align="right">文章内容:</td>
	    			<td colspan="2">
	    				<textarea id="_edit_editor_id" name="content" style="width:500px;height:300px;">
			&lt;strong&gt;HTML内容&lt;/strong&gt;
				</textarea>
				
				<script>
				
				var editEditor;
			        KindEditor.ready(function(K) {
			        	editEditor = K.create('#_edit_editor_id',{
			                	uploadJson:basePath +"upload/kindEditorFileupload.mvc"
			                });
			        });
			</script>
	    			</td>
                  
	    		</tr>
	    	  </table>
	    	</center>
	    </form>
	   
	    <div id="edit_footer" style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="ArticleAdmin.ctrl.updateArticle()">提交</a>&nbsp;&nbsp;&nbsp;
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editArticleWindow').window('close')">取消</a>
	    </div>
	   
	    </div>
	</div>



	  <input  type="file" id="uploadFile"  name="file" onchange="ArticleAdmin.ctrl.uploadFile();" style="width:0px;height:0px;"/>
	  <input  type="file" id="_editUploadFile"  name="file" onchange="ArticleAdmin.ctrl.editUploadFile();" style="width:0px;height:0px;"/>
	<script type="text/javascript" src="<c:url value='/resources/js/admin/article/articleList.js'/>"></script>
</body>
</html>