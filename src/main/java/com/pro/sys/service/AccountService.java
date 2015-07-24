package com.pro.sys.service;

import java.util.List;

import com.pro.sys.entity.Resource;
import com.pro.sys.entity.Role;
import com.pro.sys.entity.User;
import com.pro.sys.exception.ServiceException;

public interface AccountService {

	List<Resource> finAllResourceList(boolean b) throws ServiceException;

	User loadUserByUsername(String username) throws ServiceException;

	List<Role> findRoleListByUserId(Integer userId) throws ServiceException;

	List<Resource> findResourceListByRoleId(Integer roleId)throws ServiceException;

}
