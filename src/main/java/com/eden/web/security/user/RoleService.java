package com.eden.web.security.user;

import java.util.List;


public interface RoleService {
	public List<Role> getRoleByPath(String path) ;
	public List<Role> getRoleByUserName(String userName) ;
}
