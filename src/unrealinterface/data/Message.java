package unrealinterface.data;

import aiInterface.data.Data;
import aiInterface.data.DataContainer;
import aiInterface.data.conversion.Converter;

/**
 * This class is for holding a Message.
 * 
 *  This class was designed to be used with the AgentServer. This will contain
 *  the information needed to create a new Agent connection.
 * 
 * @author Glen Berseth
 *
 */
public class Message extends XMLPercept
{

	
	public Message()
	{
		super();
	}
	@Override
	public Data Convert(DataContainer convert, Converter converter, Data data)
	{
		data = converter.convert(convert);
		return data;
	}

	@Override
	public DataContainer convert(DataContainer dataContainer) 
	{
		PrologPercept prologPercept = new PrologPercept();
		Message message = new Message();
		XMLPercept XMLpercept = new XMLPercept();
		Request request = new Request();
		
		if ( dataContainer.getClass() == XMLpercept.getClass())
		{
			XMLpercept = (XMLPercept) dataContainer;
			return convert2(XMLpercept);
		}
		/*
		if ( dataContainer.getClass() == request.getClass() )
		{
			request = (Request) dataContainer;
			return convert2(request);
		}
		if ( dataContainer.getClass() == message.getClass())
		{
			message = (Message) dataContainer;
			return convert2(message);
		}
		*/
		return null;
	}
	
	private Message convert2(XMLPercept xmlPercept)
	{
		Message message = new Message();
		message.setSource(xmlPercept.getSource());
		return message;
	}
	@Override
	public DataContainer convertToDataContainer(String source)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
