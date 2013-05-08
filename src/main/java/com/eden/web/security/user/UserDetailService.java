package com.eden.web.security.user;

public interface UserDetailService {
	public UserDetail getUserDetail(String userName) ;
	
	public boolean hasUserDetail(UserDetail userDetail) ;
}
