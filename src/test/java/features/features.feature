Feature: Create and Update Defects in Jira using Rest API

@Login
Scenario: User should be able to login successfully to JIRA
	Given Add Login to JIRA API Payload
	When User should call the "LoginAPI" using "POST" http method
	Then API call got success with the response code 200

@CreateIssue
Scenario: Creation of Issue in JIRA using JIRA Rest API
	Given Add Create Issue API Payload
#	When User should call the "CreateIssue" using "POST" http method
#	Then API call got success with the response code 201
#	And Log the "id" and "key" from the API response
#	And Attach the defect log file to the created issue
	
@CloseIssue
Scenario: Close the Issue in Jira using JIRA Rest API
	Given Add Close Issue API Payload
#	When User should call the "CloseIssue" using "POST" http method
#	Then API call got success with the response code 204
#	And Attach the defect log file to the closed issue