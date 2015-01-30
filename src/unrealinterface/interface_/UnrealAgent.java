package unrealinterface.interface_;

import java.io.IOException;

import org.apache.log4j.Logger;

import aiInterface.connection.AgentConnection;
import aiInterface.data.DataContainer;
import aiInterface.testing.client.TestingUtil;
import aiInterface.intreface.Agent;
import aiInterface.logging.AIIEISLogger;

/**
 * An UnrealAgent is a Class designed to facilitate the easy access and 
 * organisation of the Unreal Interface System. 
 * 
 * @author Glen Berseth
 *
 */
public class UnrealAgent extends Agent
{
	static Logger logger = AIIEISLogger.getLogger("UnrealAgent.class");
	
	public UnrealAgent(String name, AgentConnection agentConnection)
	{
		super(name);
		this.setConnection(agentConnection);
	}
	
	@Override
	/**
	 * Implementation of the send method form the Interfacer interface.
	 * Simply calls the send methods of this classes connection.
	 * 
	 */
	public int send(String message)
	{
		logger.info(" name:" + this.getName() + ": " + message);
		try
		{
			
			this.getConnection().send(message + "\n");
		}
		catch (Exception e)
		{
			TestingUtil.printExceptionInfo(e);
		}
		
		return 0;
	}

	@Override
	/**
	 * Implements the message getting of Interfacer interface.
	 * This simply calls the getMessage() method in its connection class
	 * which block until there is a message available.
	 */
	public String getMessage()
	{
		String out = "";
		try 
		{
			out = this.getConnection().getMessage();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return out;

	}

	@Override
	/**
	 * Implementation of the send method form the Interfacer interface.
	 * Simply calls the send methods of this classes connection.
	 * 
	 */
	public int send(DataContainer dataContainer) 
	{
		logger.info(" name:" + this.getName() + ": " + dataContainer.getSource());
		try
		{
			return this.getConnection().send(dataContainer);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
		
}
