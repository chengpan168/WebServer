package com.eden.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;


public class CollectionUtil {
	public static boolean isEmpty(Collection<? extends Object> collection) {
		return collection == null?true:collection.isEmpty() ;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Map map){
		return map == null?true:map.isEmpty() ;
	}
	
	public static String join(Collection<? extends Object> collection , String separator ) {
		if(collection != null && collection.size() > 0){
			StringBuilder sb = new StringBuilder()  ;
			for(Iterator<? extends Object> iterator = collection.iterator() ;iterator.hasNext();){
				Object val = iterator.next() ;
				sb.append(val == null ? "" : val) ;
				if(iterator.hasNext()){
					sb.append(separator) ;
				}
			}
			return sb.toString() ;
		}
		return "" ;
	}
}
