package com.eden.web.security.authentication;

import com.eden.web.security.user.UserDetail;

/**
 * 	状态码 
 * 	1：有效
 *  2:不存在用户名
 *  3：密码错误
 *  4：用户过期
 *  5：账户禁用
 */
public class Authority { 
	private int status = 1 ;
	private UserDetail userDetail ;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public UserDetail getUserDetail() {
		return userDetail;
	}
	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
	
	
}
