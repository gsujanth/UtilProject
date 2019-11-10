package com.sujanth.utils.UDP.utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
	1. UDP is a communication protocol that transmits independent packets over the network 
		with no guarantee of arrival and no guarantee of the order of delivery.
	2. Faster than TCP and can be used in video streaming
*/
public class Server extends Thread {

	private DatagramSocket datagramSocket;
	private volatile boolean running = true;
	private byte[] receiveData;
	private int packetLength;

	public Server(int serverPort, int packetLength) {
		System.out.println("Welcome to UDP Server\n");
		try {
			this.packetLength = packetLength;
			datagramSocket = new DatagramSocket(serverPort);
			// datagramSocket.setSoTimeout(10000);
			System.out.println("UDP Server started on port: " + serverPort + "\n");
		} catch (Exception e) {
			System.out.println("Exception occurred while starting the server.\n");
			e.printStackTrace();
		}
	}

	public Server() {
		System.out.println("port and packetLength not mentioned");
	}

	public void stopRunning() {
		System.out.println("Inside stopRunning()\n");
		running = false;
		datagramSocket.close();
	}

	@Override
	public void run() {
		receiveData = new byte[packetLength];

		while (running) {
			try {
				// DatagramPacket to receive message
				Thread.sleep(50);
				DatagramPacket receivedPacket = new DatagramPacket(receiveData, packetLength);
				System.out.println("UDP Server waiting for incoming messages and " + "accepts packets of length "
						+ packetLength + "....\n");
				datagramSocket.receive(receivedPacket);

				String message = new String(receivedPacket.getData(), receivedPacket.getOffset(),
						receivedPacket.getLength());
				message = message.trim();

				InetAddress IPAddress = receivedPacket.getAddress();
				int clientPort = receivedPacket.getPort();
				System.out.println(
						"RECEIVED MESSAGE: \"" + message + "\" FROM CLIENT " + IPAddress + ":" + clientPort + "\n");
				if (message.equals("end")) {
					running = false;
					continue;
				} else {
					String responseMessage = "Thank you Client, I received your message\n";
					byte[] sendData = responseMessage.getBytes();
					System.out.println("Sending response to client\n");

					// DatagramPacket to send message
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, clientPort);
					datagramSocket.send(sendPacket);
				}
			} catch (Exception e) {
				running = false;
				System.out.println("Exception message: "+e.getMessage()+"\n");
				continue;
			}
		}
		System.out.println("Server no more receives messages...");
		if (datagramSocket != null) {
			datagramSocket.close();
		}
	}

}
