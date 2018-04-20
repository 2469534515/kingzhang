<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/common/global.jsp"%>	
<head>
<meta http-equiv="Content-Type" content="text/html;  ">
<script type="text/javascript" src="${path}/js/tableExport.min.js"></script>
<script type="text/javascript" src="${path}/js/jquery.base64.js"></script>

<title>查询列表</title>
<script>
	//添加
	function toAdd(){
		$app.dialog('${path}/member/member/addMemberView.do',function(){
    		refTable();
		});
	}
	//删除
	function toRemove(ids){
		
		if(!ids)
		ids=getSelectedRowsIds('manTable');
		
		if(ids){
			
			$app.confirm("删除数据不可恢复，确定要删除吗？",function(){
				 $.post('${path}/member/member/deleteMultiple.do?ids='+ids,function(data){

					    if(data.code==1){
					    	$app.alert("删除成功",function(){
					    		refTable();
					    		
					    	});
		 					
					    }else
					    	$app.alert(msg);
						   
					   
				},"json");
				
			});
			
			
			
		}else
			$app.alert("请选择一条数据进行操作");
			
	}
	
	//编辑
    function toEdit(id){
    	
    		$app.dialog('${path}/member/member/toUpdateView.do?id='+id,function(){
        		refTable();
    		});
    	
	}
	
    //查看
    function toInfo(id){
    	
    	$app.request("${path}/sys/dev/res/info.do?id="+id
    			,function(data){
    		console.log(data);
    		
    				$lxr.infoModal({title:"",items:[
    		    		{name:"姓名",val:data.userName}
    		    		,{name:"账号",val:data.account}
    		    		,{name:"电子邮箱",val:data.email}
    		    		,{name:"手机号码",val:data.mobilePhone}
    		    		,{name:"添加时间",val:data.regTime}
    		    		,{name:"添加ip",val:data.regIp}
    		    		,{name:"最后登录时间",val:data.lastLoginTime}
    		    		,{name:"最后错误登录时间",val:data.lastLoginErrTime}
    		    		
    		    		,{name:"最后登录ip",val:data.lastLoginIp}
    		    		,{name:"身份证号",val:data.idNumber}
    		    		,{name:"登录累计错误次数",val:data.loginErrTimes}
    		    		,{name:"状态",val:data.status,type:"enum",enums:{"1":"启用","2":"锁定"} }
    		    		
    		    		]
    		    	}
    		    		);
    		
    		
    	});
    	
    	
    	
    	
	}
    
    
    
	
	//设置查询参数
	function postQueryParams(params) {
		$app.form.preSubmit("#searchForm");
		var queryParams = $("#searchForm").serializeObject();
		queryParams.limit=params.limit;
		queryParams.offset=params.offset;
		return queryParams;
	}
	//查询列表
    function refTable(){
    	$('#manTable').bootstrapTable('refresh');
    }
    
  


	
    //操作工具栏
    function operatorFormatter(value, row, index) {
    	var operator='<div class="btn-group">';
//     	operator+='<button class="btn btn-info btn-round btn-xs" onclick="setUser(\''+row.id+'\');"><i class="glyphicon glyphicon-user"></i> 分配角色</button>&nbsp;&nbsp;';
    	operator+=$app.btn('edit','编辑','toEdit(\''+row.id+'\')');
   		operator+=$app.btn('delete','删除','toRemove(\''+row.id+'\')');
		return operator+'</div>';
    	
	}
    
  	//格式化图片
	function imageFormatter(value, row, index) {
		var src = "<img src='"+ value +"'/>";
		return src;

	}
    
    //格式化状态
    function statusFormatter(value,row,index){
    	if(value=='1'){
    		return '<span class="label label-success label-lg">启用</span>';
    	}else if(value=='2'){
    		return '<span class="label label-danger arrowed">锁定</span>';
    	}else{
    		return "";
    	}
    }
    
 
    
    function setUser(id){
    	$app.dialog("${path}/sys/auth/user/toSetRole.do?uid="+id,function(){
    		refTable();
		},{width:'300px',height:"500px"});
    	
    	
    	/* var selected=[id];
    	if(selected.length>0&&selected.length<2){
    		var dialog = art.dialog.open("${path}/sys/auth/role/toUserRoleTree.do?userId="+selected,{
    	  		  id:"selectResourceDialog",
    	  		  title:"选择人员",
    	  		  width :'300px',
    	  		  height:'380px',
    	  		  lock:true,
    	  		  init: function (){
    		  		$(this.iframe).attr("scrolling","no");//去掉滚动条
    		  	  },
    	  		  close:function(){
    	  			  
    	  		  }
    	  	});
    	}else{
    		//提示信息
    		$app.alert('请选择一条数据进行操作');
    		
    	} */
    }
    
    function closeDialog(){
    	art.dialog.list["selectResourceDialog"].close();
    }
    
    
    function onExcell(){
    	$('#manTable').tableExport({type:'excel',ignoreColumn: [0,9]
    		, separator:';', escape:'false'
    	,fileName: '账号表-'+new Date().format("yyyy-MM-dd")});
    	
    }
    
    
    
    
    
    //添加会员信息
    function addMemeber(){
    	$app.dialog('${path}/member/member/addMemberView.do',function(){
    		refTable();
    	});
    }
    
    
  //根据id修改会员信息
	function updateMember(id){
		alert(id);
		$app.dialog('${path}/member/member/toUpdateView.do?id='+id,function(){
			window.location.reload();
		});
	}
    
   
    //根据id删除会员信息
    function deleteId(id){
    	alert("sss" + id);    	
    	
    	if(confirm("是否删除昵称为" + id + "的会员呢？")){
    		$.ajax({
        		type:'post',
        		url:'${path}/member/member/deleteMemberById.do',
        		data:{"id":id},
        		dataType:'json',
        		success:function(result){
        			alert("删除成功！");
        			window.location.reload();
        		}
        	});
    	}else{
    		alert("删除失败！");
    	}
    }
    
    
    
  //多条件查询所有会员信息
	function refMainTable(){
	  	alert("refMainTable()");
		$app.form.preSubmit("#searchForm");
		var data = $("#searchForm").serialize();
		alert(data);
		//var d = {};
		/* var t = $('form').serializeArray(); */
		/* $.each(t, function(index){
			d[this.name] = this.value;
			if(index==t.length-1){
				d['pageNum'] = 1;
			}
		  }); */
		   /*  var sysReCharge = JSON.stringify(d);  */
		select_all(1,data);
	}
   
    
