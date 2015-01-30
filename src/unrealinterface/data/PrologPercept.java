package unrealinterface.data;

import java.util.LinkedList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import aiInterface.data.Data;
import aiInterface.data.DataContainer;
import aiInterface.data.Parameter;
import aiInterface.data.conversion.Converter;

/**
 * This class is designed Hold a Prolog Percept.
 * 
 * A PrologPercept is stored in a prolog list like structure in the form
 * 
 * "[item, [item, item], [item, [item, ...... ]"
 * @author Glen Berseth
 *
 */
public class PrologPercept extends DataContainer
{
	
	private LinkedList<Parameter> params;

	public PrologPercept()
	{
		super("");
		this.params = new LinkedList<Parameter>();
	}
	
	public PrologPercept(String prologPercept)
	{
		this();
		this.parse(new String (prologPercept));
	}
	
	protected void parse(String prologPercept)
	{
		
	}
	

	public void addParameter(Parameter param)
	{
		this.params.add(param);
	}
	
	public boolean removeParameter(Parameter param)
	{
		return this.params.remove(param);
	}
	
	public String getSource()
	{
		String out = "";
		
		out = out + "[" + super.getSource();
		
		for ( Parameter param : this.params)
		{
			out = out + "," + param.getSource();
		}
		return out + "]";
	}
	
	/**
	 * This method calls the method that will be used to convert
	 * the runtime type of convert into the runtime type of data
	 * using the runtime type of converter.
	 * 
	 * This method should be duplicated and have the types changed
	 * according to the conversion types that are supported by the 
	 * converting class.
	 */
	public Data Convert(DataContainer convert, Converter converter, Data data)
	{
		data = converter.convert(convert);
		return data;
	}

	public Data Convert(XMLPercept convert, Converter converter, Data data)
	{
		data = converter.convert(convert);
		return data;
	}
	
	public Data Convert(XMLPercept convert, PrologPercept converter, Data data)
	{
		data = converter.convert(convert);
		return data;
	}
	
	public Data Convert(Request convert, PrologPercept converter, Data data)
	{
		
		data = converter.convert(convert);
		return data;
	}
	
	/**
	 * 
	 */
	/*
	 * Had trouble getting the the EntityServer to call the correct
	 * convert method so I updated this method to check for the class
	 * this it is trying to convert.
	 * 
	 * Have to check for each class type that this Class supports.
	 */
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
		return null;
	}
	
	private DataContainer convert2(Message message) 
	{
		// TODO Auto-generated method stub
		// message.setSource(source)
		return null;
	}

	public PrologPercept convert2(XMLPercept xmlPercept)
	{
		PrologPercept prologPercept = new PrologPercept();
		// String msg = "test";
		// logger.log(Level.FINE, msg);
		Node rootNode = xmlPercept.getXMLDoc().getChildNodes().item(0);
		
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
	
	public PrologPercept convert(XMLPercept xmlPercept)
	{
		PrologPercept prologPercept = new PrologPercept();
		// String msg = "test";
		// logger.log(Level.FINE, msg);
		Node rootNode = xmlPercept.getXMLDoc().getChildNodes().item(0);
		
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
			param = new Parameter(node.getTextContent().toLowerCase() + node.getAttributes().item(0).getNodeValue());
		}
		else
		{
			param = new Parameter(node.getNodeName().toLowerCase()+ node.getAttributes().item(0).getNodeValue());
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

	/**
	 * Does not need to be implemented yet because this class is only going
	 * to be used to convert <code>XMLPercept</code>s into <code>PrologPercept</code>s.
	 */
	@Override
	public DataContainer convertToDataContainer(String source)
	{
		// TODO Auto-generated method stub
		
		return null;
	}

}
