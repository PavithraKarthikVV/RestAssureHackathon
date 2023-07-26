
@Program_module
Feature: Test LMS Apis program module with rest assured library and cucumber framework
Background:
Given User sets Authoization to "No Auth"

@Post
Scenario Outline:
Given  User creates POST Request for the LMS API endpoint  
When User sends HTTPS Request and  request Body with mandatory , additional using "<SheetName>" and <Rowno>        
Then User receives 201 Created Status with response body
Examples:
	|SheetName|Rowno|
  |user|0|  
 	
 	
 @GET_PositiveAllPrograms 
  Scenario: validating User able to retrieve all programs with valid endpoint
    Given User is provided with the BaseUrl and the Endpoints to create a GET request
    When  User send the HTTPsGET request
    Then  User receives 200 Created Status with response body
 	
 		
	@GET_PositiveByValidProgramID 
  Scenario: validating User able to retrieve program with valid programID
    Given User is provided with the BaseUrl and the Endpoints to create a GET request with valid program id
    When  User send the HTTPsGET request with valid programID
    Then  User receives 200 Created Status with response body
 	
    
 
   @PUT_PositiveUsingValidProgramID @positive
  Scenario Outline: validating user able to update a program with valid programID and Payload
    Given User is provided with the BaseUrl and the Endpoints to update status with programID
    When 	User send the HTTPsPUT request with valid programId from "<sheetname>" and <rownumber>
    Then  User receives 200 Created Status with response body
 	 Examples:
			| sheetname |rownumber|
			| user			| 0  |		     
    
    @PUT_PositiveUsingValidProgramName @positive
  Scenario Outline: validating user able to update a program with valid programName and Payload
    Given User is provided with the BaseUrl and the Endpoints to update status with programName
    When  User send the HTTPsPUT request with valid programName from "<sheetname>" and <rownumber>
    Then  User receives 200 Created Status with response body
 	 
    Examples:
			| sheetname |rownumber|
			| user			| 0  |		 
   