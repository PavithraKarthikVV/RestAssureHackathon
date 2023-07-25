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
		Assert.assertEquals(response.getStatusCode(),statuscode ); 
		
	}

}
