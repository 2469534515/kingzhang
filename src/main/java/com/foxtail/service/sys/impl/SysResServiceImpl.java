package com.foxtail.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxtail.common.page.Pagination;
import com.foxtail.common.util.PublicUtil;
import com.foxtail.core.shiro.Myprem;
import com.foxtail.core.shiro.PermManager;
import com.foxtail.core.shiro.ShiroUser;
import com.foxtail.dao.mybatis.sys.SysResDao;
import com.foxtail.dao.mybatis.sys.SysRoleResDao;
import com.foxtail.model.sys.SysResource;
import com.foxtail.service.sys.SysResService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxr.commons.exception.ApplicationException;

@Service
public class SysResServiceImpl implements SysResService{

	private final static Logger log= Logger.getLogger(SysResServiceImpl.class);

	@Autowired
	private SysResDao sysResourceDao;
	
	@Autowired
	SysRoleResDao sysRoleResDao;
	
	List<SysResource> sysResources;
	
	boolean ismodify = true;

	
/*	public SysResource selectByPrimaryKey(Integer id){
	    return sysResourceDao.selectByPrimaryKey(id);
	}

    public void deleteByPrimaryKey(Integer id){
    	this.sysResourceDao.deleteByPrimaryKey(id); 
    	ismodify = true;
    }*/

//    public void insert(SysResource model) {
//    	model.setCreateTime(new Date());
//    	model.setCreateUserId(ShiroUser.getUserId());
//    	this.sysResourceDao.save(model); 
//    	ismodify = true;
//    }
//    
//    public void insertSelective(SysResource model){
//    	model.setCreateTime(new Date());
//    	model.setCreateUserId(ShiroUser.getUserId());
//    	this.sysResourceDao.save(model); 
//    	ismodify = true;
//    }
//    
//    public void updateByPrimaryKeySelective(SysResource model){
//    	model.setModifyTime(new Date());
//    	model.setModifyUserId(ShiroUser.getUserId());
//    	this.sysResourceDao.update(model); 
//    	ismodify = true;
//    }

/*    public void updateByPrimaryKey(SysResource model) {
    	model.setModifyTime(new Date());
    	model.setModifyUserId(ShiroUser.getUserId());
		this.sysResourceDao.update(model);
		ismodify = true;
    }*/
    
    public List<SysResource> selectList(SysResource sysResource){
    	return sysResourceDao.selectList(sysResource);
    }
    
    public List<SysResource> findAll() {
    	if(sysResources==null||ismodify) {
    	
    		sysResources = sysResourceDao.findAll();
    		
    		onUpdate();
    		
        	ismodify = false;
    	} else 	System.out.println("抓取缓存");
    	
		return sysResources;
    }
    
    
    public void onUpdate() {
    	PermManager.getAllPerms().clear();
    	for (SysResource res : sysResources) {
    		if(StringUtils.isBlank(res.getPermission()))continue;
    		
			Myprem myprem = Myprem.getMyprem(res.getPermission());
			List<Myprem> myprems = PermManager.getAllPerms().get(myprem.getUrl());
			if(myprems==null) { myprems = new ArrayList<Myprem>();PermManager.getAllPerms().put(myprem.getUrl(), myprems);}
			myprems.add(myprem);
    	
    	}
    }
    
    

  /*  public void deleteAll() {
		this.sysResourceDao.deleteAll();
		ismodify = true;
    }*/

    @Override
    public void delete(String[] ids){
    	String [] idArr=ids;
    	
    	//所有需要删除的ID
    	Set<String> idd = new HashSet<>();
    	
    	if (idArr.length<1) throw new ApplicationException("ids="+ids);
			
			for (int i = 0; i < idArr.length; i++) {
				String id = idArr[i];
				idd.add(id);
				queryChild(idd, id);
				
				
			}
			
			
			List l = new ArrayList<>(idd);
			String[] tids = new String[l.size()];
			l.toArray(tids);
			sysResourceDao.delete(tids);
			//删除关联表
			sysRoleResDao.deleteByResid(tids);
			
		ismodify = true;
    }
    
    
    private void queryChild(Set<String> ids,String pid) {
		
    	List<SysResource> list = sysResourceDao.selectListByParentId(pid);
    	
    	if(list==null||list.size()<1)return;
    	
    	for (SysResource sysResource : list) {
    		String id = sysResource.getId();
    		ids.add(id);
			queryChild(ids, id);
			
		}

	}
    
    public String queryUriName(String uri) {
    	List<SysResource> list = sysResourceDao.queryUriName(uri);
    	if(list==null||list.size()<1)return "";
    	
    	if(list.size()>1)System.out.println("警告：多个资源uri相同");
    	
		return list.get(0).getName();

	}
    

    @Override
    public Pagination findForPage(Pagination page,String kw,String pid) {
    	PageHelper.startPage(page.getPageNo(), page.getPageSize());
		Page listCountry  = (Page)this.sysResourceDao.findForPage(kw,pid);
		page.setTotalCount((int)listCountry.getTotal());
		page.setList(listCountry.getResult());
		return page;
	  
    } 
    
    @Override
	public List<SysResource> selectListByParentId(String parentId) {
		
		return this.sysResourceDao.selectListByParentId(parentId);
	}

/*	@Override
	public void saveAndCreateRes(SysResource po, boolean createButton) {
		po.setCreateTime(new Date());
		po.setCreateUserId(ShiroUser.getUserId());
    	this.sysResourceDao.save(po); 
    	if(createButton){
    		this.createButtonRes(po);
    	}
    	ismodify = true;
	}*/
	

	@Override
	public List<SysResource> findAuthorizationAll(String roleId) {
		return sysResourceDao.findAuthorizationAll(roleId);
	}

	@Override
	public List<SysResource> findAllByUserId(String userId) {
		//findAll();
		return this.sysResourceDao.findAllByUid(userId);
	}

	@Override
	public Integer selectResourceReference(String resourceId) {
		return this.sysResourceDao.selectResourceReference(resourceId);
	}

	@Override
	public SysResource getById(String id) {
		  return sysResourceDao.getById(id);
	}

	@Override
	public void update(SysResource resource) {
		sysResourceDao.update(resource);
	}
	
	@Override
	public void save(SysResource resource) {
		sysResourceDao.save(resource);
	}



	
}

