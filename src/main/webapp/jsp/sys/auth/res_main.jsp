<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/global.jsp"%>	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${path}/jslib/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="${path}/jslib/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<style type="text/css">
#resource-left{
 width: 15%;
 float: left;
 padding-right: 10px;
 overflow :auto;
 height: 100%
}
#resource-right{
padding-left:10px;
 width:85%;
 height: 100%;
 float: left;
border-left: 1px solid #eee; 
}

#resource-left{    height: 821px;}
</style>

<title>查询列表</title>
<script>
	var zTree;
	var selectNode;
	var load = false;

	$(function(){
		zTree = $.fn.zTree.init($("#treeDemo"), setting);
	})
	
	//添加
	function toAdd(){
		var nodeId=getSelectTreeId();
		if(nodeId){
			var option=$('#mainTable').bootstrapTable('getOptions');
			var total=option.totalRows;
			$app.dialog('${path}/sys/auth/res/toedit.do?parentId='+nodeId+"&orderNum="+total,function(){
				refMainTable();
			},{width:"900px",height:"600px"});
		
		}else
			$app.alert("请选择左边的目录");
			
		
		
		
	}
	//删除
	function toRemove(ids){
		if(!ids)
		ids=getSelectedRowsIds('mainTable');
		
		if(ids){
			$app.confirm("删除数据不可恢复，确定要删除吗？",function(){
				$.post('${path}/sys/auth/res/delete.do?ids='+ids,function(data){
				
				    if(data.code==1){
				    	$app.alert("删除成功");
	 					
				    }else
				    	$app.alert(msg);
				    	
				    
				 });
				
			});
			
			
		}else
			$app.alert('请选择一条数据进行操作');
			
		
	}
	
	//编辑
    function toEdit(id){
    	
    		$app.dialog('${path}/sys/auth/res/toedit.do?sysA=edit&id='+id,function(){
    			refTable();
    		});
    	
	}
	
    //查看
    function toInfo(){
    	var selected=getSelectedRowsArr('mainTable');
    	if(selected.length>0&&selected.length<2){
    		
    		window.location='${path}/sys/auth/res/findById.do?id='+selected;
    		
    	}else
    		
    		$app.alert("请选择一条数据进行操作");
    	
	}
	
	//设置查询参数
	function postQueryParams(params) {
		var queryParams = $("#searchForm").serializeObject();
		queryParams.limit=params.limit;
		queryParams.offset=params.offset;
		if(selectNode){
			selectNode.parentId=selectNode.id;
		}
		return queryParams;
	}
	//查询列表
    function refTable(){
    	$('#mainTable').bootstrapTable('refresh');
    	
    }
    
  
   

	//根据id查看
	function viewById(id){
			window.location='${path}/sys/auth/res/findById.do?id='+id;
	}
	
    //操作工具栏
    function operatorFormatter(value, row, index) {
    	var operator='<div class="btn-group">';
    	operator+=$app.btn('edit','编辑','toEdit(\''+row.id+'\')');
   		operator+=$app.btn('delete','删除','toRemove(\''+row.id+'\')');
		return operator+'</div>';

    
	}
    
	var setting = {
			async : {
				enable : true, //是否通过异步方式加载数据
				dataType : "text",
				type : "post",
				url :  "${path}/sys/auth/res/loadTree.do?timestamp="+ new Date().getTime(),
				autoParam : [ "id" ] //异步加载时必须传递的父节点的id值
			},
			view : {
				dblClickExpand : true,
				selectedMulti:false
			},
			data : {
				key : {
					name : "text"
				},
				simpleData : {
					enable : true,
					idKey : "id", // id编号命名 默认   
					pIdKey : "pId", // 父id编号命名 默认    
					rootPId : 0
				// 用于修正根节点父节点数据，即 pIdKey 指定的属性值   
				}
			},
			callback : {
				onClick : onClick,
				onAsyncSuccess: zTreeOnAsyncSuccess
			}
		};

	function onClick(e, treeId, treeNode) {
		selectNode = treeNode;
		changeList();
	}
	
	function changeList(pid,parentName,dictType){
		if(!selectNode){
			$app.alert("删除失败");
		}else{
			var pid=getSelectTreeId();
			$("#searchForm input[name=parentId]").val(pid);
			refTable();
		} 
	}
	
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
		if(!load){
			var nodes = zTree.getNodesByParam("pId", 0, null);
			$.each( nodes, function(i, n){
				zTree.expandNode(n, true, false, true);
			});
			load = true;
		}else{
			if(!treeNode){
				var nodes = zTree.getNodesByParam("pId", 0, null);
				$.each( nodes, function(i, n){
					zTree.expandNode(n, true, false, true);
				});
			}else{
				var id = treeNode.id;
				if(id=='root'){
					var children = treeNode.children;
					$.each( children, function(i, child){
						zTree.expandNode(child, true, false, true);
					});
				}
			}
		}
	}
	
   function refreshNode() { 
   		var treeObj = zTree;
   		var nodes = treeObj.getSelectedNodes();
   		var node = null;
   		 if(nodes.length==1){
   			node=nodes[0];
   		} 
		if(null==node){//不选择节点则刷新整棵树
			treeObj.reAsyncChildNodes(null, "refresh");			
		}else{
			if(node.isParent){
				treeObj.reAsyncChildNodes(node, "refresh");
  	  		 }else{//如果没有子节点，则刷新父节点
  	  			treeObj.reAsyncChildNodes(node.parentNode, "refresh");
  	  	  	 }
  		}
	}

	function getSelectTreeId(){
		var treeNodeId="";
		var treeObj = zTree;
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length==1){
			treeNodeId=nodes[0].id;
		}
		return treeNodeId;
	}
	
	  function statusFormatter(value,row,index){
	    	if(value=='1'){
	    		return '<span class="label label-success label-lg">启用</span>'
	    	}else if(value=='2'){
	    		return '<span class="label label-danger arrowed">禁用</span>';
	    	}else{
	    		return "";
	    	}
	    }
	  $(function(){
	 	 var dh = $('.rightinfo').height();
	 	 console.log(dh)
	 	 $('#resource-right').css('height',dh)
	  })
	  
