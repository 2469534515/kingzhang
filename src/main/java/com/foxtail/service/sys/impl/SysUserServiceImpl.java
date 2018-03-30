package com.foxtail.service.sys.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.foxtail.bean.ServiceManager;
import com.foxtail.common.page.Pagination;
import com.foxtail.common.util.MD5Util;
import com.foxtail.common.util.PublicUtil;
import com.foxtail.common.util.TcpipUtil;
import com.foxtail.core.util.ContextHolderUtils;
import com.foxtail.core.util.PropertiesUtil;
import com.foxtail.dao.mybatis.sys.SysUserDao;
import com.foxtail.dao.mybatis.sys.SysUserRoleDao;
import com.foxtail.model.sys.SysUser;
import com.foxtail.model.sys.SysUserRole;
import com.foxtail.service.sys.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxr.commons.exception.ApplicationException;


@Service
public class SysUserServiceImpl implements SysUserService{

	private final static Logger log = Logger.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	
	public SysUser selectByPrimaryKey(Integer id){
	    return sysUserDao.getById(id+"");
	}

    public void deleteByPrimaryKey(Integer id){
    	 
    }

   
    
   

    public void updateByPrimaryKey(SysUser model) {
    	if (!StringUtils.isEmpty(model.getPassword())) {
    		String rawPwd=model.getPassword();
        	if (PublicUtil.checkEmptyString(rawPwd)) {
    			rawPwd=PropertiesUtil.getString("sys.defaultPwd");
    		}
        	String password = MD5Util.string2MD5(rawPwd);
        	model.setPassword(password);
		}
    	
    	
		this.sysUserDao.update(model);
    }
    
    public List<SysUser> selectList(SysUser sysUser){
    	return sysUserDao.selectList(sysUser);
    }
    
    public List<SysUser> findAll() {
		return null;
    }

   
    @Override
    public void delete(String[] ids){
    	
    	if(ids==null||ids.length<1)throw new ApplicationException("删除数量不能为空");
    	
    	if(ServiceManager.securityService.isOnline(ids))
    		throw new ApplicationException("存在在线用户不能删除");
    	
    	sysUserRoleDao.delete(ids);
		
		this.sysUserDao.deleteByIds(ids);
			
		
    }

    @Override
    public Pagination findForPage(Pagination page,String kw) {
    	PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page listCountry  = (Page)sysUserDao.findForPage(kw);
		page.setTotalCount((int)listCountry.getTotal());
		page.setList(listCountry.getResult());
		return page;
    }

	@Override
	public SysUser findSingleUser(String account) {
		return this.sysUserDao.findSingleUser(account);
	}

	@Override
	public void setUserRole(SysUserRole[] sysUserRoles) {
		if (null!=sysUserRoles&&sysUserRoles.length>0) {
			Integer userId=sysUserRoles[0].getUserId();
			sysUserRoleDao.delete(new String[] {userId+""});
			for (SysUserRole sysUserRole : sysUserRoles) {
				sysUserRoleDao.save(sysUserRole.getUserId()+"",sysUserRole.getRoleId()+"");
			}
		}
		
	}

	/*@Override
	public boolean findIsExist(String name, String type) {
		Map<String, String> map=new HashMap<String,String>();
		if (SysUserVo.CHECK_TYPE_ACCOUNT.equals(type)) {
			map.put("account", name);
		}else if (SysUserVo.CHECK_TYPE_EMAIL.equals(type)) {
			map.put("email", name);
		}else if (SysUserVo.CHECK_TYPE_MOBILEPHONE.equals(type)) {
			map.put("mobilePhone", name);
		}else if (SysUserVo.CHECK_TYPE_IDNUMBER.equals(type)) {
			map.put("idNumber", name);
		}
		Integer count = sysUserDao.selectCountIsExist(map);
		if (count>0) {
			return true;
		}else {
			return false;
		}
	}*/

	@Override
	public boolean updateByAccount(SysUser sysUser) {
		com.foxtail.dao.mybatis.sys.SysResDao jDao;
		if(sysUser.getPassword()!=null)
			sysUser.setPassword(MD5Util.string2MD5(sysUser.getPassword()));
		
		return sysUserDao.updateByAccount(sysUser);
	}

	@Override
	public SysUser getById(String id) {
		 return sysUserDao.getById(id);
	}

	@Override
	public void save(SysUser user) {
		
		sysUserDao.save(user);
	}

	@Override
	public void update(SysUser user) {
		sysUserDao.update(user);
		
	}

		
	
}

