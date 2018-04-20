package com.foxtail.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxtail.common.DataGridResult;
import com.foxtail.common.JsonResult;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.foxtail.model.member.MemMemberAdd;
import com.foxtail.service.member.MemMemberAddService;

@RequestMapping("memMemberAdd")
@Controller
public class MemMemberAddController extends BaseController{
	
	@Autowired
	private MemMemberAddService memMemberAddService;
	
	
	@RequestMapping("/getMemberAddList")
	public String getMemberAddList(){
		
		return "member/memberAddList";
	}
	
	
	//分页显示信息
	@RequestMapping("/memberAddList")
	@ResponseBody
	public DataGrid getMemberAddList (HttpServletRequest request,MemMemberAdd memMemberAdd) {
		System.out.println("into getMemberAddList().....................");
		System.out.println("分类:\t" + memMemberAdd.getAddClassify() + "\t开始时间:\t" + memMemberAdd.getAfterTime() + "\t结束时间:\t" +memMemberAdd.getBeforeTime() + "\t关键词:\t" + memMemberAdd.getTradeName());
			
		Pagination pagination = memMemberAddService.getMemMemberAddListByPage(getPagination(request), memMemberAdd);
			
		return DataGridResult.getResult(pagination);
	}
	
	//删除会员信息/批量删除
	@RequestMapping("/deleteMemberAddList")
	@ResponseBody
	public Object deleteMemberAddList(String ids) {
		System.out.println("into deleteMemberAddList()......");
		System.out.println("********" + ids);
		 String[] s = ids.split(",");
		 int[] ides = new int[s.length]; 
		   for(int i = 0;i<s.length;i++){
		     ides[i] = Integer.parseInt(s[i]);
		   }
		   
		   int num = memMemberAddService.deleteMemberAdd(ides);
		   System.out.println("从后台获取的状态\t" + num);
		
		return JsonResult.getSuccessResult();
	}

}
