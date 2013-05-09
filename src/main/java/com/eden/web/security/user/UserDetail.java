package com.eden.web.security.user;

import java.io.Serializable;
import java.util.List;

public class UserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private boolean isEnable;
	private boolean isExpire;
	private List<Role> roles ;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	public boolean isExpire() {
		return isExpire;
	}
	public void setExpire(boolean isExpire) {
		this.isExpire = isExpire;
	}
	@Override
	public String toString() {
		return "UserDetail [userName=" + userName + ", password=" + password
				+ ", isEnable=" + isEnable + ", isExpire=" + isExpire
				+ ", roles=" + roles + "]";
	}

}
