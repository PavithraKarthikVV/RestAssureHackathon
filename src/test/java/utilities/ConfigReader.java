package utilities;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop;
	
	public static Properties init_prop() throws IOException
	{
		prop=new Properties();
		try
		{
			FileInputStream ip=new FileInputStream("src/test/resources/testData/Config.properties");
			prop.load(ip);
		}
		catch(FileNotFoundException e)
		{
		e.printStackTrace();	
		}catch(IOException e)
		{
			e.printStackTrace();
		}
				return prop;
	}
	
	public static String programdataExcelpath() throws IOException
	{ 	prop=init_prop();
		String uri = prop.getProperty("exceldatapath");
		System.out.println(uri);
		if(uri!=null)
		{
			return uri;
		}else
		{
			System.out.println("uri is not mentioned in config properties");
		}	
		return uri;	
	}
	public static String baseUri() throws IOException
	{prop=init_prop();
		String url = prop.getProperty("baseurl");
		System.out.println(url);
		if(url!=null)
		{
			return url;
		}else
		{
			System.out.println("uri is not mentioned in config properties");
		}	
		return url;	
	}
	
	public static String endpointProgramSave() throws IOException
	{prop=init_prop();
		String getEndPoint = prop.getProperty("programsave");
		
		if(getEndPoint!=null)
		{
			return getEndPoint;
		}else
		{
			System.out.println("Endpoint is not mentioned in config properties");
		}	return getEndPoint;	
	}
	
	
	public static String endpointProgramDeleteid() throws IOException
	{prop=init_prop();
		String getEndPoint = prop.getProperty("programdeleteid");
		
		if(getEndPoint!=null)
		{
			return getEndPoint;
		}else
		{
			System.out.println("Endpoint is not mentioned in config properties");
		}	return getEndPoint;	
	}
	public static String putProgramByProgramNameEndpoint() throws IOException {
		prop=init_prop();
		String putProgramNamePoint = prop.getProperty("PutProgramByProgramNameEndpoint");
		if (putProgramNamePoint != null)
			return putProgramNamePoint;
		else
			throw new RuntimeException("GetOneProgramIdEndpoint not specified in the Config.properties file");
	}
	public static String putProgramByProgramIdEndpoint() throws IOException {
		prop=init_prop();
		String putbyprogramIdEndPoint = prop.getProperty("PutProgramByProgramIdEndpoint");
		if ( putbyprogramIdEndPoint != null)
			return  putbyprogramIdEndPoint;
		else
			throw new RuntimeException("GetOneProgramIdEndpoint not specified in the Config.properties file");
	}
	public static String getAllEndpoint() throws IOException
	{   
		prop=init_prop();
		String getAllPoint=prop.getProperty("GetAllEndpoint");
		if(getAllPoint!=null)
		{
			return getAllPoint;
		}else
		{
			System.out.println("GetAllEndpoint is not mentioned in config properties");
		}	return getAllPoint;	
	}
	
	public static String getOneProgramIdEndpoint() throws IOException {
		prop=init_prop();

		String getIdEndPoint = prop.getProperty("GetOneProgramIdEndpoint");
		if (getIdEndPoint != null)
			return getIdEndPoint;
		else
			throw new RuntimeException("GetOneProgramIdEndpoint not specified in the Config.properties file");
	}
	
	public static String endpointBatchSave() throws IOException
	{prop=init_prop();
		String getEndPoint = prop.getProperty("batchsave");
		
		if(getEndPoint!=null)
		{
			return getEndPoint;
		}else
		{
			System.out.println("Endpoint is not mentioned in config properties");
		}	return getEndPoint;	
	}

	public static String endpointBatchDeleteid() throws IOException
	{prop=init_prop();
		String getEndPoint = prop.getProperty("batchdeletid");
		
		if(getEndPoint!=null)
		{
			return getEndPoint;
		}else
		{
			System.out.println("Endpoint is not mentioned in config properties");
		}	return getEndPoint;	
	}
	
	public static String endpointUserSave() throws IOException
	{prop=init_prop();
		String getEndPoint = prop.getProperty("usersave");
		
		if(getEndPoint!=null)
		{
			return getEndPoint;
		}else
		{
			System.out.println("Endpoint is not mentioned in config properties");
		}	return getEndPoint;	
	}

	public static String endpointUserDeleteid() throws IOException
	{prop=init_prop();
		String getEndPoint = prop.getProperty("userdeleteid");
		
		if(getEndPoint!=null)
		{
			return getEndPoint;
		}else
		{
			System.out.println("Endpoint is not mentioned in config properties");
		}	return getEndPoint;	
	}
	
	public static String endpointAssignmentSave() throws IOException
	{prop=init_prop();
		String getEndPoint = prop.getProperty("assignmentsave");
		
		if(getEndPoint!=null)
		{
			return getEndPoint;
		}else
		{
			System.out.println("Endpoint is not mentioned in config properties");
		}	return getEndPoint;	
	}

	public static String endpointAssignmentDeleteid() throws IOException
	{prop=init_prop();
		String getEndPoint = prop.getProperty("assignmentdeleteid");
		
		if(getEndPoint!=null)
		{
			return getEndPoint;
		}else
		{
			System.out.println("Endpoint is not mentioned in config properties");
		}	return getEndPoint;	
	}
	
	public static String endpointAssgSubmissionSave() throws IOException
	{prop=init_prop();
		String getEndPoint = prop.getProperty("assgsubmissionsave");
		
		if(getEndPoint!=null)
		{
			return getEndPoint;
		}else
		{
			System.out.println("Endpoint is not mentioned in config properties");
		}	return getEndPoint;	
	}

	public static String endpointAssgSubmissionDeleteid() throws IOException
	{prop=init_prop();
		String getEndPoint = prop.getProperty("assgsubmissiondeleteid");
		
		if(getEndPoint!=null)
		{
			return getEndPoint;
		}else
		{
			System.out.println("Endpoint is not mentioned in config properties");
		}	return getEndPoint;	
	}

	
	public static String userUpdateuserrolestatus() throws IOException
	{   
		prop=init_prop();
		String getbatchbyid=prop.getProperty("userupdateuserrolestatus");
		if(getbatchbyid!=null)
		{
			return getbatchbyid;
		}else
		{
			System.out.println("GetAllEndpoint is not mentioned in config properties");
		}	return getbatchbyid;	
	}
	
	
	
	
	
	
}