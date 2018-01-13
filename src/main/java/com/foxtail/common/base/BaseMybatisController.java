package com.foxtail.common.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.foxtail.common.JsonResult;
import com.foxtail.common.LoggerUtils;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.WebUtils;
import com.foxtail.core.util.PageUtils;
import com.lxr.commons.exception.ApplicationException;


public class BaseMybatisController implements WebBindingInitializer
{
 
  
  
  public static final String ACTION_EDIT = "edit";
  public static final String ACTION_ADD = "add";
  public static final String ACTION_INFO = "info";
  
  public void putAction(Map<String, Object> map,Object val) {
	map.put("action", val);

}
  

  @InitBinder
  public void initBinder(WebDataBinder binder, WebRequest request)
  {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
    
  }
  
  @ExceptionHandler
  @ResponseBody
  public Object exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
	
		if(!WebUtils.isAjax(request))
			throw e;
		
		
		
		if(e instanceof ApplicationException)
			return JsonResult.getFailResult(e.getMessage());
		
		e.printStackTrace();
		return JsonResult.getFailResult("未知异常:"+e.getMessage());

	}

  
  public String getMainJsp() {
	  return getClass().getAnnotation(RequestMapping.class).value()[0]+"_main";
  }
  public String getMainJsp(String module) {
	  
	  return getClass().getAnnotation(RequestMapping.class).value()[0]+(StringUtils.isEmpty(module)?"_main":("_main@"+module));
  }
  
  public String getEditJsp() {
	  return getClass().getAnnotation(RequestMapping.class).value()[0]+"_edit";
}
  
  public String getEditJsp(String module) {
	  return getClass().getAnnotation(RequestMapping.class).value()[0]+(StringUtils.isEmpty(module)?"_edit":("_edit@"+module));
}
  
  public String getInfoJsp(String module) {
	  return getClass().getAnnotation(RequestMapping.class).value()[0]+(StringUtils.isEmpty(module)?"_info":("_info@"+module));


}
  
  
  public Pagination getPagination(HttpServletRequest request) {
	
	  Pagination page = new Pagination();
	  
	  int pageNo=PageUtils.getPage();
	int pageSize=PageUtils.getRows();
	  
	  try {
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
	} catch (Exception e) {
		LoggerUtils.getLog(this).warn("分页参数错误：pageNo="+pageNo+",pageSize="+pageSize);
	}
	  
	  
	  
	  return page;
	  
	  

  }

}
