<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<%@include file="/WEB-INF/jsps/common/taglib.jsp" %>
<script type="text/javascript" src="<c:url value='/resources/js/admin/role/easyui-treegrid-checkbox.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/admin/role/role.js'/>"></script>
</head>
<body>
<table id="_roleList"></table>

<div id="tb" style="padding:2px 5px;">
<sec:authorize url="/admin/role/addRole.mvc">
<a href="javascript:void(0);" class="easyui-linkbutton" id="_addRoleBtn" iconCls="icon-add">新增</a>
</sec:authorize>
<sec:authorize url="/admin/role/editRole.mvc">
<a href="javascript:void(0);" class="easyui-linkbutton" id="_editRoleBtn" iconCls="icon-edit">修改</a>
</sec:authorize>
<sec:authorize url="/admin/role/roleAuthorize.mvc">
<a href="javascript:void(0);" class="easyui-linkbutton" id="_settingResourceBtn" iconCls="icon-filter">角色授权</a>
</sec:authorize>
&nbsp;角色名称(模糊查询):
<input class="easyui-validatebox" id="_roleName" style="width:110px">
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
	
	
	
		<!-- 新增 -->
	<div id="_newWindow" class="easyui-window" title="新建角色" data-options="closed:true,iconCls:'icon-add',collapsible:false,minimizable:false,maximizable:false,resizable:false,footer:'#_new_role_footer'" style="width:400px;height:300px;padding:5px;">
	 <form id="_newForm" class="easyui-form" method="post" data-options="novalidate:false">
	    	<table cellpadding="5">
	    		<tr>
	    			<td align="right">角色名称:</td>
	    			<td><input class="easyui-validatebox" id="roleName"  name="roleName" data-options="required:true,validType:'length[3,10]'"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">状态</td>
	    			<td> 
					<input id="state_normal" checked="checked" type="radio" name="state"  value="0" /><label for="state_normal">正常</label>		
					<input id="state_disable" type="radio" name="state" value="1" /><label for="state_disable">无效</label>
				</td>
	    		</tr>
	    		<tr>
	    			<td align="right">角色描述:</td>
	    			<td> 
					
					<textarea rows="5" cols="30" name="des" id="des"></textarea>
				</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	<div id="_new_role_footer" style="padding:5px; text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="AdminRole.ctrl.saveRole();"  data-options="iconCls:'icon-add'" >保存</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="javascript:$('#_newWindow').window('close')"" data-options="iconCls:'icon-cancel'">取消</a>
	
	</div>
	
	
	
		<!-- 新增用户 -->
	<div id="_editWindow" class="easyui-window" title="编辑角色" data-options="closed:true,iconCls:'icon-edit',collapsible:false,minimizable:false,maximizable:false,resizable:false,footer:'#_edit_role_footer'" style="width:400px;height:300px;padding:5px;">
	 <form id="_editForm" class="easyui-form" method="post" data-options="novalidate:false">
	 <input type="hidden" name="roleId" id="roleId">
	    	<table cellpadding="5">
	    		<tr>
	    			<td align="right">角色名称:</td>
	    			<td><input class="easyui-validatebox" id="edit_RoleName"  name="roleName" data-options="required:true,validType:'length[3,10]'"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">状态</td>
	    			<td> 
					<input id="edit_state_normal" checked="checked" type="radio" name="state"  value="0" /><label for="state_normal">正常</label>		
					<input id="edit_state_disable" type="radio" name="state" value="1" /><label for="state_disable">无效</label>
				</td>
	    		</tr>
	    		<tr>
	    			<td align="right">角色描述:</td>
	    			<td> 
					
					<textarea rows="5" cols="30" name="des" id="edit_des"></textarea>
				</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	<div id="_edit_role_footer" style="padding:5px; text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="AdminRole.ctrl.updateRole();"  data-options="iconCls:'icon-edit'" >保存</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="javascript:$('#_editWindow').window('close')"" data-options="iconCls:'icon-cancel'">取消</a>
	
	</div>
	<div id="_resourceWindow" class="easyui-window" title="权限资源列表" data-options="closed:true,iconCls:'icon-edit',collapsible:false,minimizable:false,maximizable:false,resizable:false,footer:'#_resource_footer'" style="width:800px;height:450px;padding:5px;">
		<table id="_resourceList"></table>
	</div>
	
	<div id="_resource_footer" style="padding:5px; text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="AdminRole.ctrl.settingRoleResource();"  data-options="iconCls:'icon-edit'" >保存</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="javascript:$('#_resourceWindow').window('close')"" data-options="iconCls:'icon-cancel'">取消</a>
	
	</div>
	
	
	
</body>
</html>