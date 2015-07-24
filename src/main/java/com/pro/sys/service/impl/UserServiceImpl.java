package com.pro.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jasig.cas.thrift.client.UserThriftClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pro.sys.entity.User;
import com.pro.sys.exception.ServiceException;
import com.pro.sys.mapper.UserMapper;
import com.pro.sys.service.UserService;

/**
 * @author gaoyuandong
 * 2015年4月28日 下午5:44:01
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserThriftClient userThriftClient;

	@Override
	public List<User> findUserList(int page, int rows,String userName,Date startTime,Date endTime,Integer state) {
		return this.userMapper.findUserList((page-1)*rows, rows,userName,startTime,endTime,state);
	}

	/***
	 * 查询用户总数
	 */
	public int findUserCount(String userName,Date startTime,Date endTime,Integer state) {
		return this.userMapper.findUserCount(userName,startTime,endTime,state);
	}

	@Override
	public boolean checkNickName(String nickName, Integer userId)  throws ServiceException{
		Integer id = this.userMapper.checkNickName(nickName, userId);
		return id == null?false:true;
	}

	@Override
	public boolean checkUserName(String userName, Integer userId) throws ServiceException {
		Integer id = this.userMapper.checkUserName(userName, userId);
		return id == null?false:true;
	}

	@Override
	public boolean checkMobile(String mobile, Integer userId) throws ServiceException {
		Integer id = this.userMapper.checkMobile(mobile, userId);
		return id == null?false:true;
	}

	@Override
	public boolean checkEmail(String email, Integer userId) throws ServiceException {
		Integer id = this.userMapper.checkEmail(email, userId);
		return id == null?false:true;
	}

	@Override
	public void addUser(User user) throws ServiceException {
		org.jasig.cas.thrift.server.User target = new org.jasig.cas.thrift.server.User();
		BeanUtils.copyProperties(user, target);
		boolean sec = userThriftClient.addUser(target);
		if(sec == false)  throw new ServiceException("保存失败请重试");
		this.userMapper.addUser(user);
	}
	
	/***
	 * 更新用户
	 */
	public void editUser(User user) throws ServiceException {
		org.jasig.cas.thrift.server.User target = new org.jasig.cas.thrift.server.User();
		BeanUtils.copyProperties(user, target);
		boolean sec = userThriftClient.addUser(target);
		if(sec == false)  throw new ServiceException("更新失败，请重试!");
		this.userMapper.editUser(user);
	}
}
