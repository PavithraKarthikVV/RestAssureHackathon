@DeleteAll
Feature: Delete ID's
 
Background:
		Given User sets Authoization to "No Auth" for delete
		
	
	@DeleteAssignmentSubmission
 	Scenario: Check if user able to delete a user with valid assignmentsubmission ID
 	Given User creates DELETE Request for the LMS API endpoint  and  valid assignmentsubmission ID
	When User sends HTTPS Request for assignmentsubmission
	Then User receives 200 Ok status with message
		
	@DeleteAssignment
 	Scenario: Check if user able to delete a user with valid assignment ID
 	Given User creates DELETE Request for the LMS API endpoint  and  valid assignment ID
	When User sends HTTPS Request for assignment
	Then User receives 200 Ok status with message 

	@DeleteUser
 	Scenario: Check if user able to delete a user with valid user ID
 	Given User creates DELETE Request for the LMS API endpoint  and  valid user ID
	When User sends HTTPS Request for user
	Then User receives 200 Ok status with message for user	
		
	@DeleteBtch
 	Scenario: Check if user able to delete a program with valid batch ID
 	Given User creates DELETE Request for the LMS API endpoint  and  valid batch ID
	When User sends HTTPS Request for batch
	Then User receives 200 Ok status with message 		
		
	@DeletePrg
 	Scenario: Check if user able to delete a program with valid program ID
 	Given User creates DELETE Request for the LMS API endpoint  and  valid program ID
	When User sends HTTPS Request
	Then User receives 200 Ok status with message	
	
	
	@NegativeDeleteAssignmentSubmission
 	Scenario: Check if user able to delete a user with valid assignmentsubmission ID
 	Given User creates DELETE Request for the LMS API endpoint  and  valid assignmentsubmission ID
	When User sends HTTPS Request for assignmentsubmission
	Then User receives 404 Ok status with message
		

@NegativeDeleteAssignment
 	Scenario: Check if user able to delete a user with valid assignment ID
 	Given User creates DELETE Request for the LMS API endpoint  and  valid assignment ID
	When User sends HTTPS Request for assignment
	Then User receives 404 Ok status with message 

	@NegativeDeleteUser
 	Scenario: Check if user able to delete a user with valid user ID
 	Given User creates DELETE Request for the LMS API endpoint  and  valid user ID
	When User sends HTTPS Request for user
	Then User receives 404 Ok status with message for user	
		
	@NegativeDeleteBtch
 	Scenario: Check if user able to delete a program with valid batch ID
 	Given User creates DELETE Request for the LMS API endpoint  and  valid batch ID
	When User sends HTTPS Request for batch
	Then User receives 404 Ok status with message 		
		
	@NegativeDeletePrg
 	Scenario: Check if user able to delete a program with valid program ID
 	Given User creates DELETE Request for the LMS API endpoint  and  valid program ID
	When User sends HTTPS Request
	Then User receives 404 Ok status with message	
	

  
	
	
 
	