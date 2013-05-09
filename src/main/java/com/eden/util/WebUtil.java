package com.eden.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	
	public static void setCookie(HttpServletRequest request , String name , String value) {
		if(request == null) return ;
		Cookie[] cookies = request.getCookies() ;
		if(cookies == null) return ;
		for(Cookie cookie : cookies ) {
			if(cookie.getName().equals(name)) {
				cookie.setValue(value) ;
				break ;
			}
		}
	}
	
	public static String getCookie(HttpServletRequest request , String name) {
		if(request == null) return null;
		Cookie[] cookies = request.getCookies() ;
		if(cookies == null) return null;
		for(Cookie cookie : cookies ) {
			if(cookie.getName().equals(name)) {
				return cookie.getValue() ;
			}
		}
		return null ;
	}
	
	public static void removeCookie(HttpServletRequest request , HttpServletResponse response , String name) {
		if(request == null) return ;
		Cookie[] cookies = request.getCookies() ;
		if(cookies == null) return ;
		for(Cookie cookie : cookies ) {
			if(cookie.getName().equals(name)) {
				cookie.setValue(null) ;
				cookie.setMaxAge(0) ;
				response.addCookie(cookie) ;
				break;
			}
		}
	}
	
	/**
	 * remove all the cookie
	 * @param request
	 */
	public static void clearCookie(HttpServletRequest request){
		Cookie[] cookies = request.getCookies() ;
		for(Cookie cookie : cookies ) {
			cookie.setValue("") ;
			cookie.setMaxAge(0) ;
		}
	}
}
