package unrealinterface.testing;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import aiInterface.data.Data;
import aiInterface.testing.client.TestingUtil;
import unrealinterface.data.PrologPercept;
import unrealinterface.data.XMLPercept;

public class TestDataConversion 
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
			e.printStackTrace();
		} catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		} catch (SAXException e)
		{
			e.printStackTrace();
		}

		PrologPercept prologPercept = new PrologPercept();
		Data prologPercept2 = new PrologPercept();
		
		prologPercept2 = prologPercept.Convert(xmlPercept, prologPercept, prologPercept);
	
		System.out.println("prologPercept2.getSource() = \n" + prologPercept2.getSource());
	}
	

}
