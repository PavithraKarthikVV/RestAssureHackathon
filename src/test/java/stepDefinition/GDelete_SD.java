package stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import moduleobject.AProgram;
import moduleobject.BBatch;
import moduleobject.GDelete;
import utilities.ConfigReader;

public class GDelete_SD {
	GDelete delete=new GDelete();
	private String posturi;
	Response response;
	@Given("User sets Authoization to {string} for delete")
	public void user_sets_authoization_to_no_auth(String string) {
		delete.noAuthendication(string);
		}
	
	
	@Given("User creates DELETE Request for the LMS API endpoint  and  valid program ID")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_id() throws IOException {
		posturi=ConfigReader.baseUri()+ConfigReader.endpointProgramDeleteid();
	}

	@When("User sends HTTPS Request")
	public void user_sends_https_request() {
		System.out.println(posturi);
		 response= delete.deleteprogramid(posturi);
	}
	

@Then("User receives {int} Ok status with message")
public void user_receives_ok_status_with_message(Integer statuscode) {
	Assert.assertEquals(response.getStatusCode(),statuscode ); 
}

@Given("User creates DELETE Request for the LMS API endpoint  and  valid batch ID")
public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_batch_id() throws IOException {
	posturi=ConfigReader.baseUri()+ConfigReader.endpointBatchDeleteid();
}

@When("User sends HTTPS Request for batch")
public void user_sends_https_request_for_batch() throws IOException {
	System.out.println(posturi);
		response= delete.deletebatchid(posturi);
}

@Given("User creates DELETE Request for the LMS API endpoint  and  valid user ID")
public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_user_id() throws IOException {
	posturi=ConfigReader.baseUri()+ConfigReader.endpointUserDeleteid();
}

@When("User sends HTTPS Request for user")
public void user_sends_https_request_for_user() throws IOException {
		System.out.println(posturi);
		 delete.deleteuserid(posturi);}
@Then("User receives {int} Ok status with message for user")
public void user_receives_ok_status_with_message_for_user(Integer statuscode) {
	Assert.assertEquals(GDelete.status,statuscode ); 
}
@Given("User creates DELETE Request for the LMS API endpoint  and  valid assignment ID")
public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_assignment_id() throws IOException {
	posturi=ConfigReader.baseUri()+ConfigReader.endpointAssignmentDeleteid();
}

@When("User sends HTTPS Request for assignment")
public void user_sends_https_request_for_assignment() throws IOException {
	System.out.println(posturi);
		response= delete.deleteassignmentid(posturi);
}		
	
@Given("User creates DELETE Request for the LMS API endpoint  and  valid assignmentsubmission ID")
public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_assignmentsubmission_id() throws IOException {
	posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgSubmissionDeleteid();
}

@When("User sends HTTPS Request for assignmentsubmission")
public void user_sends_https_request_for_assignmentsubmission() throws IOException {
	System.out.println(posturi);
		response= delete.deleteassignmentsubmissionid(posturi);
}		


}
