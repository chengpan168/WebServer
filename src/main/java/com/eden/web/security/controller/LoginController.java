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
import com.eden.util.RandomUtil;
import com.eden.util.WebUtil;
import com.eden.web.security.SecurityContext;
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
		if(userDetailService.authenticate(userName , password) == 1){
			putUser2SessionAndCookie((HttpServletRequest)request , (HttpServletResponse)response , userName , password ,  ConvertUtil.convert2Boolean(remeberMe , false) ) ;
		} else {
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
	
	public void putUser2SessionAndCookie(HttpServletRequest request , HttpServletResponse response , String userName , String password , boolean isRemeberme){
		HttpSession session = request.getSession() ;
		session.setAttribute(securityContext.getSessionTokenKey(), RandomUtil.UUID()) ;
		String value = CryptoUtil.getInstance().encryptAES(userName) + "-" + CryptoUtil.getInstance().encryptAES(password) ;
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
