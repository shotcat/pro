package com.pro.sys.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pro.sys.entity.Resource;
import com.pro.sys.entity.Role;
import com.pro.sys.exception.ServiceException;
import com.pro.sys.mapper.AccountMapper;
import com.pro.sys.mapper.ResourceMapper;
import com.pro.sys.po.SystemMenu;
import com.pro.sys.po.Tree;
import com.pro.sys.service.ResourceService;

/**
 * @author gaoyuandong
 * 2015年4月28日 下午1:48:02
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private ResourceMapper resourceMapper;
	
	
	public Set<SystemMenu> findAllMenusByUserId(Integer userId) {
		List<Resource> resources = accountMapper.findModuleListUserId(userId);
		Set<SystemMenu> menus = new HashSet<SystemMenu>();
		if(!CollectionUtils.isEmpty(resources)) {
			for (Resource r : resources) {
				SystemMenu menu = new SystemMenu();
				BeanUtils.copyProperties(r, menu);
				List<Resource> childrenResource = accountMapper.findMenuByModuleIdAndUserId(r.getResourceId(), userId);
				menu.setChildrenMenus(this.conventSystemMeun(childrenResource));
				menus.add(menu);
			}
		}
		return menus;
	}

	private Set<SystemMenu> conventSystemMeun(List<Resource> resources) {
		Set<SystemMenu> menus = new HashSet<SystemMenu>();
		if(!CollectionUtils.isEmpty(resources)) {
			for(Resource r:resources) {
				SystemMenu menu = new SystemMenu();
				BeanUtils.copyProperties(r, menu);
				menus.add(menu);
			}
		}
		
		return menus;
	}

	@Override
	public List<Integer> findDefaultResourceIdListByRoleId(Integer roleId) throws ServiceException{
		
		return this.resourceMapper.findDefaultResourceIdListByRoleId(roleId);
	}

	@Override
	public List<Role> findAllResource() throws ServiceException {
		return this.resourceMapper.findAllResource();
	}

	@Override
	public void settingResourceByRoleId(Integer roleId, String resourceIdStr)
			throws ServiceException {
		List<Integer> resourceIdList = new ArrayList<Integer>();
		if(StringUtils.isNotBlank(resourceIdStr)) {
			String[] strs = resourceIdStr.split(",");
			for (int i = 0; i < strs.length; i++) {
				resourceIdList.add(Integer.valueOf(strs[i]));
			}
		}
		
		//清空所有关联信息
		this.resourceMapper.deleteRoleByUserId(roleId);
		
		//添加用户与角色关联关系
		if(!CollectionUtils.isEmpty(resourceIdList)) {
			this.resourceMapper.createUserRoleByUserId(roleId, resourceIdList);
		}
	}

	@Override
	public List<Tree> findMenuList() {
		List<Resource> resourceList = this.resourceMapper.findResourceListByParentId(null);
		if(!CollectionUtils.isEmpty(resourceList)){
			List<Tree> tList = new ArrayList<Tree>();
			for (Resource resource : resourceList) {
				Tree tree = new Tree();
				tree.setId(resource.getResourceId());
				tree.setText(resource.getResourceName());
				List<Tree> children  = new ArrayList<Tree>();
				List<Resource> childrenResource = this.resourceMapper.findResourceListByParentId(resource.getResourceId());
				if(!CollectionUtils.isEmpty(childrenResource)) {
					
					for (Resource rs : childrenResource) {
						Tree t = new Tree();
						t.setId(rs.getResourceId());
						t.setText(rs.getResourceName());
						children.add(t);
					}
				}
				tree.setChildren(children);
				tList.add(tree);
			}
			return tList;
		}
		return null;
	}
	
	/***
	 * 
	 */
	public void addResource(Resource resource) throws ServiceException{
		this.resourceMapper.addResource(resource);
	}

	@Override
	public void editResource(Resource resource)throws ServiceException {
		this.resourceMapper.editResource(resource);
	}

	@Override
	public Resource findResourceById(Integer id) {
		return this.resourceMapper.findResourceById(id);
	}
}
