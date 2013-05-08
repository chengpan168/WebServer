package com.eden.web.security;

import java.util.List;

import com.eden.web.security.user.Role;

public interface RoleService {
	public List<Role> getRoleByPath(String path) ;
	public List<Role> getRoleByUserName(String userName) ;
}
