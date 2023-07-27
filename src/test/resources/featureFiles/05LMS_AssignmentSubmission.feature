@User_module
Feature: Test LMS Apis batch module with rest assured library and cucumber framework
Background:
		Given User sets Authoization to "No Auth" for assignmentSubmission
@Post		
Scenario Outline:
Given User creates POST Request for the assignmentSubmission LMS API endpoint
When User sends HTTPS Request for assignmentSubmission using "<SheetName>" and <Rowno> 
Then User receives 201 Created Status with response body for assignmentSubmission
 Examples:
	|SheetName|Rowno|
  |user     |0    |    

@Get_AllSubmissions
 Scenario: validating User able to retrieve all AssignmentSubmission with valid endpoint
 Given User is provided with the BaseUrl and the Endpoints to create a GET request for AssignmentSubmission
 When  User send the HTTPsGET request for AssignmentSubmission
 Then  User receives 200 Created Status with response body for assignmentSubmission

   @Get_AssignmentSubmision_StudentID
  Scenario: validating User able to retrieve AssignmentSubmission by StudenID with valid endpoint
    Given User is provided with the BaseUrl and the Endpoints to create a GET StudentID request for AssignmentSubmission
    When  User send the HTTPsGET StudentID request for AssignmentSubmission
    Then  User receives 200 Created Status with response body for assignmentSubmission
    
    @Get_AllSubmissions
 Scenario: validating User able to retrieve all AssignmentSubmission with valid endpoint
 Given User is provided with the BaseUrl and the Endpoints to create a GET request for AssignmentSubmission
 When  User send the HTTPsGET request for AssignmentSubmission
 Then  User receives 200 Created Status with response body for assignmentSubmission
    @Get_AssignmentSubmision_BatchID     
  Scenario: validating User able to retrieve AssignmentSubmission by BatchID with valid endpoint
    Given User is provided with the BaseUrl and the Endpoints to create a GET BatchID request for AssignmentSubmission
    When  User send the HTTPsGET BatchID request for AssignmentSubmission
    Then  User receives 200 Created Status with response body for assignmentSubmission
  
   @Get_Grade_ValidAssignmentID
  Scenario: validating User able to retrieve AssignmentSubmission byID with valid endpoint
    Given User is provided with the BaseUrl and the Endpoints to create a GET GradeValidAssignment request for AssignmentSubmission
    When  User send the HTTPsGET GradeValidAssignment request valid AssignmentId for AssignmentSubmission
    Then  User receives 200 Created Status with response body for assignmentSubmission
 
   @put_assignmentsubmission
 Scenario Outline: ResubmissionAssignment
	Given User creates PUT Resubmission Request for the assignmentSubmission LMS API endpoint
	When User sends PUT_HTTPS Request for assignmentSubmission using "<SheetName>" and <Rowno> 
	Then User receives 200 Created Status with response body for assignmentSubmission
 Examples:
	|SheetName|Rowno|
  |user     |0    |   
    
 
 @put_Grade_assignment
 Scenario Outline: Grade Assignment
