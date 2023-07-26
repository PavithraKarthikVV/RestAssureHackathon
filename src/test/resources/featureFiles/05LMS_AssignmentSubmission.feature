@User_module
Feature: Test LMS Apis batch module with rest assured library and cucumber framework
Background:
		Given User sets Authoization to "No Auth" for assignmentSubmission
		
Scenario Outline:
Given User creates POST Request for the assignmentSubmission LMS API endpoint
When User sends HTTPS Request for assignmentSubmission using "<SheetName>" and <Rowno> 
Then User receives 201 Created Status with response body for assignmentSubmission
 Examples:
	|SheetName|Rowno|
  |user     |0    |    
  @tag


  @Post_ByNonExistingValue 
  Scenario: Add the assignment With Valid BaseUrl ,Endpoints nonexisting field
 
    iven User is provided with the BaseUrl and endpoint and nonexisting fields in payload
     When  User send the HTTPsPOST request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code, response time, header
    
     @Post_ByExistingValue
  Scenario: Add the assignment With Valid BaseUrl ,Endpoints and existing field
  
     iven User is provided with the BaseUrl and endpoint and existing fields in payload
     When  User send the HTTPsPOST request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code, response time, header

  @Post_MissingSubmissionFields
  Scenario Outline: Add the assignment with submission missing Parameters

    Given User is provided with the BaseUrl and endpoint and submission missing fields in payload
     When  User send the HTTPsPOST request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code, response time, header
    
    @Get_AllSubmissions
    Scenario Outline: Retrieve all submissions from valid LMS API
  
    Given User is provided with the BaseUrl ,endpoint in payload
     When  User send the HTTPsGET request with all submissions
    Then User validates the response with status code
    
     @Get_Grade_ValidAssignmentID
    Scenario Outline: Retrieve the grade with Valid AssignmentID from valid LMP API endpoint

    Given User is provided with the BaseUrl ,endpoint and Valid AssignmentID in payload
     When  User send the HTTPsGET request with Valid AssignmentID
    Then User validates the response with status code
    
     @Get_Grade_InvalidAssignmentID
    Scenario Outline: Retrieve the grade with Invalid AssignmentID from valid LMS API endpoint
  Background : No Authorization required
    Given User is provided with the BaseUrl ,endpoint and Invalid AssignmentID in payload
     When  User send the HTTPsGET request with Invalid AssignmentID
    Then User validates the response with status code
    
    
    @Get_Grade_ValidBatchID
    Scenario Outline: Retrieve the grade with Valid BatchID from valid LMS API endpoint
  Background : No Authorization required
    Given User is provided with the BaseUrl ,endpoint and Valid BatchID in payload
     When  User send the HTTPsGET request with Valid BatchID
    Then User validates the response with status code
    
    
    @Get_Grade_InvalidBatchID
    Scenario Outline: Retrieve the grade with Invalid BatchID from valid LMS API endpoint
  Background : No Authorization required
    Given User is provided with the BaseUrl ,endpoint and Invalid BatchID in payload
     When  User send the HTTPsGET request with Invalid BatchID
    Then User validates the response with status code
    
     @Get_Grade_ValidStudentID
    Scenario Outline: Retrieve the grade with Valid BatchID from valid LMS API endpoint
  Background : No Authorization required
    Given User is provided with the BaseUrl ,endpoint and Valid BatchID in payload
     When  User send the HTTPsGET request with Valid StudentID
    Then User validates the response with status code
    
    
    @Get_Grade_InvalidStudentID
    Scenario Outline: Retrieve the grade with Invalid StudentID from valid LMS API endpoint
  Background : No Authorization required
    Given User is provided with the BaseUrl ,endpoint and Invalid BatchID in payload
     When  User send the HTTPsGET request with Invalid StudentID
    Then User validates the response with status code
    
     @Get_Grade_ValidUserID
    Scenario Outline: Retrieve the submission with Valid UserID from valid LMS API endpoint
  Background : No Authorization required
    Given User is provided with the BaseUrl ,endpoint and Valid BatchID in payload
     When  User send the HTTPsGET request with Valid UserID
    Then User validates the response with status code
    
    
    @Get_Grade_InvalidUserID
    Scenario Outline: Retrieve the grade with Invalid UserID from valid LMS API endpoint
  Background : No Authorization required
    Given User is provided with the BaseUrl ,endpoint and Invalid BatchID in payload
     When  User send the HTTPsGET request with Invalid UserID
    Then User validates the response with status code
    
     @Get_Grade_ValidBatchID
    Scenario Outline: Retrieve the submission with Valid BatchID from valid LMS API endpoint
  Background : No Authorization required
    Given User is provided with the BaseUrl ,endpoint and Valid BatchID in payload
     When  User send the HTTPsGET request with Valid BatchID
    Then User validates the response with status code
    
    
    @Get_Grade_InvalidBatchID
    Scenario Outline: Retrieve the submission with Invalid BatchID from valid LMS API endpoint
  Background : No Authorization required
    Given User is provided with the BaseUrl ,endpoint and Invalid BatchID in payload
     When  User send the HTTPsGET request with Invalid BatchID
    Then User validates the response with status code
    
    
   
    @Put_validsubmissionID
    Scenario Outline: Update the submission with Valid SubmissionID from valid LMS API
  Background : No Authorization required
    Given User is provided with the BaseUrl and endpoint and Valid SubmissionID in payload
     When  User send the HTTPsPUT request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code
    
     @Put_InvalidSubmissionID
    Scenario Outline: Update the submission with Invalid SubmissionID from valid LMS API
  Background : No Authorization required
    Given User is provided with the BaseUrl and endpoint and Invalid SubmissionID in payload
     When  User send the HTTPsPUT request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code
   
   @Put_validSubmissionID_MissingField
   Scenario Outline: Update the submission with valid SubmissionID and missing mandateory field from valid LMS API
  Background : No Authorization required
    Given User is provided with the BaseUrl,endpoint,SubmissionID and missing mandateory field in payload
     When  User send the HTTPsPUT request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code
    
    
     @Put_validsubmissionID
    Scenario Outline: Update the grade assignment with Valid SubmissionID from valid LMS API
  Background : No Authorization required
    Given User is provided with the BaseUrl and endpoint and Valid SubmissionID in payload
     When  User send the HTTPsPUT request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code
    
     @Put_InvalidSubmissionID
    Scenario Outline: Update the grade assignment with Invalid SubmissionID from valid LMS API
  Background : No Authorization required
    Given User is provided with the BaseUrl and endpoint and Invalid SubmissionID in payload
     When  User send the HTTPsPUT request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code
   
   @Put_validSubmissionID_MissingField
   Scenario Outline: Update the grade assignment with valid SubmissionID and missing mandateory field from valid LMS API
  Background : No Authorization required
    Given User is provided with the BaseUrl,endpoint,SubmissionID and missing mandateory field in payload
     When  User send the HTTPsPUT request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code
    
    
    
    @Delete_ValidSubmissionID
    Scenario Outline: Delete the submission with Valid SubmissionID from valid LMS API
  Background : No Authorization required
    Given User is provided with the BaseUrl and endpoint and Valid SubmissionID in payload
     When  User send the HTTPsDELETE request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code
    
    @Delete_InvalidSubmissionID
    Scenario Outline: Delete the submission with Invalid SubmissionID from valid LMS API
  Background : No Authorization required
     Given User is provided with the BaseUrl and endpoint and Invalid SubmissionID in payload
     When  User send the HTTPsDELETE request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code
    
    
   