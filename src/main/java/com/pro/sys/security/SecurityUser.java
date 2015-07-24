package com.pro.sys.security;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.pro.sys.entity.User;

public class SecurityUser implements UserDetails {
	
	private static final long serialVersionUID = 4877602481235005637L;
	private String password;
    private String username;
	private User user;
	private  Set<SecurityGrantedAuthority> authorities;
	private  boolean accountNonExpired;
	private  boolean accountNonLocked;
	private  boolean credentialsNonExpired;
	private  boolean enabled;
	private  String nickName;
	private String email;
	@Override
	public Collection<? extends SecurityGrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}
	
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setAuthorities(Set<SecurityGrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
