package unrealinterface.interface_;

import aiInterface.connection.EntityTCPChannelServer;
import unrealinterface.data.Request;

/*
 * The EntityServer
	Will accept requests to create a new EntityConnection on the EIS 
	computer that the new UnrealEntity can connect to ( I think ). 
	Then sends a request to the Agent server to create a new Agent. 
	After the AgentServer has created a new agent the newly created 
	Entity and Agent will be associated in the EIS. This will allow 
	the messaging from one to the other to be done easily.
 */
public class TCPEntityServer implements EntityServer
{

	private UDPAgentServer agentServer;
	private EntityTCPChannelServer entityTCPChannelServer;
	public TCPEntityServer()
	{
		super();
	}
	
	public TCPEntityServer(EntityTCPChannelServer entityChannelServer)
	{
		this.setEntityTCPChannelServer(entityChannelServer);
	}
	
	@Override
	/*
	 * 
	 */
	public int entityCreated(Request request)
	{
		this.agentServer.createAgent(request);
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int entityDestroyed(int port)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	/**
	 * For now just called entityCreated.
	 */
	public int sendRequest(Request request)
	{
		this.entityCreated(request);
		return 0;
	}

	public void setEntityTCPChannelServer(EntityTCPChannelServer server)
	{
		this.entityTCPChannelServer = server;
	}
	
	public void setAgentServer(AgentServer agentServer)
	{
		this.agentServer = this.agentServer;
	}
}
