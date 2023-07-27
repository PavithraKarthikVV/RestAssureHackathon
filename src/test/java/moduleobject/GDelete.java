package moduleobject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.LoggerLoad;

public class GDelete {
	RequestSpecification requestSpecification;
	Response response;
	private String noAuth;
	public static int status;
	public RequestSpecification noAuthendication(String noauth)
	{
		noAuth=noauth;	
	RequestSpecification requestSpecification = RestAssured.given()
			.header("Authorization", noauth).contentType("application/json");
	return requestSpecification;
	}
	
	public Response deletebatchid(String uri)
	{LoggerLoad.info("----Delete Batch----");
		return response=noAuthendication(noAuth).delete(uri+"{btchid}",BBatch.BatchId);
	
	}
	
	public Response deleteprogramid(String uri)
	{
		LoggerLoad.info("----Delete Program----");
		return response=noAuthendication(noAuth).delete(uri+"{progid}",AProgram.ProgramId);	
	}
	
	public void deleteuserid(String uri)
	{
		for(int i=0;i<3;i++)
		{
		response=noAuthendication(noAuth).delete(uri+"{uid}",CUser.UserId[i]);	
		 status=response.getStatusCode();
		 LoggerLoad.info("----Delete User----");
		}
	}
	
	public Response deleteassignmentid(String uri)
	{
		LoggerLoad.info("----Delete Assignment----");
		return response=noAuthendication(noAuth).delete(uri+"{assignid}",DAssignment.AssignmentId);	
	}
	public Response deleteassignmentsubmissionid(String uri)
	{
		LoggerLoad.info("----Delete AssignmentSubmission----");
		return response=noAuthendication(noAuth).delete(uri+"{assignsubid}",FAssignmentSubmission.AssignmentSubmissionId);	
	}

	
	
}
