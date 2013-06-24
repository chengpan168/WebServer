package com.eden.json;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class JacksonMapper {
	private static ObjectMapper objectMapper ;
	static{
		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Inclusion.NON_NULL);
		objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//禁止使用int代表Enum的order()來反序列化Enum,非常危險
		objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
	}
	
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
