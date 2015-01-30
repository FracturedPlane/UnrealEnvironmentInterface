package unrealinterface.data;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import aiInterface.data.Data;
import aiInterface.data.Exception.MalformedSourceException;
import aiInterface.data.conversion.Converter;

/**
 * Created to hold a <code>Request</code> to create a new Entity <-> Agent
 * relationship on the EntityServer side.
 * 
 * The data is stored in the form of an XML Document.
 * 
 * @author Glen Berseth
 *
 */
public class Request extends XMLPercept
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7752554928955908423L;


	public Request()
	{
		super();
	}
	
	public Request(String request) throws ParserConfigurationException, SAXException, IOException
	{
		super(request);
	}
	
	public Request(Document doc) throws ParserConfigurationException, SAXException, IOException
	{
		super(doc);
	}
	
	public Request(Request request) throws ParserConfigurationException, SAXException, IOException
	{
		this(request.getXMLDoc());
	}
	
	public Data convert(Request convert)
	{
		Data data = null;
		
		return data;
	}
	
}
