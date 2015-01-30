package unrealinterface.testing;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import aiInterface.data.DataContainer;
import aiInterface.testing.client.TestingUtil;
import unrealinterface.data.XMLPercept;

public class TestDataContainer 
{

	public static void main(String args[])
	{
		String filename = "XML/GlensNemesis.xml";
		File xmlFile = new File(filename);
		
		// System.out.println(TestingUtil.XMLDOMtoString(TestingUtil.parseXmlFile(xmlFile)));
		
		DataContainer xmlPercept = new XMLPercept();
		try
		{
			xmlPercept = new XMLPercept(TestingUtil.parseXmlFile(filename));
			
			
			
		}
		catch (IOException e)
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
		
		DataContainer prologPercept = xmlPercept.convert(xmlPercept);
		
		System.out.println(prologPercept.getSource());
		
	}
	
}
