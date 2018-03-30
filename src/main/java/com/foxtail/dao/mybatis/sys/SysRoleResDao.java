package com.foxtail.dao.mybatis.sys;


import org.apache.ibatis.annotations.Param;

public interface SysRoleResDao {	
    
   void save(@Param("roleid")String roleid,@Param("resid")String resid);
   
   void delete(@Param("roleids")String[] roleids);
   
   void deleteByResid(@Param("resids")String[] resids);
}
