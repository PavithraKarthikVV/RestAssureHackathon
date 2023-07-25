package moduleobject;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ExcelReader;
import utilities.commonfun;

public class FAssignmentSubmission {
	
	private String noAuth;
	private String subDescription;
	private String comments;
	private String gradeby=null;
	private String gradedatetime=null;
	public static String AssignmentSubmissionId;
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
			subDescription = testdata.get(rownumber).get("subDesc");	
			comments=testdata.get(rownumber).get("subComments");
			
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}

   	public Response postassignmentsubmission(String uri) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("assignmentId",DAssignment.AssignmentId);
		objnode.put("userId", CUser.UserId);
		objnode.put("subDesc",subDescription);
		objnode.put("subComments",comments);
		objnode.put( "subPathAttach1","Filepath1");
		objnode.put("subDateTime",commonfun.getcurrentDateTime());
		objnode.put("gradedBy", gradeby);
		objnode.put("gradedDateTime",gradedatetime);
		objnode.put("grade",-1);
		
		String payload = objnode.toString();
		String createdNestedJsonObject = obj.writerWithDefaultPrettyPrinter().writeValueAsString(objnode);
		System.out.println("Assignment Request: \n"+ createdNestedJsonObject);
		Response response = noAuthendication(noAuth).body(payload).post(uri);
		AssignmentSubmissionId=response.jsonPath().getString("submissionId");
		System.out.println("AssignmentSubmission Response:\n"+response.jsonPath().prettyPrint());
		System.out.println(AProgram.ProgramId);
		System.out.println(BBatch.BatchId);
		System.out.println(CUser.UserId);
		System.out.println(DAssignment.AssignmentId);
		System.out.println(AssignmentSubmissionId);
		return response;
	
	}
}
