<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd

">
<html>
<%@ include file="/common/global.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改页面</title>
<script type="text/javascript">
$(function(){
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

   //赋值
   var s = ${adlist.starttime};
   var e = ${adlist.endtime};
   var start = new Date(s).Format("yyyy-MM-dd");
   var end = new Date(e).Format("yyyy-MM-dd");
   $("#starttime").val(start);
   $("#endtime").val(end);
   });
//返回列表
function goBackList(){
	var index = parent.layer.getFrameIndex(window.name);
	if(isNaN(index))window.location="${path}/ad/adlist/adlists.do";
	else
	parent.layer.close(index);
}
function toSubmit(){
	//表单验证
	$app.form.preSubmit("#submit_form");
	var data = $("#submit_form").serialize();
	alert(data);
	//表单提交带图片上传不带图片不行
    $("#submit_form").ajaxSubmit({
		url:"${path}/ad/ad/updateadlist.do",
		//data:$("#submit_form").serialize(),
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
	if(isNaN(index))window.location="${path}/ad/adlist/adlists.do";
	else
	parent.layer.close(index);
}

$(function() {
	select_all();
	selected_adspace();
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
			selected_adspace();
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
//获取所属类型的值并赋给下拉列表框
function selected_adspace(){
	var d = "${adlist.adspace}";
	$("#adspace option[value='"+d+"']").prop("selected", true);
}
</script>
</head>
<body>
	<div>
		<div class="formbody">
   			<div class="formtitle"><span>基本信息</span></div>
   			<form id="submit_form" method="post" enctype="multipart/form-data" class="vform">
				<ul class="forminfo">
						<li><input type="hidden" name="id" value="${adlist.id}"></li>
						<li><span><strong style="color:red;">*</strong>广告名称:</span>
						<input type="text" name="adname" value="${adlist.adname}" class="form-control input-primary input-sm w260">
						</li>
						<li><span><strong style="color:red;">*</strong>广告链接:</span>
						<input type="text" name="adlinks" value="${adlist.adlinks}" class="form-control input-primary input-sm w260">
						</li>
						<li><span><strong style="color:red;">*</strong>广告描述:</span>
						<input type="text" name="addesc" value="${adlist.addesc}" class="form-control input-primary input-sm w260">
						</li>
						<li><span><strong style="color:red;">*</strong>广告位:</span>
						<select id = "adspace" name = "adspace" class="form-control input-primary input-sm w260"></select>
						</li>
						<li><span><strong style="color:red;">*</strong>广告类型:</span>
						<input type="text" name="adtype" value="${adlist.adtype}" class="form-control input-primary input-sm w260">
						</li>
						<li><span><strong style="color:red;">*</strong>广告图片:</span>
						<input type="file" name="newimgs" value="${adlist.adimg}" class="form-control input-primary input-sm w260">
						</li>
						<li><span><strong style="color:red;">*</strong>排序:</span>
						<input type="text" name="sort" value="${adlist.sort}" class="form-control input-primary input-sm w260">
						</li>
						<li><span><strong style="color: red;">*</strong>有效时间(开始)：</span>
					
					    <input id="starttime" data-lxr="{type:'time',format:'yyyy-MM-dd'}" style="display: inline" type="text" class="lxr-format wdateExt Wdate input-primary" onfocus="WdatePicker({isShowClear:false})">
    				    <input type="hidden" name="starttime" value="${adlist.starttime }" >
    				    </li>
    				
    				    <li>
    				    <li><span><strong style="color: red;">*</strong>有效时间(结束)：</span>
					    <input id="endtime"  data-lxr="{type:'time',format:'yyyy-MM-dd'}" style="display: inline" type="text" class="lxr-format wdateExt Wdate input-primary" onfocus="WdatePicker({isShowClear:false})">
					    <input type="hidden" name="endtime" value="${adlist.endtime }">
			     	</li>
	    			
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
