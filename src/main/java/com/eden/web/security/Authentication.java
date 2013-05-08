package com.eden.web.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface Authentication {
	public static final String SESSION_TOKEN_KEY = "session_token_key" ;
	
	public boolean authenticate(ServletRequest req , ServletResponse res , FilterChain chain) ;
}
