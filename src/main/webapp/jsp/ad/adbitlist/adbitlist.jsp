<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/global.jsp"%>	
<head>
<meta http-equiv="Content-Type" content="text/html;  ">
<script type="text/javascript" src="${path}/js/tableExport.min.js"></script>
<script type="text/javascript" src="${path}/js/jquery.base64.js"></script>

<title>查询列表</title>
<script>
	//添加
	function toAdd(){
		$app.dialog('${path}/ad/ad/addadbitlistView.do',function(){
    		refTable();
		});
	}
	
	//删除
	function toRemove(ids) {
		if (!ids)
			ids = getSelectedRowsIds('manTable');
		if (ids) {
			$app.confirm("删除数据不可恢复，确定要删除吗？", function() {
				$.post('${path}/ad/ad/deadbitlist.do?ids=' + ids,
						function(data) {
							if (data.code == 1) {
								$app.alert("删除成功", function() {
									refTable();
								});
							} else
								$app.alert(msg);
						}, "json");
			});
		} else
			$app.alert("请选择一条数据进行操作");
	}

	
	//编辑
	function toEdit(id) {
		$app.dialog('${path}/ad/ad/updateadbitlistView.do?id=' + id,
				function() {
					refTable();
				});
	}

	//设置查询参数
	function postQueryParams(params) {
		var queryParams = $("#searchForm").serializeObject();
		queryParams.limit = params.limit;
		queryParams.offset = params.offset;
		return queryParams;
	}

	//查询列表
	function refTable() {
		$('#manTable').bootstrapTable('refresh');
	}

	//操作工具栏
	function operatorFormatter(value, row, index) {
		var operator = '<div class="btn-group">';
		operator += $app.btn('edit', '编辑', 'toEdit(\'' + row.id + '\')');
		operator += $app.btn('delete', '删除', 'toRemove(\'' + row.id + '\')');
		return operator + '</div>';
	}
	
</script>
</head>
<body>
    <div class="rightinfo">
		<div class="explain_col">
    		<form id="searchForm" name="searchForm"  method="post">
    			<label>广告位名称：</label><input type="text" name="placename" class="form-control input-sm w260" style="display: inline;">&nbsp;
    			<input type="button" class="btn btn-info btn-round btn-sm" value="查询" onclick="refTable()">&nbsp;&nbsp;
    		</form>
    	</div>
	    <div id="toolbar" class="btn-group">
		    	<button class="btn btn-info btn-round btn-sm" onclick="toAdd();">
					<i class="glyphicon glyphicon-plus"></i> 添加广告位
				</button>
				<button class="btn  btn-warning btn-round btn-sm " onclick="toRemove()">
					<i class="glyphicon glyphicon-trash"></i> 批量删除
				</button>
	    
		</div>
    	<table id="manTable" class="table_list" data-toggle="table"
			data-url="${path}/ad/ad/adbitlists.do" data-pagination="true"
			data-side-pagination="server" data-cache="false" data-query-params="postQueryParams"
			data-page-list="[15, 30, 50, 100]" data-page-size="15" data-method="post"
			data-show-refresh="false" data-show-toggle="false"
			data-show-columns="false" data-toolbar="#toolbar"
			data-click-to-select="false" data-single-select="false"
			data-striped="false" data-content-type="application/x-www-form-urlencoded"
			>
			<thead>
				<tr>
					<th data-field="" data-checkbox="true"></th>
					<th data-field="id">ID</th>
					<th data-field="placename">广告位名称</th>
					<th data-field="addesc">广告位描述</th>
					<th data-field="sort">排序</th>
					<th data-field="operator" data-formatter="operatorFormatter">操作</th>
				</tr>
			</thead>
		</table>
    </div>
</body>
</html>