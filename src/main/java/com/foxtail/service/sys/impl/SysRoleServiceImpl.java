package com.foxtail.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxtail.common.page.Pagination;
import com.foxtail.core.shiro.CustomRealm;
import com.foxtail.dao.mybatis.sys.SysRoleDao;
import com.foxtail.dao.mybatis.sys.SysRoleResDao;
import com.foxtail.dao.mybatis.sys.SysUserRoleDao;
import com.foxtail.model.sys.SysRole;
import com.foxtail.model.sys.SysRoleResource;
import com.foxtail.service.sys.SysRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxr.commons.exception.ApplicationException;


@Service
public class SysRoleServiceImpl implements SysRoleService{

	private final static Logger log= Logger.getLogger(SysRoleServiceImpl.class);

	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysRoleResDao sysRoleResourceDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Autowired
	private CustomRealm customRealm;

	
	/*public SysRole selectByPrimaryKey(Integer id){
	    return sysRoleDao.selectByPrimaryKey(id);
	}

    public void insert(SysRole model) {
    	model.setCreateTime(new Date());
    	model.setCreateUserId(ShiroUser.getUserId());
    	this.sysRoleDao.insert(model); 
    }
    
    public void insertSelective(SysRole model){
    	model.setCreateTime(new Date());
    	model.setCreateUserId(ShiroUser.getUserId());
    	this.sysRoleDao.insertSelective(model); 
    }
    
    public void updateByPrimaryKeySelective(SysRole model){
    	model.setModifyTime(new Date());
    	model.setModifyUserId(ShiroUser.getUserId());
    	this.sysRoleDao.updateByPrimaryKeySelective(model); 
    }

    public void updateByPrimaryKey(SysRole model) {
    	model.setModifyTime(new Date());
    	model.setModifyUserId(ShiroUser.getUserId());
		this.sysRoleDao.updateByPrimaryKey(model);
    }*/
    
    public List<SysRole> selectList(SysRole sysRole){
    	return sysRoleDao.selectList(sysRole);
    }
    
    @Override
	public void deleteIds(String[] ids) {
		
		if(ids==null||ids.length<1)
			throw new ApplicationException("删除数量不能为空");
		
			sysRoleResourceDao.delete(ids);
			sysUserRoleDao.delete(ids);
			
			this.sysRoleDao.deleteByIds(ids);
		
	}
    

    @Override
    public Pagination findForPage(Pagination page,String kw) {
    	PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page listCountry  = (Page)sysRoleDao.findForPage(kw);
		page.setTotalCount((int)listCountry.getTotal());
		page.setList(listCountry.getResult());
		return page;
    }

	@Override
	public void setRoleResources(String roleid,String[] resids) {
	
			sysRoleResourceDao.delete(new String[] {roleid});
			for (String resid : resids) {
				SysRoleResource roleResource = new SysRoleResource();
				roleResource.setRoleId(Integer.parseInt(roleid));
				roleResource.setResourceId(Integer.parseInt(resid));
				sysRoleResourceDao.save(roleid,resid);
			}
			customRealm.clearCached();
		
	}


	@Override
	public void copyResources(String roleid, String copyRoleid) {
		
		sysRoleResourceDao.delete(new String[] {roleid});
		sysRoleDao.copyResources(roleid, copyRoleid);
		
	}

	@Override
	public SysRole getById(String id) {
		
		return sysRoleDao.getById(id);
	}

	@Override
	public void save(SysRole role) {
		sysRoleDao.save(role);
		
	}

	@Override
	public void update(SysRole role) {
		sysRoleDao.update(role);
	}

	
}

