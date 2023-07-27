package stepDefinition;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import moduleobject.CUser;
import utilities.ConfigReader;
import utilities.LoggerLoad;

public class CUser_SD {
	CUser usr=new CUser();
	private String posturi;
	Response response;
	String noAuth;
	@Given("User sets Authoization to {string} for user")
	public void user_sets_authoization_to_no_auth(String string) {
		usr.noAuthendication(string);
		noAuth=string;
				}

	@Given("User creates POST Request for the user LMS API endpoint")
	public void user_creates_post_request_for_the_user_lms_api_endpoint() throws IOException {
		posturi=ConfigReader.baseUri()+ConfigReader.endpointUserSave();
	}

	@When("User sends HTTPS Request for user using {string} and {int}")
	public void user_sends_https_request_and_request_body_with_mandatory_additional_using_and_rowno(String sheetname,Integer rowno) throws InvalidFormatException, IOException, ParseException {
		usr.getDatafromExcel(sheetname, rowno);
		usr.postuser(posturi);
	   
	}
	@Then("User receives {int} Created Status with response body for user")
	public void user_receives_created_status_with_response_body(Integer statuscode) {
		Assert.assertEquals(CUser.statuscode,statuscode ); 
		
	}
	
	
	@Given("User is provided with the BaseUrl and the Endpoints for update user")
	public void user_is_provided_with_the_base_url_and_the_endpoints_for_update_user() throws IOException {
	    posturi=ConfigReader.baseUri()+ConfigReader.userUpdateuserrolestatus();
	    System.out.println(posturi);
	    //LoggerLoad.info("*user gets the put request by userid*"+posturi);
	    
	}

	@When("User tries to update First Name, Last Name, Time Zone, and Visa Status {string} and {int}")
	public void user_tries_to_update_first_name_last_name_time_zone_and_visa_status(String sheetname,Integer rownumber) throws InvalidFormatException, IOException {
	    usr.getDatafromExcel(sheetname,rownumber);
	    response=usr.putuser(posturi);
	   // LoggerLoad.info("*user sends the put request with payload and userid*");
	}

	@Then("User is able to update First Name, Last Name, Time Zone, and Visa Status")
	public void user_is_able_to_update_first_name_last_name_time_zone_and_visa_status(int expectedstatuscode) {
		Assert.assertEquals(response.statusCode(), expectedstatuscode);	
	}

	
	@Given("User executes GET Request to get User info by ID")
	public void user_executes_get_request_to_get_user_info_by_id() throws IOException {
	    posturi=ConfigReader.baseUri()+ConfigReader.endpointUserDeleteid();
	    LoggerLoad.info("*********"+posturi);
	    
	}

	@When("User send GET Request to get User info by ID")
	public void user_send_get_request_to_get_user_info_by_id() {
	    //response=when().get(postURI);
		usr.getuserbyid(posturi);
	    LoggerLoad.info("****user got by userid*****");
	}

	

	
}
