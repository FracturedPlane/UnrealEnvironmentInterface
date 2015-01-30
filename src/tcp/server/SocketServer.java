package tcp.server;


import java.io.*;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.util.Scanner;

import unrealinterface.data.GologExogEvent;
import unrealinterface.data.XMLPercept;

import aiInterface.connection.util.TCPNIOConnection;
import aiInterface.testing.client.TestingUtil;

/**
 * Class used to test connections and other DataContainer classes.
 * @author Glen Berseth
 *
 */
public class SocketServer implements Runnable
{
	/*
	 * Used to hold the connection to the ServerSocketChannel for reading and
	 * writing to the Socket.
	 */
	private ServerSocketChannel serverSocketChannel;
	private TCPNIOConnection entityServerConnection;
	
	int datalength = 2000;
	byte[] data = new byte[datalength];
	String message = " ";
	int listeningPort = 3840;
	int backlog = 10;
	int readstatus = 0;
	// String testType = "EntityController";
	String testType = "IndiGologAgent";
	String testingServer = new String("testingServer");
	
	public boolean keepAlive = true;
	
	
	private SocketServer() throws IOException
	{
		
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(listeningPort), backlog);
		
		//2. Wait for connection
		System.out.println("Waiting for connection");
		entityServerConnection = new TCPNIOConnection (this.serverSocketChannel.accept());
		System.out.println("Connection received " + entityServerConnection.getConnection().socket().getInetAddress().getHostName());

		sendMessage("capture");
	}
	
	
	public void startServer()
	{
		Scanner input = new Scanner(System.in);
		try{
			//1. creating a server socket
			
			//4. The two parts communicate via the input and output streams
			while(!message.equals( TestingUtil.CLOSE_CONNECTION_FLAG ) )
			{
					
				System.out.println("client>" + new String(data, 0, readstatus) );
				// System.out.println("Enter responce");
				// String inputMessage = input.nextLine();
				System.out.println("Enter responce");
				String inputMessage = input.nextLine();
				// Forgot to add the new line character so that Indigolog will work 
				this.sendMessage(inputMessage);
				if (message.equals( TestingUtil.CLOSE_CONNECTION_FLAG ))
				{
					sendMessage( TestingUtil.CLOSE_CONNECTION_FLAG );
				}
			}
		}

		finally
		{
			System.out.println("Closing connection");
			try
			{
				entityServerConnection.getConnection().close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	
	void sendMessage(String msg)
	{
		msg = msg + "\n";
		try
		{
			entityServerConnection.send(msg);
			System.out.println("server>" + msg);
		}
		catch(IOException ioException)
		{
			ioException.printStackTrace();
		}
	}
	
	
	
	public static void main(String args[])
	{
		SocketServer server = null;
		try
		{
			server = new SocketServer();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(server).start();
		server.startServer();

	}


	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		String inMessage = "";
		while(!message.equals( TestingUtil.CLOSE_CONNECTION_FLAG ) && readstatus >= 0)
		{
			try
			{
				inMessage = entityServerConnection.getMessage();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// data[readstatus + 1] = '\0';
			if ( inMessage.equals(TestingUtil.CLOSE_CONNECTION_FLAG) )
			{
				this.keepAlive = false;
				break;
			}	
			if ( inMessage.length() < 1)
			{
				try 
				{
					Thread.sleep(500);
				} catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("client>" + inMessage );
			if ( testType.equals("EntityController"))
			{
				// Do the converting the system does.
				XMLPercept xmlPercept = new XMLPercept();
				xmlPercept = (XMLPercept) xmlPercept.convertToDataContainer(inMessage);
				GologExogEvent goEvent = new GologExogEvent();
				goEvent = goEvent.convert(xmlPercept);
				System.out.println("GologEvent = " + goEvent.getSource());
			}

		}
	}
}

