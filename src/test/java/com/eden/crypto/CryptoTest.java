package com.eden.crypto;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import com.eden.BaseTest;
import com.eden.util.CryptoUtil;
import com.sun.glass.ui.SystemClipboard;

public class CryptoTest extends BaseTest{
	@Test
	public void testEncryptAES(){
		String data = "1" ;
		
		try {
			String encryStr = CryptoUtil.getInstance().encryptAES(data) ;
			print( encryStr) ;
			print(new  String(CryptoUtil.getInstance().decryptAES("26ruwo/tvRT+Iv7QEgZ00CXIqN+rlUUjCT2Qh+d27Or1NzZk6+X83y0cuaBiRgNbKwSL+Ki3LWv4DgdlbZ+OTEITHaNkNxVjMY/Y6/nOxuLWBsLsTOsYh2tyGsDeZBhNg2s9WQcNELQHQzhXVHGC/w==")) ) ;
			print(new  String(CryptoUtil.getInstance().decryptAES("Pleju+qIHqGDCV2BD8TH1w=")) ) ;
			
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
	@Test
	public void testHexAndBase64(){
		String data = "你在哪儿，我的天你在哪儿" ;
		byte[] bytes = data.getBytes() ;
		try {
			long start = System.currentTimeMillis() ;
			String encryStr = "" ;
			String decryStr = "" ; 
			for(int i= 0 ; i < 10000 ; i++) {
//				encryStr = CryptoUtil.getInstance().byte2HexStr(bytes) ;
				encryStr = Hex.encodeHexString(bytes) ;
				decryStr = new String(Hex.decodeHex(encryStr.toCharArray())) ;
//				decryStr = new String(CryptoUtil.getInstance().hexStr2Byte(encryStr)) ;
			}
			print("Hex:"  + (System.currentTimeMillis() - start ) + " ms " +  encryStr  + " | " + decryStr) ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			long start = System.currentTimeMillis() ;
			String encryStr = "" ; 
			String decryStr = "" ; 
			for(int i= 0 ; i < 10000 ; i++) {
				encryStr = Base64.encodeBase64String(bytes) ;
				decryStr = new String(Base64.decodeBase64(encryStr.getBytes()) );
			}
			print("base64: " + (System.currentTimeMillis() - start ) + " ms " +  encryStr  + " | " + decryStr) ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testBase64(){
		String data = "354543vervre" ;
		byte[] bytes = data.getBytes() ;
		try {
			long start = System.currentTimeMillis() ;
			String encryStr = "" ; 
			String decryStr = "" ; 
			for(int i= 0 ; i < 10000 ; i++) {
				encryStr = Base64.encodeBase64String(bytes) ;
				decryStr = new String(Base64.decodeBase64(encryStr.getBytes()) );
			}
			print( encryStr  + " | " + decryStr + (System.currentTimeMillis() - start ) + " ms ") ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
