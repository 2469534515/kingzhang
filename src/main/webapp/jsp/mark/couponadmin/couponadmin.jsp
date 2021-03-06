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
		$app.dialog('${path}/marketing/coupon/addcouponadminView.do',function(){
    		refTable();
		});
	}
	
	//删除
	function toRemove(ids) {
		if (!ids)
			ids = getSelectedRowsIds('manTable');
		if (ids) {
			$app.confirm("删除数据不可恢复，确定要删除吗？", function() {
				$.post('${path}/marketing/coupon/decouponadmin.do?ids=' + ids,
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
		$app.dialog('${path}/marketing/coupon/updatecouponadminView.do?id=' + id,
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
		var start = new Date(row.starttime).Format("yyyy-MM-dd hh-mm-ss");
		var end = new Date(row.endtime).Format("yyyy-MM-dd hh-mm-ss");
		return start + '/' + end
	}
</script>
</head>
<body>
    <div class="rightinfo">
		<div class="explain_col">
    		<form id="searchForm" name="searchForm"  method="post">
    			<label>名称：</label><input type="text" id="couponname" name="couponname" class="form-control input-sm w200" style="display: inline;">&nbsp;
    			<label>面值：</label><input type="text" id="facevalue" name="facevalue" class="form-control input-sm w200" style="display: inline;">
    			<label>有效时间：</label>
    				<input placeholder="开始" data-lxr="{type:'time',format:'yyyy-MM-dd'}" style="display: inline" type="text" class="lxr-format wdateExt Wdate input-primary" onfocus="WdatePicker({isShowClear:false})">
    				<input type="hidden" name="starttime">
					<input placeholder="结束" data-lxr="{type:'time',format:'yyyy-MM-dd'}" style="display: inline" type="text" class="lxr-format wdateExt Wdate input-primary" onfocus="WdatePicker({isShowClear:false})">
					<input type="hidden" name="endtime">
    			<input type="button" class="btn btn-info btn-round btn-sm" value="查询" onclick="refTable()">&nbsp;&nbsp;
    			
    		</form>
    	</div>
	    <div id="toolbar" class="btn-group">
		    	<button class="btn btn-info btn-round btn-sm" onclick="toAdd();">
					<i class="glyphicon glyphicon-plus"></i> 优惠卷生成
				</button>
				<button class="btn  btn-warning btn-round btn-sm " onclick="toRemove()">
					<i class="glyphicon glyphicon-trash"></i> 批量删除
				</button>
	    
		</div>
    	<table id="manTable" class="table_list" data-toggle="table"
			data-url="${path}/marketing/coupon/couponadmins.do" data-pagination="true"
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
					<th data-field="coupontype">优惠卷分类</th>
					<th data-field="couponname">优惠卷名称</th>
					<th data-field="facevalue">面值</th>
					<th data-field="applygoods">适用商品</th>
					<th data-field="useif">使用条件</th>
					<th data-field=" " data-formatter="operatorFormatterTime">有效时间</th>
					<th data-field="issuesum">发放总量</th>
					<th data-field="coupondesc">优惠卷说明</th>
					<th data-field="operator" data-formatter="operatorFormatter">操作</th>
				</tr>
			</thead>
		</table>
    </div>
</body>
</html>