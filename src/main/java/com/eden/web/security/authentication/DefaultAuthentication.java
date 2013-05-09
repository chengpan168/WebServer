package com.eden.web.security.authentication;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ArrayUtils;

import com.eden.util.ConvertUtil;
import com.eden.util.CryptoUtil;
import com.eden.web.security.SecurityContext;
import com.eden.web.security.user.UserDetail;
import com.eden.web.security.user.UserDetailService;

public class DefaultAuthentication implements Authentication {

	@Resource
	private SecurityContext securityContext;
	@Resource
	private UserDetailService userDetailService;

	private String[] exclude ;
	
	public String[] getExclude() {
		return exclude;
	}

	public void setExclude(String[] exclude) {
		this.exclude = exclude;
	}

	@Override
	public boolean authenticate(ServletRequest req, ServletResponse res,
			FilterChain chain) {
		boolean isPass = false ;
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpServletResponse httpResponse = (HttpServletResponse) res ;
		
		String requestUri = httpRequest.getRequestURI(); 
		try {
			//not filter login logout url
			if (securityContext.getLoginUrl().equals(requestUri)
					|| securityContext.getLogoutUrl().equals(requestUri) 
					|| ArrayUtils.contains(exclude, requestUri) ) {
				isPass =  true ;
			}  else {
				HttpSession session = httpRequest.getSession(false);
				UserDetail userDetail = null ;
				if(session != null){
					userDetail = (UserDetail)session.getAttribute(securityContext.getSessionUserKey()) ;
				}
				
				//if no user in session , try to get it from cookie and check it
				if(userDetail == null) {
					userDetail = getUserDetailFromCookie(req);
					
					//if not login , redirect to the authenticate fail url 
					if (userDetail == null || userDetailService.authenticate(userDetail) != 1) {
						isPass = false ;
						httpResponse.sendRedirect(securityContext.getAuthenticateFailUrl()) ;
					}
				} 
//				check user has permission to access this url 
				else {
					chain.doFilter(req, res) ;
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isPass ;

	}

	public UserDetail getUserDetailFromCookie(ServletRequest req) {
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		Cookie[] cookies = httpRequest.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(securityContext.getCookieUserKey())) {
				String[] userEncryStrArr = cookie.getValue().split("-");
				if (userEncryStrArr != null && userEncryStrArr.length == 2) {
					String userNameEncry = userEncryStrArr[0];
					String passwordEncry = userEncryStrArr[1];

					String userNameDecry = CryptoUtil.getInstance().decryptAES(
							userNameEncry);
					String passwordDecry = CryptoUtil.getInstance().decryptAES(
							passwordEncry);
					UserDetail userDetail = new UserDetail();
					userDetail.setUserName(userNameDecry);
					userDetail.setPassword(passwordDecry);
					return userDetail;
				}
			}
		}
		return null;
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
