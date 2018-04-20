<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加</title>
<%@ include file="/common/edit_global.jsp"%>


<script type="text/javascript">
$(function() {
	select_all();
});

//多条件查询分页所有会员信息
function select_all() {
	$.ajax({
		type : 'post',
		url : '${path}/marketing/activity/getActivityTypeName.do',
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
	$("#activitytype").empty();
	$.each(result.list, function(index, obj) {
		$("#activitytype").append('<option value = "'+obj.typename+'">'+ obj.typename + '</option>');
	});
}


	function onSubmit(){
		//表单验证
		var data = $("#sub_form").serialize();
        $("#sub_form").ajaxSubmit({
			url:"${path}/marketing/activity/updateactivitylist.do",
			data:data,
			cache:false,
			dataType:'JSON',
			type:'post',
			success:function(msg){
				if(msg==100){
					 $app.alert('修改成功',function(){
						 goBackList();
					 });
				}else{
					$app.alert("修改失败");
				}
			}
		});  
 	   
	}
	//返回列表
	function goBackList(){
		var index = parent.layer.getFrameIndex(window.name);
		if(isNaN(index)){
			window.location="${path}/marketing/activity/activitylists.do";
		}
		else{
			parent.layer.close(index);
		}
		
	}

</script>
</head>
<body>
	<div>
	
		<div class="formbody">
   			<form id="sub_form" class="vform" method="post">
				<ul class="forminfo">
				    <li><input type="hidden" name="id" value = "${activitylist.id }"></li>
					<li><span><strong style="color:red;">*</strong>选择活动分类：</span>
						<select id = "activitytype" name = "activitytype" class="form-control input-primary input-sm w260"></select>
					</li>
					<li><span><strong style="color:red;">*</strong>活动名称：</span>
					<input value = "${activitylist.activityname }" name="activityname" type="text" class="form-control input-primary input-sm w260"  /></li>
					<li><span><strong style="color:red;">*</strong>是否启用：</span>
					<input type="radio" name="state" value="1" checked="checked">是
					<input type="radio" name="state" value="2">否
					</li>
	    			</ul>
    		</form>
    		<div class="btnWrap">
					<input name="" type="button" class="btn btn-primary" value="确认" onclick="toSubmit()"/>
					<input name="" type="button" class="btn btn-warning" value="取消" onclick="goBack();"/>
	    		</div>
	    </div>
	</div>
</body>
</html>