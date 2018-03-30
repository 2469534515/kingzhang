package com.foxtail.dao.mybatis.sys;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.foxtail.model.sys.SysResource;

public interface SysResDao {	
	
	SysResource getById(String id);
	
	void save(@Param("mo")SysResource Resource);
	
	void update(SysResource resource);
    
    public void delete(@Param("ids")String[] ids);
    
    List<SysResource> findAll();
    
    public List<SysResource> findAllByUid(@Param("uid")String userId);
    
    
    
    
    
    
    public void deleteByParentId(String parent_id);
    
    public List<SysResource> selectList(SysResource sysResource);
  
    List<SysResource> findForPage(@Param("kw")String kw,@Param("pid")String pid);
    
    public List<SysResource> selectListByParentId(String parentId);
    
    
    public List<SysResource> findAuthorizationAll(String roleId);
   
    
    
    public Integer selectCountByParentId(String parentId);
    
    List<SysResource> queryUriName(@Param("uri") String uri);
    
    
    public Integer selectResourceReference(String resourceId);


}
