package com.pro.sys.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import com.pro.sys.entity.Resource;
import com.pro.sys.entity.Role;
import com.pro.sys.entity.User;
import com.pro.sys.exception.ServiceException;
import com.pro.sys.service.AccountService;

/***
 * 用户认证服务类
 * @author guaoyd
 *
 */
@Deprecated 
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	private AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		SecurityUser securityUser = null;
		User user = null;
		try {
			user = accountService.loadUserByUsername(username);
		} catch (Exception e) {
				throw new UsernameNotFoundException(" ERROR not found user!");
		}
		if (user == null) {
			throw new UsernameNotFoundException("not found user!");
		}
		if (user.getState() == 1) {
			 throw new RuntimeException("User hasn't been actived!");
		}
		
		securityUser = new SecurityUser();
		securityUser.setUsername(user.getUserName());
		securityUser.setPassword(user.getPassword());
		securityUser.setCredentialsNonExpired(true);
		securityUser.setAccountNonExpired(true);
		securityUser.setAccountNonLocked(true);
		securityUser.setEnabled(true);
		securityUser.setUser(user);
		securityUser.setNickName(user.getNickName());
		securityUser.setEmail(user.getEmail());
		
		try {
			List<Role> roles = this.accountService.findRoleListByUserId(user.getUserId());
			 Set<SecurityGrantedAuthority> authorities = new HashSet<SecurityGrantedAuthority>();
			if (!CollectionUtils.isEmpty(roles)) {
				
				for (Role role : roles) {
					SecurityGrantedAuthority authority = new SecurityGrantedAuthority(role.getRoleName());
					Set<String> permissionList = new HashSet<String>();
					List<Resource> resourceList = this.accountService.findResourceListByRoleId(role.getRoleId());
					if (!CollectionUtils.isEmpty(resourceList)) {
						for (Resource resource : resourceList) {
							String url = resource.getUrl();
							if (resource.getUrl().lastIndexOf(".") != -1) {
								url = resource.getUrl().substring(0, resource.getUrl().lastIndexOf("."));
							}
						
							permissionList.add(url);
						}
						
					}
					authority.setResources(permissionList);
					authorities.add(authority);
				}
			}
			
			securityUser.setAuthorities(authorities);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return securityUser;
	}
	
}
