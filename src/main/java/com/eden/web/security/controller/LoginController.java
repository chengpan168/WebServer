package com.eden.web.security.controller;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eden.log.Log;
import com.eden.util.ConvertUtil;
import com.eden.util.CryptoUtil;
import com.eden.util.JsonUtil;
import com.eden.util.RandomUtil;
import com.eden.util.WebUtil;
import com.eden.web.security.SecurityContext;
import com.eden.web.security.authentication.AuthenticationResult;
import com.eden.web.security.user.UserDetail;
import com.eden.web.security.user.UserDetailService;

@Controller
public class LoginController {
	
	@Resource
	private SecurityContext securityContext ;
	@Resource
	private UserDetailService userDetailService ;
	
	@RequestMapping("/loginPage")
	public String loginPage(){
		return "redirect:" + securityContext.getLoginPageUrl() ;
	}
	/**
	 * 
	 * @param userName 用户名
	 * @param password 密码
	 * @param remeberMe true|false 是否记住密码
	 * @return
	 */
	@RequestMapping("/login")
	public String login(ServletRequest request , ServletResponse response 
			, @RequestParam(required=false) String userName , @RequestParam(required=false) String password , @RequestParam(required=false) String remeberMe){
		Log.info("userName:" + userName + " ; password:" + password + "remeberMe:" + remeberMe) ;
		AuthenticationResult authenticationResult = userDetailService.authenticate(userName , password) ;
		if(authenticationResult.getStatus() == 1){
			putUser2SessionAndCookie((HttpServletRequest)request , (HttpServletResponse)response , authenticationResult.getUserDetail() ,  ConvertUtil.convert2Boolean(remeberMe , false) ) ;
		} else {
			request.setAttribute(securityContext.getLoginStatusKey(), authenticationResult.getStatus() ) ;
			return "forward:" + securityContext.getLoginFailUrl() ;
		}
		return "redirect:" + securityContext.getDefaultTargetUrl() ;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
		session.removeAttribute(securityContext.getSessionTokenKey()) ;
		WebUtil.removeCookie(request , response, securityContext.getCookieUserKey() , "/" ) ;
		return "redirect:" + securityContext.getLogoutSuccessUrl() ;
	}
	
	public void putUser2SessionAndCookie(HttpServletRequest request , HttpServletResponse response , UserDetail userDetail , boolean isRemeberme){
		HttpSession session = request.getSession() ;
		session.setAttribute(securityContext.getSessionTokenKey(), RandomUtil.UUID()) ;
		session.setAttribute(securityContext.getSessionUserKey(), userDetail) ;
		String value = CryptoUtil.getInstance().encryptAES(JsonUtil.toJson(userDetail) ) ;
		Cookie userCookie = new Cookie(securityContext.getCookieUserKey(), value) ;
		userCookie.setPath("/") ;
		if(isRemeberme){
			userCookie.setMaxAge(securityContext.getRemeberTime()) ;       
		} else {
			//delete when browser exit 
			userCookie.setMaxAge(-1) ;
		}
		response.addCookie(userCookie) ;
	}
}
