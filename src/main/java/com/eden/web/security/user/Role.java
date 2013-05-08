package com.eden.web.security.user;

import java.io.Serializable;

public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String roleName ;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false ;
		else if(!(obj instanceof Role)) return false ;
		else{
			return roleName.equals(((Role)obj).getRoleName()) ;
		}
		
	}
	
	@Override
	public int hashCode() {
		return roleName.hashCode();
	}
}
