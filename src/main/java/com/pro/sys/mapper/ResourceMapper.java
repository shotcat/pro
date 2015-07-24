package com.pro.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pro.sys.entity.Resource;
import com.pro.sys.entity.Role;
import com.pro.sys.exception.ServiceException;

public interface ResourceMapper {
	
	/***
	 * 获取所有的权限资源
	 * @param isEnable
	 * @return
	 */
	List<Resource> findAllResourceList(@Param("isEnable")boolean isEnable);

	/**
	 * 根据角色 id查询当前角色所拥有的权限资源
	 * @param roleId
	 * @return
	 */
	List<Integer> findDefaultResourceIdListByRoleId(@Param("roleId")Integer roleId);
	
	/**
	 * 获取所有权限资源
	 * @return
	 */
	List<Role> findAllResource();

	/**
	 * 删除角色与权限资源关联关系
	 * @param roleId
	 */
	void deleteRoleByUserId(@Param("roleId")Integer roleId);
	
	/**
	 * 创建角色与权限资源关联关系
	 * @param roleId
	 * @param resourceIdList
	 */
	void createUserRoleByUserId(@Param("roleId")Integer roleId, @Param("resourceIdList")List<Integer> resourceIdList);

	/**
	 * 
	 * @param object
	 * @return
	 */
	List<Resource> findResourceListByParentId(@Param("parentId")Integer parentId);

	/***
	 * 添加权限资源
	 * @param resource
	 */
	void addResource(Resource resource);

	/***
	 * 更新权限资源
	 * @param resource
	 */
	void editResource(Resource resource);

	/***
	 * 根据主键获取权限资源
	 * @param id
	 * @return
	 */
	Resource findResourceById(Integer id);
  
}