package com.foxtail.service.sys;

import java.util.List;

import com.foxtail.common.base.BaseMybatisService;
import com.foxtail.common.page.Pagination;
import com.foxtail.model.sys.SysResource;

public interface SysResService {	
    
	SysResource getById(String id);
	
	public void delete(String[] ids);
	
	void save(SysResource resource);
	
	void update(SysResource resource);
	
    public List<SysResource> selectList(SysResource sysResource);
    
    public Pagination findForPage(Pagination page,String kw,String pid);
    
    public List<SysResource> selectListByParentId(String parentId);
    
    
    
 	//public void saveAndCreateRes(SysResource po,boolean createButton);
 	//生成按钮资源
 	//public void createButtonRes (SysResource po);
 	
 	
 	public List<SysResource> findAuthorizationAll(String roleId);
 	
	public List<SysResource> findAllByUserId(String userId);
	
	 public String queryUriName(String uri);
	
	public Integer selectResourceReference(String resourceId);
 	
 	
}

