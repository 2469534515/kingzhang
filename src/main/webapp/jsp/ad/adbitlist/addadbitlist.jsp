<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加</title>
<%@ include file="/common/edit_global.jsp"%>

<script type="text/javascript" src="${path}/js/jquery.validate.min.js"></script>

<script type="text/javascript">
//	$(function() {
	
//	});
//	function toSubmit(){
//		//表单验证
//		var data = $("#submit_form").serialize();
//		alert(data);
//		//表单提交带图片上传不带图片不行
//     $("#submit_form").ajaxSubmit({
//			url:"${path}/ad/ad/addadbitlist.do",
//			data:data,
//			cache:false,
//			dataType:'JSON',
//			type:'post',
//			success:function(msg){
//				if(msg==100){
//					 $app.alert('添加成功',function(){
//						 goBackList();
//					 });
//				}else{
//					$app.alert("添加失败");
//				}
//			}
//		});  
	
	   
//		/* //带图片上传的ajax,不传图片也可以
//		//var formData = new FormData($('#submit_form')[0]);
//		$.ajax({  
//	        type: 'post',  
//	        url:"${path}/ad/ad/addadlist.do",
//	        data:formData,  
//	        cache:false,  
//	        processData:false,  
//	        contentType:false,
//	        dataType:'JSON',
//	        success:function(msg){
//				if(msg==100){
//					 $app.alert('添加成功',function(){
//						 goBackList();
//					 });
//				}else{
//					$app.alert("添加失败");
//				}
//			}
//		});*/  
//	}
	function toSubmit(){
		//表单验证
		$('#sub_form').validate({
	    rules:{
	    	placename:{
	            required: true
	        },
	        addesc:{
	            required: true
	        },
	        sort:{
	        	required:true	
	        }
	    }
	    });
		
		var data = $("#sub_form").serialize();
        $("#sub_form").ajaxSubmit({
			url:"${path}/ad/ad/addadbitlist.do",
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
			window.location="${path}/ad/ad/adbitlists.do";
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
					<li><span><strong style="color:red;">*</strong>广告位名称：</span>
					<input id="placename" name="placename" type="text" class="form-control input-primary input-sm w260" /></li>
					<li><span><strong style="color:red;">*</strong>广告位描述：</span>
					<input id="addesc" name="addesc" type="text" class="form-control input-primary input-sm w260"  /></li>
					<li><span><strong style="color:red;">*</strong>排序：</span>
					<input id="sort" name="sort" type="text" class="form-control input-primary input-sm w260"  /></li>
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