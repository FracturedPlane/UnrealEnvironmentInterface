package unrealinterface.data;

import java.util.HashMap;

import aiInterface.data.Data;
import aiInterface.data.DataContainer;
import aiInterface.data.conversion.Converter;

/**
 * Designed to hold a IndiGologCommand
 * 
 * in the form
 * 
 * "command([key, value], [key, value], .... , [key, value] )"
 * 
 * @author Glen Berseth
 *
 */
public class UnrealIndiGologCommand extends DataContainer
{

	private static String OPEN_ROUND_BRACKET = "(";
	private static String CLOSE_ROUND_BRACKET = ")";
	private static String OPEN_SQUARE_BRACKET = "[";
	private static String CLOSE_SQUARE_BRACKET = "]";
	private static String COMMA = ",";
	private static String NAME = "name";
	private HashMap<String, String> attributeMap;
	
	public UnrealIndiGologCommand()
	{
		super();// TODO Auto-generated constructor stub
		attributeMap = new HashMap<String, String>();
	}
	
	public UnrealIndiGologCommand(String source)
	{
		super(source);// TODO Auto-generated constructor stub
		attributeMap = new HashMap<String, String>();
		this.setAttributesFromString(source);
	}
	
	public void setAttributesFromString(String attributes)
	{
		String afterwrite = new String(attributes);
		int commandNameIndex = afterwrite.indexOf(UnrealIndiGologCommand.OPEN_ROUND_BRACKET);
		if ( commandNameIndex < 0)
		{
			// Message does not contain any attributes
			// just assign name
			this.attributeMap.put(UnrealIndiGologCommand.NAME, afterwrite);
			return;
			
		}
		String commandName = afterwrite.substring(0, commandNameIndex);
		this.attributeMap.put(UnrealIndiGologCommand.NAME, commandName);
		
		String attributesList = afterwrite.substring(
				afterwrite.indexOf(UnrealIndiGologCommand.OPEN_ROUND_BRACKET));
	
		this.attributeMap.putAll(this.getAttributesFromString(attributesList));
	}
	
	private HashMap<String, String> getAttributesFromString(String source)
	{
		HashMap<String, String> map = new HashMap<String, String>();
		// Loop untill ")" is encountered
		while ( source.indexOf(UnrealIndiGologCommand.CLOSE_SQUARE_BRACKET) > 0)
		{
			String nextAttributeValuePair = source.substring(
					source.indexOf(UnrealIndiGologCommand.OPEN_SQUARE_BRACKET), 
					source.indexOf(UnrealIndiGologCommand.CLOSE_SQUARE_BRACKET) + 1);
			this.attributeMap.put(this.getAttributeName(nextAttributeValuePair),
					this.getValueFromString(nextAttributeValuePair));
			source = source.substring(source.indexOf(UnrealIndiGologCommand.CLOSE_SQUARE_BRACKET) + 1);
		}
		
		return map;
		
	}
	
	/**
	 * Expects something of the form [attribute, value]
	 * @param source
	 * @return
	 */
	private String getAttributeName(String source)
	{
		return source.substring(source.indexOf(UnrealIndiGologCommand.OPEN_SQUARE_BRACKET) + 1,
				source.indexOf(UnrealIndiGologCommand.COMMA));
	}
	
	/**
	 * Expects something of the form [attribute, value].
	 * 	Is sensitive to the extra space after the comma.
	 * @param source
	 * @return
	 */
	private String getValueFromString(String source)
	{
		return source.substring(source.indexOf(UnrealIndiGologCommand.COMMA) + 2,
				source.indexOf(UnrealIndiGologCommand.CLOSE_SQUARE_BRACKET));
	}
	
	public String getName()
	{
		return this.attributeMap.get(UnrealIndiGologCommand.NAME);
	}
	
	public HashMap<String, String> getAttributeValuePairs()
	{
		return this.attributeMap;
	}
	
	@Override
	public DataContainer convert(DataContainer dataContainer)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataContainer convertToDataContainer(String source)
	{
		UnrealIndiGologCommand command = new UnrealIndiGologCommand(source);
		return command;
	}


}
