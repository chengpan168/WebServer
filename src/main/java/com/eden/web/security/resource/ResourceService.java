package com.eden.web.security.resource;

import java.util.List;

import com.eden.web.security.user.Role;

public interface ResourceService {
	public List<String> getResourceByUserName(String userName) ;
	
	public List<Role> getRoleByUrl(String url) ;
}
