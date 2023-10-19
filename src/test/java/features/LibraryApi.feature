Feature: Validating the Library Api feature

@Regression @AddBooK
Scenario Outline: Verify the Add book API and validate add book using the ID

	Given Add the addbook payload with "<Isbn>" and <Aisle>
	When user calls with "AddBookAPI" with "Post" http request
	Then the API call got success with status code is 200
	And verify the ID created using "<Isbn>" and <Aisle>
	
    Examples:
    |Isbn 	  |Aisle   		  |
    |ANaveen	|123567	 		  |
    
@Regression   @GetBook
Scenario: Verify the Get book using the ID
	
	  Given Get the book details with ID
	  When user calls with "GetBookApi" with "Get" http request
		Then the API call got success with status code is 200
		And verify "ANaveen" and <123567> details in the reponse
	
	@Regression	@DeleteBook
Scenario: Verify the Delete book using the ID
		Given Delete the book with http request with payload
		When user calls with "DeleteBookApi" with "Delete" http request 
		Then the API call got success with status code is 200
		And verify the "msg" value is "book is successfully deleted"
				
		
		
		

