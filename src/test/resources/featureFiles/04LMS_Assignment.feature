@User_module
Feature: Test LMS Apis batch module with rest assured library and cucumber framework
Background:
		Given User sets Authoization to "No Auth" for assignment
	@Post	
Scenario Outline:
Given User creates POST Request for the assignment LMS API endpoint
When User sends HTTPS Request for assignment using "<SheetName>" and <Rowno> 
Then User receives 201 Created Status with response body for assignment
 Examples:
	|SheetName|Rowno|
  |user     |0    |    
  
  
@Get_AllAssignment
 Scenario: validating User able to retrieve all Assignment with valid endpoint
 Given User is provided with the BaseUrl and the Endpoints to create a GET request for Assignment
 When  User send the HTTPsGET request for Assignment
 Then  User receives 200 Created Status with response body for assignment
 
 @Get_AssignmentId
 Scenario: validating User able to retrieve Assignment by id with valid endpoint
 Given User is provided with the BaseUrl and the Endpoints to create a GET by Id request for Assignment
 When  User send the HTTPsGET by Id request for Assignment
 Then  User receives 200 Created Status with response body for assignment
 
 @Get_AssignmentBatch
 Scenario: validating User able to retrieve Assignment by batch with valid endpoint
 Given User is provided with the BaseUrl and the Endpoints to create a GET by batch request for Assignment
 When  User send the HTTPsGET by batch request for Assignment
 Then  User receives 200 Created Status with response body for assignment
 
 
 @Put_Assignment
 Scenario Outline: Assignment
	Given User creates PUT Request for the assignment LMS API endpoint
	When User sends PUT_HTTPS Request for assignment using "<SheetName>" and <Rowno> 
	Then User receives 200 Created Status with response body for assignment
 Examples:
	|SheetName|Rowno|
  |user     |0    |
 
 
 @Negative_post
 @InvalidPost	
Scenario Outline:
Given User creates InvalidPOST Request for the assignment LMS API endpoint
When User sends HTTPS InvalidRequest for assignment using "<SheetName>" and <Rowno> 
Then User receives 400 Created Status with response body for assignment
 Examples:
	|SheetName|Rowno|
  |user     |0    | 
   
 
  @Get_InvalidAssignmentId
 Scenario Outline: validating User able to retrieve Assignment by Invalidid with valid endpoint
 Given User is provided with the BaseUrl and the Endpoints to create a GET by Id request for Assignment
 When  User send the HTTPsGET by invalidId request for Assignment using "<SheetName>" and <Rowno>
 Then  User receives 404 Created Status with response body for assignment
 Examples:
	|SheetName|Rowno|
  |user     |0    |  
  
 @Get_InvalidAssignmentBatch
 Scenario Outline: validating User able to retrieve Assignment by Invalidbatch with valid endpoint
 Given User is provided with the BaseUrl and the Endpoints to create a GET by batch request for Assignment
 When  User send the HTTPsGET by invaldbatch request for Assignment using "<SheetName>" and <Rowno>
 Then  User receives 404 Created Status with response body for assignment
 Examples:
	|SheetName|Rowno|
  |user     |0    |  
  
   
 @InvalidPut_Assignment
 Scenario Outline: Assignment
	Given User creates PUT Request for the assignment LMS API endpoint
	When User sends InvalidPUT_HTTPS Request for assignment using "<SheetName>" and <Rowno> 
	Then User receives 404 Created Status with response body for assignment
 Examples:
	|SheetName|Rowno|
  |user     |0    |
  