

var ProductAdmin = {
		
		init:function(){
			ProductAdmin.ctrl.initEvent();
			ProductAdmin.ctrl.initDatagrid();
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
			updateProduct:function(){
				if($("#editForm").form("validate")) {
					editEditor.sync("_edit_editor_id");
					var jsondata = $('#editForm').serialize();
					ProductAdmin.ctrl.showOrCloseProgress(true,"正在更新","正在更新,请稍后....");
					$.ajax({
						 url:basePath + 'admin/product/updateProduct.mvc',
						   cache:false,
						   dataType:'json',
						   type:'POST',
						   data:jsondata,
						   error:function() {
							   $.messager.alert("提示","提交失败");
							   ProductAdmin.ctrl.showOrCloseProgress(false);
						   },
						   success:function(data, textStatus, jqXHR) {
							   ProductAdmin.ctrl.showOrCloseProgress(false);
							   if(data.success == true) {
								   $.messager.alert("提示",data.message);
								   $('#editProductWindow').window("close");
									$('#_productList').datagrid("reload");
								   
							   } else{
								   $.messager.alert("提示",data.message);
							   }
						   }
					});
					
				}
				
			},
			saveProduct:function() {
				if($("#newForm").form("validate")) {
					newEditor.sync("editor_id");
					var jsondata = $('#newForm').serialize();
					ProductAdmin.ctrl.showOrCloseProgress(true,"正在保存","正在保存,请稍后....");
					$.ajax({
						 url:basePath + 'admin/product/saveProduct.mvc',
						   cache:false,
						   dataType:'json',
						   type:'POST',
						   data:jsondata,
						   error:function() {
							   $.messager.alert("提示","提交失败");
							   ProductAdmin.ctrl.showOrCloseProgress(false);
						   },
						   success:function(data, textStatus, jqXHR) {
							   ProductAdmin.ctrl.showOrCloseProgress(false);
							   if(data.success == true) {
								   $.messager.alert("提示",data.message);
								   $('#newProductWindow').window("close");
									$('#_productList').datagrid("reload");
								   
							   } else{
								   $.messager.alert("提示",data.message);
							   }
						   }
					});
					
				}
			},
			editUploadFile:function(){
				ProductAdmin.ctrl.showOrCloseProgress(true,"正在上传","文件上传中请稍后....");
				$.ajaxFileUpload({
		            url: basePath + 'upload/upload.do', 
		            type: 'post',
		            secureuri: false, //一般设置为false
		            fileElementId: '_editUploadFile', // 上传文件的id、name属性名
		            dataType: 'json', //返回值类型，一般设置为json、application/json
		            success: function(data, status){  
		            	ProductAdmin.ctrl.showOrCloseProgress(false);
		                $.messager.alert("友情提示",data.message);
		                if(data.success == true) {
		                	$("#_editImage").val(data.singleData);
		                }
		            },
		            error: function(data, status, e){ 
		            	ProductAdmin.ctrl.showOrCloseProgress(false);
		            	$.messager.alert("友情提示","上传获取必要条件失败");
		            }
		        });
				
				$("#_editUploadFile").remove();
				$("body").append("<input  type=\"file\" id=\"_editUploadFile\"  name=\"file\" onchange=\"ProductAdmin.ctrl.editUploadFile();\" style=\"width:0px;height:0px;\"/>");
			},
			uploadFile:function(){
				ProductAdmin.ctrl.showOrCloseProgress(true,"正在上传","文件上传中请稍后....");
				$.ajaxFileUpload({
					url: basePath + 'upload/upload.do', 
					type: 'post',
					secureuri: false, //一般设置为false
					fileElementId: 'uploadFile', // 上传文件的id、name属性名
					dataType: 'json', //返回值类型，一般设置为json、application/json
					success: function(data, status){  
						ProductAdmin.ctrl.showOrCloseProgress(false);
						$.messager.alert("友情提示",data.message);
						if(data.success == true) {
							$("#_image").val(data.singleData);
							
						}
					},
					error: function(data, status, e){ 
						ProductAdmin.ctrl.showOrCloseProgress(false);
						$.messager.alert("友情提示","上传获取必要条件失败");
					}
				});
				
				$("#uploadFile").remove();
				$("body").append(" <input  type=\"file\" id=\"uploadFile\" onchange=\"ProductAdmin.ctrl.uploadFile();\"  name=\"file\" style=\"width:0px;height:0px;\"/>");
			},
			initEvent:function(){
				
				
				$("#type").combobox({
					url:basePath + 'admin/product/findAllProductType.mvc',
					valueField:'id',
					textField:'typeName'
				});
				$("#_editType").combobox({
					url:basePath + 'admin/product/findAllProductType.mvc',
					valueField:'id',
					textField:'typeName'
				});
				$("#_type").combobox({
					 url:basePath + 'admin/product/findAllProductType.mvc',
					 valueField:'id',
					 textField:'typeName'
				});
				$('#_addProductlBtn').click(function(){
					
					$('#newForm').form("clear");
					$("#type").combobox("setValue","1");
					$("#_sort").numberbox("setValue",0);
					$("#state_normal").prop("checked",true);
					newEditor.html("");
					$('#newProductWindow').window("open");
				});
				
				$("#uploadFileBtn").click(function(){
					$("#uploadFile").click();
				});
				$("#_editIUploadFileBtn").click(function(){
					$("#_editUploadFile").click();
				});
				$("#_editProductBtn").click(function(){
					var row = $('#_productList').datagrid("getSelected");
					if(row == null) {
						 $.messager.alert("提示","请选择要修改的文章!");
						return false;
					}
					$("#_id").val(row.id);
					$("#_editTitle").val(row.title);
					$("#_editRemark").val(row.remark);
					$("#_editSource").val(row.source);
					$("#_editImage").val(row.image);
					$("#_editSort").numberbox("setValue",row.sort);
					$("#_editType").combobox("setValue",row.type);
					editEditor.html( row.content);
					var state = row.state;
					if(state == 0) {
						$("#_edit_state_normal").prop("checked",true);
					} else {
						$("#_edit_state_disable").prop("checked",true);
					}
					
					$("#editProductWindow").window("open");
				});
				
				$("#_searchBtn").click(function(){
					
					var options = $('#_productList').datagrid("options");
					var title = $("#_title").val();
					var startTime = $("#_startTime").datebox("getValue");
					var endTime = $("#_endTime").datebox("getValue");
					var state = $("#_state").combobox("getValue");
					var type = $("#_type").combobox("getValue");
					var params = {"title":title,"startTime":startTime,"endTime":endTime,"state":state,"type":type};
					options.queryParams = params; 
					$('#_productList').datagrid("reload");
					
				});
			},
			initDatagrid:function(){
				
				$('#_productList').datagrid({
				    url:basePath +"admin/product/findProductList.mvc",
				    title:'产品展示信息列表',
				    fit:true,
				    idField:'id',
				    nowrap:false,
				    loadMsg:'正在加载产品展示信息列表，请稍后...',
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
				    {field:'title',title:'标题',width:100},
				    {field:'remark',title:'概述',width:100},
				    {field:'sort',title:'排序',width:50},
				    {field:'state',title:'状态',width:50,formatter: function(value,row,index){
						if (row.state == 0){
							return "正常";
						} else {
							return "无效";
						}
					}},
					{field:'typeName',title:'产品类别',width:50},
					{field:'image',title:'展示图片',width:150},
				    {field:'createTime',title:'创建时间',width:150},
				    {field:'updateTime',title:'更新时间',width:150}
				    ]]
				    });
			}
		}
};


/***
 * 文档加载完执行
 */
$(function(){
	ProductAdmin.init();
});