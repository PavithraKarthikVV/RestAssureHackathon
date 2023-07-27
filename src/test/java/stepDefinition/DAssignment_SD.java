package stepDefinition;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import moduleobject.DAssignment;
import utilities.ConfigReader;
import utilities.LoggerLoad;

public class DAssignment_SD {
	DAssignment assg=new DAssignment();
	private String posturi;
	Response response;
	String noAuth;
	@Given("User sets Authoization to {string} for assignment")
	public void user_sets_authoization_to_no_auth(String string) {
		assg.noAuthendication(string);
		noAuth=string;
				}

	@Given("User creates POST Request for the assignment LMS API endpoint")
	public void user_creates_post_request_for_the_user_lms_api_endpoint() throws IOException {
		posturi=ConfigReader.baseUri()+ConfigReader.endpointAssignmentSave();
	}

	@When("User sends HTTPS Request for assignment using {string} and {int}")
	public void user_sends_https_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException, ParseException {
		assg.getDatafromExcel(sheetname, rowno);
	  response= assg.postassignment(posturi);
	   
	}
	@Then("User receives {int} Created Status with response body for assignment")
	public void user_receives_created_status_with_response_body(Integer statuscode) {
		Assert.assertEquals(assg.statuscode,statuscode ); 		
	}
	
	@Given("User is provided with the BaseUrl and the Endpoints to create a GET request for Assignment")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_create_a_get_request() throws IOException {
	  posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgnmentgetAll();
	 
	}

	@When("User send the HTTPsGET request for Assignment")
	public void user_send_the_https_get_request_for_assignment() throws IOException {
	 assg.getAllAssignment(posturi);
	}

	@Given("User is provided with the BaseUrl and the Endpoints to create a GET by Id request for Assignment")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_create_a_get_by_id_request() throws IOException {
	  posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgnmentgetbyId();
	 
	}

	@When("User send the HTTPsGET by Id request for Assignment")
	public void user_send_the_https_get_by_id_request_for_assignment() throws IOException {
	 assg.getAssignmentbyId(posturi);
	}
	
	
	@Given("User is provided with the BaseUrl and the Endpoints to create a GET by batch request for Assignment")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_create_a_get_by_batch_request() throws IOException {
	  posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgnmentByBatch();
	 
	}

	@When("User send the HTTPsGET by batch request for Assignment")
	public void user_send_the_https_get_by_batch_request_for_assignment() throws IOException {
	 assg.getAssignmentbybatchid(posturi);
	}
	
	
	@Given("User creates PUT Request for the assignment LMS API endpoint")
	public void user_creates_post_request_for_the_assignmentSubmission_lms_api_endpoint() throws IOException {
		posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgnmentput();
	}
	@When("User sends PUT_HTTPS Request for assignment using {string} and {int}")
	public void user_sends_put_https_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assg.getDatafromExcel(sheetname, rowno);
	  assg.putassignment(posturi);
	   
	}
	
	/*Negative*/
	@Given("User creates InvalidPOST Request for the assignment LMS API endpoint")
	public void user_creates_invalid_post_request_for_the_user_lms_api_endpoint() throws IOException {
		posturi=ConfigReader.baseUri()+ConfigReader.endpointAssignmentSave();
	}

	@When("User sends HTTPS InvalidRequest for assignment using {string} and {int}")
	public void user_sends_https_invali_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException, ParseException {
		assg.getDatafromExcel(sheetname, rowno);
	  response= assg.invalidpostassignment(posturi);
	   
	}
	@When("User sends HTTP InvalidRequest for assignment using {string} and {int}")
	public void user_sends_http_invali_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException, ParseException {
		assg.getDatafromExcel(sheetname, rowno);
	  response= assg.invalipostassignment404(posturi);
	   
	}
	
	@When("User send the HTTPsGET by invaldbatch request for Assignment using {string} and {int}")
	public void user_send_the_https_get_by_invalid_batch_request_for_assignment(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
	assg.getDatafromExcel(sheetname, rowno);
		assg.getAssignmentbyinvalidbatchid(posturi);
	}
	@When("User send the HTTPsGET by invalidId request for Assignment using {string} and {int}")
	public void user_send_the_https_get_by_invalidid_request_for_assignment(String sheetname,Integer rowno) throws InvalidFormatException, IOException{
		assg.getDatafromExcel(sheetname, rowno);
		assg.getAssignmentbyinvalidId(posturi);
	}
	
	@When("User sends InvalidPUT_HTTPS Request for assignment using {string} and {int}")
	public void user_sends_invalid_put_https_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assg.getDatafromExcel(sheetname, rowno);
	  assg.invalidputassignment(posturi);
	   
	}
}
