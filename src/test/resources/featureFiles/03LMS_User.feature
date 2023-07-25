@User_module
Feature: Test LMS Apis batch module with rest assured library and cucumber framework
Background:
		Given User sets Authoization to "No Auth" for user
		
Scenario Outline:
Given User creates POST Request for the user LMS API endpoint
When User sends HTTPS Request for user using "<SheetName>" and <Rowno> 
Then User receives 201 Created Status with response body for user
 Examples:
	|SheetName|Rowno|
  |user     |0    |     
  
  
  @PUT_updateUserwithuserID @Positiveu @smoke
  Scenario Outline: User tries to update a user with valid User ID and request body
    Given User is provided with the BaseUrl and the Endpoints for update user
    When User tries to update First Name, Last Name, Time Zone, and Visa Status "<sheetname>" and <rownumber>
    #Then User gets all Users related info by ID in response 200
    Examples:
    |sheetname|rownumber|
    |user|0|