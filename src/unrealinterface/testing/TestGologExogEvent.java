package unrealinterface.testing;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import unrealinterface.data.GologExogEvent;
import unrealinterface.data.XMLPercept;

public class TestGologExogEvent 
{

	public static void main(String[] args)
	{
		String xmlStringPercept = "<event><attacked><target>glen</target></attacked></event>";
		String xmlStringPercept2 = "<event><attacked><target>glen</target><target2>jim</target2></attacked></event>";
		XMLPercept xmlPercept = null;
		XMLPercept xmlPercept2 = null;
		GologExogEvent goExogEvent = new GologExogEvent();
		try 
		{
			xmlPercept = new XMLPercept(xmlStringPercept);
			xmlPercept2 = new XMLPercept(xmlStringPercept2);
		} 
		catch (ParserConfigurationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GologExogEvent goExogEvent2 = goExogEvent.convert(xmlPercept);
		
		System.out.println(goExogEvent2.getSource());
		
		goExogEvent2 = goExogEvent.convert(xmlPercept2);
		
		System.out.println(goExogEvent2.getSource());
	}
}
