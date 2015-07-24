package com.pro.sys.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.userdetails.AbstractCasAssertionUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.pro.sys.entity.Resource;
import com.pro.sys.entity.Role;
import com.pro.sys.entity.User;
import com.pro.sys.exception.ServiceException;
import com.pro.sys.mapper.UserMapper;
import com.pro.sys.service.AccountService;

/**
 * cas 单点 
 * @author yuandong
 *
 */
public class SecurityCasGrantedAuthorityFromAssertionAttributesUserDetailsService extends AbstractCasAssertionUserDetailsService {

	private static final String NON_EXISTENT_PASSWORD_VALUE = "NO_PASSWORD";

    private final String[] attributes;

    private boolean convertToUpperCase = false;
    
    @Autowired
	private AccountService accountService;
    @Autowired
    private UserMapper userMapper;

    public SecurityCasGrantedAuthorityFromAssertionAttributesUserDetailsService(final String[] attributes) {
        Assert.notNull(attributes, "attributes cannot be null.");
        Assert.isTrue(attributes.length > 0, "At least one attribute is required to retrieve roles from.");
        this.attributes = attributes;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected UserDetails loadUserDetails(final Assertion assertion) {
        final List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        SecurityUser securityUser = null;
		User user = null;
		user = new User();
		securityUser = new SecurityUser();
        for (final String attribute : this.attributes) {
            final Object value = assertion.getPrincipal().getAttributes().get(attribute);

           
            if (value == null) {
                continue;
            } else {
            	 if(attribute.equals("userName")) {
                 	user.setUserName(String.valueOf(value));
                 } 
            	 else if(attribute.equals("password")) {
            		 user.setPassword(String.valueOf(value));
                    
                 } 
            	 else if(attribute.equals("nickName")) {
            		 user.setPassword(String.valueOf(value));
            		
            	 } 
            	 else if(attribute.equals("email")) {
            		 user.setNickName(String.valueOf(value));
            		
            	 } 
            	 else if(attribute.equals("mobile")) {
            		 user.setMobile(String.valueOf(value));
            		 
            	 }  
            }
        }
        
        
        try {
			User selfUser = accountService.loadUserByUsername(user.getUserName());
			if(selfUser == null) {//如果不是当前应用的用户
				userMapper.addUser(user);
					
				
			} else {
				user.setUserId(selfUser.getUserId());
			}
			
			securityUser.setUsername(user.getUserName());
			securityUser.setPassword(user.getPassword());
			securityUser.setCredentialsNonExpired(true);
			securityUser.setAccountNonExpired(true);
			securityUser.setAccountNonLocked(true);
			securityUser.setEnabled(true);
			securityUser.setUser(user);
			securityUser.setNickName(user.getNickName());
			securityUser.setEmail(user.getEmail());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
		
		return securityUser;
    }

    /**
     * Converts the returned attribute values to uppercase values.
     *
     * @param convertToUpperCase true if it should convert, false otherwise.
     */
    public void setConvertToUpperCase(final boolean convertToUpperCase) {
        this.convertToUpperCase = convertToUpperCase;
    }
}
