package com.eden.util;

public class MappingUtil {
	
	
	public static boolean mapping(String source , String target) {
		if(source == null || target == null) return false ;
		else if(source.equals(target)) return true ;
		else {
			
			String[] paths = target.split("/") ;
			return false ;
			
		}
	}
}
