package unrealinterface.testing;

import java.io.IOException;

import aiInterface.testing.client.TestingUtil;

public class TestLogFileCreating 
{

	public static void main(String[] args)
	{
		
		try 
		{
			TestingUtil.createFile("logs/EntityTCPChannelServer/EntityTCPChannelServer.log");
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
