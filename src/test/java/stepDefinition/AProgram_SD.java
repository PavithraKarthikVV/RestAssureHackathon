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
import utilities.LoggerLoad;
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
  LoggerLoad.info("***user sends the post  request****");
   
}

@Then("User receives {int} Created Status with response body")
public void user_receives_created_status_with_response_body(Integer statuscode) {
	Assert.assertEquals(AProgram.statuscode,statuscode ); 
	
}

@Given("User is provided with the BaseUrl and the Endpoints to create a GET request")
public void user_is_provided_with_the_base_url_and_the_endpoints_to_create_a_get_request() throws IOException {
  posturi=ConfigReader.baseUri()+ConfigReader.getAllEndpoint();
  LoggerLoad.info("***Sending the GetAllProgram Given code***" + posturi);
}

@When("User send the HTTPsGET request")
public void user_send_the_https_get_request() throws IOException {
 prg.getAllprogram(posturi);
 LoggerLoad.info("***user sends the get all request****");		  
}

@Given("User is provided with the BaseUrl and the Endpoints to create a GET request with valid program id")
public void user_is_provided_with_the_base_url_and_the_endpoints_to_create_a_get_request_with_valid_program_id() throws IOException {

	posturi=ConfigReader.baseUri()+ConfigReader.getOneProgramIdEndpoint();
   LoggerLoad.info("***Sending the GetAllProgram Given code***" + posturi);
}

@When("User send the HTTPsGET request with valid programID")
public void user_send_the_htt_ps_get_request_with_valid_program_id() throws IOException
{	prg.getprogramid(posturi);
   LoggerLoad.info("***user sends the get request by sending the program id***");
}


@Given("User is provided with the BaseUrl and the Endpoints to update status with programName")
public void user_is_provided_with_the_base_url_and_the_endpoints_to_update_status_with_program_name() throws IOException {
   posturi=ConfigReader.baseUri()+ConfigReader.putProgramByProgramNameEndpoint();
   LoggerLoad.info("**********Given baseurl and endpoint for update program with name*********");
}

@SuppressWarnings("unchecked")
@When("User send the HTTPsPUT request with valid programName from {string} and {int}")
public void user_send_the_htt_ps_put_request_with_valid_program_name(String SheetName, Integer rowno) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
	prg.getDatafromExcel(SheetName, rowno);
	prg.putprogramname(posturi)	;
    LoggerLoad.info("****************successfully updated with program name*********************");
}



@Given("User is provided with the BaseUrl and the Endpoints to update status with programID")
public void user_is_provided_with_the_base_url_and_the_endpoints_to_update_status_with_program_id() throws IOException {
	
  posturi=ConfigReader.baseUri()+ ConfigReader.putProgramByProgramIdEndpoint();
  LoggerLoad.info("******given with the baseurl and endpoint for update program status with programid*******");
		   
}

@When("User send the HTTPsPUT request with valid programId from {string} and {int}")
public void user_send_the_htt_ps_put_request_with_valid_program_id(String SheetName, Integer rowno) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException, IOException {
	prg.getDatafromExcel(SheetName, rowno);
	prg.putprogramid(posturi)	;
    LoggerLoad.info("****************successfully updated with program name*********************");
}



}


