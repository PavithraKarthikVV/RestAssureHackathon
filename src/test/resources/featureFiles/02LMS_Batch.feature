@Batch_module
Feature: Test LMS Apis batch module with rest assured library and cucumber framework
Background:
		Given User sets Authoization to "No Auth" for batch
		@Post
Scenario Outline:
Given User creates POST Request for the batch LMS API endpoint
When User sends HTTPS Request for batch using "<SheetName>" and <Rowno> 
Then User receives 201 Created Status with response body for batch
 Examples:
	|SheetName|Rowno|
  |user     |0    |     



	
	