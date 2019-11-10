package com.sujanth.utils.Base64.utils;

import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class Base64Util {

	public String encodeToString() {
		String originalInput = "test input";
		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		return encodedString;
	}
	
	public String encodeToString1() {
		StringBuilder sb = new StringBuilder();
		sb.append("padding.length=24\n");
		sb.append("padding.digit=16");
		byte[] encodedByteArray = Base64.getEncoder().encode(sb.toString().getBytes());
		
		//prints a string representation of the contents of the encoded byte array
		System.out.println(Arrays.toString(encodedByteArray));
		
		//Prints new String by decoding the encoded array of bytes
		String encodedString = new String(encodedByteArray);
		System.out.println(encodedString);
		return encodedString;
	}
	
	public byte[] encodeToByteArray() {
		String originalInput = "test input";
		byte[] encodedByteArray = Base64.getEncoder().encode(originalInput.getBytes());
		return encodedByteArray;
	}
	
	public String encodeWithoutPadding() {
		String originalInput = "test input";
		return Base64.getEncoder().withoutPadding().encodeToString(originalInput.getBytes());
	}
	
	public void encodeSrcToDestByteArray() {
		String originalInput = "test input";
		byte[] src = originalInput.getBytes();
		byte[] dest1 = new byte[4 * ((src.length + 2) / 3)];
		int x = Base64.getEncoder().encode(src,dest1);
		System.out.println(x);
		byte[] dest2 = new byte[4 * (src.length / 3) + (src.length % 3 == 0 ? 0 : src.length + 1)];
		int y = Base64.getEncoder().withoutPadding().encode(src, dest2);
		System.out.println(y);
	}

	public String decode() {
		byte[] decodedBytes1 = Base64.getDecoder().decode(encodeToString());
		String decodedString1 = new String(decodedBytes1);
		//byte[] decodedBytes2 = Base64.getDecoder().decode(encodeWithoutPadding());
		//String decodedString2 = new String(decodedBytes2);
		return decodedString1;
	}

	public String encodeURL() {
		String originalUrl = "https://www.google.co.nz/?gfe_rd=cr&ei=dzbFV&gws_rd=ssl#q=java";
		String encodedUrl = Base64.getUrlEncoder().encodeToString(originalUrl.getBytes());
		return encodedUrl;
	}

	public String decodeURL() {
		byte[] decodedBytes = Base64.getUrlDecoder().decode(encodeURL());
		String decodedUrl = new String(decodedBytes);
		return decodedUrl;
	}
	
	public StringBuilder getMimeBuffer() {
	    StringBuilder buffer = new StringBuilder();
	    for (int count = 0; count < 10; ++count) {
	        buffer.append(UUID.randomUUID().toString());
	    }
	    return buffer;
	}
	
	public String encodeMIME(StringBuilder buffer) {
		byte[] encodedAsBytes = buffer.toString().getBytes();
		String encodedMime = Base64.getMimeEncoder().encodeToString(encodedAsBytes);
		return encodedMime;
	}
	
	public String decodeMIME(String encodedMime) {
		byte[] decodedBytes = Base64.getMimeDecoder().decode(encodedMime);
		String decodedMime = new String(decodedBytes);
		return decodedMime;
	}
	
	public void encodeAndDecodeMIME() {
		StringBuilder buffer=getMimeBuffer();
		System.out.println(buffer.toString()+"\n");
		String encodedMime = encodeMIME(buffer);
		System.out.println(encodedMime+"\n");
		String decodedMime = decodeMIME(encodedMime);
		System.out.println(decodedMime);
	}
	
	public static void main(String[] args) {
		Base64Util b64 = new Base64Util();
		System.out.println(b64.encodeToString());
		System.out.println(b64.encodeWithoutPadding());
		System.out.println(b64.decode());

		System.out.println(b64.encodeURL());
		System.out.println(b64.decodeURL()+"\n");
		
		b64.encodeAndDecodeMIME();
		b64.encodeSrcToDestByteArray();
	}

}
