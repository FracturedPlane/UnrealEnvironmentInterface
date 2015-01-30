package unrealinterface.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import aiInterface.data.Data;
import aiInterface.data.DataContainer;
import aiInterface.data.conversion.Converter;

/**
 * This Class is designed to hold a Entity Controller Command
 * 
 * Entity Controller Commands are sent to the UnrealEngine. This class can parse
 * an UnrealIndiGologCommand that is sent from the IndiGolog Agent will be 
 * converted into this before it is sent to the UnrealEngin.
 * 
 * Command is stored in the form
 * "type [key, value] [key, value] ...... [key, value]"
 * 
 * @author Glen Berseth
 *
 */
public class EntityControllerCommand extends DataContainer
{

	private HashMap<String, String> attributeMap;
	private String type;
	
	
	public EntityControllerCommand()
	{
		super();
		this.attributeMap = new HashMap<String, String>();
	}
	
	
	
	@Override
	public DataContainer convertToDataContainer(String source)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataContainer convert(DataContainer dataContainer)
	{
		// TODO Auto-generated method stub
		UnrealIndiGologCommand command = new UnrealIndiGologCommand();
	
		if ( command.getClass() == dataContainer.getClass())
		{
			UnrealIndiGologCommand uiCommand = (UnrealIndiGologCommand) dataContainer;
			return convert2(uiCommand);
		}
		return null;
	}
	
	private EntityControllerCommand convert2(UnrealIndiGologCommand command)
	{
		EntityControllerCommand entityCommand = new EntityControllerCommand();
		
		entityCommand.setType(new String(command.getName()));
		entityCommand.setAttributeMap(new HashMap<String, String>(command.getAttributeValuePairs()));
		
		return entityCommand;
	}

	public HashMap<String, String> getAttributeMap()
	{
		return attributeMap;
	}

	public void setAttributeMap(HashMap<String, String> attributeMap)
	{
		this.attributeMap = attributeMap;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getSource()
	{
		String out = "";
		Iterator<Entry <String, String>> iter = this.attributeMap.entrySet().iterator();
		
		out = out + this.getType();
		
		while ( iter.hasNext() )
		{
			Entry<String, String> entry = iter.next();
			out = out + " [" + entry.getKey() + ", " + entry.getValue() + "]";
		}
		
		return out;
			
	}



	@Override
	public Data Convert(DataContainer convert, Converter converter, Data data)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
