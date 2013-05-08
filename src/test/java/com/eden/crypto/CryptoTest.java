package com.eden.crypto;

import org.junit.Test;

import com.eden.BaseTest;
import com.eden.util.CryptoUtil;

public class CryptoTest extends BaseTest{
	@Test
	public void testEncryptAES(){
		String data = "1" ;
		
		try {
			String encryStr = CryptoUtil.getInstance().encryptAES(data) ;
			print( encryStr) ;
			print(new  String(CryptoUtil.getInstance().decryptAES(encryStr)) ) ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEncryptDES(){
		String data = "你在哪儿，我的天" ;
		
		try {
			String encryStr = CryptoUtil.getInstance().encryptDES(data) ;
			print( encryStr) ;
			print( CryptoUtil.getInstance().decryptDES(encryStr)) ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
