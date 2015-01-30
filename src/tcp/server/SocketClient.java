package tcp.server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import aiInterface.environment.UnrealEI;
import aiInterface.testing.client.TestingUtil;
import aiInterface.util.MessageFraming;

/**
 * Class used to test the interface
 * Specifically to test a TCPServer connection.
 * @author Glen Berseth
 *
 */
public class SocketClient implements Runnable
 {
 	Socket requestSocket;
 	final int sendSocket = 3840;
 	DataOutputStream out;
  	DataInputStream in;
  	String message = " ";
  	String hostName = "localhost";
  	final String closeConnectionFlag = TestingUtil.CLOSE_CONNECTION_FLAG;
	int datalength = 2000;
	byte[] data = new byte[datalength];
	int readstatus = 0;
	private Scanner input;

	public boolean keepAlive = true;
	
	
	private SocketClient() throws IOException
	{
		requestSocket = new Socket( hostName, sendSocket);
		System.out.println("Connected to localhost in port " + sendSocket);
		out = new DataOutputStream(requestSocket.getOutputStream());
		out.flush();
		in = new DataInputStream(requestSocket.getInputStream());
		System.out.println("Starting message sending");

	}
  	
  	public void startClient()
 	{
  		input = new Scanner(System.in);
			while ( ! message.equals("stop"))
 			{
 				message = input.nextLine();
 				sendMessage(message);
 			}
 					sendMessage(TestingUtil.CLOSE_CONNECTION_FLAG);
 			
 		
 		
 		System.out.println("Done");
 	}
 	void sendMessage(String msg)
 	{
 		msg = MessageFraming.frameString(msg);
 		try
 		{
 			out.write(msg.getBytes(UnrealEI.CHARSET));
 			out.flush();
 			System.out.println("client>" + msg);
 		}
 		catch(IOException ioException)
 		{
 			ioException.printStackTrace();
 		}
 	}
 	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		while(!message.equals( TestingUtil.CLOSE_CONNECTION_FLAG ) && readstatus >= 0)
		{
			try
			{
				readstatus = in.read(data, 0, datalength - 1);
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// data[readstatus + 1] = '\0';
			if ( readstatus < 0 || message.equals(TestingUtil.CLOSE_CONNECTION_FLAG) )
			{
				this.keepAlive = false;
				break;
			}	
				
			System.out.println("client>" + new String(data, 0, readstatus) );
		}
	}
		
 	public static void main(String args[])
 	{
 		SocketClient client = null;
		try 
		{
			client = new SocketClient();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(client).start();
 		client.startClient();
 	}
}
