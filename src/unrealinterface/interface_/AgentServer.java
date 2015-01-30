package unrealinterface.interface_;

import unrealinterface.data.Request;

/**
 * Class designed to be used to facilitate the an AgentServer. 
 * 
 * The Agent server should provide facilities to create new agents, connect them
 * to Entitys and do the reverse.
 * 
 * @author Glen Berseth
 *
 */
public interface AgentServer
{

	public int processRequest(Request request);
	
	public int createAgent(Request request);
	
	public int destroyAgent(int port);
	
	
}
