package com.eden.util;

import java.math.BigDecimal;

import org.junit.Test;

import com.eden.BaseTest;
import com.eden.web.security.user.UserDetail;

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
	
	@Test
	public  void testJson(){
		UserDetail userDetail = new UserDetail() ;
		userDetail.setUserName("hello") ;
		print(JsonUtil.toJson(userDetail)) ;
		print(JsonUtil.fromJson(JsonUtil.toJson(userDetail) , UserDetail.class) ) ;
	}
}
