package com.eden.json;

import com.alibaba.fastjson.JSON;

public class FastJson {
	
	
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonStr) {
		try {
			return (T) JSON.parse(jsonStr) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	public static String toJson(Object obj) {
		try {
			return JSON.toJSONString(obj) ;
		} catch (Exception e) {
			e.printStackTrace() ;
			return null ;
		}
	}
}
