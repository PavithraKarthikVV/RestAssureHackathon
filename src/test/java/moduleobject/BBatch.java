package moduleobject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ExcelReader;
import utilities.LoggerLoad;

public class BBatch {
	private String noAuth;
	private String batchdes;
	private String batchname;
	private String batchclassno;
	private String batchstatus;
	public static String BatchId;
	Response response;
	RequestSpecification requestSpecification;
	public RequestSpecification noAuthendication(String noauth)
	{
		noAuth=noauth;	
	RequestSpecification requestSpecification = RestAssured.given()
			.header("Authorization", noauth).contentType("application/json");
	return requestSpecification;
	}

	public void getDatafromExcel(String sheetname, int rownumber) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		LoggerLoad.info("----Reading TestData from Excel----");
		ExcelReader reader = new ExcelReader();
		String data[]=new String[2];
		List<Map<String, String>> testdata;
		try {
			testdata = reader.getData("src/test/resources/testData/data.xlsx", sheetname);
			 batchdes = testdata.get(rownumber).get("batchDescription");	
			 batchname=testdata.get(rownumber).get("batchName");
			 batchclassno=testdata.get(rownumber).get("batchNoOfClasses");
			 batchstatus=testdata.get(rownumber).get("batchStatus");
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	
	
	public Response postbatch(String uri)
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("batchDescription",batchdes);
		jsonObject.put("batchName", batchname);
		jsonObject.put("batchNoOfClasses", batchclassno);
		jsonObject.put("batchStatus", batchstatus);
		jsonObject.put("programId", AProgram.ProgramId);		//AProgram.ProgramId
		jsonObject.put("programName", AProgram.ProgramName);//AProgram.ProgramName
		String payload = jsonObject.toString();
		System.out.println("Batch Request:\n"+payload);
		Response response = noAuthendication(noAuth).body(payload).post(uri);
		BatchId=response.jsonPath().getString("batchId");
		System.out.println("Batch Response:\n"+response.jsonPath().prettyPrint());
		LoggerLoad.info("----Save Batch----");
		return response;
	}
	
}

