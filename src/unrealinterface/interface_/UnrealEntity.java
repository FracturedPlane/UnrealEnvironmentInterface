package unrealinterface.interface_;

import java.io.IOException;

import org.apache.log4j.Logger;

import aiInterface.connection.EntityConnection;
import aiInterface.data.DataContainer;
import aiInterface.intreface.Entity;
import aiInterface.logging.AIIEISLogger;
import aiInterface.testing.client.TestingUtil;

/**
 * This class is a Customized Entity. Designed specifically to work with the 
 * UnrealEngine.
 *  
 * @author Glen Berseth
 *
 */
public class UnrealEntity extends Entity
{
	static Logger logger = AIIEISLogger.getLogger("UnrealEntity.class");
	public UnrealEntity(String identifier, EntityConnection entityConnection)
	{
		super(identifier);
		this.setConnection(entityConnection);
		// entityConnection.no
	}
	
	public int send(String message)
	{
		logger.info(" name:" + this.getIdentifier() + ": " + message);
		try
		{
			this.getConnection().send(message);
		}
		catch (Exception e)
		{
			TestingUtil.printExceptionInfo(e);
		}
		
		return 0;
	}

	
	public String getMessage()
	{
		String out = null;	
		try 
		{
			return this.getConnection().getMessage();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}

	@Override
	/**
	 * Not implemented yet.
	 */
	public int send(DataContainer dataContainer) throws IOException 
	{
		// TODO Auto-generated method stub
		logger.info(" name:" + this.getIdentifier() + ": " + dataContainer.getSource());
		return this.getConnection().send(dataContainer);
		
	}
	
}
