package com.eden.util;

import java.lang.reflect.Field;

public class BeanUtil {

		public static String toString(Object obj) {

			if(obj == null) 
				return "null";
			StringBuffer sb = new StringBuffer();

			Class<?> clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();
			
			sb.append(clazz.getName() + "{");
			try {
				for (Field field : fields) {
					field.setAccessible(true);
					sb.append("\n  " + field.getName() + ":" + field.get(obj));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			sb.append("\n}");
			return sb.toString();
		}
		

}
