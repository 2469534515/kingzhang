<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
<%@ include file="/common/edit_global.jsp"%>


<script type="text/javascript">
	function toSubmit() {
		$app.form.preSubmit("#sub_form");
		var data = $("#sub_form").serialize();
		alert(data);
		$("#sub_form").ajaxSubmit({
			url : "${path}/marketing/coupon/addcouponadmin.do",
			data : data,
			cache : false,
			dataType : 'JSON',
			type : 'post',
			success : function(msg) {
				if (msg == 100) {
					$app.alert('添加成功', function() {
						goBackList();
					});
				} else {
					$app.alert("添加失败");
				}
			}
		});

	}
	//返回列表
	function goBackList() {
		var index = parent.layer.getFrameIndex(window.name);
		if (isNaN(index)) {
			window.location = "${path}/marketing/coupon/couponadmins.do";
		} else {
			parent.layer.close(index);
		}

	}
	
	$(function() {
		select_all();
		select_ClassifyName();
	});

	//多条件查询分页所有优惠卷信息
	function select_all() {
		$.ajax({
			type : 'post',
			url : '${path}/marketing/coupon/findCouponName.do',
			dataType : 'json',
			success : function(result) {
				//插入用户数据信息
				addBox(result);
			}
		});
	}

	//插入用户数据信息
	function addBox(result) {
		$("#coupontype").empty();
		$.each(result.list, function(index, obj) {
			$("#coupontype").append('<option value = "'+obj.coupontypename+'">'+ obj.coupontypename + '</option>');
		});
	}
	
	//多条件查询分页所有商品分类信息
	function select_ClassifyName() {
		$.ajax({
			type : 'post',
			url : '${path}/classify/getClassifyName.do',
			dataType : 'json',
			success : function(result) {
				//插入用户数据信息
				addBox1(result);
			}
		});
	}

	//插入用户数据信息
	function addBox1(result) {
		$("#applygoods").empty();

		$.each(result.list, function(index, obj) {
			$("#applygoods").append(
					'<option value = "'+obj.goodsClassifyName+'">'
							+ obj.goodsClassifyName + '</option>');
		});
	}
</script>
</head>
<body>
	<div>
		<div class="formbody">
			<form id="sub_form" class="vform" method="post">
				<ul class="forminfo">	

					<li><span><strong style="color: red;">*</strong>优惠卷分类：</span>
						<select id = "coupontype" name = "coupontype" class="form-control input-primary input-sm w260"></select>
						</li>

					<li><span><strong style="color: red;">*</strong>优惠卷名称：</span>
						<input name="couponname" type="text" class="form-control input-primary
						input-sm w260" /></li>

					<li><span><strong style="color: red;">*</strong>面值：</span> <input
						name="facevalue" type="text"
						class="form-control input-primary input-sm w260" /></li>

					<li><span><strong style="color: red;">*</strong>适用商品：</span> 
						<select id = "applygoods" name = "applygoods" class="form-control input-primary input-sm w260"></select>
						</li>

					<li><span><strong style="color: red;">*</strong>使用条件：</span> <input
						name="useif" type="text"
						class="form-control input-primary input-sm w260" /></li>

					<li><span><strong style="color: red;">*</strong>有效时间(开始)：</span>
					<input placeholder="开始时间" data-lxr="{type:'time',format:'yyyy-MM-dd'}" style="display: inline" type="text" class="lxr-format wdateExt Wdate input-primary" onfocus="WdatePicker({isShowClear:false})">
    				<input type="hidden" name="starttime">
    				</li>
    				
    				<li>
    				<li><span><strong style="color: red;">*</strong>有效时间(结束)：</span>
					<input placeholder="结束时间" data-lxr="{type:'time',format:'yyyy-MM-dd'}" style="display: inline" type="text" class="lxr-format wdateExt Wdate input-primary" onfocus="WdatePicker({isShowClear:false})">
					<input type="hidden" name="endtime">
			     	</li>

					<li><span><strong style="color: red;">*</strong>发行总量：</span> <input
						name="issuesum" type="text"
						class="form-control input-primary input-sm w260" /></li>

					<li><span><strong style="color: red;">*</strong>优惠卷说明：</span>
						<input name="coupondesc" type="text"
						class="form-control input-primary input-sm w260" /></li>

				</ul>
			</form>
			<div class="btnWrap">
				<input name="" type="button" class="btn btn-primary" value="确认"
					onclick="toSubmit()" /> <input name="" type="button"
					class="btn btn-warning" value="取消" onclick="goBack();" />
			</div>
		</div>
	</div>
</body>
</html>