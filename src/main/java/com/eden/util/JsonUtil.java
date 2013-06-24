package com.eden.util;

import com.eden.json.FastJson;

public class JsonUtil {
	public static <T> T fromJson(String jsonStr , Class<T> clazz ) {
		try {
//			return JacksonMapper.fromJson(jsonStr, clazz) ;
			return FastJson.fromJson(jsonStr ) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	public static String toJson(Object obj) {
		try {
//			return JacksonMapper.toJson(obj) ;
			return FastJson.toJson(obj) ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return null ;
		}
	}
}