</script>

</head>
<body>

      	<div id="resource-left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>  
    <div class="rightinfo">

		<div id="resource-right">
			<div class="explain_col">
	    		<form id="searchForm" name="searchForm"  method="post">
	    		
	    			<span>资源名称：</span><input type="text" name="resourceName" class="form-control input-sm w260" style="display: inline;">&nbsp;
	    			<input type="hidden" name="parentId">
	    			
	    			<input type="button" class="btn btn-info btn-round btn-sm" value="查询" onclick="refTable()">&nbsp;&nbsp;
	    			<input type="button" class="btn btn-warning btn-round btn-sm" value="重置" onclick="$('#searchForm')[0].reset();"> 
	    		</form>
	    	</div>
		    <div id="toolbar" class="btn-group">
		    
			    	<button class="btn btn-info btn-round btn-sm" onclick="toAdd();">
						<i class="glyphicon glyphicon-plus"></i> 添加
					</button>
			    
					<button class="btn btn-danger btn-sm" onclick="toRemove()">
						<i class="glyphicon glyphicon-trash"></i> 批量删除
					</button>
				
		 
			</div>
	    
	    	<table id="mainTable" class="table_list" data-toggle="table"
				data-url="${path}/sys/auth/res/view.do" data-pagination="true"
				data-side-pagination="server" data-cache="false" data-query-params="postQueryParams"
				data-page-list="[10, 30, 50, 100]" data-page-size="10" data-method="post"
				data-show-refresh="false" data-show-toggle="false"
				data-show-columns="false" data-toolbar="#toolbar"
				data-click-to-select="false" data-single-select="false"
				data-striped="false" data-content-type="application/x-www-form-urlencoded">
				<thead>
					<tr>
						<th data-field="" data-checkbox="true"></th>
						<th data-field="id">ID</th>
						<th data-field="name">资源名称</th>
						<th data-field="parentName">上级资源</th>
						<th data-field="path" >资源路径</th>
						<th data-field="resourceLevelStr">层级</th>
						<th data-field="permission">权限字符串</th>
						<th data-field="sort">排序</th>
						<th data-field="status" data-formatter="statusFormatter" >状态</th>
						<th data-field="createtime" data-formatter="dateFormatter" >创建时间</th>
						<th data-field="operator" data-formatter="operatorFormatter">操作</th>
					</tr>
				</thead>
			</table>
			<!--<label class="select_all"><input type="checkbox" name="checkall" onclick=" var sl =$('#mainTable thead input[name=btSelectAll]');  if(sl.prop('checked')!=this.checked)sl.click();" class="J_checkall">全选/取消</label>-->

		</div>
		<div style="clear: both;"></div>
    </div>
</body>
</html>