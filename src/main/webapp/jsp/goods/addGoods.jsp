<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd 

">
<html>
<%@ include file="/common/global.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
<title>修改页面</title>
<script type="text/javascript">
	

	$(function() {
		select_ClassifyName();
		select_BrandName();
	});

	//多条件查询分页所有会员信息
	function select_ClassifyName() {
		$.ajax({
			type : 'post',
			url : '${path}/classify/getClassifyName.do',
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
		$("#goodsClassify").empty();
		$.each(result.list, function(index, obj) {
			$("#goodsClassify").append(
					'<option value = "'+obj.goodsClassifyName+'">'
							+ obj.goodsClassifyName + '</option>');
		});
	}
	
	//多条件查询分页所有会员信息
	function select_BrandName() {
		$.ajax({
			type : 'post',
			url : '${path}/brand/getBrandName.do',
			//	     		data:{"pageNum":pageNum},
			//			data : kw + "&pageNum=" + pageNum,
			dataType : 'json',
			success : function(result) {
				//插入用户数据信息
				brandName(result);
			}
		});
	}

	//插入用户数据信息
	function brandName(result) {
		$("#goodsBrand").empty();

		$.each(result.list, function(index, obj) {
			$("#goodsBrand").append(
					'<option value = "'+obj.brandName+'">'
							+ obj.brandName + '</option>');
		});
	}

	function toSubmit() {
// 		$app.form.preSubmit("#submit_form");
		alert($("#submit_form").serialize());
		//表单提交
		$("#submit_form").ajaxSubmit({
			url : "${path}/goods/addGoods.do",
			data : $("#submit_form").serialize(),
			cache : false,
			dataType : 'JSON',
			type : 'post',
			success : function(msg) {
				alert("返回的msg:" + msg);
				if (msg == 100) {
					$app.alert('添加成功!', function() {
						window.location.reload();
					});
				} else {
					$app.alert("添加失败!");
				}
			}
		});
	}

	//返回列表
	function goBackList() {
		var index = parent.layer.getFrameIndex(window.name);
		if (isNaN(index))
			window.location = "${path}/member/memberList.do";
		else
			parent.layer.close(index);
	}
	
	
	
	
	//验证商品名称
	function Name(){
		//获取用户名
		var stuname = $("#goodsName").val();
		alert(stuname);
	
		if(stuname == null || stuname == ""){
			alert("商品名不能为空！");
			return false;
		}
 		return true;
	}
	
	//验证商品概要
	function Outline(){
		//获取商品概要
		var stuname = $("#goodsOutline").val();
	
		if(stuname == null || stuname == ""){
			alert("商品概要不能为空！");
			return false;
		}
 		return true;
	}
	
	//验证商品排序
	function Sort(){
		//获取商品排序
		var stuname = $("#goodsSort").val();
	
		if(stuname == null || stuname == ""){
			alert("商品排序不能为空！");
			return false;
		}
 		return true;
	}
	
	
	//验证商品规格
	function Format(){
		//获取商品排序
		var stuname = $("#goodsFormat").val();
	
		if(stuname == null || stuname == ""){
			alert("商品规格不能为空！");
			return false;
		}
 		return true;
	}
	
	//验证商品商城价格
	function goodPrice(){
		//获取商品商城价格
		var stuname = $("#goodsPrice").val();
	
		if(stuname == null || stuname == ""){
			alert("商品商城价格不能为空！");
			return false;
		}
 		return true;
	}
	
	//验证商品市场价
	function marketsPrice(){
		//获取商品市场价
		var stuname = $("#marketPrice").val();
	
		if(stuname == null || stuname == ""){
			alert("商品市场价不能为空！");
			return false;
		}
 		return true;
	}
	
	//验证商品库存
	function Stock(){
		//获取商品库存
		var stuname = $("#goodsStock").val();
	
		if(stuname == null || stuname == ""){
			alert("商品库存不能为空！");
			return false;
		}
 		return true;
	}
	
	//验证商品销量
	function Sales(){
		//获取商品销量
		var stuname = $("#goodsSales").val();
	
		if(stuname == null || stuname == ""){
			alert("商品销量不能为空！");
			return false;
		}
 		return true;
	}
	
	//验证商品详细信息
	function detailedInfors(){
		//获取商品详细信息
		var stuname = $("#detailedInfor").val();
	
		if(stuname == null || stuname == ""){
			alert("商品详细信息不能为空！");
			return false;
		}
 		return true;
	}
	
	//验证是否满足所有提交条件
	function submit_sub(){
		if(Name() && Outline() && Sort() && Format() && goodPrice() && marketsPrice() && Stock() && Sales() && detailedInfors()){
			
			return true;
		}
		
		return false;
	}
</script>
</head>
<body>
	<div>
		
		<div class="formbody">
   			<div class="formtitle"><span>基本信息</span></div>
   			<form id="submit_form" method="post" enctype="multipart/form-data" onsubmit="return submit_sub()">
				<ul class="forminfo">
						<li>所属分类:<select name="goodsClassify" id = "goodsClassify">
								  </select>
						</li>
						<li>商品名称:<input type="text" name="goodsName" class="form-control input-primary input-sm w260" id="goodsName" onblur="Name()"></li>
						<li>
							商品概要:<textarea rows="5" cols="10" name="goodsOutline" id = "goodsOutline" onblur="Outline()" style="border: solid 1px;"></textarea>
						</li>
						<li>
							品牌:<select name="goodsBrand" id = "goodsBrand">
							    </select>
						</li>
						<li>商品主图:<input type="file" name="imgs" class="form-control input-primary input-sm w260"></li>
						<li>
							商品类型:<input type="checkbox" name="goodsType" value="热销产品">热销产品  &nbsp;&nbsp;&nbsp;<input type="checkbox" name="goodsType" value="新品推荐">新品推荐
						</li>
						<li>商品排序:<input type="text" name="goodsSort" class="form-control input-primary input-sm w260" id="goodsSort" onblur="Sort()"></li>
						
						<li>
							商品规格:<input type="text" name="goodsFormat" id="goodsFormat" onblur="Format()" class="form-control input-primary input-sm w260">
							商品价格:<input type="text" name="goodsPrice" id="goodsPrice" onblur="goodPrice()" class="form-control input-primary input-sm w260">
							市场价:<input type="text" name="marketPrice" id="marketPrice" onblur="marketsPrice()" class="form-control input-primary input-sm w260">
							库存:<input type="text" name="goodsStock" id="goodsStock" onblur="Stock()" class="form-control input-primary input-sm w260">
							销量:<input type="text" name="goodsSales" id="goodsSales" onblur="Sales()" class="form-control input-primary input-sm w260">
						</li>
						<li>
							详细信息:
							<textarea rows="15" cols="20" name="detailedInfor" id="detailedInfor" onblur="detailedInfors()" style="border: solid 1px;"></textarea>
						</li>
	    			<li><span>&nbsp;</span><input name="" type="button" class="btn btn-primary" value="提交" onclick="toSubmit()"/></li>
	    		</ul>
    		</form>
	    </div>
	</div>
</body>
</html>