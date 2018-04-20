package com.foxtail.controller.goods;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.foxtail.common.DataGridResult;
import com.foxtail.common.JsonResult;
import com.foxtail.common.SpringFileupload;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.foxtail.model.goods.Classify;
import com.foxtail.model.goods.Goods;
import com.foxtail.service.goods.ClassifyService;
import com.github.pagehelper.PageInfo;

@RequestMapping("classify")
@Controller
public class ClassifyController extends BaseController{
	
	@Autowired
	private ClassifyService classifyService;
	
	
//	public JsonData addClassify() {
//		JsonData json = new JsonData();
//	}
	
	//跳转到分页显示页面
	@RequestMapping("/classifyList")
	public String classifyList() {
		
		return "goods/classifyList";
	}
	
	
	
	//分页显示信息
	@RequestMapping("/getClassifyList")
	@ResponseBody
	public DataGrid getClassifyList (HttpServletRequest request) {
		
		Pagination pagination = classifyService.getAllClassifyByPage(getPagination(request));
		
		return DataGridResult.getResult(pagination);
	}
	
	//获取分类名称
	@RequestMapping("/getClassifyName")
	@ResponseBody
	public PageInfo<Classify> getCalssifyName(){
		List<Classify> classifyName =  classifyService.getClassifyName();
		PageInfo page = new PageInfo(classifyName);
		return page;
	}
	
	//跳转到添加页面
	@RequestMapping("/toAddClassify")
	public ModelAndView addClassify(){
		ModelAndView mv = new ModelAndView("goods/addClassify");
		return mv;
	} 
	
	
	
	/*
	 * 通过Ajax提交添加商品分类并返回添加页面
	 */
	@RequestMapping("/addClassify")
	@ResponseBody
	public String addGoods(Classify classify,MultipartFile imgs,HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/");
	
		try {
			classify.setGoodsClassifyPhoto(SpringFileupload.upload(imgs, path));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int num = classifyService.addClassify(classify);
		System.out.println("添加商品后的状态\t" + num);
		
		return "100";
	}
	
	
	//跳转到修改页面
		@RequestMapping("/toUpdateClassify")
		public ModelAndView updateClassify(int id){
			ModelAndView mv = new ModelAndView("goods/updateClassify");
			Classify classify = classifyService.getCalssifyById(id);
			mv.getModelMap().addAttribute("classify", classify);
			return mv;
		}
		
	//通过商品分类对象修改商品分类信息
		@RequestMapping("/updateCalssify")
		@ResponseBody
		public String updateCalssify(Classify classify,@RequestParam(value = "imgs",required=false)MultipartFile imgs,HttpServletRequest request) {
			System.out.println("into updateCalssify.............\t" + imgs);
			System.out.println(imgs.isEmpty());
				Classify before_classify = classifyService.getCalssifyById(classify.getId());
				if(imgs.isEmpty()) {
					classify.setGoodsClassifyPhoto(before_classify.getGoodsClassifyPhoto());
				}else {
					String fileName = before_classify.getGoodsClassifyPhoto();
					String beforPath = request.getServletContext().getRealPath("/");
					File file = new File(beforPath);
					String beforPhotoPath = new File(file.getParent()).getPath();
					String deletePhoto = beforPhotoPath+fileName;
					new File(deletePhoto).delete();
					
					//新增图片
					String path = request.getServletContext().getRealPath("/");
					
					try {
						classify.setGoodsClassifyPhoto(SpringFileupload.upload(imgs, path));
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			
			int num = classifyService.updateClassifyByCalssify(classify);
			System.out.println("返回的状太\t" + num);
			return "100";
		}
		
		/*
		 * 批量删除或单个删除
		 */
		@RequestMapping("/deleteCalssify")
		@ResponseBody
		public Object deleteCalssify(String ids) {
			
			System.out.println("********" + ids);
			 String[] s = ids.split(",");
			 int[] ides = new int[s.length]; 
			   for(int i = 0;i<s.length;i++){
			     ides[i] = Integer.parseInt(s[i]);
			   }
			   
			   int num = classifyService.deleteCalssify(ides);
			   System.out.println("从后台获取的状态\t" + num);
			
			return JsonResult.getSuccessResult();
		}
		
}
