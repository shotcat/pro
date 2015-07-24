


var AdminUser = {
		currentRoleIdList:[],
		init:function(){
			
			AdminUser.ctrl.initEvent();
			AdminUser.ctrl.initUserDatagrid();
			AdminUser.ctrl.initRoleDatagrid();
		},
		ctrl:{
			
			
			showOrCloseProgress:function(show,title,message){
				
				if(show == true) {
					var win = $.messager.progress({
						title:title,
						msg:message
					});
				} else {
					 $.messager.progress("close");
				}
				
			},
			/***
			 * 设置角色
			 */
			settingRole:function(){
				
				var userRows =  $('#_userList').datagrid("getChecked");
				if(userRows == null || userRows.length == 0 ) {
					$.messager.alert('友情提示','请选择要设置角色的用户');
					return false;
				}
				
				
				var rows = $('#_roleList').datagrid("getChecked");
				var roleIds = [];
				var roleIdStr ="";
				if(rows.length >0) {
					$.each(rows,function(indx,row){
						
						roleIds.push(row.roleId);
					});
				}
				if(roleIds.length >0) {
					roleIdStr = roleIds.join(",");
				}
				
				var formData = {"userId":userRows[0].userId,"roleIdStr":roleIdStr};
				
				AdminUser.ctrl.showOrCloseProgress(true, "设置角色", "正在设置角色，请稍后...");
				$.ajax({
					 url:basePath + 'admin/role/settingRoleByUserId.mvc',
					   cache:false,
					   dataType:'json',
					   type:'POST',
					   data:formData,
					   error:function() {
						   $.messager.alert("提示","提交失败");
						   AdminUser.ctrl.showOrCloseProgress(false);
					   },
					   success:function(data, textStatus, jqXHR) {
						   AdminUser.ctrl.showOrCloseProgress(false);
						   if(data.success == true) {
							   $.messager.alert("提示",data.message);
							   $("#_roleListWindow").window("close");
						   } else{
							   $.messager.alert("提示",data.message);
						   }
					   }
				});
				
			},
			
			/**
			 * 获取默认的角色id集合
			 */
			findDefalutRoleIdList:function(row) {
				
				$.ajax({
					 url:basePath + 'admin/role/findRoleIdListByUserId.mvc',
					   cache:false,
					   dataType:'json',
					   type:'POST',
					   data:{"userId":row.userId},
					   error:function() {
						   $.messager.alert("提示","提交失败");
						   AdminUser.ctrl.showOrCloseProgress(false);
					   },
					   success:function(data, textStatus, jqXHR) {
						   if(data.success == true) {
							   if(data.singleData.length >0) {
								   var rows =  $('#_roleList').datagrid("getRows");
									 var arra = [];
									  for(var j = 0 ;j <data.singleData.length;j++) {
										  for (var i = 0; i < rows.length; i++) {
												if(data.singleData[j] == rows[i].roleId) {
													var indx = $('#_roleList').datagrid("getRowIndex",rows[i]);
													$('#_roleList').datagrid("checkRow",indx);
												}
										  }
									  }
							   }
							 
							   $("#_roleListWindow").window("open");
						   } else{
							   $.messager.alert("提示",data.message);
						   }
					   }
				});
			},
			updateUser:function(){
				var valida = $('#_editForm').form("validate");
				if(valida == false) return ;
				
				var formData = $('#_editForm').serialize();
				AdminUser.ctrl.showOrCloseProgress(true, "更新用户", "正在更新，请稍后...");
				$.ajax({
					 url:basePath + 'admin/user/editUser.mvc',
					   cache:false,
					   dataType:'json',
					   type:'POST',
					   data:formData,
					   error:function() {
						   $.messager.alert("提示","提交失败");
						   AdminUser.ctrl.showOrCloseProgress(false);
					   },
					   success:function(data, textStatus, jqXHR) {
						   AdminUser.ctrl.showOrCloseProgress(false);
						   if(data.success == true) {
							   $.messager.alert("提示",data.message);
							   $("#_editWindow").window("close");
							   $('#_userList').datagrid("reload");
						   } else{
							   $.messager.alert("提示",data.message);
						   }
					   }
				});
				
			},
			/***
			 * 保存用户
			 */
			saveUser:function(){
				var valida = $('#_newForm').form("validate");
				if(valida == false) return ;
				
				var formData = $('#_newForm').serialize();
				AdminUser.ctrl.showOrCloseProgress(true, "保存用户", "正在保存，请稍后...");
				$.ajax({
					 url:basePath + 'admin/user/addUser.mvc',
					   cache:false,
					   dataType:'json',
					   type:'POST',
					   data:formData,
					   error:function() {
						   $.messager.alert("提示","提交失败");
						   AdminUser.ctrl.showOrCloseProgress(false);
					   },
					   success:function(data, textStatus, jqXHR) {
						   AdminUser.ctrl.showOrCloseProgress(false);
						   if(data.success == true) {
							   $.messager.alert("提示",data.message);
							   $("#_newWindow").window("close");
							   $('#_userList').datagrid("reload");
						   } else{
							   $.messager.alert("提示",data.message);
						   }
					   }
				});
			},
			/***
			 * 初始化事件监听
			 */
			initEvent:function(){
				$("#_searchBtn").click(function(){
					var options = $('#_userList').datagrid("options");
					var userName = $("#_userName").val();
					var startTime = $("#_startTime").datebox("getValue");
					var endTime = $("#_endTime").datebox("getValue");
					var state = $("#_state").combobox("getValue");
					var params = {"userName":userName,"startTime":startTime,"endTime":endTime,"state":state};
					options.queryParams = params; 
					$('#_userList').datagrid("reload");
				});
				//添加用户
				$("#_addUserBtn").click(function(){
					$("#nickName").val("");
					$("#userName").val("");
					$("#password").val("");
					$("#confromPassword").val("");
					$("#email").val("");
					$("#mobile").val("");
					$("#address").val("");
					$("#sex_t").prop("checked",true);
					$("#state_normal").prop("checked",true);
					$("#age").numberspinner("setValue",0);
					$("#_newWindow").window("open");
				});
				//添加修改用户
				$("#_editUserBtn").click(function(){
					var rows =  $('#_userList').datagrid("getChecked");
					if(rows == null || rows.length == 0 ) {
						$.messager.alert('友情提示','请选择要修改的用户');
						return false;
					}
					
					$("#editUserId").val(rows[0].userId);
					$("#editNickName").val(rows[0].nickName);
					$("#editUserName").val(rows[0].userName);
					$("#editPassword").val(rows[0].password);
					$("#editMobile").val(rows[0].mobile);
					$("#editAddress").val(rows[0].address);
					$("#editEmail").val(rows[0].email);
					$("#editAge").numberbox("setValue",rows[0].age);
					var state = rows[0].state;
					if(state == 0) {
						$("#edit_state_normal").prop("checked",true);
					} else {
						$("#edit_state_disable").prop("checked",true);
					}
					
					var sex = rows[0].sex;
					if(sex == 0) {
						$("#edit_sex_t").prop("checked",true);
					} else {
						$("#edit_sex_f").prop("checked",true);
					}
					
					$("#_editWindow").window("open");
				});
				
				/***
				 * 设置角色
				 */
				$("#_settingRolesBtn").click(function(){
					
					var rows =  $('#_userList').datagrid("getChecked");
					if(rows == null || rows.length == 0 ) {
						$.messager.alert('友情提示','请选择要设置角色的用户');
						return false;
					}
					$('#_roleList').datagrid("clearChecked");
					AdminUser.ctrl.findDefalutRoleIdList(rows[0]);
					
				});
			},
			initUserDatagrid:function(){
					$('#_userList').datagrid({
				    url:basePath +"admin/user/findUserList.mvc",
				    title:'用户信息列表',
				    fit:true,
				    idField:'userId',
				    loadMsg:'正在加载用户列表，请稍后...',
				    pagination:true,
				    rownumbers:true,
				    singleSelect:true,
				    pageSize:10,
				    pageNumber:1,
				    fitColumns:true,
				    toolbar:'#tb',
				    pageList:[10,20,30,40,50],
				    columns:[[
				    {field:'ck',checkbox:true,width:50},
				    {field:'nickName',title:'昵称',width:100},
				    {field:'userName',title:'用户名',width:100},
				    {field:'password',title:'密码',width:150,formatter: function(value,row,index){
						return "******";
					}},
				    {field:'email',title:'邮箱',width:150},
				    {field:'age',title:'年龄',width:50},
				    {field:'sex',title:'性别',width:50,formatter: function(value,row,index){
						if (row.sex == 0){
							return "男";
						} else {
							return "女";
						}
					}},
					{field:'mobile',title:'手机号码',width:150},
				    {field:'state',title:'状态',width:50,formatter: function(value,row,index){
						if (row.state == 0){
							return "正常";
						} else {
							return "无效";
						}
					}},
					{field:'address',title:'联系方式',width:200},
				    {field:'createTime',title:'创建时间',width:150},
				    {field:'updateTime',title:'更新时间',width:150}
				    ]]
				    });
			},
			/***
			 * 角色列表
			 */
			initRoleDatagrid:function(){
				$('#_roleList').datagrid({
					url:basePath +"admin/role/findAllRole.mvc",
					fit:true,
					idField:'roleId',
					loadMsg:'正在加载角色列表，请稍后...',
					pagination:false,
					rownumbers:true,
					singleSelect:false,
					fitColumns:true,
					columns:[[
					          {field:'ck',checkbox:true,width:50},
					          {field:'roleName',title:'角色名称',width:100},
					          {field:'state',title:'状态',width:100,formatter: function(value,row,index){
					        	  if (row.state == 0){
					        		  return "正常";
					        	  } else {
					        		  return "无效";
					        	  }
					          }},
					          {field:'des',title:'描述',width:350},
					          {field:'createTime',title:'创建时间',width:150},
					          {field:'updateTime',title:'更新时间',width:150}
					          ]]
				});
			}
		}
};

$(function(){
	AdminUser.init();
});