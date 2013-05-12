package com.eden.web.security.user;

import java.io.Serializable;
import java.util.List;

public class UserDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id ;
	private String name;
	private String password;
	private boolean isEnable;
	private boolean isExpire;
	private List<Role> roles ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserDetail() {}
	public UserDetail(String userName, String password2) {
		this.name = userName ;
		this.password = password2 ;
	}
	public String getName() {
		return name;
	}
	public void setName(String userName) {
		this.name = userName;
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
		return "UserDetail [userName=" + name + ", password=" + password
				+ ", isEnable=" + isEnable + ", isExpire=" + isExpire
				+ ", roles=" + roles + "]";
	}

}
