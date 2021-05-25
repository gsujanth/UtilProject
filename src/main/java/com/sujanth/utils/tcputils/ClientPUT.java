package com.sujanth.utils.tcputils;

import java.io.*;
import java.net.Socket;

public class ClientPUT {
	public static void putResponse(String serverName, int portNumber, String filename) throws IOException {

		Socket Clientsocket = new Socket(serverName, portNumber);
		System.out.println("Connection Established to " + serverName + ":" + portNumber);
		System.out.println();

		PrintWriter pw = new PrintWriter(Clientsocket.getOutputStream());
		pw.print("PUT /" + filename + " HTTP/1.1\r\n");
		pw.print("Accept: text/plain, text/html, text/*\r\n\r\n");

		// send a file code
		try {
			filename = System.getProperty("user.dir") + "/" + filename;
			FileReader fr = new FileReader(filename);
			BufferedReader bfr = new BufferedReader(fr);
			String line;

			while ((line = bfr.readLine()) != null) {
				pw.write(line);

			}
			pw.flush();

			// Receive OK 201
			BufferedReader br = new BufferedReader(new InputStreamReader(Clientsocket.getInputStream()));
			System.out.println(br.readLine());
			br.close();
			bfr.close();

		} catch (IOException e) {
			System.err.println(e.getMessage());
			pw.close();
		}
		Clientsocket.close();
	}
}
