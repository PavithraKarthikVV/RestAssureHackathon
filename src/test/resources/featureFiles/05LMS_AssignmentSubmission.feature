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