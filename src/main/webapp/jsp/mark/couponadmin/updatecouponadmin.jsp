<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改</title>
<%@ include file="/common/edit_global.jsp"%>


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
		var s = ${couponadmin.starttime};
		alert(s);
		var e = ${couponadmin.endtime};
		var start = new Date(s).Format("yyyy-MM-dd");
		var end = new Date(e).Format("yyyy-MM-dd");
		$("#starttime").val(start);
		$("#endtime").val(end);
	        });

	function toSubmit(){
		$app.form.preSubmit("#sub_form");
		var data = $("#sub_form").serialize();
		alert(data);
        $("#sub_form").ajaxSubmit({
			url:"${path}/marketing/coupon/updatecouponadmin.do",
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
			window.location="${path}/marketing/coupon/couponadmins.do";
		}
		else{
			parent.layer.close(index);
		}
	}
	
	
	
	
	
	
	
	$(function() {
		select_all();
		select_ClassifyName();
		
	});

	//多条件查询分页所有会员信息
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
				selected_classify();
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
	
	//获取商品类型的值并赋给下拉列表框
	function selected_classify(){
		var d = "${couponadmin.applygoods}";
		$("#applygoods option[value='"+d+"']").prop("selected", true);
	}
</script>
</head>
<body>
	<div>
		<div class="formbody">
			<form id="sub_form" class="vform" method="post">
				<ul class="forminfo">
					<li><input type="hidden" name="id" value="${couponadmin.id }"></li>
					
					<li><span><strong style="color: red;">*</strong>优惠卷分类：</span>
						<select id = "coupontype" name = "coupontype" class="form-control input-primary input-sm w260"></select>
					</li>
					
					<li><span><strong style="color: red;">*</strong>优惠卷名称：</span>
						<input value="${couponadmin.couponname }" name="couponname"
						type="text" class="form-control input-primary input-sm w260" />
					</li>
					
					<li><span><strong style="color: red;">*</strong>面值：</span> <input
						value="${couponadmin.facevalue }" name="facevalue" type="text"
						class="form-control input-primary input-sm w260" />
					</li>
					
					<li><span><strong style="color: red;">*</strong>适用商品：</span>
                       <select id = "applygoods" name = "applygoods" class="form-control input-primary input-sm w260"></select>
					</li>
					
					<li><span><strong style="color: red;">*</strong>使用条件：</span> <input
						value="${couponadmin.useif }" name="useif" type="text"
						class="form-control input-primary input-sm w260" />
					</li>
					
					<li><span><strong style="color: red;">*</strong>有效时间(开始)：</span>
					
					<input id="starttime" data-lxr="{type:'time',format:'yyyy-MM-dd'}" style="display: inline" type="text" class="lxr-format wdateExt Wdate input-primary" onfocus="WdatePicker({isShowClear:false})">
    				<input type="hidden" name="starttime" value="${couponadmin.starttime }" >
    				</li>
    				
    				<li>
    				<li><span><strong style="color: red;">*</strong>有效时间(结束)：</span>
					<input id="endtime"  data-lxr="{type:'time',format:'yyyy-MM-dd'}" style="display: inline" type="text" class="lxr-format wdateExt Wdate input-primary" onfocus="WdatePicker({isShowClear:false})">
					<input type="hidden" name="endtime" value="${couponadmin.endtime }">
			     	</li>
					
					<li><span><strong style="color: red;">*</strong>发行总量：</span> <input
						value="${couponadmin.issuesum }" name="issuesum" type="text"
						class="form-control input-primary input-sm w260" />
					</li>
					
					<li><span><strong style="color: red;">*</strong>优惠卷说明：</span>
						<input value="${couponadmin.coupondesc }" name="coupondesc" type="text"
						class="form-control input-primary input-sm w260" />
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