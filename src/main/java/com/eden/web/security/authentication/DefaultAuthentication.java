package com.eden.web.security.authentication;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eden.util.ConvertUtil;
import com.eden.util.CryptoUtil;
import com.eden.web.security.SecurityContext;
import com.eden.web.security.user.UserDetail;
import com.eden.web.security.user.UserDetailService;

public class DefaultAuthentication implements Authentication{
	
	@Resource
	private SecurityContext securityContext ;
	@Resource
	private UserDetailService userDetailService ;
	
	@Override
	public boolean authenticate(ServletRequest req , ServletResponse res , FilterChain chain) {
		HttpServletRequest httpServletRequest = (HttpServletRequest)req ;
		HttpSession session = httpServletRequest.getSession(false) ;
		String token = null ;
		if(session != null){
			token = ConvertUtil.convert2Str(session.getAttribute(SESSION_TOKEN_KEY) );
		}
		if(token == null) {
			UserDetail userDetail = getUserDetailFromCookie(req) ;
			if(userDetailService.authenticate(userDetail) == 1){
				
			}
		}
		return false ;
		
	}
	
	public UserDetail getUserDetailFromCookie(ServletRequest req){
		HttpServletRequest httpRequest = (HttpServletRequest) req ;
		Cookie[] cookies = httpRequest.getCookies() ;
		for(Cookie cookie : cookies){
			if(cookie.getName().equals(securityContext.getCookieUserKey())){
				String[] userEncryStrArr = cookie.getValue().split("-") ;
				if(userEncryStrArr != null && userEncryStrArr.length == 2) {
					String userNameEncry = userEncryStrArr[0] ;
					String passwordEncry = userEncryStrArr[1] ;
					
					String userNameDecry = CryptoUtil.getInstance().decryptAES(userNameEncry) ;
					String passwordDecry = CryptoUtil.getInstance().decryptAES(passwordEncry) ;
					UserDetail userDetail = new UserDetail() ;
					userDetail.setUserName(userNameDecry) ;
					userDetail.setPassword(passwordDecry) ;
					return userDetail ;
				}
			}
		}
		return null ;
	}

	public SecurityContext getSecurityContext() {
		return securityContext;
	}

	public void setSecurityContext(SecurityContext securityContext) {
		this.securityContext = securityContext;
	}

	public UserDetailService getUserDetailService() {
		return userDetailService;
	}

	public void setUserDetailService(UserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}
	

}
