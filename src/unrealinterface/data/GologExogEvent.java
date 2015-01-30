package unrealinterface.data;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import aiInterface.data.Data;
import aiInterface.data.DataContainer;
import aiInterface.data.conversion.Converter;

/**
 * Class designed to hold a Golog Exogenous Action.
 * 
 * Will be stored in the form
 * "event_name(data)"
 * 
 * Example:
 * attacked( glen, jim )
 * event_name = attacked
 * data = " glen, jim"
 * 
 * @author Glen Berseth
 *
 */
public class GologExogEvent extends DataContainer
{

	private String eventName;
	private String eventData;
	
	private final static String EVENT = "event";
	
	public GologExogEvent()
	{
		super();
		this.setEventName("");
		this.setEventData("");
	}
	
	public String getEventName() {
		return new String(eventName);
	}


	public void setEventName(String eventName) {
		this.eventName = new String(eventName);
	}


	public String getEventData() {
		return new String(eventData);
	}


	public void setEventData(String eventData) {
		this.eventData = new String(eventData);
	}

	
	public String getSource()
	{
		String out = "";
		
		out = this.getEventName() + "(" + this.getEventData() + ")";
		// IndiGolog driver expects messages to end with newline.
		return out + "\n";
	}

	public GologExogEvent convert(XMLPercept xmlPercept)
	{
		String regex1 = "[0-9]";
		GologExogEvent goExogEvent = new GologExogEvent();
		Document xmlDoc = xmlPercept.getXMLDoc();
		// Had an error here.
		Node rootNode = xmlDoc.getDocumentElement();
		String rootName = rootNode.getNodeName();
		
		if (rootName.equals(EVENT))
		{
			String eventName = rootNode.getFirstChild().getNodeName();
			goExogEvent.setEventName(eventName.toLowerCase());
			String eventData = rootNode.getFirstChild().getFirstChild().getTextContent().replaceAll(regex1, "");
			Node childData = rootNode.getFirstChild().getFirstChild().getNextSibling();
			while ( childData != null )
			{
				eventData = eventData + ", " + childData.getTextContent().replaceAll(regex1, "");
				childData = childData.getNextSibling();
			}
			goExogEvent.setEventData(eventData.toLowerCase());
		}
		
		return goExogEvent;
	}
	
	@Override
	public DataContainer convert(DataContainer dataContainer) 
	{
		XMLPercept xp = new XMLPercept();
		
		xp = (XMLPercept) dataContainer;
		return this.convert(xp);
	}

	@Override
	public DataContainer convertToDataContainer(String source) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Data Convert(DataContainer convert, Converter converter, Data data)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
