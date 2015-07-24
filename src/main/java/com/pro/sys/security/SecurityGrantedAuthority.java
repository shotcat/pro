package com.pro.sys.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

public class SecurityGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 5702629738310622786L;
	private final String role;
	private Set<String> resources = new HashSet<String>();
	 public SecurityGrantedAuthority(String role) {
	        Assert.hasText(role, "A granted authority textual representation is required");
	        this.role = role;
	    }

	    public String getAuthority() {
	        return role;
	    }

	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }

	        if (obj instanceof SecurityGrantedAuthority) {
	        	 role.equals(((SecurityGrantedAuthority) obj).role);
	        }
	        return false;
	    }

	    
	    public Set<String> getResources() {
			return resources;
		}

		public void setResources(Set<String> resources) {
			this.resources = resources;
		}

		public int hashCode() {
	        return this.role.hashCode() + resources.hashCode();
	    }

	    public String toString() {
	        return this.role + " : " + this.resources;
	    }
}
