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
		$app.dialog('${path}/infoment/posts/addpostsView.do',function(){
    		refTable();
		});
	}
	
	//删除
	function toRemove(ids) {
		if (!ids)
			ids = getSelectedRowsIds('manTable');
		if (ids) {
			$app.confirm("删除数据不可恢复，确定要删除吗？", function() {
				$.post('${path}/infoment/posts/deposts.do?ids=' + ids,
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
		$app.dialog('${path}/infoment/posts/upadtepostsView.do?id=' + id,
				function() {
					refTable();
				});
	}

	//设置查询参数
	function postQueryParams(params) {
		$app.form.preSubmit("#searchForm");
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
	 //格式化状态
    function statusFormatter(value,row,index){
    	if(value=='1'){
    		return '<span class="label label-success label-lg">启用</span>';
    	}else if(value=='2'){
    		return '<span class="label label-danger arrowed">锁定</span>';
    	}else{
    		return "";
    	}
    }
    //格式时间方法
	Date.prototype.Format = function(fmt) {
		var o = {
				"M+" : this.getMonth() + 1, //月份 
				"d+" : this.getDate(), //日 
				"h+" : this.getHours(), //小时 
				"m+" : this.getMinutes(), //分 
				"s+" : this.getSeconds(), //秒 
				//季度
				"q+" : Math.floor((this.getMonth() + 3) / 3),
				"S" : this.getMilliseconds()
				//毫秒 
		};
		if (/(y+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
	
	//解析时间戳
	function operatorFormatterTime(value, row, index) {
		var start = new Date(row.time).Format("yyyy-MM-dd hh-mm-ss");
		return start
	}
</script>
</head>
<body>
    <div class="rightinfo">
		<div class="explain_col">
    		<form id="searchForm" name="searchForm"  method="post">
    			<label>文章标题关键词：</label><input type="text" name="titles"  class="form-control input-sm w260" style="display: inline;">&nbsp;
    			<label>所属栏目：</label><input type="text" name="classId" class="form-control input-sm w260" style="display: inline;">&nbsp;
    			<label>有效时间：</label>
    			<input data-lxr="{type:'time',format:'yyyy-MM-dd'}" style="display: inline" type="text" class="lxr-format wdateExt Wdate input-primary" onfocus="WdatePicker({isShowClear:false})">
    			<input type="hidden" name="starttime"> ---
				<input data-lxr="{type:'time',format:'yyyy-MM-dd'}" style="display: inline" type="text" class="lxr-format wdateExt Wdate input-primary" onfocus="WdatePicker({isShowClear:false})">
				<input type="hidden" name="endtime">
    			<label>状态</label>
    			<select name="issue">
                 <option value ="0" selected="selected">所有</option>
                 <option value ="1">启用</option>
                 <option value ="2">锁定</option>
                 </select>
    			<input type="button" class="btn btn-info btn-round btn-sm" value="查询" onclick="refTable()">&nbsp;&nbsp;
    		</form>
    	</div>
	    <div id="toolbar" class="btn-group">
		    	<button class="btn btn-info btn-round btn-sm" onclick="toAdd();">
					<i class="glyphicon glyphicon-plus"></i> 添加文章
				</button>
				<button class="btn  btn-warning btn-round btn-sm " onclick="toRemove()">
					<i class="glyphicon glyphicon-trash"></i> 批量删除
				</button>
		</div>
    	<table id="manTable" class="table_list" data-toggle="table"
			data-url="${path}/infoment/posts/postss.do" data-pagination="true"
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
					<th data-field="titles">文章标题</th>
					<th data-field="classId">所属栏目</th>
					<th data-field="author">作者</th>
					<th data-field=" " data-formatter="operatorFormatterTime">发布时间</th>
					<th data-field="sort">排序</th>
					<th data-field="issue" data-formatter="statusFormatter">状态</th>
					<th data-field="operator" data-formatter="operatorFormatter">操作</th>
				</tr>
			</thead>
		</table>
    </div>
</body>

</html>