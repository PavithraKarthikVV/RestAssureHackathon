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
	private String invalidstudentid;
	private String invalidbatchid;
	private String invalidassignmentid;
	public static String AssignmentId;
	public static String AssignmentName;
	RequestSpecification requestSpecification;
	Response response;
	public static int statuscode;
	
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
			invalidstudentid=testdata.get(rownumber).get("InvalidStudentid");
			invalidbatchid=testdata.get(rownumber).get("Invalidbatchid");
			invalidassignmentid=testdata.get(rownumber).get("InvalidAssignmentid");
			
			
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
		AssignmentName=response.jsonPath().getString("assignmentName");
		System.out.println("Assignment Response:\n"+response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();
		LoggerLoad.info("----Create Assignment----");
		return response;
		}
	
	
	
		public Response putassignment(String uri) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("assignmentDescription", assignmentDescription+"");
		objnode.put("assignmentId", Integer.valueOf(AssignmentId));
		objnode.put("assignmentName",AssignmentName);
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
		Response response = noAuthendication(noAuth).body(payload).put(uri+"{assgid}",AssignmentId);
		System.out.println("Assignment Response:\n"+response.jsonPath().prettyPrint());
		LoggerLoad.info("----update Assignment----");
		return response;
		}
		
		
	public void getAllAssignment(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri);
	System.out.println("###AllAssignment####"+response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	LoggerLoad.info("----getAll Assignment----");
	}
	
	public void getAssignmentbyId(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{assgid}",AssignmentId);
	System.out.println("###AssignmentID####"+response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	LoggerLoad.info("----get Assignment by Id----");
	}
	
	public void getAssignmentbybatchid(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{bid}",BBatch.BatchId);
	System.out.println("###By Batchid####"+response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	LoggerLoad.info("----get by BatchId----");
	}
	
	/*Negative*/
	public Response invalidpostassignment(String uri) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("assignmentName",AssignmentName);
		objnode.put("assignmentDescription", assignmentDescription);
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
		System.out.println("Assignment Response:\n"+response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();
		LoggerLoad.info("----Create InvalidAssignment----");
		return response;
		}
	public void getAssignmentbyinvalidId(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{assgid}",invalidassignmentid);
	System.out.println("###AssignmentID####"+response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	LoggerLoad.info("----get Assignment by InvalidId----");
	}
	
	public void getAssignmentbyinvalidbatchid(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{bid}",invalidbatchid);
	System.out.println("###By Batchid####"+response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	LoggerLoad.info("----get by InvalidBatchId----");
	}
	public Response invalidputassignment(String uri) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("assignmentDescription", assignmentDescription+"");
		objnode.put("assignmentId", Integer.valueOf(AssignmentId));
		objnode.put("assignmentName",AssignmentName);
		objnode.put("batchId", BBatch.BatchId);
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
		Response response = noAuthendication(noAuth).body(payload).put(uri+"{assgid}",AssignmentId);
		System.out.println("Assignment Response:\n"+response.jsonPath().prettyPrint());
		LoggerLoad.info("----update Assignment----");
		return response;
		}
		
	
	public Response invalipostassignment404(String uri) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("assignmentName",AssignmentName);
		objnode.put("assignmentDescription", assignmentDescription);
		objnode.put("batchId",invalidbatchid);
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
		AssignmentName=response.jsonPath().getString("assignmentName");
		System.out.println("Assignment Response:\n"+response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();
		LoggerLoad.info("----Create Assignment----");
		return response;
		}
}
