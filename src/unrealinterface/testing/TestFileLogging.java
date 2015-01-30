package unrealinterface.testing;

import java.io.BufferedWriter;
import java.io.IOException;

import aiInterface.logging.LogFileUtil;
import aiInterface.testing.client.TestingUtil;

@Deprecated

/*
 * Used to test old debug system
 * Have moved to log4j now.
 * 
 */
public class TestFileLogging 
{

	
	
	public static void main(String[] args)
	{
		
		BufferedWriter fileWriter = null;
		
		
		try {
			fileWriter = LogFileUtil.createLogWriterFile((LogFileUtil.createLogFile(LogFileUtil.LOG_FILES_DIR, "Agent", "." + LogFileUtil.LOG_FILES_DIR)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			TestingUtil.printExceptionInfo(e);
			e.printStackTrace();
		}
		for ( int i = 0; i < 20; i++)
		{
		try {
			fileWriter.write("blah\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			TestingUtil.printExceptionInfo(e);
			e.printStackTrace();
		}
		
		try {
			fileWriter.flush();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
	}
	
}
