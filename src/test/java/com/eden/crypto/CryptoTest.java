package com.eden.crypto;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.eden.BaseTest;
import com.eden.util.CryptoUtil;

public class CryptoTest extends BaseTest{
	@Test
	public void testEncryptAES(){
		String data = "你在哪儿，我的天" ;
		
		byte[] encrypt = CryptoUtil.encryptAES(data.getBytes()) ;
		try {
			print(IOUtils.toString(encrypt) ) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
