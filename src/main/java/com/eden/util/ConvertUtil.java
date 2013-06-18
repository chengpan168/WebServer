package com.eden.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;


public class ConvertUtil {
	private static String[] datePatterns = {"yyyy-dd-mm HH:MM:ss" , "yyyy-dd-mm"} ;
	
	/**
	 * null return null
	 * any  return toString()
	 * @param obj
	 * @return
	 */
	public static String convert2Str(Object obj) {
		if(obj==null) return null ;
		if(obj instanceof Date){
			return DateFormatUtils.format((Date)obj, datePatterns[0]) ;
		}
		return obj.toString() ;
	}
	
	public static String convert2Str(Object obj , String defaultValue){
		if(obj == null) return defaultValue ;
		return convert2Str(obj) ;
	}
	
	/**
	 * null 或不是整数，或大于int型  return null 
	 * int型数字或字 符串  return integer
	 * @param value
	 * @return
	 */
	public static Integer convert2Integer(Object value) {
		if(value==null) return  null ;
		try{
			if(value instanceof Integer) return (Integer)value ;
			else 
				return Integer.parseInt(value.toString()) ;
		} catch(Exception e) {
			return null ;
		}
	}
	
	/**
	 * null 或不是整数，可大于int型  return defaultValue 
	 * int型数字或字 符串  return integer
	 * @param value
	 * @return
	 */
	public static Integer convert2Integer(Object value , Integer defaultValue) {
		Integer result = convert2Integer(value) ;
		return result == null ? defaultValue : result ;
	}

	public static Boolean convert2Boolean(Object value) {
		if(value == null) return null ;
		else if(value instanceof Boolean) return (Boolean) value ;
		else if(value instanceof String) {
			if("true".equalsIgnoreCase((String)value) ) return Boolean.TRUE;
			else if("false".equalsIgnoreCase((String)value) ) return Boolean.FALSE;
		}
		return null ;
	}
	
	public static Boolean convert2Boolean(Object value , Boolean defaultValue) {
		Boolean result = convert2Boolean(value) ;
		return result == null ? defaultValue : result ;
	}
	

	public static Date convert2Date(String dateStr) {
		try {
			return DateUtils.parseDate(dateStr, datePatterns) ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null ;
	}
}
