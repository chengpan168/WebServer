package com.eden.util;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper objectMapper = new ObjectMapper() ;
	public static <T> T fromJson(String jsonStr , Class<T> clazz ) {
		try {
			return objectMapper.readValue(jsonStr , clazz) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	public static String toJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj) ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return null ;
		}
	}
}
