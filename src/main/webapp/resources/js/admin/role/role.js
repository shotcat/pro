

var AdminRole = {
		
		 init:function(){
			 AdminRole.ctrl.initEvent();
			 AdminRole.ctrl.initDataGrid();
			 AdminRole.ctrl.initResourceDataGrid();
			 
			 
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
				 * 查找当前选择的角色对应的权限资源id集合
				 */
				findDefaultResouceIdListByRoleId:function(row) {
					$.ajax({
						 url:basePath + 'admin/resource/findDefaultResourceIdListByRoleId.mvc',
						   cache:false,
						   dataType:'json',
						   type:'POST',
						   data:{"roleId":row.roleId},
						   error:function() {
							   $.messager.alert("提示","提交失败");
							   AdminRole.ctrl.showOrCloseProgress(false);
						   },
						   success:function(data, textStatus, jqXHR) {
							   if(data.success == true) {
								   if(data.singleData.length >0) {
									 $.each(data.singleData,function(indx,va){
										 var resourceId = va;
										$('#_resourceList').treegrid("select",resourceId); 
										$("#ch_" +resourceId).prop("checked",true);
									 });
								   }
								 
								   $("#_resourceWindow").window("open");
								   return false;
							   } else if(data.success == false){
								   $.messager.alert("提示",data.message);
							   }
						   }
					});
				},
				
				settingRoleResource:function(){
					var roleRows =  $('#_roleList').datagrid("getChecked");
					if(roleRows == null || roleRows.length == 0 ) {
						$.messager.alert('友情提示','请选择要设置权限资源的角色');
						return false;
					}
					
					
					var rows = $('#_resourceList').treegrid("getSelections");
					var resourceIds = [];
					var resourceIdStr ="";
					if(rows.length >0) {
						$.each(rows,function(indx,row){
							
							resourceIds.push(row.resourceId);
						});
					}
					if(resourceIds.length >0) {
						resourceIdStr = resourceIds.join(",");
					}
					
					var formData = {"roleId":roleRows[0].roleId,"resourceIdStr":resourceIdStr};
					
					AdminRole.ctrl.showOrCloseProgress(true, "设置权限资源", "正在设置权限资源，请稍后...");
					$.ajax({
						 url:basePath + 'admin/resource/settingResourceByRoleId.mvc',
						   cache:false,
						   dataType:'json',
						   type:'POST',
						   data:formData,
						   error:function() {
							   $.messager.alert("提示","提交失败");
							   AdminRole.ctrl.showOrCloseProgress(false);
						   },
						   success:function(data, textStatus, jqXHR) {
							   AdminRole.ctrl.showOrCloseProgress(false);
							   if(data.success == true) {
								   $.messager.alert("提示",data.message);
								   $("#_resourceWindow").window("close");
							   } else{
								   $.messager.alert("提示",data.message);
							   }
						   }
					});
					
				},
				updateRole:function() {
					var valida = $('#_editForm').form("validate");
					if(valida == false) return ;
					
					var formData = $('#_editForm').serialize();
					AdminRole.ctrl.showOrCloseProgress(true, "更新角色", "正在更新，请稍后...");
					$.ajax({
						 url:basePath + 'admin/role/editRole.mvc',
						   cache:false,
						   dataType:'json',
						   type:'POST',
						   data:formData,
						   error:function() {
							   $.messager.alert("提示","提交失败");
							   AdminRole.ctrl.showOrCloseProgress(false);
						   },
						   success:function(data, textStatus, jqXHR) {
							   AdminRole.ctrl.showOrCloseProgress(false);
							   if(data.success == true) {
								   $.messager.alert("提示",data.message);
								   $("#_editWindow").window("close");
								   $('#_roleList').datagrid("reload");
							   } else{
								   $.messager.alert("提示",data.message);
							   }
						   }
					});
					
						
						
				},
			 saveRole:function(){
				 var valida = $('#_newForm').form("validate");
					if(valida == false) return ;
					
					var formData = $('#_newForm').serialize();
					AdminRole.ctrl.showOrCloseProgress(true, "保存角色", "正在保存，请稍后...");
					$.ajax({
						 url:basePath + 'admin/role/addRole.mvc',
						   cache:false,
						   dataType:'json',
						   type:'POST',
						   data:formData,
						   error:function() {
							   $.messager.alert("提示","提交失败");
							   AdminRole.ctrl.showOrCloseProgress(false);
						   },
						   success:function(data, textStatus, jqXHR) {
							   AdminRole.ctrl.showOrCloseProgress(false);
							   if(data.success == true) {
								   $.messager.alert("提示",data.message);
								   $("#_newWindow").window("close");
								   $('#_roleList').datagrid("reload");
							   } else{
								   $.messager.alert("提示",data.message);
							   }
						   }
					});
			 },
			 initEvent:function(){
				
				 $("#_addRoleBtn").click(function(){
					 $("#state_normal").prop("checked",true);
					 $("#des").val("");
					 $("#roleName").val("");
					 $("#_newWindow").window("open");
				 });
				 $("#_searchBtn").click(function(){
					 var options = $('#_roleList').datagrid("options");
						var roleName = $("#_roleName").val();
						var startTime = $("#_startTime").datebox("getValue");
						var endTime = $("#_endTime").datebox("getValue");
						var state = $("#_state").combobox("getValue");
						var params = {"roleName":roleName,"startTime":startTime,"endTime":endTime,"state":state};
						options.queryParams = params; 
						$('#_roleList').datagrid("reload");
				 });
				 
				 $("#_editRoleBtn").click(function(){
					 
					 var rows =  $('#_roleList').datagrid("getChecked");
						if(rows == null || rows.length == 0 ) {
							$.messager.alert('友情提示','请选择要修改的角色');
							return false;
						}
						
						$("#roleId").val(rows[0].roleId);
						$("#edit_RoleName").val(rows[0].roleName);
						$("#edit_des").val(rows[0].des);
						var state = rows[0].status;
						if(state == 0) {
							$("#edit_state_normal").prop("checked",true);
						} else {
							$("#edit_state_disable").prop("checked",true);
						}
						
						$("#_editWindow").window("open");	
				 });
				 
				 $("#_settingResourceBtn").click(function(){
					 var rows =  $('#_roleList').datagrid("getChecked");
						if(rows == null || rows.length == 0 ) {
							$.messager.alert('友情提示','请选择要授权的角色');
							return false;
						}
						$('#_resourceList').treegrid("clearChecked");
						$("input[id^='ch_']").prop("checked",false);
					 AdminRole.ctrl.findDefaultResouceIdListByRoleId(rows[0]);
				 });
			 },
			 
			 onChangeCheckbox:function(id) {
				 if($("#ch_" +id).prop("checked") == true) {
					 AdminRole.ctrl.settingParentNode(id);
				 } else {
					 AdminRole.ctrl.settingChildrenNode(id);
				 }
			 },
			 settingChildrenNode:function(id){
				 var node =  $('#_resourceList').treegrid("find",id); 
					if(node !="" && node != null) {
						
							var resourceId = node.resourceId;
							$("#ch_" +resourceId).prop("checked",false);
							$('#_resourceList').treegrid("unselect",resourceId);
							
							var childrenNodes = $('#_resourceList').treegrid("getChildren",resourceId);
							if(childrenNodes != "" &&childrenNodes.length >0) {
								$.each(childrenNodes,function(indx,va){
									AdminRole.ctrl.settingChildrenNode(va.resourceId);
								});
							}
					}
			 },
			 settingParentNode:function(id){
				 var node =  $('#_resourceList').treegrid("find",id); 
				if(node !="" && node != null) {
					
						var resourceId = node.resourceId;
						$("#ch_" +resourceId).prop("checked",true);
						$('#_resourceList').treegrid("select",resourceId);
						var parentId = node.parentId;
						if(parentId != "" && parentId != null){
							AdminRole.ctrl.settingParentNode(parentId);
						}
				}
			 },
			 initResourceDataGrid:function(){
				 $('#_resourceList').treegrid({
						iconCls:'icon-ok',
						width:700,
						height:430,
						singleSelect:false,
						rownumbers: true,
						animate:true,
						checkbox:true,
						fit:true,
						checkOnSelect:false,
						collapsible:true,
						fitColumns:true,
						lines:true,
						cascadeCheck:true,
						url:basePath + "admin/resource/findAllResource.mvc",
						method: 'post',
						idField:'resourceId',
						parentId:'parentId',
						treeField:'resourceName',
						showFooter:true,
						columns:[[
							{field:'resourceName',title:'资源名称',width:300,align:'left', formatter:function(value,row,index){
								return "<input type='checkbox' id ='ch_"+row.resourceId+"' onchange ='AdminRole.ctrl.onChangeCheckbox("+row.resourceId+")'/>" +row.resourceName; 
							}},
							{field:'status',title:'资源状态',width:100,
							    formatter:function(value,row,index){
							    	if (row.status == 0){
								    	
							    		return '正常';
							    	} else {
								    	return '无效';
							    	}
						    	}
							},
							{field:'url',title:'资源Url',width:350},
							{field:'createTime',title:'创建时间',width:150},
							{field:'updateTime',title:'更新时间',width:150}
						]],
						onSelect:function(row){
							if(row == null) return;
							$("#ch_" +row.resourceId).prop("checked",true);
						},
						onUnselect:function(row){
							if(row == null) return;
							$("#ch_" +row.resourceId).prop("checked",false);
						}
					});
			 },
			 initDataGrid:function(){
				 
				 $('#_roleList').datagrid({
					    url:basePath +"admin/role/findRoleList.mvc",
					    title:'角色信息列表',
					    fit:true,
						idField:'roleId',
					    loadMsg:'正在加载角色列表，请稍后...',
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
					    {field:'roleName',title:'角色名称',width:100},
					    {field:'state',title:'状态',width:50,formatter: function(value,row,index){
							if (row.state == 0){
								return "正常";
							} else {
								return "无效";
							}
						}},
						{field:'des',title:'描述',width:200},
					    {field:'createTime',title:'创建时间',width:150},
					    {field:'updateTime',title:'更新时间',width:150}
					    ]]
					    });
				 
			 }
		 }
};

$(function(){
	
	AdminRole.init();
});