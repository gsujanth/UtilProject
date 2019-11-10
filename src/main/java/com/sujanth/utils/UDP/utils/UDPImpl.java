package com.sujanth.utils.UDP.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UDPImpl {

	public static void main(String[] args) {
		Server server = new Server(5000, 100);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			server.start();

			int status = 0;
			boolean first = true;

			Client client;
			while (status == 0) {
				if (first) {
					client = new Client(5000);
					status = client.sendMessage();
					first = false;
				} else {
					System.out.println("Client is closed and server is still runnning\n");
					System.out.println("Do you want to start another client (y/n)?\n");
					String message = in.readLine();
					if (message.equalsIgnoreCase("y")) {
						client = new Client(5000);
						status = client.sendMessage();
					} else {
						status = -1;
						if (server != null) {
							System.out.println("Closing Server...");
							server.stopRunning();
						}
					}
				}
			}
			server.join();
			System.out.println("Server Closed.");
		} catch (Exception e) {
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			if (server != null) {
				System.out.println("Closing Server in catch...");
				server.stopRunning();
				try {
					server.join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("Server Closed.");
			}
		}
	}
}
