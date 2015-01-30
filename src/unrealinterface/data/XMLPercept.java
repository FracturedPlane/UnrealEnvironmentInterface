package unrealinterface.data;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import aiInterface.data.Data;
import aiInterface.data.DataContainer;
import aiInterface.data.Parameter;
import aiInterface.data.Exception.MalformedSourceException;
import aiInterface.data.conversion.Converter;
import aiInterface.testing.client.TestingUtil;

/**
 * This class holds the information sent from the UnrealEngin.
 * 
 * The data is stored in the form of a XMl Document.
 * 
 * @author Glen Berseth
 *
 */
public class XMLPercept extends DataContainer implements Converter
{

	private Document XMLDoc;
	private static final Logger logger = Logger.getLogger(XMLPercept.class.getName());
	protected static final String PERCEPT_ROOT = "percept";

	
	public XMLPercept()
	{
		super("");
	}

	public XMLPercept(String xmlString) throws ParserConfigurationException, SAXException, IOException
	{
		this();
		this.XMLDoc = TestingUtil.parseXmlString(xmlString);
		/*
		if ( !this.XMLDoc.getDocumentElement().getFirstChild().getNodeName().equals(XMLPercept.PERCEPT_ROOT) )
		{
			throw new MalformedSourceException();
		}
		*/
		this.setSource(xmlString);
	}
	
	public XMLPercept(Document xmlDoc) throws ParserConfigurationException, SAXException, IOException
	{
		this(TestingUtil.XMLDOMtoString(xmlDoc));
	}
	
	public XMLPercept(XMLPercept percept) throws ParserConfigurationException, SAXException, IOException
	{
		this(percept.getXMLDoc());
	}
	
	public PrologPercept convert(XMLPercept xmlPercept)
	{
		PrologPercept prologPercept = new PrologPercept();
		// String msg = "test";
		// logger.log(Level.FINE, msg);
		Node rootNode = this.getXMLDoc().getChildNodes().item(0);
		
		// System.out.println("rootNodeName = " + rootNode.getNodeName());
		// System.out.println("rootNode.textContent = " + rootNode.getTextContent());
		prologPercept.setSource(rootNode.getNodeName().toLowerCase());
		// System.out.println("first child node = " + rootNode.getChildNodes().item(0).getTextContent());
		
		for (int i =0; i < rootNode.getChildNodes().getLength(); i++)
		{
			Node childNode = rootNode.getChildNodes().item(i);
			// System.out.println("childNode.textContent = " + childNode.getTextContent());
			// System.out.println("childNode.nodeType = " + childNode.getNodeType());
			if ( childNode.getNodeType() == Node.ELEMENT_NODE )
			{
				prologPercept.addParameter(convert(childNode));
			}
		}
		
		return prologPercept;
	}
	
	/**
	 * Converts a Xml to to a Prolog parameter.
	 * Everything is converted to lowercase to help avoid issues 
	 * in the Prolog programing language.
	 * @param node
	 * @return
	 */
	private Parameter convert( Node node )
	{
		Parameter param = null;
		
		NodeList childNodes = node.getChildNodes();
		
		if ( !node.hasChildNodes() )
		{
			// System.out.println("textContent= " + node.getTextContent());
			param = new Parameter(node.getTextContent().toLowerCase());
		}
		else
		{
			if (node.getAttributes().getLength() > 0)
			{
				param = new Parameter(node.getNodeName().toLowerCase()+ node.getAttributes().item(0).getNodeName());
			}
			else
			{
				param = new Parameter(node.getNodeName().toLowerCase());
			}
			int nodeamount = childNodes.getLength();
			
			for ( int i = 0 ; i < nodeamount; i++)
			{
				Node tmpNode = childNodes.item(i);
				// System.out.println(tmpNode.getTextContent());
				param.addParameter(convert(childNodes.item(i)));
			}
		}
		
		
		return param;
	}

	public DataContainer convert(DataContainer dataContainer)
	{
		System.out.println("DataContainer Convert + " + dataContainer.getClass().getName());
		
		DataContainer container = null;
		try {
			container = this.convert( new XMLPercept((XMLPercept)dataContainer));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return container;
	}

	public Document getXMLDoc()
	{
		return this.XMLDoc;
	}

	@Override
	public DataContainer convertToDataContainer(String source)
	{
		XMLPercept xmlPercept = null;
		try
		{
			xmlPercept = new XMLPercept(source);
		} catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmlPercept;
	}

	@Override
	public Data Convert(DataContainer convert, Converter converter, Data data)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
