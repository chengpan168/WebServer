package com.eden.web.security.user;

import java.io.Serializable;

public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id ;
	private String name ;

	public String getName() {
		return name;
	}

	public void setName(String roleName) {
		this.name = roleName;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false ;
		else if(!(obj instanceof Role)) return false ;
		else{
			return name.equals(((Role)obj).getName()) ;
		}
		
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public int getId() {
		return id;
	}

	public void setId(int roleId) {
		this.id = roleId;
	}
}
