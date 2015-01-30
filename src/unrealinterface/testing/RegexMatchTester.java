package unrealinterface.testing;

import java.io.IOException;

import aiInterface.environment.UnrealEI;
import aiInterface.testing.client.TestingUtil;
import aiInterface.util.MessageFraming;

public class RegexMatchTester 
{

	public static void main(String[] args) throws IOException
	{
		
		String testFalse = MessageFraming.END_FRAME + " " + MessageFraming.START_FRAME;
		String string = new String("<Percept>" +
	"<Player>" +
	"	<Name>GlensNemesis0</Name>" +
	"	<Location>" +
	"		<X>345.23</X>" +
	"		<Y>425</Y>" +
	"		<Z>0</Z>" +
	"	</Location>" +
	"	<Physics>Walking</Physics>" +
	"	<Weapon>" +
	"		<Name>Plasma Rifle</Name>" +
	"		<Range>Middle</Range>" +
	"	</Weapon>" +
	"	<Team>" +
	"		<Colour>Blue</Colour>" +
	"		<Number>2</Number>" +
	"	</Team>" +
	"</Player>" +
"</Percept></message5><message5><?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
"<Percept>" +
"<Player>" +
"		<Name>GlensNemesis0</Name>" +
"		<Location>" +
"			<X>345.23</X>" +
"			<Y>425</Y>" +
"			<Z>0</Z>" +
"		</Location>" +
"		<Physics>Walking</Physics>" +
"		<Weapon>" +
"			<Name>Plasma Rifle</Name>" +
"			<Range>Middle</Range>" +
"		</Weapon>" +
"		<Team>" +
"			<Colour>Blue</Colour>" +
"			<Number>2</Number>" +
"		</Team>" +
"	</Player>" +
"</Percept></message5><message5>CloseConnection</message5>");
		
		String testFilePath = "tests/EntityTCPChannelServer.getMessage()2011-08-14-12-06-04.logs";
		String testStringFromFile = new String (TestingUtil.readFileAsString(testFilePath) );
		
		
		
		
		System.out.println("testStringFromFile matches ? " + testStringFromFile.matches(MessageFraming.MESSAGE_MATCHING_REGEX));
		System.out.println(MessageFraming.containsMessageFrame(testFalse, MessageFraming.START_FRAME, MessageFraming.END_FRAME));
		System.out.println(parseOutMessage(testStringFromFile));
		
		
	}
	
	
	
	private static String parseOutMessage(String buffer)
	{
		return MessageFraming.parseOutMessage(buffer);
	}
}
