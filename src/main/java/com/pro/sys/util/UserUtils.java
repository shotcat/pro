package com.pro.sys.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.pro.sys.security.SecurityUser;

public class UserUtils {

	/***
	 * 获取当前用户
	 * @return
	 */
	public static SecurityUser findCurrentUser() {
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(object == null || !(object instanceof SecurityUser)) {
			return null;
		}
		return (SecurityUser) object;
	}
}
