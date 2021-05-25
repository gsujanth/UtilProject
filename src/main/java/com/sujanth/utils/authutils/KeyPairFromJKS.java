package com.sujanth.utils.authutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyPairFromJKS {
	
	private static final URL path = KeyPairFromJKS.class.getResource("sujanth.jks");
	private static final File file = new File(path.getFile());
	private static final String JKSPASS = "sujanth";
	private static final String JKSALIAS = "sujanthalias";
	
	public static PrivateKey getPrivateKey() {
		PrivateKey privateKey = null;
		try {
			KeyPair keyPair = getKeyPair();
			privateKey = keyPair.getPrivate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return privateKey;
	}
	
	public static PublicKey getPublicKey() {
		PublicKey publicKey = null;
		try {
			KeyPair keyPair = getKeyPair();
			publicKey = keyPair.getPublic();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return publicKey;
	}
	
	public static KeyPair getKeyPair() throws IOException {
		KeyPair keyPair = null;
		KeyStore keyStore = null;
		FileInputStream fis = null;
		try {
			
			//String filePath = "C:\\Users\\princ\\eclipse-workspace\\UtilProject\\src\\main\\java\\com\\sujanth\\utils\\AuthUtil\\sujanth.jks";
			String filePath = file.getAbsolutePath();
			fis = new FileInputStream(filePath);
			keyStore = KeyStore.getInstance("JKS");
			keyStore.load(fis,JKSPASS.toCharArray());
			Key key = keyStore.getKey(JKSALIAS, JKSPASS.toCharArray());
			if(key!=null && key instanceof PrivateKey) {
				Certificate cert = keyStore.getCertificate(JKSALIAS);
				PublicKey publicKey = cert.getPublicKey();
				keyPair = new KeyPair(publicKey, (PrivateKey) key);
			} else {
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			fis.close();
		}
		return keyPair;
	}
	
	public static void main(String args[]) throws IOException {
		KeyPairFromJKS.getKeyPair();
		System.out.println(KeyPairFromJKS.getPrivateKey());
		System.out.println(KeyPairFromJKS.getPublicKey());
	}

}
