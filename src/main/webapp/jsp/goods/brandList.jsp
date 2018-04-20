<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/global.jsp"%>	
<head>
<meta http-equiv="Content-Type" content="text/html;  ">
<script type="text/javascript" src="${path}/js/tableExport.min.js"></script>
<script type="text/javascript" src="${path}/js/jquery.base64.js"></script>

<title>商品分类列表</title>
<script>
	

	$(function() {
		select_all();
	});

	//多条件查询分页所有会员信息
	function select_all() {
		$.ajax({
			type : 'post',
			url : '${path}/classify/getClassifyName.do',
			//	     		data:{"pageNum":pageNum},
			//			data : kw + "&pageNum=" + pageNum,
			dataType : 'json',
			success : function(result) {
				//插入用户数据信息
				addBox(result);
			}
		});
	}

	//插入用户数据信息
	function addBox(result) {
		$("#brandClassify").empty();
		$("#brandClassify").append(
		'<option value = "">请选择</option>');
		$.each(result.list, function(index, obj) {
			$("#brandClassify").append(
					'<option value = "'+obj.goodsClassifyName+'">'
							+ obj.goodsClassifyName + '</option>');
		});
	}

	//添加
	function toAdd() {
		$app.dialog('${path}/brand/toAddBrand.do', function() {
			refTable();
		});
	}
	//删除
	function toRemove(ids) {

		if (!ids)
			ids = getSelectedRowsIds('manTable');
		alert(ids);
		if (ids) {

			$app.confirm("删除数据不可恢复，确定要删除吗？", function() {
				$.post('${path}/brand/deleteBrand.do?ids=' + ids,
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

		$app.dialog('${path}/brand/toUpdateBrand.do?id=' + id, function() {
			refTable();
		});

	}

	//查看
	function toInfo(id) {

		$app.request("${path}/sys/dev/res/info.do?id=" + id, function(data) {
			console.log(data);

			$lxr.infoModal({
				title : "",
				items : [ {
					name : "姓名",
					val : data.userName
				}, {
					name : "账号",
					val : data.account
				}, {
					name : "电子邮箱",
					val : data.email
				}, {
					name : "手机号码",
					val : data.mobilePhone
				}, {
					name : "添加时间",
					val : data.regTime
				}, {
					name : "添加ip",
					val : data.regIp
				}, {
					name : "最后登录时间",
					val : data.lastLoginTime
				}, {
					name : "最后错误登录时间",
					val : data.lastLoginErrTime
				}

				, {
					name : "最后登录ip",
					val : data.lastLoginIp
				}, {
					name : "身份证号",
					val : data.idNumber
				}, {
					name : "登录累计错误次数",
					val : data.loginErrTimes
				}, {
					name : "状态",
					val : data.status,
					type : "enum",
					enums : {
						"1" : "启用",
						"2" : "锁定"
					}
				}

				]
			});

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
		//     	operator+='<button class="btn btn-info btn-round btn-xs" onclick="setUser(\''+row.id+'\');"><i class="glyphicon glyphicon-user"></i> 分配角色</button>&nbsp;&nbsp;';
		operator += $app.btn('edit', '编辑', 'toEdit(\'' + row.id + '\')');
		operator += $app.btn('delete', '删除', 'toRemove(\'' + row.id + '\')');
		return operator + '</div>';

	}

	//格式化状态
	function statusFormatter(value, row, index) {
		if (value == 1) {
			return '<span class="label label-success label-lg">打勾</span>';
		} else if (value == 2) {
			return '<span class="label label-danger arrowed">打叉</span>';
		} else {
			return "";
		}
	}

	//格式化图片
	function imageFormatter(value, row, index) {
		var src = "<img src='"+ value +"'/>";
		return src;

	}

	function setUser(id) {
		$app.dialog("${path}/sys/auth/user/toSetRole.do?uid=" + id, function() {
			refTable();
		}, {
			width : '300px',
			height : "500px"
		});

		/* var selected=[id];
		if(selected.length>0&&selected.length<2){
			var dialog = art.dialog.open("${path}/sys/auth/role/toUserRoleTree.do?userId="+selected,{
		  		  id:"selectResourceDialog",
		  		  title:"选择人员",
		  		  width :'300px',
		  		  height:'380px',
		  		  lock:true,
		  		  init: function (){
			  		$(this.iframe).attr("scrolling","no");//去掉滚动条
			  	  },
		  		  close:function(){
		  			  
		  		  }
		  	});
		}else{
			//提示信息
			$app.alert('请选择一条数据进行操作');
			
		} */
	}

	function closeDialog() {
		art.dialog.list["selectResourceDialog"].close();
	}

	function onExcell() {
		$('#manTable').tableExport({
			type : 'excel',
			ignoreColumn : [ 0, 9 ],
			separator : ';',
			escape : 'false',
			fileName : '账号表-' + new Date().format("yyyy-MM-dd")
		});

	}
</script>
</head>
<body>

    
    <div class="rightinfo">
    
    		<div id="toolbar" class="btn-group">
				<button class="btn  btn-warning btn-round btn-sm " onclick="toAdd()">
					<i class="glyphicon glyphicon-trash">新增分类</i> 
				</button>
				
				<button class="btn  btn-warning btn-round btn-sm " onclick="toRemove()">
					<i class="glyphicon glyphicon-trash">批量删除</i> 
				</button>
			</div>
    	
    
    		<div class="explain_col">
    		<form id="searchForm"  method="post">
    			<span>所属分类:<select name="brandClassify" id="brandClassify">
    							
    						</select>
    			</span>
 
    			<div style="display: inline;" class="lxr_multipleSelect" data-name="deptid" data-model="deptSelect"> </div>
    				<span>关键词：</span>
    				<input name="brandName" placeholder="昵称"  class="form-control input-sm w200" type="text" style="display: inline;" >
    				<input type="button" class="btn btn-info btn-round btn-sm" value="搜索" onclick="refTable()">
    		</form>
    	</div>
    
    
			
    	
    	
<!-- 	    <div id="toolbar" class="btn-group"> -->
<!-- 				<button class="btn  btn-warning btn-round btn-sm " onclick="toRemove()"> -->
<!-- 					<i class="glyphicon glyphicon-trash">批量删除</i>  -->
<!-- 				</button> -->
<!-- 		</div> -->
	   
    
    	<table id="manTable" class="table_list" data-toggle="table"
			data-url="${path}/brand/getBrandList.do" data-pagination="true"
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
					<th data-field="brandName">名称</th>
					<th data-field="brandClassify">所属分类</th>
					<th data-field="brandPhoto" data-formatter="imageFormatter">品牌图片</th>
					<th data-field="brandSort">排序</th>
					<th data-field="brandStatus" data-formatter="statusFormatter">状态</th>
					<th data-field="operator" data-formatter="operatorFormatter">操作</th>
				</tr>
			</thead>
		</table>
		
		
			<!--<label class="select_all"><input type="checkbox" name="checkall" onclick=" var sl =$('#manTable thead input[name=btSelectAll]');  if(sl.prop('checked')!=this.checked)sl.click();" class="J_checkall">全选/取消</label>-->

    </div>
</body>
</html>