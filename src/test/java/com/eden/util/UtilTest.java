package com.eden.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.junit.Test;

import com.eden.BaseTest;
import com.eden.web.security.user.Role;
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
		userDetail.setName("hello") ;
		userDetail.setEnable(true) ;
		Role role1 = new Role() , role2 = new Role() ;
		role1.setName("admin") ;role2.setName("user") ;
		userDetail.setRoles(Arrays.asList(role1, role2)) ;
		print(JsonUtil.toJson(userDetail)) ;
		print(JsonUtil.fromJson(JsonUtil.toJson(userDetail) , UserDetail.class) ) ;
	}
	
	@Test
	public void testRegex(){
		long start = System.currentTimeMillis() ;
		Pattern pattern = Pattern.compile("/action/.*") ;
		for(int i = 0 ; i < 100000 ; i++) {
			Pattern.compile("/action/.*").matcher("/action/json").matches() ;
		}
		print("spend:" + (System.currentTimeMillis() - start) + " ms") ;
	}
	
	@Test
	public void testMyReg(){
		String patter = "/action**" ;
		
	}
	
	@Test
	public void testDigit(){
		print( (0xF0 & -123) >>> 4 ) ;
	}
}
