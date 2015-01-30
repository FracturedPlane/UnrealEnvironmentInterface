package unrealinterface.interface_;

import unrealinterface.data.Request;

/**
 * Class designed to support the functionality of a EntityServer.
 * 
 * The server should support the creation and deletion of Entitys in the system.
 *  
 * @author glen
 *
 */
public interface EntityServer
{

	public int sendRequest(Request request);
	
	public int entityCreated(Request request);
	
	public int entityDestroyed(int port);
}
