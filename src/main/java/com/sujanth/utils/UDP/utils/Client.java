package com.sujanth.utils.UDP.utils;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	private DatagramSocket clientSocket;
	private InetAddress serverIPAddress;
	private int serverPort;
	/*
	 * The InetAddress class’s toString() method returns both hostname and IP
	 * address, e.g. www.codejava.net/198.57.151.22.
	 */

	public Client(String serverHostname, int serverPort) {
		try {
			clientSocket = new DatagramSocket();
			serverIPAddress = InetAddress.getByName(serverHostname);
			this.serverPort = serverPort;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Client(byte[] serverAddr, int serverPort) {
		try {
			clientSocket = new DatagramSocket();
			serverIPAddress = InetAddress.getByAddress(serverAddr);
			this.serverPort = serverPort;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Client(int serverPort) {
		try {
			clientSocket = new DatagramSocket();
			serverIPAddress = InetAddress.getLocalHost();
			this.serverPort = serverPort;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InetAddress[] getAllIPAddresses() {
		InetAddress[] google = null;
		try {
			google = InetAddress.getAllByName("google.com");
			for (InetAddress addr : google) {
				System.out.println(addr.getHostAddress());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return google;
	}

	public int sendMessage() {
		System.out.println("Welcome to UDP Client\n");
		String serverResponse = null;
		String message = null;
		int status = -1;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Attempting to send to server " + serverIPAddress + " via UDP\n");
			/*System.out.println("Server Host Address: " + serverIPAddress.getHostAddress() + " Server Host Name: "
					+ serverIPAddress.getHostName());*/

			byte[] receiveData = new byte[100];
			boolean send = true;
			while (send) {
				System.out.println("Enter\n"
						+ "1. message to be sent to server\n"
						+ "2. \"close\" to close the client \n"
						+ "3. \"end\" to close the server");
				message = inFromUser.readLine();
				
				if(message.equalsIgnoreCase("close")) {
					send = false;
					status = 0;
					continue;
				}else if(message.equalsIgnoreCase("end")) {
					send = false;
					status = -1;
					System.out.println("Sending the message to end the Server...");
					byte[] sendData = message.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIPAddress, serverPort);
					clientSocket.send(sendPacket);
					continue;
				}

				System.out.println("Sending the message to Server...");
				byte[] sendData = message.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIPAddress, serverPort);
				clientSocket.send(sendPacket);

				// receiving message from server
				DatagramPacket receivedPacket = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivedPacket);
				serverResponse = new String(receivedPacket.getData(), receivedPacket.getOffset(),
						receivedPacket.getLength());
				System.out.println("RESPONSE FROM SERVER: " + serverResponse);
			}
			close();
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
		
	}

	public void close() {
		try {
			System.out.println("Closing Client....");
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
