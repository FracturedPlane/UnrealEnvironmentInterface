package unrealinterface.data;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import aiInterface.data.DataContainer;
import aiInterface.testing.client.TestingUtil;

public class BMLMessage extends XMLPercept
{

	public BMLMessage(String source) throws ParserConfigurationException, SAXException, IOException
	{
		super(source);
	}
	
	public BMLMessage(Document xmlDoc) throws ParserConfigurationException, SAXException, IOException
	{
		this(TestingUtil.XMLDOMtoString(xmlDoc));
	}
	
	public BMLMessage(XMLPercept percept) throws ParserConfigurationException, SAXException, IOException
	{
		this(percept.getXMLDoc());
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
}
