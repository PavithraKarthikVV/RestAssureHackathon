
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
 	
 	
 		
	