//     $(function(){
//     	select_all(1,null);
//     }); 
	
    //多条件查询分页所有会员信息
    function select_all(pageNum,kw){
    	$.ajax({
    		type:'post',
    		url:'${path}/member/member/memberUserList.do',
//     		data:{"pageNum":pageNum},
			data:kw+"&pageNum="+pageNum,
    		dataType:'json',
    		success:function(result){
    			//插入用户数据信息
    			addBox(result);
    			//显示分页信息
    			get_users_pageInfo(result);
    			//做分页条
    			get_users_nav(result,kw);
    		}
    	});
    }

    
    
  	//插入用户数据信息
    function addBox(result){
    	$("#box").empty();
		
    	
    	$.each(result.list,function(index,obj){
    		var tr = $("<tr></tr>");
    		var date = new Date(obj.regTime).Format("yyyy-MM-dd hh-mm-ss");//格式化时间
    		
    		$("#box").append(tr);
    		tr.append('<td><input class="cbox" type="checkbox" name = "boss" value="'+obj.id+'"></td>');
    		tr.append("<td>"+obj.id+"</td>");
    		tr.append('<td><img src="'+obj.headImg+'"/></td>');
    		tr.append("<td>"+obj.mName+"</td>");
    		tr.append("<td>"+obj.sex+"</td>");
    		tr.append("<td>"+obj.phoneNumber+"</td>");
    		tr.append("<td>"+obj.balance+"</td>");
    		tr.append("<td>"+date+"</td>");
    		tr.append('<td><a onclick="updateMember('+obj.id+')">编辑</a>|<a onclick="deleteId('+obj.id+')">删除</a></td>');
    	});
    } 
    
    
