package stepDefinition;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import moduleobject.BBatch;
import moduleobject.AProgram;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.ConfigReader;

public class BBatch_SD {
	BBatch btch=new BBatch();
	private String posturi;
	Response response;
	String noAuth;
	@Given("User sets Authoization to {string} for batch")
	public void user_sets_authoization_to_no_auth(String string) {
		btch.noAuthendication(string);
		noAuth=string;
				}

	@Given("User creates POST Request for the batch LMS API endpoint")
	public void user_creates_post_request_for_the_batch_lms_api_endpoint() throws IOException {
		posturi=ConfigReader.baseUri()+ConfigReader.endpointBatchSave();
	}

	@When("User sends HTTPS Request for batch using {string} and {int}")
	public void user_sends_https_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
		btch.getDatafromExcel(sheetname, rowno);
	  response= btch.postbatch(posturi);
	   
	}
	@Then("User receives {int} Created Status with response body for batch")
	public void user_receives_created_status_with_response_body(Integer statuscode) {
		Assert.assertEquals(response.getStatusCode(),statuscode ); 
		
	}

	
}
