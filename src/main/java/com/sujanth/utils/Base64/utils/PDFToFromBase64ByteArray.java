package com.sujanth.utils.Base64.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

public class PDFToFromBase64ByteArray {
	
	public void generatePDFFromNormalStringDecodedB64EncodedByteArrayOfPDF(String encodedString,String outputFilepath) {
		File file = new File(outputFilepath);
		OutputStream op;
		try {
			op = new FileOutputStream(file);
			op.write(decode(encodedString));
			op.flush();
			op.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public byte[] getB64EncodedByteArrayOfFile(String filepath) throws Exception{
		File file = new File(filepath);
		int length = (int) file.length();
		byte[] bytes = new byte[length];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytes);
		fis.close();
		
		byte[] base64EncodedByteArrayOfFile = Base64.getEncoder().encode(bytes);
		
		System.out.println(new String(base64EncodedByteArrayOfFile));
		return base64EncodedByteArrayOfFile;
	}
	
	public byte[] decode(String encodedString) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		System.out.println(decodedBytes);
		String decodedString = new String(decodedBytes);
		System.out.println(decodedString);
		return decodedBytes;
	}
	
	public static void main(String[] args) throws Exception {
		PDFToFromBase64ByteArray p = new PDFToFromBase64ByteArray();
		String inputFilepath = "C:\\Users\\princ\\Desktop\\helloworld.pdf";
		String outputFilepath = "C:\\Users\\princ\\Desktop\\out.pdf";
		p.generatePDFFromNormalStringDecodedB64EncodedByteArrayOfPDF(new String(p.getB64EncodedByteArrayOfFile(inputFilepath)),outputFilepath);
	}

}
