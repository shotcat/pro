package com.pro.sys.job;

import java.util.List;

import org.jasig.cas.thrift.client.UserThriftClient;
import org.jasig.cas.thrift.server.User;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pro.sys.mapper.UserMapper;
import com.pro.sys.web.context.SpringContextHolder;

public class UserJob {

	//每次查询行数
	private static final int ROW_COUNT = 50;
	private UserMapper userMapper;
	private UserThriftClient userThriftClient;
	
	/***
	 * 同步用户
	 */
	public void  syncUser() {
		if (userMapper == null) {
			userMapper = SpringContextHolder.getBean(UserMapper.class);
		}
		if (userThriftClient == null) {
			userThriftClient = SpringContextHolder.getBean(UserThriftClient.class);
		}
		System.err.println("OJ" + userThriftClient);
		if(userThriftClient != null) {
			int userCount = userThriftClient.findUserCount();
			int page = userCount % ROW_COUNT == 0 ?userCount / ROW_COUNT : userCount/ROW_COUNT +1;
			for (int i = 1; i <= page; i++) {
				int startIndex = (i - 1) * ROW_COUNT;
				List<User> users = userThriftClient.findUserList(startIndex, ROW_COUNT);
				
				if(CollectionUtils.isEmpty(users)) continue;
				for (User user : users) {
					com.pro.sys.entity.User localUser = new com.pro.sys.entity.User();
					BeanUtils.copyProperties(user, localUser);
					com.pro.sys.entity.User userTemp = userMapper.loadUserByUsername(user.getUserName());
					if(userTemp != null) {//更新用户信息
						localUser.setUserId(userTemp.getUserId());
						userMapper.editUser(localUser);
					} else {//插入用户信息
						userMapper.addUser(localUser);
					}
					
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		int page = 102 % ROW_COUNT == 0 ? 102 / ROW_COUNT : 102 / ROW_COUNT +1;
		
		System.err.println(page);
	}
}
