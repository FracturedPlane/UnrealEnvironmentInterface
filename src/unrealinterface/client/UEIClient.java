package unrealinterface.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import aiInterface.connection.EntityTCPChannelServer;
import aiInterface.connection.TCPAgentConnection;
import unrealinterface.data.EntityControllerCommand;
import unrealinterface.data.GologExogEvent;
import unrealinterface.data.UnrealIndiGologCommand;
import unrealinterface.data.XMLPercept;
import unrealinterface.environment.UnrealEnvironmentInterface;
import aiInterface.handler.AgentConnectionHandler;
import aiInterface.handler.AgentServerConnectionHandler;
import aiInterface.handler.EntityConnectionHandler;
import aiInterface.handler.EntityServerConnectionHandler;
import unrealinterface.interface_.UnrealAgent;
import unrealinterface.interface_.UnrealEntity;
import aiInterface.testing.client.TestingUtil;

/**
 * This is an example of how to use the Interface system.
 * 
 * This is a functional example of an Interface designed to work with the
 * UnrealEngine system. 
 * 
 *  Although this system is meant to support a multi-agent system currently
 *  it only implements a 1-to-1 relationship using the AgentServer and
 *  EntityServer to connect directly to an individual Agent and Entity.
 * 
 * @author Glen Berseth
 *
 */
public class UEIClient
{

