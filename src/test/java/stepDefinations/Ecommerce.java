package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import resources.APIResources;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.File;

public class Ecommerce extends Utils{
	
	static String loginToken;
	static String userId;
	RequestSpecification reqSpec;
	Response resp;
	static String ProductId;
	APIResources resourceApi;
	static String orderId;
	
	@Given("Login the Ecommerce App with {string} auth login Endpoint")
	public String login_the_ecommerce_app_with_auth_login_endpoint(String apiEndPoint) {
		resourceApi= APIResources.valueOf(apiEndPoint);
		if(loginToken==null) {
		Response loginRes=  given().spec(loginEcommerceSpec()).post(resourceApi.getResources())
				.then().assertThat().statusCode(200).extract().response();
		loginToken=getJsonPath(loginRes,"token");
		userId=getJsonPath(loginRes,"userId");
		System.out.println(loginToken);
		System.out.println(userId);

		}
		return loginToken;
	}
	
	@Given("Add the product with {string} and UserID")
	public void add_the_product_with_and_user_id(String proName) {
		reqSpec=given().log().all().spec(ecomLoginSpec(loginToken))
				.header("Content-Type","multipart/form-data; boundary=<calculated when request is sent>")
				.formParam("productName",proName).formParam("productAddedBy",userId).formParam("productCategory","Vechicle")
				.formParam("productSubCategory","Car for travelling").formParam("productPrice","10000000")
				.formParam("productDescription", "Mahindra Thar").formParam("productFor", "Travel")
				.multiPart("productImage", new File(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\mahindra-thar.jpg"));
	}
	@When("User calls with {string} and {string} request")
	public void user_calls_with_and_request(String apiEndPoint, String method) {
		resourceApi= APIResources.valueOf(apiEndPoint);
		if(method.equalsIgnoreCase("Post")) {
		 resp= reqSpec.when().log().all().post(resourceApi.getResources());
		}
		else if(method.equalsIgnoreCase("Get")) {
			resp= reqSpec.when().log().all().get(resourceApi.getResources());
		}
		else if(method.equalsIgnoreCase("Delete")) {
			resp= reqSpec.when().log().all().delete(resourceApi.getResources());
			System.out.println("Response print"+resp.asString());
			System.out.println("Response print status code: "+resp.getStatusCode());

		}
	}
	@Then("Get the ProductID and verify the message is {string}")
	public void get_the_product_id_and_verify_the_message_is(String messageText) {
		ProductId=getJsonPath(resp,"productId");
		assertEquals(getJsonPath(resp,"message"),messageText);
		System.out.println("Message for the reponse: "+getJsonPath(resp,"message"));
		System.out.println("Product ID is:"+ProductId);
	}
	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(int statusCode) {
		//assertEquals(resp.getStatusCode(),statusCode);
		assertEquals(resp.getStatusCode(),statusCode);
		System.out.println(resp.getStatusCode());
	}
	@Given("^Create Order with (.+) and ProductId$")
	public void create_order_with_country_and_product_id(String Country) {
		System.out.println("Product ID is in create order:"+ProductId);
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		//Authorization
		 reqSpec= given().log().all().spec(createOrderSpec(loginToken,Country,ProductId));
	
	}
	@Then("Get orderID and verify the message is {string}")
	public void get_order_id_and_verify_the_message_is(String responseMeassage) {
	    // Write code here that turns the phrase above into concrete actions
		orderId=getJsonPath(resp,"orders[0]");
		assertEquals(getJsonPath(resp,"message"),responseMeassage);
	}
	@Given("Get order details with orderID")
	public void get_order_details_with_order_id() {
		reqSpec=given().spec(getOrderSpec(loginToken,orderId));
	}
	@Then("Verify the message is {string}")
	public void verify_the_message_is(String messageText) {
		assertEquals(getJsonPath(resp,"message"),messageText);	
		}
	@Given("Delete the product with productID")
	public void delete_the_product_with_product_id() {
	    // Write code here that turns the phrase above into concrete actions
		reqSpec=given().spec(ecomLoginSpec(loginToken)).pathParam("productId", ProductId);
	}
}
