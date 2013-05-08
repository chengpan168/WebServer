package com.eden.web.security;

import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eden.util.ConvertUtil;
import com.eden.web.security.user.Role;
import com.eden.web.security.user.UserDetail;
import com.eden.web.security.user.UserDetailService;

public class DefaultAuthentication implements Authentication{
	
	private boolean alwaysUseDefaultTarget = false ;
	private String defaultTargetUrl ;
	private String loginUrl = "/login" ;
	private String loginSuccessUrl ;
	private String loginFailUrl ;
	private String logoutUrl = "/logout" ;
	private String logoutSuccessUrl  ;
	//认证失败路转的url
	private String authenticateFailUrl ;
	private boolean isRemeber = true ;
	
	private UserDetailService userDetailService ;
	private RoleService roleService ;
	private ResourceService resourceService ;
	
	@Override
	public boolean authenticate(ServletRequest req , ServletResponse res , FilterChain chain) {
		HttpServletRequest httpServletRequest = (HttpServletRequest)req ;
		HttpSession session = httpServletRequest.getSession(false) ;
		String token = null ;
		if(session != null){
			token = ConvertUtil.convert(session.getAttribute(SESSION_TOKEN_KEY) );
		}
		if(token == null) {
			getUserDetailFromCookie(req) ;
		}
		String userName = null ;
		if(userName == null) return false ;
		
		List<Role> roleUser = roleService.getRoleByUserName(userName) ;
		List<Role> rolePath = roleService.getRoleByPath(httpServletRequest.getRequestURI()) ;
		
		for(Role role : roleUser){
			if(rolePath.contains(role)) return true ;
		}
		
		return false;
	}
	
	public UserDetail getUserDetailFromCookie(ServletRequest req){
		HttpServletRequest httpRequest = (HttpServletRequest) req ;
		Cookie[] cookies = httpRequest.getCookies() ;
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("")){
				
			}
		}
		return null ;
	}
	
	
	public boolean isAlwaysUseDefaultTarget() {
		return alwaysUseDefaultTarget;
	}
	public void setAlwaysUseDefaultTarget(boolean alwaysUseDefaultTarget) {
		this.alwaysUseDefaultTarget = alwaysUseDefaultTarget;
	}
	public String getDefaultTargetUrl() {
		return defaultTargetUrl;
	}
	public void setDefaultTargetUrl(String defaultTargetUrl) {
		this.defaultTargetUrl = defaultTargetUrl;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getLoginSuccessUrl() {
		return loginSuccessUrl;
	}
	public void setLoginSuccessUrl(String loginSuccessUrl) {
		this.loginSuccessUrl = loginSuccessUrl;
	}
	public String getLoginFailUrl() {
		return loginFailUrl;
	}
	public void setLoginFailUrl(String loginFailUrl) {
		this.loginFailUrl = loginFailUrl;
	}
	public String getLogoutUrl() {
		return logoutUrl;
	}
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}
	public String getLogoutSuccessUrl() {
		return logoutSuccessUrl;
	}
	public void setLogoutSuccessUrl(String logoutSuccessUrl) {
		this.logoutSuccessUrl = logoutSuccessUrl;
	}

}
