package com.eden.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLog {
	private static final Logger logger = LoggerFactory.getLogger("Server") ;
	
	public static void debug(String msg) {
		logger.debug(msg) ;
	}
	
	public static void debug(String msg , Throwable e) {
		logger.debug(msg, e) ;
	}
	
	public static void info(String msg) {
		logger.info(msg) ;
	}
	
	public static void info(String msg , Throwable e) {
		logger.info(msg, e) ;
	}
	
	public static void error(String msg) {
		logger.error(msg) ;
	}
	
	public static void error(String msg , Throwable e) {
		logger.error(msg, e) ;
	}
}
