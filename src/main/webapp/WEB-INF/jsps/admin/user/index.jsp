<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<%@include file="/WEB-INF/jsps/common/taglib.jsp" %>
<script type="text/javascript" src="<c:url value='/resources/js/admin/user/user.js'/>"></script>
</head>
<body >
<table id="_userList"></table>

<div id="tb" style="padding:2px 5px;">
<sec:authorize url="/admin/user/addUser.mvc">
<a href="javascript:void(0);" class="easyui-linkbutton" id="_addUserBtn" iconCls="icon-add">新增</a>
</sec:authorize>
<sec:authorize url="/admin/user/editUser.mvc">
<a href="javascript:void(0);" class="easyui-linkbutton" id="_editUserBtn" iconCls="icon-edit">修改</a>
</sec:authorize>
<sec:authorize url="/admin/user/deleteUser.mvc">
	<a href="javascript:void(0);" class="easyui-linkbutton" id="_deleteUserBtn" iconCls="icon-remove">删除</a>
</sec:authorize>

<a href="javascript:void(0);" class="easyui-linkbutton" id="_settingRolesBtn" iconCls="icon-filter">角色分配</a>
&nbsp;用户名(模糊查询):
<input class="easyui-validatebox" id="_userName" style="width:110px">
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
	
	
	
	<!-- 新增用户 -->
	<div id="_newWindow" class="easyui-window" title="新建用户" data-options="closed:true,iconCls:'icon-add',collapsible:false,minimizable:false,maximizable:false,resizable:false,footer:'#_newUser_footer'" style="width:500px;height:300px;padding:5px;">
	 <form id="_newForm" class="easyui-form" method="post" data-options="novalidate:false">
	    	<table cellpadding="5">
	    		<tr>
	    			<td align="right">昵称:</td>
	    			<td><input class="easyui-validatebox" id="nickName"  name="nickName" data-options="required:true,validType:'length[3,10]'"></td>
	    			<td align="right">用户名:</td>
	    			<td><input class="easyui-validatebox" id="userName"   name="userName" data-options="required:true,validType:'length[3,10]'"></input></td>
	    		</tr>
	    		<tr>
	    			<td align="right">密码:</td>
	    			<td><input class="easyui-validatebox" id="password" type="password" name="password" data-options="required:true,validType:'length[3,32]'"></input></td>
	    			<td align="right">确认密码:</td>
	    			<td><input class="easyui-validatebox" id="confromPassword" type="password" name="confromPassword" data-options="required:true"  validType="equalTo['#password']" invalidMessage="两次输入密码不匹配"></td>
	    		</tr>
	    		<tr>
	    			<td align="right">邮箱:</td>
	    			<td><input class="easyui-validatebox" id="email" name="email" data-options="required:true,validType:['email','length[6,32]']"></input></td>
	    			<td align="right">年龄:</td>
	    			<td><input class="easyui-numberspinner" id="age" name="age" value="0" data-options="min:0,max:150,editable:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td align="right">手机号码:</td>
	    			<td><input class="easyui-validatebox" id="mobile" name="mobile"  data-options="required:true,validType:['mobile']"></input></td>
	    			<td align="right">地址:</td>
	    			<td><input class="easyui-validatebox" id="address" name="address" data-options="required:false,validType:'length[0,100]'"></input></td>
	    		</tr>
	    		<tr>
	    			<td align="right">性别:</td>
	    			<td>
				<input id="sex_t" type="radio" name="sex"  value="0" /><label for="sex_t">男</label>		
				<input id="sex_f" type="radio" name="sex" value="1" /><label for="sex_f">女</label>
	    			</td>
	    			<td align="right">状态</td>
	    			<td> 
					<input id="state_normal" type="radio" name="state"  value="0" /><label for="state_normal">正常</label>		
					<input id="state_disable" type="radio" name="state" value="1" /><label for="state_disable">无效</label>
				</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	
	<div id="_newUser_footer" style="padding:5px; text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="AdminUser.ctrl.saveUser();"  data-options="iconCls:'icon-add'" >保存</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="javascript:$('#_newWindow').window('close')"" data-options="iconCls:'icon-cancel'">取消</a>
	
	</div>
	
	
	
	<div id="_editWindow" class="easyui-window" title="修改用户" data-options="closed:true,iconCls:'icon-edit',collapsible:false,minimizable:false,maximizable:false,resizable:false,footer:'#_editUser_footer'"  style="width:500px;height:300px;padding:5px;">
	 <form id="_editForm" class="easyui-form" method="post" data-options="novalidate:false">
	 <input type="hidden" name="userId" id="editUserId">
	    	<table cellpadding="5">
	    		<tr>
	    			<td align="right">昵称:</td>
	    			<td><input class="easyui-validatebox"  id="editNickName" name="nickName" data-options="required:true,validType:'length[3,10]'"></input></td>
	    			<td align="right">用户名:</td>
	    			<td><input class="easyui-validatebox" readonly="readonly" id="editUserName" type="text" name="userName" data-options="required:true,validType:'length[3,10]'"></input></td>
	    		</tr>
	    		<tr>
	    			<td align="right">密码:</td>
	    			<td><input class="easyui-validatebox"  id="editPassword" type="password" name="password" data-options="required:true,validType:'length[3,32]'"></input></td>
	    			<td align="right">确认密码:</td>
	    			<td><input class="easyui-validatebox" id="editConfromPassword" type="password" name="confromPassword" data-options="required:true" validType="equalTo['#editPassword']" invalidMessage="两次输入密码不匹配"></input></td>
	    		</tr>
	    		<tr>
	    			<td align="right">手机号码:</td>
	    			<td><input class="easyui-validatebox" type="text" id="editMobile" name="mobile" data-options="required:true"></input></td>
	    			<td align="right">地址:</td>
	    			<td><input class="easyui-validatebox" type="text" id="editAddress" name="address" data-options="required:false,validType:'length[0,100]'"></input></td>
	    		</tr>
	    		<tr>
	    			<td align="right">邮箱:</td>
	    			<td><input class="easyui-validatebox" type="text" id="editEmail" name="email" data-options="required:true,validType:'email'"></input></td>
	    			<td align="right">年龄:</td>
	    			<td><input class="easyui-numberbox" type="text" id="editAge" name="age" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td align="right">性别:</td>
	    			<td>
				<input id="edit_sex_t" type="radio" name="sex" checked value="0" /><label for="edit_sex_t">男</label>		
				<input id="edit_sex_f" type="radio" name="sex" value="1" /><label for="edit_sex_f">女</label>
	    			</td>
	    			<td align="right">状态</td>
	    			<td> 
					<input id="edit_state_normal" type="radio" name="state" checked value="0" /><label for="edit_state_normal">正常</label>		
					<input id="edit_state_disable" type="radio" name="state" value="1" /><label for="edit_state_disable">无效</label>
				</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	<div id="_editUser_footer" style="padding:5px; text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="AdminUser.ctrl.updateUser();"  data-options="iconCls:'icon-edit'" >保存</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="javascript:$('#_editWindow').window('close')"" data-options="iconCls:'icon-cancel'">取消</a>
	
	</div>
	
	
	<!-- 添加角色 -->
	<div id="_roleListWindow" class="easyui-window" title="角色列表" data-options="closed:true,iconCls:'icon-add',collapsible:false,minimizable:false,maximizable:false,resizable:false,footer:'#_settingRole_footer'" style="width:800px;height:500px;padding:5px;">
			<table id="_roleList"></table>
	</div>
	
	
		<div id="_settingRole_footer" style="padding:5px; text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="AdminUser.ctrl.settingRole();"  data-options="iconCls:'icon-edit'" >保存</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="javascript:$('#_roleListWindow').window('close')"" data-options="iconCls:'icon-cancel'">取消</a>
	
	</div>
	
</body>
</html>