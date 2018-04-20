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
			url:"${path}/infoment/posts/updateposts.do",
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
			window.location="${path}/infoment/posts/postss.do";
		}
		else{
			parent.layer.close(index);
		}
		
	}
	$(function() {
		select_all();
		selected_classId()
		checked();
	});

	//多条件查询分页所有会员信息
	function select_all() {
		$.ajax({
			type : 'post',
			url : '${path}/infoment/posts/findColumnName.do',
			dataType : 'json',
			success : function(result) {
				//插入用户数据信息
				addBox(result);
				selected_classId();
			}
		});
	}

	//插入用户数据信息
	function addBox(result) {
		$("#classId").empty();
		$.each(result.list, function(index, obj) {
			$("#classId").append('<option value = "'+obj.columnname+'">'+ obj.columnname + '</option>');
		});
	}

	//自动获得复选框的值并实现自动勾选
	function checked(){
		var s = "${posts.issue }";
		var item = s.split(",");
		for(var i = 0;i<item.length;i++){
			$("input:radio[value="+item[i]+"]").attr('checked',true);
		}
	}
	//获取所属类型的值并赋给下拉列表框
	function selected_classId(){
		var d = "${posts.classId}";
		$("#classId option[value='"+d+"']").prop("selected", true);
	}
</script>
</head>
<body>
	<div>
	
		<div class="formbody">
   			<form id="sub_form" class="vform" method="post" enctype="multipart/form-data" class="formbody">
				<ul class="forminfo">
				    <li><input type="hidden" name="id" value="${posts.id }"></li>
					<li><span><strong style="color:red;">*</strong>所属栏目：</span>
					<select id = "classId" name = "classId" class="form-control input-primary input-sm w260"></select></li>
					
					<li><span><strong style="color:red;">*</strong>文章标题：</span>
					<input value="${posts.titles }" name="titles" type="text" class="form-control input-primary input-sm w260" /></li>
					
					<li><span><strong style="color:red;">*</strong>短标题：</span>
					<input value="${posts.title }" name="title" type="text" class="form-control input-primary input-sm w260" /></li>
					
					<li><span><strong style="color:red;">*</strong>图片：</span>
					<input name="newimg" type="file" class="form-control input-primary input-sm w260" /></li>
					
					<li><span><strong style="color:red;">*</strong>发布：</span>
					<input type="radio" name="issue" value="1">是
					<input type="radio" name="issue" value="2">否
					</li>
					
					<li><span><strong style="color:red;">*</strong>排序：</span>
					<input value="${posts.sort }" name="sort" type="text" class="form-control input-primary input-sm w260" /></li>
					
					<li><span><strong style="color:red;">*</strong>摘要：</span>
					<input value="${posts.abstracts }" name="abstracts" type="text" class="form-control input-primary input-sm w260" /></li>
					
					<li><span><strong style="color:red;">*</strong>详细内容：</span>
					<input value="${posts.detail }" name="detail" type="text" class="form-control input-primary input-sm w260" /></li>
					
					
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