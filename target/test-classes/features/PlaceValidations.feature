Feature: Validation Place API's

@AddPlace @Regression
Scenario Outline: Verify if Place is being added succesfully added using AddPlaceAPI
    Given Add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" is response body is "OK"
    And "scope" is response body is "APP"
    And verify place_ID created maps to "<name>" using "GetPlaceAPI"
    
    Examples:
    |name 	|language   |address								|
    |Naveen	|English	  |Gachibowlic Atrium Mall|
#   |Naveen	|Telugu 	  |Gachibowlic Centro     |

@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
		
		Given DeletePlace Payload
		When user calls "DeletePlaceAPI" with "Post" http request 
    Then the API call got success with status code 200
    And "status" is response body is "OK"