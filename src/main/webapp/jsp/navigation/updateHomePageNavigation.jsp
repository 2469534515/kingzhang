<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd 

">
<html>
<%@ include file="/common/global.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改页面</title>
<script type="text/javascript">
	$(function() {
		
	});
	function toSubmit(){
		//表单验证
		var message="";
		var remoney=$.trim($("#remoney").val());
		var givemoney=$.trim($('#givemoney').val());
		var sort=$.trim($('#sort').val());
		var restate=$.trim($('#restate').val());
		if(!remoney){
			message="-充值金额不能为空!";
		}
		if(!givemoney){
			message+="<br/>-赠送金额不能为空!";
		}
		if(!sort){
			message+="<br/>-排序不能为空!";
		}
		if(!restate){
			message+="<br/>-状态不能为空!";
		}
		alert($("#submit_form").serialize());
		//表单提交
		$("#submit_form").ajaxSubmit({
			url:"${path}/navigation/updateNavigation.do",
			data : $("#submit_form").serialize(),
			cache : false,
			dataType : 'JSON',
			type:'post',
			success:function(msg){
				alert("返回的msg:" + msg);
				if(msg==100){
					 $app.alert('修改成功!',function(){
						 goBackList(); 
					 });
				}else{
					$app.alert("修改失败!");
				}
			}
		});
	}
	
	
	//返回列表
	function goBackList(){
		var index = parent.layer.getFrameIndex(window.name);
		if(isNaN(index))window.location="${path}/navigation/homePageNavigation.do";
		else
		parent.layer.close(index);
	}
	
	
</script>
</head>
<body>
	<div>
		
		<div class="formbody">
   			<div class="formtitle"><span>基本信息</span></div>
   			<form id="submit_form" method="post" enctype="multipart/form-data">
				<ul class="forminfo">
						<li><input type="hidden" name="id" value="${navigation.id}" class="form-control input-primary input-sm w260"></li>
						
						<li>首页导航名称:<input type="text" name="navigationName" value="${navigation.navigationName}" class="form-control input-primary input-sm w260"></li>
						<li>导航小图标:<input type="file" name="imgs" class="form-control input-primary input-sm w260"></li>
						<li>排序:<input type="text" name="navigationSort" value="${navigation.navigationSort}" class="form-control input-primary input-sm w260"></li>
	    			<li><span>&nbsp;</span><input name="" type="button" class="btn btn-primary" value="确认修改" onclick="toSubmit()"/></li>
	    		</ul>
    		</form>
	    </div>
	</div>
</body>
</html>