Given User creates PUT Request for the assignmentSubmission grade LMS API endpoint
When User sends HTTPS Request for assignmentSubmission grade using "<SheetName>" and <Rowno> 
Then User receives 200 Created Status with response body for assignmentSubmission
 Examples:
	|SheetName|Rowno|
  |user     |0    |   
 
 
  @Get_Grade_ValidStudentID
  Scenario: validating User able to retrieve AssignmentSubmission by GradeStudentID with valid endpoint
    Given User is provided with the BaseUrl and the Endpoints to create a GET GradeStudentID request for AssignmentSubmission
    When  User send the HTTPsGET GradeStudentID request for AssignmentSubmission
    Then  User receives 200 Created Status with response body for assignmentSubmission
    
     @Get_Grade_ValidBatchID
  Scenario: validating User able to retrieve AssignmentSubmission by GradeBatchID with valid endpoint
    Given User is provided with the BaseUrl and the Endpoints to create a GET GradeBatchID request for AssignmentSubmission
    When  User send the HTTPsGET GradeBatchID request for AssignmentSubmission
    Then  User receives 200 Created Status with response body for assignmentSubmission
    
     @NegativeGet_AssignmentSubmision_StudentID
 Scenario Outline: validating User able to retrieve AssignmentSubmission by StudenID with valid endpoint
    Given User is provided with the BaseUrl and the Endpoints to create a GET StudentID request for AssignmentSubmission
    When  User send the HTTPsGET InvalidStudentID request for AssignmentSubmission "<SheetName>" and <Rowno>
    Then  User receives 404 Created Status with response body for assignmentSubmission
    Examples:
	|SheetName|Rowno|
  |user     |0    |
    
    @NegativeGet_AssignmentSubmision_BatchID     
 Scenario Outline: validating User able to retrieve AssignmentSubmission by BatchID with valid endpoint
    Given User is provided with the BaseUrl and the Endpoints to create a GET BatchID request for AssignmentSubmission
    When  User send the HTTPsGET InvalidBatchID request for AssignmentSubmission "<SheetName>" and <Rowno> 
    Then  User receives 404 Created Status with response body for assignmentSubmission
    Examples:
	|SheetName|Rowno|
  |user     |0    |
  
   @NegativeGet_Grade_ValidAssignmentID
  Scenario Outline: validating User able to retrieve AssignmentSubmission byID with valid endpoint
    Given User is provided with the BaseUrl and the Endpoints to create a GET GradeValidAssignment request for AssignmentSubmission
    When  User send the HTTPsGET InvalidGradeValidAssignment request valid AssignmentId for AssignmentSubmission "<SheetName>" and <Rowno>
    Then  User receives 404 Created Status with response body for assignmentSubmission
    Examples:
		|SheetName|Rowno|
  	|user     |0    |
  
  @NegativeGet_Grade_StudentID
  Scenario Outline: validating User able to retrieve AssignmentSubmission by GradeStudentID with valid endpoint
    Given User is provided with the BaseUrl and the Endpoints to create a GET GradeStudentID request for AssignmentSubmission
    When  User send the HTTPsGET InvalidGradeStudentID request for AssignmentSubmission "<SheetName>" and <Rowno>
    Then  User receives 404 Created Status with response body for assignmentSubmission
    Examples:
	|SheetName|Rowno|
  |user     |0    |
    
     @NegativeGet_Grade_BatchID
  Scenario Outline: validating User able to retrieve AssignmentSubmission by GradeBatchID with valid endpoint
    Given User is provided with the BaseUrl and the Endpoints to create a GET GradeBatchID request for AssignmentSubmission
    When  User send the HTTPsGET InvalidGradeBatchID request for AssignmentSubmission "<SheetName>" and <Rowno>
    Then  User receives 404 Created Status with response body for assignmentSubmission
    Examples:
	|SheetName|Rowno|
  |user     |0    |
  
  
  @NegativePost400		
Scenario Outline:
Given User creates POST Request for the assignmentSubmission LMS API endpoint
When User sends invalidHTTPS Request for assignmentSubmission using "<SheetName>" and <Rowno> 
Then User receives 400 Created Status with response body for assignmentSubmission
 Examples:
	|SheetName|Rowno|
  |user     |0    |  
  
  
  @NegativePost404	
Scenario Outline:
Given User creates POST Request for the assignmentSubmission LMS API endpoint
When User sends invalidHTTPS404 Request for assignmentSubmission using "<SheetName>" and <Rowno> 
Then User receives 404 Created Status with response body for assignmentSubmission
 Examples:
	|SheetName|Rowno|
  |user     |0    |  
  
   @NegativePut400		
Scenario Outline:
Given User creates PUT Resubmission Request for the assignmentSubmission LMS API endpoint
When User sends invalidHTTPS PUT Request for assignmentSubmission using "<SheetName>" and <Rowno> 
Then User receives 400 Created Status with response body for assignmentSubmission
 Examples:
	|SheetName|Rowno|
  |user     |0    |  
  
  
  @NegativePut404	
Scenario Outline:
Given User creates PUT Resubmission Request for the assignmentSubmission LMS API endpoint
When User sends invalidHTTPS404 PUT Request for assignmentSubmission using "<SheetName>" and <Rowno> 
Then User receives 404 Created Status with response body for assignmentSubmission
 Examples:
	|SheetName|Rowno|
  |user     |0    |  
  
  @Negativeput_Grade_assignment
 Scenario Outline: Grade Assignment
Given User creates PUT Request for the assignmentSubmission grade LMS API endpoint
When User sends HTTPS Invalid Request for assignmentSubmission grade using "<SheetName>" and <Rowno> 
Then User receives 400 Created Status with response body for assignmentSubmission
 Examples:
	|SheetName|Rowno|
  |user     |0    |
  
   @Negativeput_Grade_assignment
 Scenario Outline: Grade Assignment
Given User creates PUT Request for the assignmentSubmission grade LMS API endpoint
When User sends HTTPS404 invalid Request for assignmentSubmission grade using "<SheetName>" and <Rowno> 
Then User receives 404 Created Status with response body for assignmentSubmission
 Examples:
	|SheetName|Rowno|
  |user     |0    |      