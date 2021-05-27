package com.sujanth.utils.encryptionutils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.NoIvGenerator;
import org.jasypt.salt.StringFixedSaltGenerator;

public class JasyptEncryptionUtil {
	
	StandardPBEStringEncryptor encryptor;
	
	public void init() {
		encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("password");
		encryptor.setSaltGenerator(new StringFixedSaltGenerator("salt1234"));
		encryptor.setIvGenerator(new NoIvGenerator());
	}
	
	public String encrypt(String str) {
		String encryptedString = encryptor.encrypt(str);
		System.out.println(encryptedString);
		return encryptedString;
	}
	
	public String decrypt(String str) {
		String decryptedString = encryptor.decrypt(str);
		System.out.println(decryptedString);
		return decryptedString;
	}

	public static void main(String[] args) {
		
		JasyptEncryptionUtil jasyptEncryptionUtil = new JasyptEncryptionUtil();
		jasyptEncryptionUtil.init();
		jasyptEncryptionUtil.decrypt(jasyptEncryptionUtil.encrypt("this is going to encrypted"));
		
	}

}
