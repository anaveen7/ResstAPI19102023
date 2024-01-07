Feature: Ecommerce page portal validation for API requests

Background:
Given Login the Ecommerce App with "LoginEcomAPI" auth login Endpoint 

@All
Scenario Outline: Validate Add product details

Given Add the product with "<ProductName>" and UserID
When User calls with "AddProductAPI" and "Post" request
Then Get the ProductID and verify the message is "Product Added Successfully"
And API call got success with status code 201

Examples:
	|ProductName |
	|Car				 |
	
@All	
Scenario Outline: Create order with API request with in Ecommerce page 
Given Create Order with <Country> and ProductId
When User calls with "CreateProductAPI" and "Post" request
Then Get orderID and verify the message is "Order Placed Successfully"
And API call got success with status code 201

Examples:
|Country	|
|India		|

@All
Scenario: View the order details in Ecommerce page
Given Get order details with orderID
When User calls with "GetOrderDetailsAPI" and "Get" request
Then Verify the message is "Orders fetched for customer Successfully"
And API call got success with status code 200

@All
Scenario: Verify Delete product
Given Delete the product with productID
When User calls with "DeleteProductAPI" and "Delete" request
Then Verify the message is "Product Deleted Successfully"
And API call got success with status code 200