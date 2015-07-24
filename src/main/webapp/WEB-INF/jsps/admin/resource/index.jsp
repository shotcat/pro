<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限资源管理</title>
<%@include file="/WEB-INF/jsps/common/taglib.jsp" %>
<script type="text/javascript" src="<c:url value='/resources/js/admin/resource/resource.js'/>"></script>
</head>
<body>


<div id="tb" style="padding:2px 5px;">
<sec:authorize url="/admin/resource/addResource.mvc">
<a href="javascript:void(0);" class="easyui-linkbutton" id="_addResourceBtn" iconCls="icon-add">新增</a>
</sec:authorize>
<sec:authorize url="/admin/resource/editResource.mvc">
<a href="javascript:void(0);" class="easyui-linkbutton" id="_editResourceBtn" iconCls="icon-edit">修改</a>
</sec:authorize>
<a href="#" title="配置说明:权限资源菜单级别:1:系统 2:模块菜单 3:功能菜单，请按照对应级别进行配置" class="easyui-tooltip">配置说明</a>
</div>
<table id="_resourceList"></table>

<!-- 新增用户 -->
	<div id="_newWindow" class="easyui-window" title="新建权限资源" data-options="closed:true,iconCls:'icon-add',collapsible:false,minimizable:false,maximizable:false,resizable:false,footer:'#_newResource_footer'" style="width:450px;height:300px;padding:5px;">
	 <form id="_newForm" class="easyui-form" method="post" data-options="novalidate:false">
	    	<table cellpadding="5">
	    		<tr>
	    			<td align="right">名称:</td>
	    			<td><input class="easyui-validatebox" id="resourceName"  name="resourceName" data-options="required:true,validType:'length[3,32]'"></td>
	    			<td align="right">URL:</td>
	    			<td><input class="easyui-validatebox" id="url"   name="url" data-options="required:true,validType:'length[6,255]'"></input></td>
	    		</tr>
	    		<tr>
	    			<td align="right">上级:</td>
	    			<td>
	    			
	    			<select id="parentId" class="easyui-combotree"  name="parentId"
	    			 data-options="url:'<c:url value="/admin/resource/findMenuList.mvc"/>',required: false,editable:true"  style="width:150px;"></select>  
	    			</td>
	    			<td align="right">状态:</td>
	    			<td> 
					<input id="state_normal" type="radio" name="state" checked="checked"  value="0" /><label for="state_normal">正常</label>		
					<input id="state_disable" type="radio" name="state" value="1" /><label for="state_disable">无效</label>
				</td>
	    		</tr>
	    			
	    	</table>
	    </form>
	</div>
	
	
	<div id="_newResource_footer" style="padding:5px; text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="AdminResource.ctrl.saveResource();"  data-options="iconCls:'icon-add'" >保存</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="javascript:$('#_newWindow').window('close')"" data-options="iconCls:'icon-cancel'">取消</a>
	
	</div>
	
	<div id="_editWindow" class="easyui-window" title="编辑权限资源" data-options="closed:true,iconCls:'icon-add',collapsible:false,minimizable:false,maximizable:false,resizable:false,footer:'#_editResource_footer'" style="width:450px;height:300px;padding:5px;">
	 <form id="_editForm" class="easyui-form" method="post" data-options="novalidate:false">
	    	<input type="hidden" name="resourceId" id="resourceId">
	    	<table cellpadding="5">
	    		<tr>
	    			<td align="right">名称:</td>
	    			<td><input class="easyui-validatebox" id="edit_resourceName"  name="resourceName" data-options="required:true,validType:'length[3,32]'"></td>
	    			<td align="right">URL:</td>
	    			<td><input class="easyui-validatebox" id="edit_url"   name="url" data-options="required:true,validType:'length[6,255]'"></input></td>
	    		</tr>
	    		<tr>
	    			<td align="right">上级:</td>
	    			<td>
	    			
	    			<select id="edit_parentId" class="easyui-combotree" 
	    			 data-options="url:'<c:url value="/admin/resource/findMenuList.mvc"/>' ,required: false,editable:true"  name="parentId" style="width:150px;"   
        	></select>  
	    			</td>
	    			<td align="right">状态:</td>
	    			<td> 
					<input id="edit_state_normal" type="radio" name="state"  value="0" /><label for="state_normal">正常</label>		
					<input id="edit_state_disable" type="radio" name="state" value="1" /><label for="state_disable">无效</label>
				</td>
	    		</tr>
	    			
	    	</table>
	    </form>
	</div>
	
	
	<div id="_editResource_footer" style="padding:5px; text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="AdminResource.ctrl.updateResource();"  data-options="iconCls:'icon-add'" >保存</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="javascript:$('#_editWindow').window('close')"" data-options="iconCls:'icon-cancel'">取消</a>
	
	</div>
</body>
</html>