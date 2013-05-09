package com.eden.util;

import java.util.Random;

public class RandomUtil {
	private static Random random = new Random() ;
	public static String UUID(){
		return java.util.UUID.randomUUID().toString().replace("-", "") ;
	}
	
	public static int nextInt(){
		return random.nextInt() ;
	}
	
	public static int nextInt(int n){
		return random.nextInt(n) ;
	}
}
