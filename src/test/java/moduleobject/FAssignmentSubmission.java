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
import utilities.LoggerLoad;
import utilities.commonfun;

public class FAssignmentSubmission {
	
	private String noAuth;
	private String subDescription;
	private String comments;
	private String gradeby=null;
	private String gradedatetime=null;
	public static String submissiondatetime=null;
	private String invalidstudentid;
	private String invalidbatchid;
	private String invalidassignmentid;
	public static String AssignmentSubmissionId;
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
			subDescription = testdata.get(rownumber).get("subDesc");	
			comments=testdata.get(rownumber).get("subComments");
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

   	public void postassignmentsubmission(String uri) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("assignmentId",Integer.valueOf(DAssignment.AssignmentId));
		objnode.put("userId",CUser.UserId[2]);
		objnode.put("subDesc",subDescription);
		objnode.put("subComments",comments);
		objnode.put( "subPathAttach1","Filepath1");
		objnode.put("subDateTime",commonfun.getcurrentDateTime());
		objnode.put("gradedBy",CUser.UserId[0]);
		objnode.put("gradedDateTime",gradedatetime);
		objnode.put("grade",-1);
		
		String payload = objnode.toString();
		String createdNestedJsonObject = obj.writerWithDefaultPrettyPrinter().writeValueAsString(objnode);
		System.out.println("Assignment Request: \n"+ createdNestedJsonObject);
		Response response = noAuthendication(noAuth).body(payload).post(uri);
		AssignmentSubmissionId=response.jsonPath().getString("submissionId");
		submissiondatetime=response.jsonPath().getString("subDateTime");
		//System.out.println("----------------------"+submissiondatetime);
		System.out.println("AssignmentSubmission Response:\n"+response.jsonPath().prettyPrint());
//		System.out.println(AProgram.ProgramId);
//		System.out.println(BBatch.BatchId);
//		System.out.println(CUser.UserId[0]);
//		System.out.println(DAssignment.AssignmentId);
//		System.out.println(AssignmentSubmissionId);
		statuscode=response.getStatusCode();	
		LoggerLoad.info("----Save AssignmentSubmission----");
	}
   	
   	public void putassignmentResubmission(String uri) throws JsonProcessingException
	{	ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
   		objnode.put("assignmentId",DAssignment.AssignmentId);
		objnode.put("userId",CUser.UserId[2]);
		objnode.put("subDesc",subDescription);
		objnode.put("subComments",comments+"updated");
		objnode.put( "subPathAttach1","Filepath1");
		objnode.put("subDateTime",commonfun.getcurrentDateTime());
		objnode.put("gradedBy",CUser.UserId[0]);
		objnode.put("gradedDateTime",gradedatetime);
		objnode.put("grade",-1);
		
		
		String payload = objnode.toString();
		String createdNestedJsonObject = obj.writerWithDefaultPrettyPrinter().writeValueAsString(objnode);
		System.out.println("Assignment Request: \n"+ createdNestedJsonObject);
		Response response = noAuthendication(noAuth).body(payload).put(uri+"{assignsid}",AssignmentSubmissionId);
		System.out.println("AssignmentSubmission Response:\n"+response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();
		LoggerLoad.info("----Update AssignmentSubmission----");
	
	}
   	
   	
	public void putassignmentsubmissionGrade(String uri) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("assignmentId",Integer.valueOf(DAssignment.AssignmentId));
		objnode.put("userId",CUser.UserId[2]);
		objnode.put("subDesc",subDescription);
		objnode.put("subComments","Done");
		objnode.put( "subPathAttach1","Filepath1");
		objnode.put("subDateTime",submissiondatetime);
		objnode.put("gradedBy",CUser.UserId[0]);
		objnode.put("gradedDateTime",commonfun.getcurrentDateTime());
		objnode.put("grade",1);
		
		String payload = objnode.toString();
		String createdNestedJsonObject = obj.writerWithDefaultPrettyPrinter().writeValueAsString(objnode);
		System.out.println("Assignment Request: \n"+ createdNestedJsonObject);
		Response response = noAuthendication(noAuth).body(payload).put(uri+"{assignsid}",AssignmentSubmissionId);
		System.out.println("AssignmentSubmission Response:\n"+response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();
		LoggerLoad.info("----GradeAssignmentSubmission----");
	
	}
   	public void getAssignmentSubmissionGradeByAssignmentId(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{assgid}",AssignmentSubmissionId);
											
	System.out.println("###AssignmentID####"+response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	LoggerLoad.info("----get AssignmentSubmission Grade AssignmentId----");
	
	}
   	public void getAllAssignmentSubmission(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri);
	System.out.println("###AllAssignmentSubmission####"+response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	LoggerLoad.info("----getAll AssignmentSubmission----");
	
	}
   	
	public void getAllAssignmentgradeStudentId(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{stdid}",CUser.UserId[2]);
	System.out.println("###StudentID####"+response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	LoggerLoad.info("----get AssignmentSubmission by gradeStudentId----");
	}
	
	public void getAllAssignmentgradeBatchId(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{btchid}",BBatch.BatchId);
	System.out.println("###BatchID####"+response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	LoggerLoad.info("----get AssignmentSubmission by gradeBatchId----");
	
	}
	public void getAssignmentSubmissionStudentId(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{uid}",CUser.UserId[2]);
	System.out.println("###UserID####"+response.jsonPath().prettyPrint());
	LoggerLoad.info("----get AssignmentSubmission Grade AssignmentId----");
	statuscode=response.getStatusCode();
	
	}
	public void getAssignmentSubmissionBatchId(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{uid}",BBatch.BatchId);
	System.out.println("###BatchID####"+response.jsonPath().prettyPrint());
	LoggerLoad.info("----get AssignmentSubmission BatchId----");
	statuscode=response.getStatusCode();
	
	}
	
	
	
	/*Negative*/
	public void getAllAssignmentgradeInvalidStudentId(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{stdid}",invalidstudentid);
	System.out.println("###StudentID####"+response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
		LoggerLoad.info("----get AssignmentSubmission by InvalidgradeStudentId----");
	}
	
	public void getAllAssignmentgradeInvalidBatchId(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{btchid}",invalidbatchid);
	System.out.println("###BatchID####"+response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	LoggerLoad.info("----get AssignmentSubmission by InvalidgradeBatchId----");
	}
	public void getAssignmentSubmissionInvalidStudentId(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{uid}",invalidstudentid);
	System.out.println("###UserID####"+response.jsonPath().prettyPrint());
	LoggerLoad.info("----get AssignmentSubmission by InvalidStudentId----");
	statuscode=response.getStatusCode();
	
	}
	public void getAssignmentSubmissionInvalidBatchId(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{uid}",invalidbatchid);
	System.out.println("###BatchID####"+response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	LoggerLoad.info("----get AssignmentSubmission by InvalidBatchId----");
	}
	public void getAssignmentSubmissionGradeByInvalidAssignmentId(String postUri)
	{
	response=noAuthendication(noAuth).get(postUri+"{assgid}",invalidassignmentid);
											
	System.out.println("###AssignmentID####"+response.jsonPath().prettyPrint());
	statuscode=response.getStatusCode();
	LoggerLoad.info("----get AssignmentSubmission by InvalidSubmissionId----");
	}
	public void invalidpostassignmentsubmission(String uri) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("userId",CUser.UserId[2]);/*missing assignment id*/
		objnode.put("subDesc",subDescription);
		objnode.put("subComments",comments);
		objnode.put( "subPathAttach1","Filepath1");
		objnode.put("subDateTime",commonfun.getcurrentDateTime());
		objnode.put("gradedBy",CUser.UserId[0]);
		objnode.put("gradedDateTime",gradedatetime);
		objnode.put("grade",-1);
		
		String payload = objnode.toString();
		String createdNestedJsonObject = obj.writerWithDefaultPrettyPrinter().writeValueAsString(objnode);
		System.out.println("Assignment Request: \n"+ createdNestedJsonObject);
		Response response = noAuthendication(noAuth).body(payload).post(uri);
		submissiondatetime=response.jsonPath().getString("subDateTime");
		System.out.println("AssignmentSubmission Response:\n"+response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();	
		LoggerLoad.info("----Invalid SubmissionPost----");
	}
	public void invalidpostassignmentsubmission404(String uri) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("assignmentId",DAssignment.AssignmentId);
		objnode.put("userId",invalidbatchid);
		objnode.put("subDesc",subDescription);
		objnode.put("subComments",comments);
		objnode.put( "subPathAttach1","Filepath1");
		objnode.put("subDateTime",commonfun.getcurrentDateTime());
		objnode.put("gradedBy",CUser.UserId[0]);
		objnode.put("gradedDateTime",gradedatetime);
		objnode.put("grade",-1);
		String payload = objnode.toString();
		String createdNestedJsonObject = obj.writerWithDefaultPrettyPrinter().writeValueAsString(objnode);
		System.out.println("Assignment Request: \n"+ createdNestedJsonObject);
		Response response = noAuthendication(noAuth).body(payload).post(uri);
		System.out.println("AssignmentSubmission Response:\n"+response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();	
		LoggerLoad.info("----Invalid Submission 404----");
	}
	public void invalidputtassignmentsubmission(String uri) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("userId",CUser.UserId[2]);
		objnode.put("subDesc",subDescription);
		objnode.put("subComments","Done");
		objnode.put( "subPathAttach1","Filepath1");
		objnode.put("gradedBy",CUser.UserId[0]);
		objnode.put("gradedDateTime",commonfun.getcurrentDateTime());
		objnode.put("grade",1);
		
		String payload = objnode.toString();
		String createdNestedJsonObject = obj.writerWithDefaultPrettyPrinter().writeValueAsString(objnode);
		System.out.println("Assignment Request: \n"+ createdNestedJsonObject);
		Response response = noAuthendication(noAuth).body(payload).put(uri+"{assignsid}",AssignmentSubmissionId);
		System.out.println("AssignmentSubmission Response:\n"+response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();
		LoggerLoad.info("----Re-AssignmentSubmission----");
	
	}
	public void invalidputassignmentsubmission(String uri) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("userId",CUser.UserId[2]);
		objnode.put("subDesc",subDescription);
		objnode.put("subComments","Done");
		objnode.put( "subPathAttach1","Filepath1");
		objnode.put("subDateTime",submissiondatetime);
		objnode.put("gradedBy",CUser.UserId[0]);
		objnode.put("gradedDateTime",commonfun.getcurrentDateTime());
		objnode.put("grade",1);
		
		String payload = objnode.toString();
		String createdNestedJsonObject = obj.writerWithDefaultPrettyPrinter().writeValueAsString(objnode);
		System.out.println("Assignment Request: \n"+ createdNestedJsonObject);
		Response response = noAuthendication(noAuth).body(payload).put(uri+"{assignsid}",AssignmentSubmissionId);
		System.out.println("AssignmentSubmission Response:\n"+response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();
		LoggerLoad.info("----Re-AssignmentSubmission----");
	
	}
	public void invalidputassignmentsubmission404(String uri) throws JsonProcessingException
	{ObjectMapper obj=new ObjectMapper();
	ObjectNode objnode = obj.createObjectNode();
		objnode.put("assignmentId",Integer.valueOf(DAssignment.AssignmentId));
		objnode.put("userId",invalidbatchid);
		objnode.put("subDesc",subDescription);
		objnode.put("subComments",comments);
		objnode.put( "subPathAttach1","Filepath1");
		objnode.put("subDateTime",gradeby);
		objnode.put("gradedBy",CUser.UserId[0]);
		objnode.put("gradedDateTime",gradedatetime);
		objnode.put("grade",-1);
		String payload = objnode.toString();
		String createdNestedJsonObject = obj.writerWithDefaultPrettyPrinter().writeValueAsString(objnode);
		System.out.println("Assignment Request: \n"+ createdNestedJsonObject);
		Response response = noAuthendication(noAuth).body(payload).put(uri+"{assignsid}",invalidassignmentid);
		System.out.println("AssignmentSubmission Response:\n"+response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();
		LoggerLoad.info("----Inavlid404put----");
	
	
	}
	
	public void invaldputassignmentsubmissionGrade(String uri) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("userId",CUser.UserId[2]);
		objnode.put("subDesc",subDescription);
		objnode.put("subComments","Done");
		
		objnode.put("subDateTime",submissiondatetime);
		objnode.put("gradedBy",CUser.UserId[0]);
		objnode.put("gradedDateTime",commonfun.getcurrentDateTime());
		objnode.put("grade",1);
		
		String payload = objnode.toString();
		String createdNestedJsonObject = obj.writerWithDefaultPrettyPrinter().writeValueAsString(objnode);
		System.out.println("Assignment Request: \n"+ createdNestedJsonObject);
		Response response = noAuthendication(noAuth).body(payload).put(uri+"{assignsid}",AssignmentSubmissionId);
		System.out.println("AssignmentSubmission Response:\n"+response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();
		LoggerLoad.info("----InvalidPutGradeAssignmentSubmission----");
	
	}
	public void invalidputassignmentsubmissionGrade404(String uri) throws JsonProcessingException
	{
		ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("assignmentId",DAssignment.AssignmentId);
		objnode.put("userId",invalidstudentid);
		objnode.put("subDesc",subDescription);
		objnode.put("subComments","Done");
		objnode.put( "subPathAttach1","Filepath1");
		objnode.put("subDateTime",submissiondatetime);
		objnode.put("gradedBy",CUser.UserId[0]);
		objnode.put("gradedDateTime",commonfun.getcurrentDateTime());
		objnode.put("grade",1);
		
		String payload = objnode.toString();
		String createdNestedJsonObject = obj.writerWithDefaultPrettyPrinter().writeValueAsString(objnode);
		System.out.println("Assignment Request: \n"+ createdNestedJsonObject);
		Response response = noAuthendication(noAuth).body(payload).put(uri+"{assignsid}",invalidassignmentid);
		System.out.println("AssignmentSubmission Response:\n"+response.jsonPath().prettyPrint());
		statuscode=response.getStatusCode();
		LoggerLoad.info("----InvaliGradeAssignmentSubmission404----");
	
	}
}
