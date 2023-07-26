package moduleobject;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ExcelReader;
public class AProgram {
	private String noAuth;
	private String programdec;
	private String progname;
	private String progstatus;
	public static int statuscode;
	public static String ProgramId;
	public static String ProgramName;
	RequestSpecification requestSpecification;
	Response response;
	public RequestSpecification noAuthendication(String noauth)
	{
		noAuth=noauth;	
	RequestSpecification requestSpecification = RestAssured.given()
			.header("Authorization", noauth).contentType("application/json");
	return requestSpecification;
	}

	public void getDatafromExcel(String sheetname, int rownumber) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		ExcelReader reader = new ExcelReader();
		String data[]=new String[2];
		List<Map<String, String>> testdata;
		try {
			testdata = reader.getData("src/test/resources/testData/data.xlsx", sheetname);
			 programdec = testdata.get(rownumber).get("programDescription");	
			 progname=testdata.get(rownumber).get("programName");
			 progstatus=testdata.get(rownumber).get("programStatus");
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	
	
	public Response postprogram(String uri)
	{Random generator = new Random();
	int i = generator.nextInt(500) + 1;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("programDescription",programdec);
		jsonObject.put("programName", progname+i);
		jsonObject.put("programStatus", progstatus);
		String payload = jsonObject.toString();
		System.out.println("Program Reponse:\n"+payload);
		Response response = noAuthendication(noAuth).body(payload).post(uri);
		ProgramId=response.jsonPath().getString("programId");
		ProgramName = response.jsonPath().getString("programName");
		System.out.println("Program Reponse:\n"+response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();
		return response;
	}
	
	
	public Response putprogramid(String postUri)
	{
		JSONObject jsonObjectput = new JSONObject();
		 String programDesc=programdec+"_modifiedID";
		  jsonObjectput.put("programDescription",programDesc);
		  jsonObjectput.put("programName", ProgramName);
		  jsonObjectput.put("programStatus", progstatus);
		String payload = jsonObjectput.toString();
		Response response = noAuthendication(noAuth).body(payload).put(postUri+"{id}",ProgramId);
		System.out.println(response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();
		return response;
	}
	public Response putprogramname(String postUri)
	{
		JSONObject jsonObjectput = new JSONObject();
		String programDesc=programdec+"_modifiedname";
		  
		  jsonObjectput.put("programDescription",programDesc);
		  jsonObjectput.put("programName", ProgramName);
		  jsonObjectput.put("programStatus", progstatus);
		String payload = jsonObjectput.toString();
		Response response = noAuthendication(noAuth).body(payload).put(postUri+"{pnme}",ProgramName);
		statuscode=response.getStatusCode();
		System.out.println(response.jsonPath().prettyPrint());
		return response;
	}
	public Response getAllprogram(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri);
	System.out.println(response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	return response;
	}
	
	public Response getprogramid(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{pid}",ProgramId);
	System.out.println(response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	return response;
	}
}

