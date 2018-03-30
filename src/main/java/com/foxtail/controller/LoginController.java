package com.foxtail.controller;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.foxtail.bean.ServiceManager;
import com.foxtail.common.LoggerUtils;
import com.foxtail.common.util.VerifyCodeUtils;
import com.foxtail.core.shiro.IncorrectCaptchaException;
import com.foxtail.core.shiro.ShiroUser;
import com.foxtail.model.sys.SysResource;
import com.foxtail.model.sys.SysUser;
import com.foxtail.service.sys.SysResService;

@Controller
public class LoginController {
	
	@Autowired
	private SysResService sysResourceService;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, ModelMap model) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){ //已经登录，重新登录
			SecurityUtils.getSecurityManager().logout(subject);
		}
		SysUser user = (SysUser) subject.getPrincipal();
		//String loginName = request.getParameter("loginName");
    	if(user != null){
    		model.addAttribute("loginName",user.getAccount());
    	}
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				model.addAttribute("warn","账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(
					exceptionClassName)) {
				String msg="用户名/密码错误";
				model.addAttribute("warn",msg);
			}else if (IncorrectCaptchaException.class.getName().equals(exceptionClassName)){
				model.addAttribute("warn","验证码错误");
			}else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
				model.addAttribute("warn","账户被禁用");
			}else {
				model.addAttribute("warn","登录异常");
			}
		}
		return "/login";
	}
	
	/**
	 * 
	* Description:加载验证码    
	* @Title: loadPasskey  
	* @since 2016年5月31日 下午2:27:30
	* @param request
	* @param response
	* @throws Exception
	 */
	@RequestMapping("/loadPasskey") 
	public void loadPasskey(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = request.getSession();
		int width = 80, height = 30;
		ServletOutputStream responseOutputStream = null;
		try {
			 ServletOutputStream outputStream = response.getOutputStream();
			String codeKey= VerifyCodeUtils.outputVerifyImage(width, height, outputStream, 4);
			session.setAttribute("CodeKey", codeKey);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != responseOutputStream){
				responseOutputStream.flush();
				responseOutputStream.close();
			}
		}
	}

	/**
	* Description:授权成功    
	* @since 2016年5月31日 下午2:27:54
	* @return
	 */
	@RequestMapping(value = "admin")
    public String pass(ModelMap model)
    {
		SysUser user = ShiroUser.getUser();
    	model.put("user", user);
    	List<SysResource> resList = sysResourceService.findAllByUserId(ShiroUser.getUserId());
    	model.put("resList", resList);
    	return "main";
    }
	
	
}
