package com.eden.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
	private static final String secKey = "aad5&*#!~Ko8";
	
	public static byte[] encryptAES(byte[] data){
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secKey.getBytes(), "AES")) ;
			return cipher.doFinal(data) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}

}
