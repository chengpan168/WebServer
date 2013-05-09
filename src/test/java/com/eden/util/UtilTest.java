package com.eden.util;

import java.math.BigDecimal;

import org.junit.Test;

import com.eden.BaseTest;

public class UtilTest extends BaseTest{
	@Test
	public void testConvert(){
		print(ConvertUtil.convert2Integer(new BigDecimal("2343548545464345") , 2)) ;
	}
	
	
	@Test
	public void testUUID(){
		print(RandomUtil.UUID()) ;
	}
	
	@Test
	public void testFor(){
		int[] a = null ;
		for(int i : a){
			print(i) ;
		}
	}
}
