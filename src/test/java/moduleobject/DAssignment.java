package moduleobject;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ExcelReader;
import utilities.LoggerLoad;
import utilities.commonfun;

public class DAssignment {
	private String noAuth;
	private String assignmentDescription;
	private String assignmentName;
	private String comments;
	public static String AssignmentId;
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
		LoggerLoad.info("----Reading TestData from Excel----");
		ExcelReader reader = new ExcelReader();
		String data[]=new String[2];
		List<Map<String, String>> testdata;
		try {
			testdata = reader.getData("src/test/resources/testData/data.xlsx", sheetname);
			assignmentName = testdata.get(rownumber).get("assignmentName");	
			assignmentDescription=testdata.get(rownumber).get("assignmentDescription");
			comments=testdata.get(rownumber).get("Assignmentcomments");
			
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	
	
	public Response postassignment(String uri) throws JsonProcessingException
	{
		Random generator = new Random();
		int i = generator.nextInt(300) + 1;
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("assignmentName",assignmentName+i);
		objnode.put("assignmentDescription", assignmentDescription);
		objnode.put("batchId", BBatch.BatchId);
		objnode.put("comments",comments);
		objnode.put("createdBy",CUser.UserId[0]);
		objnode.put("dueDate",commonfun.duedate());
		objnode.put("graderId", CUser.UserId[0]);
		objnode.put("pathAttachment1","assg1");
		objnode.put("pathAttachment2","assg2");
		objnode.put("pathAttachment3","assg3");
		objnode.put("pathAttachment4","assg4");
		objnode.put("pathAttachment5","assg5");
		String payload = objnode.toString();
		String createdNestedJsonObject = obj.writerWithDefaultPrettyPrinter().writeValueAsString(objnode);
		System.out.println("Assignment Request: \n"+ createdNestedJsonObject);
		Response response = noAuthendication(noAuth).body(payload).post(uri);
		AssignmentId=response.jsonPath().getString("assignmentId");
		System.out.println("Assignment Response:\n"+response.jsonPath().prettyPrint());
		LoggerLoad.info("----Create Assignment----");
		return response;
		}
	
}
