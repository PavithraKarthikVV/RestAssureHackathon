@User_module
Feature: Test LMS Apis batch module with rest assured library and cucumber framework
Background:
		Given User sets Authoization to "No Auth" for assignment
		
Scenario Outline:
Given User creates POST Request for the assignment LMS API endpoint
When User sends HTTPS Request for assignment using "<SheetName>" and <Rowno> 
Then User receives 201 Created Status with response body for assignment
 Examples:
	|SheetName|Rowno|
  |user     |0    |    