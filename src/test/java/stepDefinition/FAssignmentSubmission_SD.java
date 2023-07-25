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
	public void user_creates_post_request_for_the_assignmentSubmission_lms_api_endpoint() throws IOException {
		posturi=ConfigReader.baseUri()+ConfigReader.endpointAssgSubmissionSave();
	}

	@When("User sends HTTPS Request for assignmentSubmission using {string} and {int}")
	public void user_sends_https_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		assgSub.getDatafromExcel(sheetname, rowno);
	  response= assgSub.postassignmentsubmission(posturi);
	   
	}
	@Then("User receives {int} Created Status with response body for assignmentSubmission")
	public void user_receives_created_status_with_response_body(Integer statuscode) {
		Assert.assertEquals(response.getStatusCode(),statuscode ); 
		
	}

}
