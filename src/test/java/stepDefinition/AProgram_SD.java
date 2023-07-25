package stepDefinition;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import moduleobject.AProgram;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.ConfigReader;
public class AProgram_SD {
AProgram prg=new AProgram();
private String posturi;
Response response;
@Given("User sets Authoization to {string}")
public void user_sets_authoization_to_no_auth(String string) {
	prg.noAuthendication(string);
	}

@Given("User creates POST Request for the LMS API endpoint")
public void user_creates_post_request_for_the_lms_api_endpoint() throws IOException {
	posturi=ConfigReader.baseUri()+ConfigReader.endpointProgramSave();
}

@When("User sends HTTPS Request and  request Body with mandatory , additional using {string} and {int}")
public void user_sends_https_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException {
   prg.getDatafromExcel(sheetname, rowno);
  response= prg.postprogram(posturi);
   
}

@Then("User receives {int} Created Status with response body")
public void user_receives_created_status_with_response_body(Integer statuscode) {
	Assert.assertEquals(response.getStatusCode(),statuscode ); 
	
}


}


