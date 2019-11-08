package com.sujanth.utils.Base64.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;

public class GeneratePDFItext {

	public void genearteSamplePDF(String filepath) {
		Document document = new Document();
		try {
			PdfWriter pdfwriter = PdfWriter.getInstance(document,new FileOutputStream(filepath));
			document.open();
			document.add(new Paragraph("Hello World"));
			document.close();
			pdfwriter.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generatePDFFromEncodedString(String encodedString, String outputFilepath) {
		File file = new File(outputFilepath);
		OutputStream os;
		try {
			os = new FileOutputStream(file);
			os.write(Base64.decode(encodedString));
			os.flush();
			os.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String generateEncodedStringFromPDF(String path) {
		String base64 = null;
		try {
			PdfReader reader = new PdfReader(path);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfStamper stamper = new PdfStamper(reader,baos);
			stamper.close();
			base64 = Base64.encodeBytes(baos.toByteArray());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return base64;
	}
	
	public static void main(String[] args) {
		GeneratePDFItext g = new GeneratePDFItext();
		String filepath = "C:\\Users\\princ\\Desktop\\helloworld.pdf";
		String outputFilepath = "C:\\Users\\princ\\Desktop\\outhw.pdf";
		g.genearteSamplePDF(filepath);
		String encodedString = g.generateEncodedStringFromPDF(filepath);
		g.generatePDFFromEncodedString(encodedString, outputFilepath);
	}

}
