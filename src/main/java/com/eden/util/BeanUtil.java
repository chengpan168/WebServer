package com.eden.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

public class BeanUtil {

	public static String toString(Object obj) {

		if (obj == null)
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

	@SuppressWarnings("unchecked")
	public static <T> T clone(Object src) {
		try {
			if (src instanceof Serializable) {
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				ObjectOutputStream oout = new ObjectOutputStream(bout);
				oout.writeObject(src);
				oout.flush();
				oout.close();

				ObjectInputStream oin = new ObjectInputStream(
						new ByteArrayInputStream(bout.toByteArray()));
				Object o = oin.readObject() ;
				oin.close() ;
				return (T) o ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