//	解析分頁信息
	function get_users_pageInfo(result){
		$("#pageInfo_area").empty();
		$("#pageInfo_area").append(
				"当前第" + result.pageNum + "页,总共"
				+ result.pages + "页,总共"
				+ result.total + "条记录");
	}
	
//	解析分頁條
	function get_users_nav(result,kw){
		$("#pageNav_area").empty();
		var nav = $("<nav></nav>");
		var ul = $("<ul></ul>").addClass("pagination");
		var firstPageLi = $("<li></li>").append(
				$("<a></a>").append("首页").attr("href", "javascript:;"));
		var lastPageLi = $("<li></li>").append(
				$("<a></a>").append("末页").attr("href", "javascript:;"));
		var prePageLi = $("<li></li>").append(
				$("<a></a>").append("&laquo;").attr("href", "javascript:;"));
		var nextPageLi = $("<li></li>").append(
				$("<a></a>").append("&raquo;").attr("href", "javascript:;"));
		if (result.isFirstPage == true) {
			firstPageLi.addClass("disabled");
			prePageLi.addClass("disabled");
		} else {
			firstPageLi.click(function() {
				select_all(1,kw);
			});
			prePageLi.click(function() {
				select_all(result.pageNum - 1,kw);
			});
		}
		if (result.isLastPage == true) {
			lastPageLi.addClass("disabled");
			nextPageLi.addClass("disabled");
		} else {
			nextPageLi.click(function() {
				select_all(result.pageNum + 1,kw);
			});
			lastPageLi.click(function() {
				select_all(result.pages,kw);
			});
		}
		ul.append(firstPageLi).append(prePageLi);
		$.each(result.navigatepageNums, function(index, item) {
			var numLi = $("<li></li>").append(
					$("<a></a>").append(item).attr("href", "javascript:;"));
			if (result.pageNum == item) {
				numLi.addClass("active");
			}
			numLi.click(function() {
				select_all(item,kw);
			});
			ul.append(numLi);
		});
		ul.append(nextPageLi).append(lastPageLi);
		nav.append(ul).appendTo("#pageNav_area");
	}
	
	
	//格式时间方法
	Date.prototype.Format = function(fmt) {
		var o = {
				"M+" : this.getMonth() + 1, //月份 
				"d+" : this.getDate(), //日 
				"h+" : this.getHours(), //小时 
				"m+" : this.getMinutes(), //分 
				"s+" : this.getSeconds(), //秒
				//季度
				"q+" : Math.floor((this.getMonth() + 3)/3), 
				"S" : this.getMilliseconds()//毫秒
				 
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
	
		
	   		
 	  	function getAll(){
	         //给全选的复选框添加事件
	        $("#all").click(function(){
	             // this 全选的复选框
	            var userids=this.checked;
	          
	              //获取name=boss的复选框 遍历输出复选框
	              $("#checked").each(function(){
	                 this.checked=userids;
	             });
	              
	          });
 	  	}    
// 	         //给name=boss的复选框绑定单击事件
// 	         $("input[name='boss']").click(function(){
// 	              //获取选中复选框长度
// 	              var length=$("input[name=boss]:checked").length;
// 	              //未选中的长度
// 	              var len=$("input[name='boss']").length;
// 	              if(length==len){
// 	                  $("#all").get(0).checked=true;
// 	              }else{
// 	                  $("#all").get(0).checked=false;
// 	              }
// 	          });
//  	      });
	   		
	   		
	  	//批量删除
// 	  		function toRemove(){
// 	  			 $("#btn").click(function(){
// 	  			var checkedbox = $("input[name='boss']:checked");
	  		
// 	  		      if(checkedbox.length == 0){
// 	  	              alert("请选择要删除的数据");
// 	  	          }else{
// 	  	              if(confirm("你确定要删除吗？？？")){
// 	  	                     /**
// 	  	                                                    如下面，如果调用map方法，
// 	  	                                                     会把函数里面的返回值作为jquery对象--res返回
// 	  	                                                    注意，这里的res.toArray()等同于res.toArray().join(",");
// 	  	                                                  它默认就是这样做的呢，这个需要记住嘛
// 	  	                     */
// 	  	                 var ides = checkedbox.map(function(){
// 	  	                       return this.value;
// 	  	                   });
	  	                                               
// 	  	                      var ids =ides.toArray().join(","); 
// 	  	                      alert(ids);
// 	  	                 $.ajax({
// 	  	                	 type:'post',
// 	  	                	 url:'${path}/member/member/deleteMultiple.do',
// 	  	                	 data:{'ids':ids},
// 	  	                	 dataType:'json',
// 	  	                	 success:function(msg){
// 	  	                		 if(msg==100)
// 	  	                 			window.location.reload();
// 	  	                	 }
// 	  	                 });
// 	  	              }
// 	  	          }
// 	  	      });
// 	  		}	

		



    
</script>
</head>
<body class="mlr15">

    
    <div class="rightinfo explain_col">
		<div>
    		<form id="searchForm" name="searchForm"  method="post">
    			<div style="display: inline;" class="lxr_multipleSelect" data-name="deptid" data-model="deptSelect"> </div>
				
    				<span>时间：</span>
    				<input placeholder="开始" data-lxr="{type:'time',format:'yyyy-MM-dd'}" style="display: inline" type="text" class="lxr-format wdateExt Wdate input-primary" onfocus="WdatePicker({isShowClear:false})">
    				<input type="hidden" name="beforeTime">
					<input placeholder="结束" data-lxr="{type:'time',format:'yyyy-MM-dd'}" style="display: inline" type="text" class="lxr-format wdateExt Wdate input-primary" onfocus="WdatePicker({isShowClear:false})">
					<input type="hidden" name="afterTime">
    				<span>关键词：</span>
    				<input name="mName" value="" placeholder="昵称"  class="form-control input-sm w200" type="text" style="display: inline;" >
    				<input type="button" class="btn btn-info btn-round btn-sm" value="查询" onclick="refTable()">
    		</form>
    	 </div>
	  
    </div>
    
    	<table id="manTable" class="table_list" data-toggle="table"
			data-url="${path}/member/member/memberUserList.do" data-pagination="true"
			data-side-pagination="server" data-cache="false" data-query-params="postQueryParams"
			data-page-list="[15, 30, 50, 100]" data-page-size="15" data-method="post"
			data-show-refresh="false" data-show-toggle="false"
			data-show-columns="false" data-toolbar="#toolbar"
			data-click-to-select="false" data-single-select="false"
			data-striped="false" data-content-type="application/x-www-form-urlencoded"
			>
			<div>
					<button class="btn btn-danger btn-sm" id="btn" onclick="toAdd()">
						<i class="glyphicon glyphicon-plus"></i> 添加会员
					</button>
					<button class="btn btn-danger btn-sm" id="btn" onclick="toRemove()">
						<i class="glyphicon glyphicon-trash"></i> 批量删除
					</button>
		    </div>
			<thead>
				<tr>
					<th data-field="" data-checkbox="true" id = "checked"></th>
					<th data-field="id">ID</th>
					<th data-field="headImg" data-formatter="imageFormatter">头像</th>
					<th data-field="mName">昵称</th>
					<th data-field="sex">性别</th>
					<th data-field="phoneNumber">手机号</th>
					<th data-field="balance">余额</th>
					<th data-field="operator" data-formatter="operatorFormatter">管理操作</th>
				</tr>
			</thead>
		</table>
		
		
<!-- 		<div id = "pageNav_area"></div> -->
		
  <!--   <div class="select_btn">
	   	<label class="select_all">
	   		<input type="checkbox" name="checkall" onclick="if($('#mainTable thead input[name=btSelectAll]').prop('checked')!=this.checked)$('#mainTable thead input[name=btSelectAll]').click();"> 全选/取消
	   	</label>
	   	<button class="btn btn-danger btn-round btn-xs" onclick="toRemove()"><i class="glyphicon glyphicon-trash"></i> 批量删除</button>
	</div> -->
</body>
</html>