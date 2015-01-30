package unrealinterface.testing;

import unrealinterface.data.EntityControllerCommand;
import unrealinterface.data.UnrealIndiGologCommand;

public class UnrealIndiGologCommandTester
{

	
	public static void main(String[] args)
	{
		// String testCommandSource = "attack([type, state_change], [target, glen], [method, agressive])";
		String testCommandSource = "look";
		String testAttributeValuePair = "([type, state_change])";
		UnrealIndiGologCommand command = new UnrealIndiGologCommand();
		EntityControllerCommand ecCommand = new EntityControllerCommand();
		command.setAttributesFromString(testCommandSource);
		
		System.out.println("test = " + testCommandSource);
		System.out.println("Command name = " + command.getName());
		System.out.println("Attribute map = " + command.getAttributeValuePairs());
		// System.out.println("Attribute = " + command.getAttributeName(testAttributeValuePair));
		// System.out.println("Value = " + command.getValueFromString(testAttributeValuePair));
		
		EntityControllerCommand eCommand = (EntityControllerCommand) ecCommand.convert(command);
		
		System.out.println("EntityCommand = " + eCommand.getSource());
		
		
	}
}