	public static void main(String[] args) throws UnknownHostException
	{
		Scanner input = new Scanner( System.in );
		String glensNemesis = "GlensNemesis";
		String heather = "heather";
		String jim = "jim";
		String bob = "bob";
		String agenthost = "localhost";
		String agentAcknowledge = "ack\n";
		// String agenthost = "192.168.0.184";
		final int agentportnumber = 3840;
		final int entityPortNumber = 3850;
		final int agentportnumber2 = 3841;
		final int entityPortNumber2 = 3851;
		final int agentportnumber3 = 3842;
		final int entityPortNumber3 = 3852;
		final int agentportnumber4 = 3843;
		final int entityPortNumber4 = 3853;
		
		UnrealEnvironmentInterface unrealEI = new UnrealEnvironmentInterface();
	
		
		AgentConnectionHandler agentServerConnectionHandler = new AgentServerConnectionHandler() ;
		EntityConnectionHandler entityServerConnectionHandler = new EntityServerConnectionHandler();
		
		AgentConnectionHandler agentServerConnectionHandler2 = new AgentServerConnectionHandler() ;
		EntityConnectionHandler entityServerConnectionHandler2 = new EntityServerConnectionHandler();
		
		AgentConnectionHandler agentServerConnectionHandler3 = new AgentServerConnectionHandler() ;
		EntityConnectionHandler entityServerConnectionHandler3 = new EntityServerConnectionHandler();
		
		AgentConnectionHandler agentServerConnectionHandler4 = new AgentServerConnectionHandler() ;
		EntityConnectionHandler entityServerConnectionHandler4 = new EntityServerConnectionHandler();
		
		/*
		 * The Agent connection is going to be a server that waits for incoming
		 * connection request and create one for each agent that needs to be
		 * created for each entity in the environment. SO the APL is the server
		 * and this interface is the client to that Server
		 */
		TCPAgentConnection agentServerConnection = null;
		TCPAgentConnection agentServerConnection2 = null;
		TCPAgentConnection agentServerConnection3 = null;
		TCPAgentConnection agentServerConnection4 = null;
		
		/*
		 * In the case of the entity connection it will be a server
		 * waiting for signals from the environment that entities have been created
		 * and that an entity needs a agent to control it. 
		 */
		EntityTCPChannelServer entityServerConnection = null;
		EntityTCPChannelServer entityServerConnection2 = null;
		EntityTCPChannelServer entityServerConnection3 = null;
		EntityTCPChannelServer entityServerConnection4 = null;
		
		
		try
		{
			entityServerConnection =  new EntityTCPChannelServer(entityPortNumber, 10, entityServerConnectionHandler);
			entityServerConnection2 =  new EntityTCPChannelServer(entityPortNumber2, 10, entityServerConnectionHandler2);
			entityServerConnection3 =  new EntityTCPChannelServer(entityPortNumber3, 10, entityServerConnectionHandler3);
			entityServerConnection4 =  new EntityTCPChannelServer(entityPortNumber4, 10, entityServerConnectionHandler4);
			/*
			 * When messages are received they will be converted to XMLPercepts
			 */
			entityServerConnection.setContainerType(new XMLPercept());
			entityServerConnection2.setContainerType(new XMLPercept());
			entityServerConnection3.setContainerType(new XMLPercept());
			entityServerConnection4.setContainerType(new XMLPercept());
			/*
			 * When notified of a DataContainer to convert it will convert it to XMLPercept
			 */
			entityServerConnection.setConverter(new EntityControllerCommand());
			entityServerConnection2.setConverter(new EntityControllerCommand());
			entityServerConnection3.setConverter(new EntityControllerCommand());
			entityServerConnection4.setConverter(new EntityControllerCommand());
			
			agentServerConnection = new TCPAgentConnection(agentportnumber, 10, agentServerConnectionHandler);
			agentServerConnection2 = new TCPAgentConnection(agentportnumber2, 10, agentServerConnectionHandler2);
			agentServerConnection3 = new TCPAgentConnection(agentportnumber3, 10, agentServerConnectionHandler3);
			agentServerConnection4 = new TCPAgentConnection(agentportnumber4, 10, agentServerConnectionHandler4);
			
			agentServerConnection.setAckString(agentAcknowledge);
			agentServerConnection2.setAckString(agentAcknowledge);
			agentServerConnection3.setAckString(agentAcknowledge);
			agentServerConnection4.setAckString(agentAcknowledge);
			/*
			 * When messages are received they will be converted to XMLPercepts
			 */
			agentServerConnection.setContainerType(new UnrealIndiGologCommand());
			agentServerConnection2.setContainerType(new UnrealIndiGologCommand());
			agentServerConnection3.setContainerType(new UnrealIndiGologCommand());
			agentServerConnection4.setContainerType(new UnrealIndiGologCommand());
			/*
			 * When notified of a DataContainer to convert it will convert it to XMLPercept
			 */
			agentServerConnection.setConverter(new GologExogEvent());
			agentServerConnection2.setConverter(new GologExogEvent());
			agentServerConnection3.setConverter(new GologExogEvent());
			agentServerConnection4.setConverter(new GologExogEvent());
			
			// agentServer = new UDPAgentServer(agentClientConnection);

			// entityServerConnection.setAgentConnection(agentServerConnection);
			
			// TODO
			// Need to make EntityTCPChannelServer a subclass of AgentConnection
			// agentServerConnection.setEntityConnection(entityServerConnection);
			
			new Thread(entityServerConnection).start();
			new Thread(agentServerConnection).start();
			new Thread(entityServerConnection2).start();
			new Thread(agentServerConnection2).start();
			new Thread(entityServerConnection3).start();
			new Thread(agentServerConnection3).start();
			new Thread(entityServerConnection4).start();
			new Thread(agentServerConnection4).start();
			
			System.out.println("Done creating Connections/Threads");
			
		} catch (UnknownHostException e1)
		{
			// TODO Auto-generated catch block
			TestingUtil.printExceptionInfo(e1);
			e1.printStackTrace();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			TestingUtil.printExceptionInfo(e1);
			e1.printStackTrace();
		}
		
		System.out.println("About to create Agent and Entity");
		UnrealAgent glensNemesisAgent = new UnrealAgent(glensNemesis, agentServerConnection);
		UnrealEntity glensNemesisEntity = new UnrealEntity(glensNemesis, entityServerConnection );
		UnrealAgent glensNemesisAgent2 = new UnrealAgent(heather, agentServerConnection2);
		UnrealEntity glensNemesisEntity2 = new UnrealEntity(heather, entityServerConnection2 );
		UnrealAgent glensNemesisAgent3 = new UnrealAgent(jim, agentServerConnection3);
		UnrealEntity glensNemesisEntity3 = new UnrealEntity(jim, entityServerConnection3 );
		UnrealAgent glensNemesisAgent4 = new UnrealAgent(bob, agentServerConnection4);
		UnrealEntity glensNemesisEntity4 = new UnrealEntity(bob, entityServerConnection4 );
		System.out.println("Done creating Interfacers");
		
		/*
		 * Start the Entity Servre
		 */
		
		
		
		try
		{
			unrealEI.registerAgent(glensNemesisAgent);
			unrealEI.registerAgent(glensNemesisAgent2);
			unrealEI.registerAgent(glensNemesisAgent3);
			unrealEI.registerAgent(glensNemesisAgent4);
			// unrealEI.attachAgentListener(glensNemesisAgent, agentServerConnectionHandler);
			unrealEI.addEntity(glensNemesisEntity);
			unrealEI.associateEntity(glensNemesisAgent, glensNemesisEntity);
			unrealEI.addEntity(glensNemesisEntity2);
			unrealEI.associateEntity(glensNemesisAgent2, glensNemesisEntity2);
			unrealEI.addEntity(glensNemesisEntity3);
			unrealEI.associateEntity(glensNemesisAgent3, glensNemesisEntity3);
			unrealEI.addEntity(glensNemesisEntity4);
			unrealEI.associateEntity(glensNemesisAgent4, glensNemesisEntity4);
			
			// These lines make no sense...
			/*
			 * 
			 */
			// unrealEI.attachAgentListener(glensNemesisAgent, agentServerConnectionHandler);
			// unrealEI.attachEntityListener(glensNemesisEntity, entityServerConnectionHandler);
			
			/*
			 * These add the Interfacer relations to the Handlers. 
			 */
			System.out.println("About to add Interfacer refferences to Connection Handlers");
			agentServerConnectionHandler.setAgent(glensNemesisAgent);
			entityServerConnectionHandler.setEntity(glensNemesisEntity);
			agentServerConnectionHandler2.setAgent(glensNemesisAgent2);
			entityServerConnectionHandler2.setEntity(glensNemesisEntity2);
			agentServerConnectionHandler3.setAgent(glensNemesisAgent3);
			entityServerConnectionHandler3.setEntity(glensNemesisEntity3);
			agentServerConnectionHandler4.setAgent(glensNemesisAgent4);
			entityServerConnectionHandler4.setEntity(glensNemesisEntity4);
			System.out.println("Done adding Interfacer refferences to Connection Handlers");
			
			
			// unrealEI.attachEntityListener();
			
			
			// Allows program to block.
			String message = input.nextLine();
			message.toString();
			
			// entityServerConnection.getEntityServerConnection().getConnection().write(new ByteBuffer(message.getBytes()));
			
		}
		catch ( Exception e )
		{
			System.out.println("Error");
			TestingUtil.printExceptionInfo(e);
			
		}
	}
	
}
