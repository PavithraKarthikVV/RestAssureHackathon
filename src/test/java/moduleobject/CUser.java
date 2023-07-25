package moduleobject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ExcelReader;
import utilities.commonfun;

public class CUser {
	private String noAuth;
	private String usercomments;
	private String usereduPg;
	private String userduUg;
	private String userfirstname;
	private String userlastname;
	private String userlinkedinUrl;
	private String userlocation;
	private String usermiddlename;
	private String roleid;
	private String userRoleStatus;
	private String userTimeZone;
	private String userVisaStatus;
	private String userid;
	public static String UserId;
	public static String StaffId;
	public static String AdminId;
	public static String StudId;
	RequestSpecification requestSpecification;
	Response response;
	public RequestSpecification noAuthendication(String noauth)
	{
		noAuth=noauth;	
	RequestSpecification requestSpecification = RestAssured.given()
			.header("Authorization", noauth).contentType("application/json");
	return requestSpecification;
	}

	public void getDatafromExcel(String sheetname, int rownumber) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		ExcelReader reader = new ExcelReader();
		String data[]=new String[2];
		List<Map<String, String>> testdata;
		try {
			testdata = reader.getData("src/test/resources/testData/data.xlsx", sheetname);
			usercomments = testdata.get(rownumber).get("userComments");	
			 userduUg=testdata.get(rownumber).get("userEduPg");
			 usereduPg=testdata.get(rownumber).get("userEduUg");
			 userfirstname = testdata.get(rownumber).get("userFirstName");	
			 userlastname=testdata.get(rownumber).get("userLastName");
			 userlinkedinUrl=testdata.get(rownumber).get("userLinkedinUrl");
			 userlocation = testdata.get(rownumber).get("userLocation");	
			 usermiddlename=testdata.get(rownumber).get("userMiddleName");
			 roleid=testdata.get(rownumber).get("roleIdR01");
			 userRoleStatus=testdata.get(rownumber).get("userRoleStatusActive");
			 userTimeZone = testdata.get(rownumber).get("TimeEST");	
			 userVisaStatus=testdata.get(rownumber).get("userVisaStatus");		
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	
	
	public Response postuser(String uri) throws IOException, ParseException
	{ObjectMapper obj=new ObjectMapper();
		ObjectNode objnode = obj.createObjectNode();
		objnode.put("userComments",usercomments);
		objnode.put("userEduPg", usereduPg);
		objnode.put("userEduUg", userduUg);
		objnode.put("userFirstName",userfirstname);
		objnode.put("userId","");
		objnode.put("userLastName", userlastname);
		objnode.put("userLinkedinUrl", userlinkedinUrl);
		objnode.put("userLocation",userlocation);
		objnode.put("userMiddleName", usermiddlename);
		objnode.put("userPhoneNumber", commonfun.phoneNum());
		//System.out.println(commonfun.phoneNum());
		ObjectNode objnode2 = obj.createObjectNode();
		objnode2.put("roleId",roleid);
		objnode2.put("userRoleStatus", userRoleStatus);
		ArrayNode  objnode3 = obj.createArrayNode();
		objnode3.add(objnode2);
		objnode.set("userRoleMaps",objnode3);
		objnode.arrayNode();
		objnode.put("userTimeZone",userTimeZone);
		objnode.put("userVisaStatus", userVisaStatus);
		String payload = objnode.toString();
		String createdNestedJsonObject = obj.writerWithDefaultPrettyPrinter().writeValueAsString(objnode);
		System.out.println("User Request : \n"+ createdNestedJsonObject);
		Response response = noAuthendication(noAuth).body(payload).post(uri);
		UserId=response.jsonPath().getString("userId");
//		if(roleid=="R01")
//		{
//			AdminId=response.jsonPath().getString("userRoleId");
//		}
//		else if(roleid=="R02")
//		{
//			StaffId=response.jsonPath().getString("userRoleId");
//		}
//		else
//		{StudId=response.jsonPath().getString("userRoleId");}
//			
		System.out.println("User Response:\n"+response.jsonPath().prettyPrint());
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public Response putuser(String postURI) throws JsonProcessingException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("roleId",roleid);
		jsonObject.put("userRoleStatus",userRoleStatus);			
		String payload = jsonObject.toString();
		Response response = noAuthendication(noAuth).body(payload).put(postURI+"{uid}",CUser.UserId);
		String body=response.statusLine();	
		System.out.println("MESSAGE:"+body);
		return response;
		
	}
	
}
