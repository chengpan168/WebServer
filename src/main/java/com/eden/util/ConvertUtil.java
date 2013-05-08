package com.eden.util;

import org.apache.commons.beanutils.ConvertUtils;

public class ConvertUtil {
	
	/**
	 * null return null
	 * ""   return "" ;
	 * any  return toString()
	 * @param obj
	 * @return
	 */
	public static String convert(Object obj) {
		return ConvertUtils.convert(obj) ;
	}
	
	public static String convert(Object obj , String defaultValue){
		if(obj == null) return defaultValue ;
		return convert(obj) ;
	}
}
