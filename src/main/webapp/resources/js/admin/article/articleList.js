

var ArticleAdmin = {
		
		init:function(){
			ArticleAdmin.ctrl.initEvent();
			ArticleAdmin.ctrl.initDatagrid();
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
			updateArticle:function(){
				if($("#editForm").form("validate")) {
					editEditor.sync("_edit_editor_id");
					var jsondata = $('#editForm').serialize();
					ArticleAdmin.ctrl.showOrCloseProgress(true,"正在更新","正在更新,请稍后....");
					$.ajax({
						 url:basePath + 'admin/article/updateArticle.mvc',
						   cache:false,
						   dataType:'json',
						   type:'POST',
						   data:jsondata,
						   error:function() {
							   $.messager.alert("提示","提交失败");
							   ArticleAdmin.ctrl.showOrCloseProgress(false);
						   },
						   success:function(data, textStatus, jqXHR) {
							   ArticleAdmin.ctrl.showOrCloseProgress(false);
							   if(data.success == true) {
								   $.messager.alert("提示",data.message);
								   $('#editArticleWindow').window("close");
									$('#_articleList').datagrid("reload");
								   
							   } else{
								   $.messager.alert("提示",data.message);
							   }
						   }
					});
					
				}
				
			},
			saveArticle:function() {
				if($("#newForm").form("validate")) {
					newEditor.sync("editor_id");
					var jsondata = $('#newForm').serialize();
					ArticleAdmin.ctrl.showOrCloseProgress(true,"正在保存","正在保存,请稍后....");
					$.ajax({
						 url:basePath + 'admin/article/saveArticle.mvc',
						   cache:false,
						   dataType:'json',
						   type:'POST',
						   data:jsondata,
						   error:function() {
							   $.messager.alert("提示","提交失败");
							   ArticleAdmin.ctrl.showOrCloseProgress(false);
						   },
						   success:function(data, textStatus, jqXHR) {
							   ArticleAdmin.ctrl.showOrCloseProgress(false);
							   if(data.success == true) {
								   $.messager.alert("提示",data.message);
								   $('#newArticleWindow').window("close");
									$('#_articleList').datagrid("reload");
								   
							   } else{
								   $.messager.alert("提示",data.message);
							   }
						   }
					});
					
				}
			},
			editUploadFile:function(){
				ArticleAdmin.ctrl.showOrCloseProgress(true,"正在上传","文件上传中请稍后....");
				$.ajaxFileUpload({
		            url: basePath + 'upload/upload.do', 
		            type: 'post',
		            secureuri: false, //一般设置为false
		            fileElementId: '_editUploadFile', // 上传文件的id、name属性名
		            dataType: 'json', //返回值类型，一般设置为json、application/json
		            success: function(data, status){  
		            	ArticleAdmin.ctrl.showOrCloseProgress(false);
		                $.messager.alert("友情提示",data.message);
		                if(data.success == true) {
		                	$("#_editImage").val(data.singleData);
		                }
		            },
		            error: function(data, status, e){ 
		            	ArticleAdmin.ctrl.showOrCloseProgress(false);
		            	$.messager.alert("友情提示","上传获取必要条件失败");
		            }
		        });
				
				$("#_editUploadFile").remove();
				$("body").append("<input  type=\"file\" id=\"_editUploadFile\"  name=\"file\" onchange=\"ArticleAdmin.ctrl.editUploadFile();\" style=\"width:0px;height:0px;\"/>");
			},
			uploadFile:function(){
				ArticleAdmin.ctrl.showOrCloseProgress(true,"正在上传","文件上传中请稍后....");
				$.ajaxFileUpload({
					url: basePath + 'upload/upload.do', 
					type: 'post',
					secureuri: false, //一般设置为false
					fileElementId: 'uploadFile', // 上传文件的id、name属性名
					dataType: 'json', //返回值类型，一般设置为json、application/json
					success: function(data, status){  
						ArticleAdmin.ctrl.showOrCloseProgress(false);
						$.messager.alert("友情提示",data.message);
						if(data.success == true) {
							$("#_image").val(data.singleData);
							
						}
					},
					error: function(data, status, e){ 
						ArticleAdmin.ctrl.showOrCloseProgress(false);
						$.messager.alert("友情提示","上传获取必要条件失败");
					}
				});
				
				$("#uploadFile").remove();
				$("body").append(" <input  type=\"file\" id=\"uploadFile\" onchange=\"ArticleAdmin.ctrl.uploadFile();\"  name=\"file\" style=\"width:0px;height:0px;\"/>");
			},
			initEvent:function(){
				
				$('#_addArticlelBtn').click(function(){
					
					$('#newForm').form("clear");
					$("#type").combobox("setValue","1");
					$("#_sort").numberbox("setValue",0);
					$("#state_normal").prop("checked",true);
					newEditor.html("");
					$('#newArticleWindow').window("open");
				});
				
				$("#uploadFileBtn").click(function(){
					$("#uploadFile").click();
				});
				$("#_editIUploadFileBtn").click(function(){
					$("#_editUploadFile").click();
				});
				$("#_editArticleBtn").click(function(){
					var row = $('#_articleList').datagrid("getSelected");
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
					
					$("#editArticleWindow").window("open");
				});
				
				$("#_searchBtn").click(function(){
					
					var options = $('#_articleList').datagrid("options");
					var title = $("#_title").val();
					var startTime = $("#_startTime").datebox("getValue");
					var endTime = $("#_endTime").datebox("getValue");
					var state = $("#_state").combobox("getValue");
					var type = $("#_type").combobox("getValue");
					var params = {"title":title,"startTime":startTime,"endTime":endTime,"state":state,"type":type};
					options.queryParams = params; 
					$('#_articleList').datagrid("reload");
					
				});
			},
			initDatagrid:function(){
				
				$('#_articleList').datagrid({
				    url:basePath +"/admin/article/findArticlelList.mvc",
				    title:'文章信息列表',
				    fit:true,
				    idField:'id',
				    nowrap:false,
				    loadMsg:'正在加载文章信息列表，请稍后...',
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
					{field:'type',title:'文章类别',width:50,formatter: function(value,row,index){
						if (row.type ==1){
							return "最新动态";
						} else if(row.type == 2) {
							return "新闻中心";
						}else if(row.type == 3) {
							return "关于我们";
						}else if(row.type == 4) {
							return "招聘信息";
						}else if(row.type == 5) {
							return "联系我们";
						}
					}},
					{field:'source',title:'来源',width:150},
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
	ArticleAdmin.init();
});