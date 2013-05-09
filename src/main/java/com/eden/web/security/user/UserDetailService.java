package com.eden.web.security.user;

public interface UserDetailService {
	public UserDetail getUserDetail(String userName) ;
	

	/**
	 * 
	 * @param userName 用户名
	 * @param password 密码
	 * @return 
	 *  状态码 
	 * 	1：有效
	 *  2:不存在用户名
	 *  3：密码错误
	 *  4：用户过期
	 *  5：账户禁用
	 */
	public int authenticate(String userName, String password);

	/**
	 * 
	 * @param userName 用户名
	 * @param password 密码
	 * @return 
	 * 	状态码 
	 * 	1：有效
	 *  2:不存在用户名
	 *  3：密码错误
	 *  4：用户过期
	 *  5：账户禁用
	 */
	public int authenticate(UserDetail userDetail);

}
