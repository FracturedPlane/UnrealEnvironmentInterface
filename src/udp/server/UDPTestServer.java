package udp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import aiInterface.environment.UnrealEI;
import aiInterface.testing.client.TestingUtil;

/**
 * This class is designed to assist in testing a UDP based communications
 * 
 * @author Glen Berseth
 *
 */
public class UDPTestServer 
{
	


	public static void main(String[] args) throws Exception
	{
		
		DataOutputStream out;
		int datalength = 2000;
		byte[] data = new byte[datalength];
		String message = " ";
		int listeningPort = 3840;
		int backlog = 10;
		int readstatus = 0;
		
		DatagramSocket serverSocket = new DatagramSocket(listeningPort);
		DatagramPacket recievePacket = new DatagramPacket(data, datalength);
		
		
		while ( !message.contains((TestingUtil.CLOSE_CONNECTION_FLAG)) )
		{
			System.out.println("Waiting for connection");
			serverSocket.receive(recievePacket);
			data = recievePacket.getData();
			message = new String (recievePacket.getData(), UnrealEI.CHARSET );
			message = message.substring(0, recievePacket.getLength());
			
			System.out.println("recieved a packet from " + recievePacket.getAddress() + " " + recievePacket.getPort());
			System.out.println(data);
			System.out.println("data length = " + recievePacket.getLength());
			/*
			for (int i = 0; i < recievePacket.getData().length; i++)
			{
				System.out.print((int)data[i]);
			}
			*/
			System.out.println("\nclient>" + message);
			
			/*
			 * Make a response Packet.
			 */
			
			InetAddress responseAddr = recievePacket.getAddress();
			int responsePort = recievePacket.getPort();
			String responceMessage = "Wad you say? " + message;
			DatagramPacket responsePacket = new DatagramPacket(responceMessage.getBytes(),
					responceMessage.getBytes().length , responseAddr, responsePort );
			System.out.println("Responding with \n" + responceMessage );
			serverSocket.send(responsePacket);
			
		}
		
	}
}
