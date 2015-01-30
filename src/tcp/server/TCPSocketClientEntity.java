package tcp.server;

import java.io.*;
import java.net.*;
import java.nio.channels.SocketChannel;
import java.util.Properties;

import aiInterface.connection.EntityTCPChannelServer;
import aiInterface.connection.util.TCPNIOConnection;
import aiInterface.testing.client.TestingUtil;
import aiInterface.util.MessageFraming;

/**
 * Class designed to assist in testing the functionality of a TCP based
 * connection.
 * 
 * @author Glen Berseth
 *
 */
public class TCPSocketClientEntity extends EntityTCPChannelServer
 {
 	// Socket requestSocket;
 	final int sendSocket = 3850;
 	// ObjectOutputStream out;
  	// ObjectInputStream in;
 	/*
 	 * testType controlls the type of testing be done.
 	 * 1 = multiple percept sending test
 	 * 2 = new Entity request
 	 */
 	final int testType = 1;
  	String message[] = new String[10];
  	String hostName = "localhost";
	int datalength = 2000;
	byte[] data = new byte[datalength];
	int readstatus = 0;
	private Properties props;
	private static final String PROPERTIES = "properties";
	private static final String PROP_TEST_FILE_DIR = "testFileDir";
	private static final String PROP_TEST_FILE_NAME = "testFileName";
	private static final String PROP_REQUEST_FILE = "requestFile";
	private int messages = 5;
	private TCPNIOConnection NIOChannel;

  	private TCPSocketClientEntity()
  	{
  		
  		String propsFilePath= new String (PROPERTIES + File.separator + this.getClass().getName() + "." + 
			PROPERTIES);
  		// System.out.println("propsFilePath = " + propsFilePath);
  		File propsFile = new File( propsFilePath );
  		// System.out.println("file exists = " + propsFile.exists());
  		props = new Properties();
  		
  		try {
			props.load( new FileInputStream(propsFile) );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			TestingUtil.printExceptionInfo(e);
		}
		
		System.out.println(props.getProperty(PROP_TEST_FILE_DIR) + props.getProperty(PROP_TEST_FILE_NAME+ 0));
		// TODO Auto-generated method stub
		SocketChannel channel = null;
		try 
		{
			channel = SocketChannel.open(new InetSocketAddress(InetAddress.getByName(hostName), sendSocket));
			this.NIOChannel = new TCPNIOConnection(channel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
  	}
 	
  	
  	public void startClient()
 	{
 		try
 		{
 			
 			if ( testType == 1)
 			{
	 			for (int m=0 ; m < messages; m++)
	 			{
	 				String message0 = new String(TestingUtil.readFileAsString(props.getProperty(PROP_TEST_FILE_DIR) +
	 	 	  				props.getProperty(PROP_TEST_FILE_NAME + m)));
	 				message[m] = MessageFraming.removeWhitespace(message0);
	 			
	 			}
 			}
 			else if ( testType == 2 )
 			{
 				String message0 = new String(TestingUtil.readFileAsString(props.getProperty(PROP_REQUEST_FILE)));
 				message[0] = MessageFraming.removeWhitespace(message0);
 				messages = 1;
 			}
 	  		// System.out.println(message);
 			//1. creating a socket to connect to the server
 			Thread.sleep(1000);
 			for (int i = 0; (!message.equals("stop")) && i < messages; i++)
 			{
 				Thread.sleep(200);
 				System.out.println("About to send " + message[i] + "\n from TCPClient");
 				this.NIOChannel.send(MessageFraming.START_FRAME+(message[i] +
 						MessageFraming.END_FRAME));
 			}
 		// sendMessage(TestingUtil.CLOSE_CONNECTION_FLAG);
 			
 		}
 		catch(UnknownHostException unknownHost)
 		{
 			System.err.println("You are trying to connect to an unknown host!");
 		}
 		catch(IOException ioException)
 		{
 			TestingUtil.printExceptionInfo(ioException);
 		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		/*
 		finally
 		{
 			//4: Closing connection
 			System.out.println("Finaly");
 			try
 			{
 				
 			}
 			catch(IOException ioException)
 			{
 				TestingUtil.printExceptionInfo(ioException);
 			}
 		}
 		*/
 		System.out.println("Done");
 	}
  	
  	/*
 	void sendMessage(String msg)
 	{
 		msg = new String (MessageFraming.frameString(msg));
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
 	*/
 	public static void main(String args[])
 	{
 		
 		TCPSocketClientEntity client = new TCPSocketClientEntity();
 		new Thread(client).start();
 		client.startClient();
 	}


	@Override
	public void run() 
	{
		
		String message = new String("");
		
		for (int q = 0; message.indexOf(TestingUtil.CLOSE_CONNECTION_FLAG) < 0 && q < 5; q++ )
		{
			
			message = this.NIOChannel.getMessageWithoutFrame();
			System.out.println("server> " + message);
			//String out = new String ("Sending \"Luke I am your controler\" " + message);
			//System.out.println(out);
			//this.NIOChannel.send(MessageFraming.START_FRAME + out + 
			//		MessageFraming.END_FRAME);
		}
	}
}
