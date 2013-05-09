package com.eden.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eden.log.Log;
import com.eden.web.security.authentication.Authentication;

public class SecurityFilter implements Filter {
	
	private Authentication authentication ;
	private String interceptorPath ;
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		authentication.authenticate(req, res, chain) ;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext()) ;
		if(applicationContext == null ) {
			Log.error("con't find spring applicationContext check you app") ;
			throw new RuntimeException("con't find spring applicationContext check you app") ;
		}
		authentication = (Authentication) applicationContext.getBean("authentication") ;
	}

}
