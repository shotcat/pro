package com.pro.sys.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.util.CollectionUtils;

/**
 * @author Administrator
 *
 */
public class SecurityAccessDecisionManager implements AccessDecisionManager {

	private final static Logger logger = Logger.getLogger(SecurityAccessDecisionManager.class);
	
	//任何一个成功匹配则授权成功
	private static final String  ANY_ACCESS_SUCCESS_STRATEHY ="any_strategy";
	
	private String authorizeStrategy;
	
	
	public String getAuthorizeStrategy() {
		
		return authorizeStrategy == null ? ANY_ACCESS_SUCCESS_STRATEHY:authorizeStrategy;
	}

	public void setAuthorizeStrategy(String authorizeStrategy) {
		this.authorizeStrategy = authorizeStrategy;
	}

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		logger.info("into com.pro.system.security.SecurityAccessDecisionManager.decide method");
		if (CollectionUtils.isEmpty(configAttributes)) {
			throw new AccessDeniedException(" 没有权限访问！ ");
		}
		 Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		 
		 while (iterator.hasNext()) {
	            ConfigAttribute configAttribute = iterator.next();
	            // 访问所请求资源所需要的权限
	            String needPermission = configAttribute.getAttribute();
	            System.out.println("needPermission is " + needPermission);
	            // 用户所拥有的权限authentication
	            if (authentication instanceof AnonymousAuthenticationToken) {//是游客
	            	 throw new AccessDeniedException(" 游客身份 没有权限访问！ ");
				}
	            //已经验证用户
	          Collection<SecurityGrantedAuthority> authorities =   (Collection<SecurityGrantedAuthority>) authentication.getAuthorities();
	          	//
	          for (SecurityGrantedAuthority securityGrantedAuthority : authorities) {
				
	        	Set<String> permissionList =   securityGrantedAuthority.getResources();
	        	if (!CollectionUtils.isEmpty(permissionList)) {
	        		if(permissionList.contains(needPermission)){
	        			return ;
	        		}
				}
	          }
	        }
		 throw new AccessDeniedException(" 没有权限访问！ ");

	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
