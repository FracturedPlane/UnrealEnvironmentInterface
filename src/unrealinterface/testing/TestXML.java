package unrealinterface.testing;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import aiInterface.testing.client.TestingUtil;

import unrealinterface.data.Request;
import unrealinterface.data.XMLPercept;

public class TestXML
{

	public static void main(String[] args)
	{
		
		String filename = "XML/requests/newEntity.xml";
		File xmlFile = new File(filename);
		
		XMLPercept xmlPercept = new XMLPercept();
		try
		{
			xmlPercept = new Request(TestingUtil.parseXmlFile(filename));
			
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println(xmlPercept.getXMLDoc().getFirstChild().getNodeName());
	}
}
