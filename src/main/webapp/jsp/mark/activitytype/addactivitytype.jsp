<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加</title>
<%@ include file="/common/edit_global.jsp"%>


<script type="text/javascript">
	function onSubmit(){
		//表单验证
		var data = $("#sub_form").serialize();
        $("#sub_form").ajaxSubmit({
			url:"${path}/marketing/activity/addactivitytype.do",
			data:data,
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
					<li><span><strong style="color:red;">*</strong>分类名称：</span>					
       				<input name="typename"type="text" class="form-control input-primary input-sm w260" /></li>
					<li><span><strong style="color:red;">*</strong>简要说明：</span>
					<input name="typedesc" type="text" class="form-control input-primary input-sm w260"  /></li>
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