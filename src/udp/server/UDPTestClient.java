package udp.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import aiInterface.testing.client.TestingUtil;


/**
 * Used to test the UDP capabilites of Prolog.
 * @author glen
 *
 */
public class UDPTestClient
{
	
	public static void main(String[] args)
	{
		
		InetAddress address;
		DatagramSocket socket = null;
		DatagramPacket packet;
		String message = "[percept,[player,[[name,[glensnemesis0]],[location,[[x,[345.23]],[y,[425]],[z,[0]]]],[physics,[walking]],[weapon,[[name,[plasma_rifle]],[range,[middle]]]],[team,[[colour,[blue]],[number,[2]]]],[friendorfoe,[foe]]]]]";
		String addressString = "localhost";
		int port = 3840;
		
		try
		{
		
		socket = new DatagramSocket();

		address = InetAddress.getByName(addressString);
		packet = new DatagramPacket(message.getBytes(), message.length(), 
		                                           address, port);
		socket.send(packet);
		// Need to put new bute[] into packet so old data isn't seen.
		socket.receive(packet);
		System.out.println( "responce = " + new String (packet.getData()));
		/*
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String received = new String(packet.getData(), 0, packet.getLength());
		System.out.println("Quote of the Moment: " + received);
*/
		}
		catch( Exception e)
		{
			TestingUtil.printExceptionInfo(e);
		}
	}

}
