package unrealinterface.interface_;

import aiInterface.connection.UDPAgentServerConnection;
import aiInterface.data.Data;
import unrealinterface.data.PrologPercept;
import unrealinterface.data.Request;

public class UDPAgentServer implements AgentServer
{
	
	private UDPAgentServerConnection udpAgentServerConnection;
	
	public UDPAgentServer()
	{
		
	}
	
	public UDPAgentServer(UDPAgentServerConnection connection)
	{
		this.udpAgentServerConnection = connection;
	}

	@Override
	public int createAgent(Request request)
	{
		// TODO Auto-generated method stub
		// Convert to Prolog first
		Data data = null;
		PrologPercept prologPercept = new PrologPercept();
		data = prologPercept.Convert(request, prologPercept, prologPercept);
		
		this.udpAgentServerConnection.send(data.getSource());
		return 0;
	}

	@Override
	public int destroyAgent(int port)
	{
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	/**
	 * Ment to figure out what kind of Request is being requested and then
	 * the proper method is used to process this request
	 * 
	 * For now just directly calls createAgent().
	 */
	public int processRequest(Request request)
	{
		createAgent(request);
		return 0;
	}
	
	public void setAgentServerConnection(UDPAgentServerConnection serverConnection)
	{
		this.udpAgentServerConnection = serverConnection;
	}
	
	public UDPAgentServerConnection getAgentServerConnection()
	{
		return this.udpAgentServerConnection;
	}

}
