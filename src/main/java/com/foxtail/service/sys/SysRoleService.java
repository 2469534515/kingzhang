package com.foxtail.service.sys;

import java.util.List;

import com.foxtail.common.base.BaseMybatisService;
import com.foxtail.common.page.Pagination;
import com.foxtail.model.sys.SysRole;
import com.foxtail.model.sys.SysRoleResource;

public interface SysRoleService/* extends BaseMybatisService<SysRole,Integer>*/ {	
    
    
	SysRole getById(String id);
	
     public void deleteIds(String[] ids);
    
     void save(SysRole role);
     
     void update(SysRole role);
    
    public Pagination findForPage(Pagination page,String kw);
   
    /**
     * 授权
    * Description:    
     */
    public void setRoleResources(String roleid,String[] resids);
    
    
    public List<SysRole> selectList(SysRole sysRole);
   /* *//**
     * 
    * Description:根据用户id查找角色    
    * @Title: findRoleTypesByUserId  
     *//*
    public List<String> findRoleTypesByUserId(Integer userId);*/
    
    
    void copyResources(String roleid,String copyRoleid);
    
    
    
}

