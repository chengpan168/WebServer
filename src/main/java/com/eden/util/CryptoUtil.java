package com.eden.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class CryptoUtil {
	private static final String secKey = "pi*$d k3?#fs34,.!]";
	private String defaultCharset = "utf-8" ;
	private Key AESKey ;
	private Key DESKey ;
	
	private static CryptoUtil instance = new CryptoUtil() ;
	
	public CryptoUtil(){
		try {
			KeyGenerator AESKeyGenerator = KeyGenerator.getInstance("AES") ;
			AESKeyGenerator = KeyGenerator.getInstance("AES") ;
			AESKeyGenerator.init(new SecureRandom(secKey.getBytes())) ;
			AESKey = AESKeyGenerator.generateKey() ;
			
			KeyGenerator DESKeyGenerator = KeyGenerator.getInstance("DES") ;
			DESKeyGenerator = KeyGenerator.getInstance("DES") ;
			DESKeyGenerator.init(new SecureRandom(secKey.getBytes())) ;
			DESKey = DESKeyGenerator.generateKey() ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CryptoUtil getInstance(){
		return instance ;
	}
	
	public String encryptAES(String content){
		try {
			byte[] data = (content.getBytes()) ;
			return new String(encryptAES(data) , defaultCharset) ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null; 
		}
	}
	public byte[] encryptAES(byte[] data){
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, AESKey) ;
			return Base64.encodeBase64(cipher.doFinal(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	public String decryptAES(String content){
		try {
			return new String(decryptAES(content.getBytes(defaultCharset)) , defaultCharset);
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
	public byte[] decryptAES(byte[] data){
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, AESKey) ;
			return cipher.doFinal(Base64.decodeBase64(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	public String encryptDES(String content){
		try{
			return Base64.encodeBase64String(encryptDES(content.getBytes(defaultCharset))) ;
		} catch(Exception e) {
			e.printStackTrace() ;
			return null; 
		}
	}
	public String decryptDES(String content){
		try{
			return new String(decryptDES(Base64.decodeBase64(content.getBytes(defaultCharset))) , defaultCharset) ;
		} catch(Exception e) {
			e.printStackTrace() ;
			return null; 
		}
	}
	public byte[] encryptDES(byte[] data){
		try{
			Cipher cipher = Cipher.getInstance("DES") ;
			cipher.init(Cipher.ENCRYPT_MODE, DESKey) ;
			return cipher.doFinal(data);
		} catch(Exception e) {
			e.printStackTrace() ;
		}
		return null ;
	}
	public byte[] decryptDES(byte[] data){
		try{
			Cipher cipher = Cipher.getInstance("DES") ;
			cipher.init(Cipher.DECRYPT_MODE, DESKey) ;
			return cipher.doFinal(data);
		} catch(Exception e) {
			e.printStackTrace() ;
		}
		return null ;
	}
	
    /**
     * 将二进制转换成16进制
     * @param buf
     * @return
     */
    public String byte2HexStr(byte[] bytes) {
    	return Hex.encodeHexString(bytes);
    }
    
    /**
     * 将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public byte[] hexStr2Byte(String hexStr) {
        try {
			return Hex.decodeHex(hexStr.toCharArray()) ;
		} catch (DecoderException e) {
			e.printStackTrace();
			return null ;
		}
    }

}
