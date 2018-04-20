package com.foxtail.controller.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.foxtail.common.DataGridResult;
import com.foxtail.common.JsonResult;
import com.foxtail.common.base.BaseController;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
import com.foxtail.model.info.Columns;
import com.foxtail.model.mark.CouponType;
import com.foxtail.service.info.ColumnService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/infoment/posts")
public class ColumnController extends BaseController{

	@Autowired
	private ColumnService columnservice;
	
	/**
	 * 页面
	 * @return
	 */
	@RequestMapping(value="/column")
	public String findAllColumn() {
		return "info/column/column";
	}
	/**
	 * 分页显示
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/columns")
	public DataGrid findAllColumns(HttpServletRequest request) {
		Pagination pagination = columnservice.findAll(getPagination(request));
		return DataGridResult.getResult(pagination);
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/decolumn")
	public Object deColumns(String ids) {
		this.columnservice.deColumns(ids.split(","));
		return JsonResult.getSuccessResult();
	}
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(value="/addcolumnView")
	public ModelAndView AddView() {
		ModelAndView view = new ModelAndView("/info/column/addcolumn");
		return view;
	}
	/**
	 * 添加
	 * @param columns
	 * @return
	 */
	@RequestMapping(value="/addcolumn")
	@ResponseBody
	public String AddColumn(Columns columns) {
		columnservice.addColumns(columns);
		return "100";
	}
	/**
	 * 跳转到修改页面
	 * @return
	 */
	@RequestMapping(value="/upadtecolumnView")
	public ModelAndView updateView(Integer id) throws Exception{
		ModelAndView view = new ModelAndView("/info/column/updatecolumn");
		Columns columns = new Columns();
		columns = columnservice.findAllById(id);
		view.getModelMap().addAttribute("columns", columns);
		return view;
	}
	/**
	 * 修改
	 * @param columns
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updatecolumn")
	public String updateColumn(Columns columns) {
		Integer update = columnservice.updateColumns(columns);
		return "100";
	}
	
	  /**
     * 查询所有类型名称
     */
    @RequestMapping("/findColumnName")
    @ResponseBody
    public PageInfo<Columns> findColumnName(){
    	List<Columns> list = columnservice.findColumnName();
    	PageInfo page = new PageInfo(list);
    	return page;
    }
}
