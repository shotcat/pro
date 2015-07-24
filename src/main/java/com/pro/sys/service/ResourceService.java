package com.pro.sys.service;

import java.util.List;
import java.util.Set;

import com.pro.sys.entity.Resource;
import com.pro.sys.entity.Role;
import com.pro.sys.exception.ServiceException;
import com.pro.sys.po.SystemMenu;
import com.pro.sys.po.Tree;

/**
 * @author gaoyuandong
 * 2015年4月28日 下午1:47:37
 */
public interface ResourceService {
	
	/***
	 * 获取系统菜单
	 * @param userId
	 * @return
	 */
	Set<SystemMenu> findAllMenusByUserId(Integer userId);
	/**
	 * 根据角色id查询当前角色所拥有的权限资源
	 * @param roleId
	 * @return
	 */
	List<Integer> findDefaultResourceIdListByRoleId(Integer roleId)throws ServiceException;
	/***
	 * 查询所有权限资源
	 * @return
	 * @throws ServiceException
	 */
	List<Role> findAllResource() throws ServiceException;
	/***
	 * 设置权限资源根据角色id
	 * @param roleId
	 * @param resourceIdStr
	 */
	void settingResourceByRoleId(Integer roleId, String resourceIdStr) throws ServiceException;
	/***
	 * 查询系统模块和菜单
	 * @return
	 */
	List<Tree> findMenuList();
	/***
	 * 添加权限资源
	 * @param resource
	 */
	void addResource(Resource resource)throws ServiceException;
	/***
	 * 更新权限资源
	 * @param resource
	 */
	void editResource(Resource resource)throws ServiceException;
	/***
	 * 根据主键 获取对应的权限资源
	 * @param parentId
	 * @return
	 */
	Resource findResourceById(Integer parentId);

}
