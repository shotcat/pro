package com.pro.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pro.sys.entity.Resource;
import com.pro.sys.entity.Role;
import com.pro.sys.entity.User;
import com.pro.sys.exception.ServiceException;
import com.pro.sys.mapper.AccountMapper;
import com.pro.sys.mapper.ResourceMapper;
import com.pro.sys.mapper.UserMapper;
import com.pro.sys.service.AccountService;

/**
 * @author gaoyuandong
 * 2015年4月28日 上午11:57:40
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ResourceMapper resourceMapper;
	@Override
	public List<Resource> finAllResourceList(boolean b) throws ServiceException{
		return this.resourceMapper.findAllResourceList(true);
	}

	@Override
	public User loadUserByUsername(String username) throws ServiceException{
		return this.userMapper.loadUserByUsername(username);
	}

	@Override
	public List<Role> findRoleListByUserId(Integer userId)throws ServiceException {
		return this.accountMapper.findRoleListByUserId(userId);
	}

	@Override
	public List<Resource> findResourceListByRoleId(Integer roleId) throws ServiceException{
		return this.accountMapper.findResourceListByRoleId(roleId);
	}

}
