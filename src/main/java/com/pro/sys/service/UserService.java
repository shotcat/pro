package com.pro.sys.service;

import java.util.Date;
import java.util.List;

import com.pro.sys.entity.User;
import com.pro.sys.exception.ServiceException;

/**
 * @author gaoyuandong
 * 2015年4月28日 下午5:43:37
 */
public interface UserService {

	/***
	 * 分页查找用户信息
	 * @param page
	 * @param rows
	 * @return
	 */
	List<User> findUserList(int page, int rows,String userName,Date startTime,Date endTime,Integer state);

	/***
	 * 查询用户总数
	 * @return
	 */
	int findUserCount(String userName,Date startTime,Date endTime,Integer state);

	/***
	 * 检查用户昵称
	 * @param nickName
	 * @param userId
	 * @return
	 */
	boolean checkNickName(String nickName, Integer userId) throws ServiceException;

	/**
	 * 检查用户名是否存在
	 * @param userName
	 * @param userId
	 * @return
	 */
	boolean checkUserName(String userName, Integer userId)throws ServiceException;

	/***
	 * 检查手机号码是否存在
	 * @param mobile
	 * @param userId
	 * @return
	 */
	boolean checkMobile(String mobile, Integer userId)throws ServiceException;

	/***
	 * 检查邮箱是否存在
	 * @param email
	 * @param userId
	 * @return
	 */
	boolean checkEmail(String email, Integer userId)throws ServiceException;

	/***
	 * 新增用户
	 * @param user
	 */
	void addUser(User user) throws ServiceException;

	/**
	 * 更新用户
	 * @param user
	 * @throws ServiceException
	 */
	void editUser(User user) throws ServiceException;

}
