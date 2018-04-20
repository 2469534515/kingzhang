package com.foxtail.controller.message;

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
import com.foxtail.model.goods.Goods;
import com.foxtail.model.message.Message;
import com.foxtail.service.message.MessageManagerService;

@RequestMapping("message")
@Controller
public class MessageManagerController extends BaseController{
		
		@Autowired
		private MessageManagerService messageManagerService;
		
		@RequestMapping("/messageList")
		public String toMessageList() {
			return "message/messageManager";
		}
		
		
		/*
		 * 多条件查询分页信息反馈页面
		 */
		@RequestMapping("/getMessageList")
		@ResponseBody
		public DataGrid list (HttpServletRequest request,Message message) {
			
			System.out.println("传过来的from表单信息:" + "\t开始时间:\t" + message.getBeforeTime() + "\t结束时间:\t" + message.getAfterTime());
			Pagination pagination = messageManagerService.getAllMessageByPage(getPagination(request), message);
			
			return DataGridResult.getResult(pagination);
		}
		
		/*
		 * 批量删除或单个删除
		 */
		@RequestMapping("/deleteMessageList")
		@ResponseBody
		public Object deleteGoods(String ids) {
			
			System.out.println("********" + ids);
			 String[] s = ids.split(",");
			 int[] ides = new int[s.length]; 
			   for(int i = 0;i<s.length;i++){
			     ides[i] = Integer.parseInt(s[i]);
			   }
			   
			 int num = messageManagerService.deleteMessageManager(ides);
			   System.out.println("从后台获取的状态\t" + num);
			
			return JsonResult.getSuccessResult();
		}
}
