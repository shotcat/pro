

var CarouselAdmin = {
		
		init:function(){
			CarouselAdmin.ctrl.initEvent();
			CarouselAdmin.ctrl.initDatagrid();
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
			updateCarousel:function(){
				if($("#editForm").form("validate")) {
					
					var jsondata = $('#editForm').serialize();
					CarouselAdmin.ctrl.showOrCloseProgress(true,"正在更新","正在更新,请稍后....");
					$.ajax({
						 url:basePath + 'admin/carousel/updateCarousel.mvc',
						   cache:false,
						   dataType:'json',
						   type:'POST',
						   data:jsondata,
						   error:function() {
							   $.messager.alert("提示","提交失败");
							   CarouselAdmin.ctrl.showOrCloseProgress(false);
						   },
						   success:function(data, textStatus, jqXHR) {
							   CarouselAdmin.ctrl.showOrCloseProgress(false);
							   if(data.success == true) {
								   $.messager.alert("提示",data.message);
								   $('#editCarouselWindow').window("close");
									$('#_carouseList').datagrid("reload");
								   
							   } else{
								   $.messager.alert("提示",data.message);
							   }
						   }
					});
					
				}
				
			},
			saveCarousel:function() {
				if($("#newForm").form("validate")) {
					
					var jsondata = $('#newForm').serialize();
					CarouselAdmin.ctrl.showOrCloseProgress(true,"正在保存","正在保存,请稍后....");
					$.ajax({
						 url:basePath + 'admin/carousel/saveCarousel.mvc',
						   cache:false,
						   dataType:'json',
						   type:'POST',
						   data:jsondata,
						   error:function() {
							   $.messager.alert("提示","提交失败");
							   CarouselAdmin.ctrl.showOrCloseProgress(false);
						   },
						   success:function(data, textStatus, jqXHR) {
							   CarouselAdmin.ctrl.showOrCloseProgress(false);
							   if(data.success == true) {
								   $.messager.alert("提示",data.message);
								   $('#newCarouselWindow').window("close");
									$('#_carouseList').datagrid("reload");
								   
							   } else{
								   $.messager.alert("提示",data.message);
							   }
						   }
					});
					
				}
			},
			editUploadFile:function(){
				CarouselAdmin.ctrl.showOrCloseProgress(true,"正在上传","文件上传中请稍后....");
				$.ajaxFileUpload({
		            url: basePath + 'upload/upload.do', 
		            type: 'post',
		            secureuri: false, //一般设置为false
		            fileElementId: '_editUploadFile', // 上传文件的id、name属性名
		            dataType: 'json', //返回值类型，一般设置为json、application/json
		            success: function(data, status){  
		            	CarouselAdmin.ctrl.showOrCloseProgress(false);
		                $.messager.alert("友情提示",data.message);
		                if(data.success == true) {
		                	$("#_editImageUrl").val(data.singleData);
		                }
		            },
		            error: function(data, status, e){ 
		            	CarouselAdmin.ctrl.showOrCloseProgress(false);
		            	$.messager.alert("友情提示","上传获取必要条件失败");
		            }
		        });
				
				$("#_editUploadFile").remove();
				$("body").append("<input  type=\"file\" id=\"_editUploadFile\"  name=\"file\" onchange=\"CarouselAdmin.ctrl.editUploadFile();\" style=\"width:0px;height:0px;\"/>");
			},
			uploadFile:function(){
				CarouselAdmin.ctrl.showOrCloseProgress(true,"正在上传","文件上传中请稍后....");
				$.ajaxFileUpload({
					url: basePath + 'upload/upload.do', 
					type: 'post',
					secureuri: false, //一般设置为false
					fileElementId: 'uploadFile', // 上传文件的id、name属性名
					dataType: 'json', //返回值类型，一般设置为json、application/json
					success: function(data, status){  
						CarouselAdmin.ctrl.showOrCloseProgress(false);
						$.messager.alert("友情提示",data.message);
						if(data.success == true) {
							$("#imageUrl").val(data.singleData);
							
						}
					},
					error: function(data, status, e){ 
						CarouselAdmin.ctrl.showOrCloseProgress(false);
						$.messager.alert("友情提示","上传获取必要条件失败");
					}
				});
				
				$("#uploadFile").remove();
				$("body").append(" <input  type=\"file\" id=\"uploadFile\" onchange=\"CarouselAdmin.ctrl.uploadFile();\"  name=\"file\" style=\"width:0px;height:0px;\"/>");
			},
			initEvent:function(){
				
				$('#_addCarouselBtn').click(function(){
					
					$('#newForm').form("clear");
					$("#state_normal").prop("checked",true);
					$('#newCarouselWindow').window("open");
				});
				
				$("#uploadFileBtn").click(function(){
					$("#uploadFile").click();
				});
				$("#_editIUploadFileBtn").click(function(){
					$("#_editUploadFile").click();
				});
				$("#_editCarouselBtn").click(function(){
					var row = $('#_carouseList').datagrid("getSelected");
					if(row == null) {
						 $.messager.alert("提示","请选择要修改的轮播图!");
						return false;
					}
					$("#_editId").val(row.id);
					$("#_editTitle").val(row.title);
					$("#_editUrl").val(row.url);
					$("#_editImageUrl").val(row.imageUrl);
					$("#_editSort").numberbox("setValue",row.sort);
					
					var state = row.state;
					if(state == 0) {
						$("#_edit_state_normal").prop("checked",true);
					} else {
						$("#_edit_state_disable").prop("checked",true);
					}
					
					$("#_editDes").val(row.des);
					$("#editCarouselWindow").window("open");
				});
				
				$("#_searchBtn").click(function(){
					
					var options = $('#_carouseList').datagrid("options");
					var title = $("#_title").val();
					var startTime = $("#_startTime").datebox("getValue");
					var endTime = $("#_endTime").datebox("getValue");
					var state = $("#_state").combobox("getValue");
					var params = {"title":title,"startTime":startTime,"endTime":endTime,"state":state};
					options.queryParams = params; 
					$('#_carouseList').datagrid("reload");
					
				});
			},
			initDatagrid:function(){
				
				$('#_carouseList').datagrid({
				    url:basePath +"/admin/carousel/findCarouselList.mvc",
				    title:'轮播图信息列表',
				    fit:true,
				    idField:'id',
				    nowrap:false,
				    loadMsg:'正在加载轮播图列表，请稍后...',
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
				    {field:'url',title:'跳转url链接',width:100},
				    {field:'sort',title:'排序',width:50},
				    {field:'imageUrl',title:'轮播图片',width:150},
				    {field:'state',title:'状态',width:50,formatter: function(value,row,index){
						if (row.state == 0){
							return "正常";
						} else {
							return "无效";
						}
					}},
					{field:'des',title:'描述',width:150},
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
	CarouselAdmin.init();
});