package unrealinterface.testing;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import aiInterface.testing.client.TestingUtil;

import unrealinterface.data.PrologPercept;
import unrealinterface.data.XMLPercept;

public class TestXMLPercept
{
	
	public static void main(String args[])
	{
		String filename = "XML/nemesie/nemesis0.xml";
		File xmlFile = new File(filename);
		
		// System.out.println(TestingUtil.XMLDOMtoString(TestingUtil.parseXmlFile(xmlFile)));
		
		XMLPercept xmlPercept = new XMLPercept();
		try
		{
			xmlPercept = new XMLPercept(TestingUtil.parseXmlFile(filename));
			
			
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
		
		PrologPercept prologPercept = xmlPercept.convert(xmlPercept);
		
		System.out.println("prologPercept = " + prologPercept.getSource());
	}
}
