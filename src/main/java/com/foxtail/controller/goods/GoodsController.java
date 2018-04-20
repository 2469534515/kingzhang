package com.foxtail.controller.goods;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.foxtail.service.goods.GoodsService;
import com.github.pagehelper.PageInfo;

@RequestMapping(value = "goods")
@Controller
public class GoodsController extends BaseController{
	
	@Autowired
	private GoodsService goodsService;
	
//	@Autowired
//	private ClassifyService classifyService;
	
	
	/*
	 * 进入商品添加页面
	 */
	@RequestMapping(value = "/goodsAdd")
	public String goodsAdd() {
	
		return "goods/addGoods";
	}
	
	
//	/*
//	 * 获取商品分类名称
//	 */
//	@RequestMapping("/getClassifyName")
//	@ResponseBody
//	public PageInfo<Classify> getCalssifyName(){
//		List<Classify> classifyName =  classifyService.getClassifyName();
//		PageInfo page = new PageInfo(classifyName);
//		return page;
//	}
	
	
	/*
	 * 通过Ajax提交添加信息并返回添加页面
	 */
	@RequestMapping("/addGoods")
	@ResponseBody
	public String addGoods(Goods goods,MultipartFile imgs,HttpServletRequest request) {
		
		System.out.println("从form表单传过来的数据\t所属分类:\t" + goods.getGoodsClassify() + "\t商品名称:\t" + goods.getGoodsName() 
		+ "\t商品概要:\t" + goods.getGoodsOutline() + "\t品牌:\t" + goods.getGoodsBrand() + "\t商品类型:\t" + goods.getGoodsType() 
		+ "\t商品排序:\t" + goods.getGoodsSort() + "\t商品规格:\t"  + goods.getGoodsFormat() + "\t商品价格:\t" + goods.getGoodsPrice()
		+ "\t市场价:\t" + goods.getMarketPrice() + "\t库存:\t" + goods.getGoodsStock() + "\t销量:\t" + goods.getGoodsSales());
		String path = request.getServletContext().getRealPath("/");
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println("保存到数据库中的时间搓\t" + currentTimeMillis);
		goods.setAgTime(currentTimeMillis);
		try {
			goods.setGoodsMasterPhoto(SpringFileupload.upload(imgs, path));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		goods.setGoodsStatus(1);
		
		int num = goodsService.addGoods(goods);
		System.out.println("添加商品后的状态\t" + num);
		
		return "100";
	}
	
	/*
	 * 进入商品列表页面
	 */
	@RequestMapping("/goodsList")
	public String goodsList() {
		
		return "goods/goodsList";
	}
	
	/*
	 * 多条件查询分页展示商品列表页面
	 */
	@RequestMapping("/getGoodsList")
	@ResponseBody
	public DataGrid list (HttpServletRequest request,Goods goods) {
		
		System.out.println("传过来的from表单信息:\t商品分类:" + goods.getGoodsClassify() + "\t商品状态:\t" + goods.getGoodsStatus() + "\t开始时间:\t" + goods.getBeforeTime() + "\t结束时间:\t" + goods.getAfterTime() + "\t关键词:\t" + goods.getGoodsName());
		Pagination pagination = goodsService.findForPage(getPagination(request), goods);
		
		return DataGridResult.getResult(pagination);
	}
	
	/*
	 * 批量删除或单个删除
	 */
	@RequestMapping("/deleteGoods")
	@ResponseBody
	public Object deleteGoods(String ids) {
		
		System.out.println("********" + ids);
		 String[] s = ids.split(",");
		 int[] ides = new int[s.length]; 
		   for(int i = 0;i<s.length;i++){
		     ides[i] = Integer.parseInt(s[i]);
		   }
		   
		   int num = goodsService.deleteGoods(ides);
		   System.out.println("从后台获取的状态\t" + num);
		
		return JsonResult.getSuccessResult();
	}
	
	
	/*
	 * 跳转修改页面
	 */
	@RequestMapping("/editGoods")
	public ModelAndView updateGoodsView(int id){
		System.out.println("into updateMemberView()...。。。。。。");
		ModelAndView mv = new ModelAndView("goods/updateGoods");

		Goods goods = new Goods(); 

		goods = goodsService.getGoodsById(id);

		mv.getModelMap().addAttribute("goods",goods);
		
		return mv;
	} 
	
	
	/*
	 * 根据商品对象修改商品信息
	 */
	@RequestMapping("/updateGoods")
	@ResponseBody
	public String updateGoods(Goods goods,MultipartFile imgs,HttpServletRequest request) {
		System.out.println("into updateGoods.............");
		
		/*String path = request.getServletContext().getRealPath("/");
		try {
			goods.setGoodsMasterPhoto(SpringFileupload.upload(imgs, path));
			System.out.println("获得的图片路径\t" + goods.getGoodsMasterPhoto());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
			Goods before_goods = goodsService.getGoodsById(goods.getId());
			if(imgs.isEmpty()) {
				goods.setGoodsMasterPhoto(before_goods.getGoodsMasterPhoto());
			}else {
				String fileName = before_goods.getGoodsMasterPhoto();
				String beforPath = request.getServletContext().getRealPath("/");
				File file = new File(beforPath);
				String beforPhotoPath = new File(file.getParent()).getPath();
				String deletePhoto = beforPhotoPath+fileName;
				new File(deletePhoto).delete();
				
				//新增图片
				String path = request.getServletContext().getRealPath("/");
				
				try {
					goods.setGoodsMasterPhoto(SpringFileupload.upload(imgs, path));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		
		int num = goodsService.updateGoodsByGoods(goods);
		System.out.println("返回的状太\t" + num);
		return "100";
	}
	
	
	

}
