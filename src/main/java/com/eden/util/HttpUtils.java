package com.eden.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {
	
	public static String getIPAddress(HttpServletRequest request) {  
		 String realIP = request.getHeader("x-forwarded-for");  
		 if (realIP != null && realIP.length() != 0) {  
		       while ((realIP != null && realIP.equals("unknow"))) {  
		             realIP = request.getHeader("x-forwarded-for");  
		       }  
		 }  
		 if (realIP == null || realIP.length() == 0) {  
	           realIP = request.getHeader("Proxy-Clint-IP");  
		       }  
		 if (realIP == null || realIP.length() == 0) {  
		       realIP = request.getHeader("WL-Proxy-Clint-IP");  
		 }  
		 if (realIP == null || realIP.length() == 0) {  
		       realIP = request.getRemoteAddr();  
		 }  
		       return realIP;  
		} 

}
