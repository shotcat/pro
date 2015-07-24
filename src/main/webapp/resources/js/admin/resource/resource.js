

var AdminResource = {
		
		init:function(){
			
			AdminResource.ctrl.initEvent();
			AdminResource.ctrl.initDatagrid();
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
				 * 更新权限资源
				 */
				updateResource:function(){
					var valida = $('#_editForm').form("validate");
					if(valida == false) return ;
					var va = $("#edit_parentId").combotree("getText");
					var value = $("#edit_parentId").combotree("getValue");
					var parentId  = $("#resourceId").val();
					if(parentId == value) {
						  $.messager.alert("提示",'上级菜单不能是自己本身!');
						  return false;
					}
					if($.trim(va) == "") {
						 $("#edit_parentId").combotree("setValue","");
					}
					var formData = $('#_editForm').serialize();
					
					AdminResource.ctrl.showOrCloseProgress(true, "更新权限资源", "正在更新，请稍后...");
					$.ajax({
						 url:basePath + 'admin/resource/editResource.mvc',
						   cache:false,
						   dataType:'json',
						   type:'POST',
						   data:formData,
						   error:function() {
							   $.messager.alert("提示","提交失败");
							   AdminResource.ctrl.showOrCloseProgress(false);
						   },
						   success:function(data, textStatus, jqXHR) {
							   AdminResource.ctrl.showOrCloseProgress(false);
							   if(data.success == true) {
								   $.messager.alert("提示",data.message);
								   $("#_editWindow").window("close");
									$('#_resourceList').treegrid("reload");
									$('#parentId').combotree("reload");
									$('#edit_parentId').combotree("reload");
							   } else{
								   $.messager.alert("提示",data.message);
							   }
						   }
					});
				},
				saveResource:function(){
					var valida = $('#_newForm').form("validate");
					if(valida == false) return ;
					
					var va = $("#parentId").combotree("getText");
					if($.trim(va) == "") {
						 $("#parentId").combotree("setValue","");
					}
					var formData = $('#_newForm').serialize();
					
					AdminResource.ctrl.showOrCloseProgress(true, "新增权限资源", "正在保存，请稍后...");
					$.ajax({
						 url:basePath + 'admin/resource/addResource.mvc',
						   cache:false,
						   dataType:'json',
						   type:'POST',
						   data:formData,
						   error:function() {
							   $.messager.alert("提示","提交失败");
							   AdminResource.ctrl.showOrCloseProgress(false);
						   },
						   success:function(data, textStatus, jqXHR) {
							   AdminResource.ctrl.showOrCloseProgress(false);
							   if(data.success == true) {
								   $.messager.alert("提示",data.message);
								   $("#_newWindow").window("close");
									$('#_resourceList').treegrid("reload");
									$('#parentId').combotree("reload");
									$('#edit_parentId').combotree("reload");
							   } else{
								   $.messager.alert("提示",data.message);
							   }
						   }
					});
				},
			initEvent:function(){
				/***
				 * 弹出新增权限资源窗口
				 */
				$('#_addResourceBtn').click(function(){
					$("#resourceName").val("");
					$("#url").val("");
					$("#parentId").combotree("setValue","");
					$("#state_normal").prop("checked",true);
					$("#_newWindow").window("open");
				});
				$('#_editResourceBtn').click(function(){
					
					var row = $('#_resourceList').treegrid("getSelected");
					if(row == null) {
						 $.messager.alert("提示","请选择要修改的权限资源!");
						return false;
					}
					$("#resourceId").val(row.resourceId);
					$("#edit_resourceName").val(row.resourceName);
					$("#edit_url").val(row.url);
					if(row.parentId != null) {
						$("#edit_parentId").combotree("setValue",row.parentId);
					} else {
						$("#edit_parentId").combotree("setValue","");
					}
					 var state = row.status;
					 if(state == 0) {
						 $("#edit_state_normal").prop("checked",true);
					 } else {
						 $("#edit_state_disable").prop("checked",true);
					 }
					
					$("#_editWindow").window("open");
				});
				
			},
			initDatagrid:function(){
				$('#_resourceList').treegrid({
					iconCls:'icon-ok',
					title:'权限资源列表',
					width:700,
					singleSelect:true,
					rownumbers: true,
					animate:true,
					checkbox:true,
					fit:true,
					checkOnSelect:true,
					collapsible:true,
					fitColumns:true,
					lines:true,
					toolbar:'#tb',
					cascadeCheck:true,
					url:basePath + "admin/resource/findAllResource.mvc",
					method: 'post',
					idField:'resourceId',
					parentId:'parentId',
					treeField:'resourceName',
					showFooter:true,
					columns:[[
					    {field:'ch',checkbox:true, width:50,align:'left'},
						{field:'resourceName',title:'资源名称',width:300,align:'left'},
						{field:'status',title:'资源状态',width:60,
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
					]]
				});
			}
		}
}; 


$(function(){
	AdminResource.init();
});