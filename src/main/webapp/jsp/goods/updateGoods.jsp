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
		checked();
	});
	
	
	//获取商品类型的值并赋给下拉列表框
	function selected_classify(){
		var d = "${goods.goodsClassify}";
		$("#goodsClassify option[value='"+d+"']").prop("selected", true);
	}
	
	//获取品牌的值并赋给下拉列表框
	function selected_brand(){
		var s = "${goods.goodsBrand}";
		$("#goodsBrand option[value='"+s+"']").prop("selected",true);
	}
	
	//自动获得复选框的值并实现自动勾选
	function checked(){
		var s = "${goods.goodsType}";
		var item = s.split(",");
		for(var i = 0;i<item.length;i++){
			$("input:checkbox[value="+item[i]+"]").attr('checked',true);
		}
	}
	
	
	//把所有分类信息查询出来
	function select_ClassifyName() {
		alert("select_ClassifyName()....................");
		$.ajax({
			type : 'post',
			url : '${path}/classify/getClassifyName.do',
			dataType : 'json',
			success : function(result) {
				//插入用户数据信息
				addBox(result);
				selected_classify();
			}
		});
	}

	//插入分类数据信息
	function addBox(result) {
		$("#goodsClassify").empty();

		$.each(result.list, function(index, obj) {
			$("#goodsClassify").append(
					'<option  value = "'+obj.goodsClassifyName+'">'
							+ obj.goodsClassifyName + '</option>');
		});
	}
	
	//把所有品牌信息查询出来
	function select_BrandName() {
		alert("select_BrandName()........................");
		$.ajax({
			type : 'post',
			url : '${path}/brand/getBrandName.do',
			dataType : 'json',
			success : function(result) {
				//插入用户数据信息
				brandName(result);
				selected_brand();
			}
		});
	}

	//插入品牌数据信息
	function brandName(result) {
		$("#goodsBrand").empty();

		$.each(result.list, function(index, obj) {
			$("#goodsBrand").append(
					'<option value = "'+obj.brandName+'">'
							+ obj.brandName + '</option>');
		});
	}

	
	function toSubmit(){
		//表单验证
		$app.form.preSubmit("#submit_form");
		alert($("#submit_form").serialize());
		//表单提交
		$("#submit_form").ajaxSubmit({
			url:"${path}/goods/updateGoods.do",
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
		if(isNaN(index))window.location="${path}/goods/goodsList.do";
		else
		parent.layer.close(index);
	}
	
	
	//验证商品名称
	function Name(){
		alert("into goodsName()..............");
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
	function update_sub(){
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
   			<form id="submit_form" method="post" enctype="multipart/form-data" onsubmit="return update_sub()">
				<ul class="forminfo">
						<li><input type="hidden" name = "id" value="${goods.id}"/></li>
						
						
						<li>
							
							所属分类:<select name="goodsClassify" id = "goodsClassify" class="input-primary input-sm w260">
											<%-- 										<c:forEach  items=""  var="l"> --%>
											<%-- <option value = "${l.}"  <c:if test="${l.} == ">cheked</c:if>></option> --%>
											<%-- </c:forEach> --%>
								  </select>
						</li>
						<li>商品名称:<input type="text" name="goodsName" value="${goods.goodsName}" class="form-control input-primary input-sm w260" id = "goodsName" onblur="Name()"></li>
						<li>
							商品概要:<textarea rows="5" cols="10" id = "goodsOutline" name="goodsOutline" class="form-control input-primary input-sm w260" onblur="Outline()">${goods.goodsOutline}</textarea>
						</li>
						<li>
							品牌:<select name="goodsBrand" id = "goodsBrand" class="form-control input-primary input-sm w260">
							    </select>
						</li>
						<li>商品主图:<input type="file" name="imgs" class="form-control input-primary input-sm w260"></li>
						<li>
							商品类型:<input type="checkbox" name = "goodsType" value="热销产品">热销产品  &nbsp;&nbsp;&nbsp;<input type="checkbox" name = "goodsType" value="新品推荐">新品推荐
						</li>
						<li>
							商品排序:<input type="text" name="goodsSort" id = "goodsSort" class="form-control input-primary input-sm w260" value = "${goods.goodsSort}" onblur="Sort()">
							商品规格:<input type="text" class="form-control input-primary input-sm w260" id = "goodsFormat" name="goodsFormat" value = "${goods.goodsFormat}" onblur="Format()">
							商城价格:<input type="text" name="goodsPrice" id = "goodsPrice" value = "${goods.goodsPrice}" class="form-control input-primary input-sm w260" onblur="goodPrice()">
							市场价:<input type="text" name="marketPrice" id = "marketPrice" value = "${goods.marketPrice}" class="form-control input-primary input-sm w260" onblur="marketsPrice()">
							库存:<input type="text" name="goodsStock" id = "goodsStock" value = "${goods.goodsStock}" class="form-control input-primary input-sm w260" onblur="Stock()">
							销量:<input type="text" name="goodsSales" id = "goodsSales" value = "${goods.goodsSales}" class="form-control input-primary input-sm w260" onblur="Sales()">
						</li>
						<li>
							详细信息:
							<textarea rows="15" cols="20" name="detailedInfor" id = "detailedInfor" class="form-control input-primary input-sm w260" onblur="detailedInfors()">${goods.detailedInfor}</textarea>
						</li>
						
						
						
						
						
						
						
	    			<li><span>&nbsp;</span><input name="" type="button" class="btn btn-primary" value="确认修改" onclick="toSubmit()"/></li>
	    		</ul>
    		</form>
	    </div>
	</div>
</body>
</html>