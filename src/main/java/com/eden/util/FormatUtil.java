package com.eden.util;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatUtil {
	private static final Logger logger = LoggerFactory.getLogger(FormatUtil.class) ;
	
	private static String datePattern = "yyyy-MM-dd HH:mm:ss" ;
	
	/**
	 * 默认格式为 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, datePattern) ;
	}
	public static String formatDate(Date date , String pattern) {
		try{
			if(date == null) return null ;
			return DateFormatUtils.format(date, pattern) ;
		} catch(Exception e) {
			logger.warn("format date error ") ;
			return null ;
		}
	}
}
