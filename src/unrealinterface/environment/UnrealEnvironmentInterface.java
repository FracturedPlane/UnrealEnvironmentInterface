package unrealinterface.environment;

import java.util.LinkedList;

import unrealinterface.interface_.UnrealAgent;
import unrealinterface.interface_.UnrealEntity;

import aiInterface.data.Action;
import aiInterface.data.EnvironmentCommand;
import aiInterface.data.Percept;
import aiInterface.environment.UnrealEI;
import aiInterface.intreface.Agent;
import aiInterface.intreface.Entity;
import eis.exceptions.ActException;
import eis.exceptions.EntityException;
import eis.exceptions.ManagementException;
import eis.exceptions.NoEnvironmentException;
import eis.exceptions.PerceiveException;
import eis.exceptions.RelationException;

/**
 * This is a slightly modified version of the EnvironmentInterfaceStandard
 * 
 * The modified part is to support Agent and Entity classes rather then
 * them just being <code>Strings</code> with the Interface doing all of the work
 * of keeping track of everything. This allows for a much better Interface that
 * is more re-usable.
 * 
 * @author Glen Berseth
 *
 */
public class UnrealEnvironmentInterface extends UnrealEI
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3334630816442614211L;

	public UnrealEnvironmentInterface()
	{
		super();
	}
	
	public void addEntity(Entity entity) throws EntityException
	{
		super.addEntity(entity);
	}
	
	public void associateEntity(Agent agent, Entity entity) throws RelationException 
	{
		
		// check if exists
		if( !this.getEntities().contains(entity) )
			throw new RelationException("Entity \"" + entity.getIdentifier() + "\" does not exist!!!");

		if( !this.getAgents().contains(agent) )
			throw new RelationException("Agent \"" + agent.getName() + "\" has not been registered!");

		// check if associated
		if( !this.getFreeEntities().contains(entity) )
			throw new RelationException("Entity \"" + entity.getIdentifier() + "\" has already been associated!");
	
		// remove
		this.getFreeEntities().remove(entity);
		
		// associate
		UnrealAgent unrealAgent = (UnrealAgent) agent;
		UnrealEntity unrealEntity = (UnrealEntity) entity;
		
		unrealAgent.associateEntity(entity);
		unrealEntity.associateAgent(agent);
	}

	@Override
	public boolean isConnected()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void manageEnvironment(EnvironmentCommand command)
			throws ManagementException, NoEnvironmentException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void release()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected LinkedList<Percept> getAllPerceptsFromEntity(Entity entity)
			throws PerceiveException, NoEnvironmentException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<Percept> performAction(Agent agent, Action action,
			Entity... entities) throws ActException, NoEnvironmentException
	{
		// TODO Auto-generated method stub
		return null;
	}
	

}
