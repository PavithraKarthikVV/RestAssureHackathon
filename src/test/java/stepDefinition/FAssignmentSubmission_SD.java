package stepDefinition;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import moduleobject.BBatch;
import moduleobject.FAssignmentSubmission;
import utilities.ConfigReader;
import utilities.LoggerLoad;

public class FAssignmentSubmission_SD {
	FAssignmentSubmission assgSub=new FAssignmentSubmission();
	private String posturi;
	Response response;
	String noAuth;
	@Given("User sets Authoization to {string} for assignmentSubmission")
	public void user_sets_authoization_to_no_auth(String string) {
		assgSub.noAuthendication(string);
		noAuth=string;
				}

	@Given("User creates POST Request for the assignmentSubmission LMS API endpoint")
	public void user_creates_put_request_for_the_assignmentSubmission_lms_api_endpoint() throws IOException {
		posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgSubmissionSave();
	}
	@When("User sends HTTPS Request for assignmentSubmission using {string} and {int}")
	public void user_sends_https_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assgSub.getDatafromExcel(sheetname, rowno);
	  assgSub.postassignmentsubmission(posturi);
	   
	}
	
	
	@Given("User creates PUT Resubmission Request for the assignmentSubmission LMS API endpoint")
	public void user_creates_post_Resubmission_request_for_the_assignmentSubmission_lms_api_endpoint() throws IOException {
		posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgSubmissionResubmit();
	}
	@When("User sends PUT_HTTPS Request for assignmentSubmission using {string} and {int}")
	public void user_sends_put_https_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assgSub.getDatafromExcel(sheetname, rowno);
	  assgSub.putassignmentResubmission(posturi);
	   
	}
	
	
	
	
	
	@Given("User creates PUT Request for the assignmentSubmission grade LMS API endpoint")
	public void user_creates_post_request_for_the_assignmentSubmission_grade_lms_api_endpoint() throws IOException {
		posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgSubmissiongradeAssignmentSub();
	}

	@When("User sends HTTPS Request for assignmentSubmission grade using {string} and {int}")
	public void user_sends_https_grade_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assgSub.getDatafromExcel(sheetname, rowno);
	  assgSub.putassignmentsubmissionGrade(posturi);
	   
	}
	
	@Given("User is provided with the BaseUrl and the Endpoints to create a GET request for AssignmentSubmission")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_create_a_get_request() throws IOException {
	  posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgSubmissiongettall();
	  LoggerLoad.info("***Sending the GetAllProgram Given code***" + posturi);
	}

	@When("User send the HTTPsGET request for AssignmentSubmission")
	public void user_send_the_https_get_request_for_assignmentsubmission() throws IOException {
	 assgSub.getAllAssignmentSubmission(posturi);
	 LoggerLoad.info("***user sends the get all request****");		  
	}
	@Given("User is provided with the BaseUrl and the Endpoints to create a GET GradeValidAssignment request for AssignmentSubmission")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_create_a_get_GradeValidAssignment_request() throws IOException {
	  posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgSubmissiongradeassignmentid();
	  LoggerLoad.info("***Sending the GradeValidAssignment Given code***" + posturi);
	}

	@When("User send the HTTPsGET GradeValidAssignment request valid AssignmentId for AssignmentSubmission")
	public void user_send_the_https_get_GradeValidAssignment_request_for_assignmentsubmission() throws IOException {
	 assgSub.getAssignmentSubmissionGradeByAssignmentId(posturi);
	 LoggerLoad.info("***user sends the get gradeassignmentID request****");		  
	}
	
	
	@Given("User is provided with the BaseUrl and the Endpoints to create a GET GradeStudentID request for AssignmentSubmission")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_create_a_get_GradeStudentID_request() throws IOException {
	  posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgSubmissiongradestudentid();
	  LoggerLoad.info("***Sending the GradeStudentID Given code***" + posturi);
	}

	@When("User send the HTTPsGET GradeStudentID request for AssignmentSubmission")
	public void user_send_the_https_get_GradeStudentID_request_for_assignmentsubmission() throws IOException {
	 assgSub.getAllAssignmentgradeStudentId(posturi);
	 LoggerLoad.info("***user sends the get GradeStudentID request****");		  
	}
	
	@Given("User is provided with the BaseUrl and the Endpoints to create a GET GradeBatchID request for AssignmentSubmission")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_create_a_get_GradeBatchID_request() throws IOException {
	  posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgSubmissiongraderbatchid();
	  LoggerLoad.info("***Sending the GradeBatchID Given code***" + posturi);
	}

	@When("User send the HTTPsGET GradeBatchID request for AssignmentSubmission")
	public void user_send_the_https_get_GradeBatchID_request_for_assignmentsubmission() throws IOException {
	 assgSub.getAllAssignmentgradeBatchId(posturi);
	 LoggerLoad.info("***user sends the get GradeBatchID request****");		  
	}
	
	@Given("User is provided with the BaseUrl and the Endpoints to create a GET BatchID request for AssignmentSubmission")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_create_a_get_BatchID_request() throws IOException {
	  posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgSubmissionbatchid();
	  LoggerLoad.info("***Sending the GET BatchID Given code***" + posturi);
	}

	@When("User send the HTTPsGET BatchID request for AssignmentSubmission")
	public void user_send_the_https_get_BatchID_request_for_assignmentsubmission() throws IOException {
	 assgSub.getAssignmentSubmissionBatchId(posturi);
	 LoggerLoad.info("***user sends the GET BatchID request****");		  
	}
	
	@Given("User is provided with the BaseUrl and the Endpoints to create a GET StudentID request for AssignmentSubmission")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_create_a_get_StudentID_request() throws IOException {
	  posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgSubmissiongradeuserid();
	  LoggerLoad.info("***Sending the GET StudentID Given code***" + posturi);
	}

	@When("User send the HTTPsGET StudentID request for AssignmentSubmission")
	public void user_send_the_https_get_StudentID_request_for_assignmentsubmission() throws IOException {
	 assgSub.getAssignmentSubmissionStudentId(posturi);
	 LoggerLoad.info("***user sends the GET StudentID request****");		  
	}
	@Then("User receives {int} Created Status with response body for assignmentSubmission")
	public void user_receives_created_status_with_response_body(Integer statuscode) {
		Assert.assertEquals(assgSub.statuscode,statuscode ); 
			
	}
	/*negative*/
	
	@When("User send the HTTPsGET InvalidGradeValidAssignment request valid AssignmentId for AssignmentSubmission {string} and {int}")
	public void user_send_the_https_get_InvalidGradeValidAssignment_request_for_assignmentsubmission(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assgSub.getDatafromExcel(sheetname, rowno);
		assgSub.getAssignmentSubmissionGradeByInvalidAssignmentId(posturi);
	 LoggerLoad.info("***user sends the get InvalidgradeassignmentID request****");		  
	}
	
	
	@When("User send the HTTPsGET InvalidGradeStudentID request for AssignmentSubmission {string} and {int}")
	public void user_send_the_https_get_InvalidGradeStudentID_request_for_assignmentsubmission(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assgSub.getDatafromExcel(sheetname, rowno);
		assgSub.getAllAssignmentgradeInvalidStudentId(posturi);
	 LoggerLoad.info("***user sends the get InvalidGradeStudentID request****");		  
	}
	
	@When("User send the HTTPsGET InvalidGradeBatchID request for AssignmentSubmission {string} and {int}")
	public void user_send_the_https_get_InvalidGradeBatchID_request_for_assignmentsubmission(String sheetname,Integer rowno) throws InvalidFormatException, IOException{
		assgSub.getDatafromExcel(sheetname, rowno);
		assgSub.getAllAssignmentgradeInvalidBatchId(posturi);
	 LoggerLoad.info("***user sends the get InvalidGradeBatchID request****");		  
	}
	@When("User send the HTTPsGET InvalidBatchID request for AssignmentSubmission {string} and {int}")
	public void user_send_the_https_get_InvalidBatchID_request_for_assignmentsubmission(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assgSub.getDatafromExcel(sheetname, rowno);
		assgSub.getAssignmentSubmissionInvalidBatchId(posturi);
	 LoggerLoad.info("***user sends the GET BatchID request****");		  
	}
	@When("User send the HTTPsGET InvalidStudentID request for AssignmentSubmission {string} and {int}")
	public void user_send_the_https_get_InvaliStudentID_request_for_assignmentsubmission(String sheetname,Integer rowno) throws InvalidFormatException, IOException{
		assgSub.getDatafromExcel(sheetname, rowno);
		assgSub.getAssignmentSubmissionInvalidStudentId(posturi);
	 LoggerLoad.info("***user sends the GET StudentID request****");		  
	}
	
	@When("User sends invalidHTTPS Request for assignmentSubmission using {string} and {int}")
	public void user_sends_invalidhttps_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assgSub.getDatafromExcel(sheetname, rowno);
	  assgSub.invalidpostassignmentsubmission(posturi);
	   
	}
	@When("User sends invalidHTTPS404 Request for assignmentSubmission using {string} and {int}")
	public void user_sends_invalidhttps404_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assgSub.getDatafromExcel(sheetname, rowno);
	  assgSub.invalidpostassignmentsubmission404(posturi);
	   
	}

	@When("User sends invalidHTTPS PUT Request for assignmentSubmission using {string} and {int}")
	public void user_sends_invalidhttps_put_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assgSub.getDatafromExcel(sheetname, rowno);
	  assgSub.invalidputtassignmentsubmission(posturi);
	   
	}
	@When("User sends invalidHTTPS404 PUT Request for assignmentSubmission using {string} and {int}")
	public void user_sends_invalidhttps404_put_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assgSub.getDatafromExcel(sheetname, rowno);
	  assgSub.invalidputassignmentsubmission404(posturi);
	}
	
	
	@When("User sends HTTPS Invalid Request for assignmentSubmission grade using {string} and {int}")
	public void user_send_the_https_invalid_get_GradeValidAssignment_request_for_assignmentsubmission(String sheetname,Integer rowno) throws InvalidFormatException, IOException  {
		assgSub.getDatafromExcel(sheetname, rowno);
		assgSub.invaldputassignmentsubmissionGrade(posturi);
	 LoggerLoad.info("***user sends the getInvalid gradeassignmentID request****");	
	}
	@When("User sends HTTPS404 invalid Request for assignmentSubmission grade using {string} and {int}")
		public void user_send_the_invalid_https_get_GradeValidAssignment_request_for_assignmentsubmission(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assgSub.getDatafromExcel(sheetname, rowno);
		assgSub.invalidputassignmentsubmissionGrade404(posturi);
		 LoggerLoad.info("***user sends the getInvalid gradeassignmentID request****");		  
		}
	
}
