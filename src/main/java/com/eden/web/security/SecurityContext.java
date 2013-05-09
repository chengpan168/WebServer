package com.eden.web.security;

public class SecurityContext {
	/*
	 * 用于保存用户信息的cookie 中的  name
	 */
	private String cookieUserKey = "user" ;
	
	private boolean alwaysUseDefaultTarget = false ;
	private String defaultTargetUrl = "/index.jsp" ;
	private String loginUrl = "/action/login" ;
	private String loginSuccessUrl ;
	private String loginFailUrl = "/login.jsp";
	private String logoutUrl = "/action/logout" ;
	private String logoutSuccessUrl = "/action/loginPage"  ;
	private String loginPageUrl = "/login.jsp" ;
	//认证失败路转的url
	private String authenticateFailUrl ;
	//is remeber user in cookie
	private boolean isRemeber = true ;
	//the time of the cookie which save user info
	private int remeberTime = 15 * 24 * 60 * 60;
	
	private String sessionTokenKey = "session_user_token" ;
	
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
	public String getAuthenticateFailUrl() {
		return authenticateFailUrl;
	}
	public void setAuthenticateFailUrl(String authenticateFailUrl) {
		this.authenticateFailUrl = authenticateFailUrl;
	}
	public boolean isRemeber() {
		return isRemeber;
	}
	public void setRemeber(boolean isRemeber) {
		this.isRemeber = isRemeber;
	}
	public String getCookieUserKey() {
		return cookieUserKey;
	}
	public void setCookieUserKey(String cookieUserKey) {
		this.cookieUserKey = cookieUserKey;
	}
	public int getRemeberTime() {
		return remeberTime;
	}
	public void setRemeberTime(int remeberTime) {
		this.remeberTime = remeberTime;
	}
	public String getSessionTokenKey() {
		return sessionTokenKey;
	}
	public void setSessionTokenKey(String sessionTokenKey) {
		this.sessionTokenKey = sessionTokenKey;
	}
	public String getLoginPageUrl() {
		return loginPageUrl;
	}
	public void setLoginPageUrl(String loginPageUrl) {
		this.loginPageUrl = loginPageUrl;
	}
	
	
}
