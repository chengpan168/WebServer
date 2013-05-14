package com.eden.web.security.authentication;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.eden.util.CryptoUtil;
import com.eden.util.JsonUtil;
import com.eden.web.security.SecurityContext;
import com.eden.web.security.resource.ResourceService;
import com.eden.web.security.user.Role;
import com.eden.web.security.user.UserDetail;
import com.eden.web.security.user.UserDetailService;

public class DefaultAuthentication implements Authentication {

	@Resource
	private SecurityContext securityContext;
	@Resource
	private UserDetailService userDetailService;
	@Resource
	private ResourceService resourceService ;

	private String[] exclude ={};
	
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
			//not filter login logout url and exclude url
			if (securityContext.getLoginUrl().equals(requestUri)
					|| securityContext.getLogoutUrl().equals(requestUri) 
					|| ArrayUtils.contains(exclude, requestUri) ) {
				isPass =  true ;
			}  
//			check the user has Permission 
			else {
				HttpSession session = httpRequest.getSession(false);
				UserDetail userDetail = null ;
				if(session != null){
					userDetail = (UserDetail)session.getAttribute(securityContext.getSessionUserKey()) ;
				}
				//if no user in session , try to get it from cookie and check it
				if(userDetail == null) {
					userDetail = getUserDetailFromCookie(req);
					
					//if not login , redirect to the authenticate fail url 
					Authority authority = userDetailService.authenticate(userDetail) ;
					if (authority.getStatus()  != 1) {
						isPass = false ;
					}
//				check user has permission to access this url 
					else {
						boolean isAdmin = false ;
						if(userDetail.getRoles() != null ){
							for(Role role : userDetail.getRoles() ) {
								if(role.getId() == 0) {
									isAdmin = true ;
									isPass = true ;
									break ;
								}
							} }
						if(!isAdmin){
							List<com.eden.web.security.resource.Resource> resources = resourceService.getResourceByRole(userDetail.getRoles()) ;
							if(!CollectionUtils.isNotEmpty(resources)){
								for(com.eden.web.security.resource.Resource resource : resources) {
									if(Pattern.matches(resource.getUrl(), requestUri) ){
										isPass = true ;
										break ;
									}
								}
							}
						}
					}
				} 
				
			}
			
			if(isPass){
				chain.doFilter(req, res) ;
			} else {
				httpResponse.sendRedirect(securityContext.getAuthenticateFailUrl()) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isPass ;

	}

	public UserDetail getUserDetailFromCookie(ServletRequest req) {
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		Cookie[] cookies = httpRequest.getCookies();
		if(cookies != null ) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(securityContext.getCookieUserKey())) {
					String userJsonEncry = cookie.getValue() ;
					String userJsonDecry = CryptoUtil.getInstance().decryptAES(userJsonEncry) ;
					UserDetail userDetail = JsonUtil.fromJson( userJsonDecry , UserDetail.class );
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
