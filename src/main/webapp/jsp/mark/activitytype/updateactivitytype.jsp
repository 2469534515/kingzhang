<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
<%@ include file="/common/edit_global.jsp"%>


<script type="text/javascript">
	function toSubmit(){
		var data = $("#sub_form").serialize();
		alert(data);
        $("#sub_form").ajaxSubmit({
			url:"${path}/marketing/activity/updateactivitytype.do",
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
			window.location="${path}/marketing/activity/activitytypes.do";
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
				    <li><input type="hidden" name="id" value = "${activitytype.id }"></li>
					<li><span><strong style="color:red;">*</strong>优惠卷分类名称：</span>
					<input value="${activitytype.typename }" name="typename" type="text" class="form-control input-primary input-sm w260" /></li>
					<li><span><strong style="color:red;">*</strong>排序：</span>
					<input value="${activitytype.typedesc }" name="typedesc" type="text" class="form-control input-primary input-sm w260"  /></li>
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