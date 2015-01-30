package unrealinterface.testing;


import java.io.IOException;
import java.net.UnknownHostException;

import aiInterface.connection.*;
import aiInterface.handler.AgentConnectionHandler;
import aiInterface.handler.AgentHandler;
import aiInterface.testing.client.TestingUtil;

@Deprecated
/*
 * Used to test deprecated UDPConnection class
 */
public class TestConnection 
{
	
	private final String host = "localhost";
	private final int portnumber = 3850;
	
	public static void main(String[] args)
	{
		// ConnectionHandler connectionListener = new Conn
		
		final String host = "localhost";
		final int portnumber = 3850;
		final AgentHandler agentConnectionHandler = new AgentConnectionHandler();
		UDPAgentConnection botConnection = null;
	
		try
		{
			botConnection = new UDPAgentConnection( agentConnectionHandler, host, portnumber);
		} 
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		botConnection.act("Whoooo Hoooo!!!");
		// botConnection.act(TestingUtil.CLOSE_CONNECTION_FLAG);
		try
		{
			botConnection.getOutputStream().write(TestingUtil.CLOSE_CONNECTION_FLAG.getBytes());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
