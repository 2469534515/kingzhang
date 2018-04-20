<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd

">
<html>
<%@ include file="/common/global.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加页面</title>
<script type="text/javascript">
	function toSubmit(){
		//表单验证
		$app.form.preSubmit("#submit_form");
// 		var data = $("#submit_form").serialize();
		var formData = new FormData($( "#submit_form" )[0]);  
		//表单提交带图片上传不带图片不行
        $("#submit_form").ajaxSubmit({
			url:"${path}/ad/ad/addadlist.do",
			//data:$("#submit_form").serialize(),
			data:formData,
			cache:false,
			dataType:'JSON',
			type:'post',
			success:function(msg){
				if(msg==100){
					 $app.alert('添加成功',function(){
						 goBackList();
					 });
				}else{
					$app.alert("添加失败");
				}
			}
		}); 
	}

	//返回列表
	function goBackList(){
		var index = parent.layer.getFrameIndex(window.name);
		if(isNaN(index))window.location="${path}/ad/adlist/adlists.do";
		else
		parent.layer.close(index);
	}
	
	$(function() {
		select_all();
	});

	//多条件查询分页所有会员信息
	function select_all() {
		$.ajax({
			type : 'post',
			url : '${path}/ad/ad/findAdBitName.do',
			dataType : 'json',
			success : function(result) {
				//插入用户数据信息
				addBox(result);
			}
		});
	}

	//插入用户数据信息
	function addBox(result) {
		$("#adspace").empty();
		$.each(result.list, function(index, obj) {
			$("#adspace").append('<option value = "'+obj.placename+'">'+ obj.placename + '</option>');
		});
	}
</script>
</head>
<body>
            <form id="submit_form" name="submit_form"  method="post" enctype="multipart/form-data" class="formbody">
    			<div style="display: inline;" class="lxr_multipleSelect" data-name="deptid" data-model="deptSelect"> </div>
    			<ul class="forminfo">
			<li><span><strong style="color: red;">*</strong>广告名称:</span> <input
				type="text" name="adname"
				class="form-control input-primary input-sm w260"></li>
			<li><span><strong style="color: red;">*</strong>广告链接:</span> <input
				type="text" name="adlinks"
				class="form-control input-primary input-sm w260"></li>
			<li><span><strong style="color: red;">*</strong>广告描述:</span> <input
				type="text" name="addesc"
				class="form-control input-primary input-sm w260"></li>
			<li><span><strong style="color: red;">*</strong>广告位</span> <select
				id="adspace" name="adspace"
				class="form-control input-primary input-sm w260"></select></li>
			<li><span><strong style="color: red;">*</strong>广告类型:</span> <input
				type="text" name="adtype"
				class="form-control input-primary input-sm w260"></li>
			<li><span><strong style="color: red;">*</strong>广告图片:</span> <input
				name="imgs" type="file"
				class="form-control input-primary input-sm w260" /></li>
			<li><span><strong style="color: red;">*</strong>排序:</span> <input
				type="text" name="sort"
				class="form-control input-primary input-sm w260"></li>
			<li><span><strong style="color: red;">*</strong>开始时间：</span> <input
				placeholder="开始时间" data-lxr="{type:'time',format:'yyyy-MM-dd'}"
				style="display: inline" type="text"
				class="lxr-format wdateExt Wdate input-primary"
				onfocus="WdatePicker({isShowClear:false})"> <input
				type="hidden" name="starttime"></li>
			<li><span><strong style="color: red;">*</strong>结束时间：</span> <input
				placeholder="结束时间" data-lxr="{type:'time',format:'yyyy-MM-dd'}"
				style="display: inline" type="text"
				class="lxr-format wdateExt Wdate input-primary"
				onfocus="WdatePicker({isShowClear:false})"> <input
				type="hidden" name="endtime"></li>
			<li><input type="button" class="btn btn-info btn-round btn-sm"
				value="添加" onclick="toSubmit()"></li>
		</ul>
    		</form>
</body>