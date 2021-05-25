package com.sujanth.utils.tcputils;

import java.io.*;

public class ClientMain {

	public static void main(String[] args) throws IOException {

		String serverName = args[0];
		int portNumber = Integer.parseInt(args[1]);
		String commandtype = args[2];
		String filename = args[3];

		if (commandtype.equalsIgnoreCase("get")) {
			ClientGET.getResponse(serverName, portNumber, filename);

		} else if (commandtype.equalsIgnoreCase("put")) {

			ClientPUT.putResponse(serverName, portNumber, filename);
		} else {
			System.out.println("Only GET or PUT supported");
		}

	}

}
