package stepDefinations;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.xmlbeans.impl.values.JavaListObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static org.junit.Assert.*;



public class LibraryApi extends Utils{
	RequestSpecification req;
	Response response;
	static String id;

	TestDataBuild data = new TestDataBuild();
	
	@Given("Add the addbook payload with {string} and {int}")
	public void add_the_addbook_payload_with_and(String Isbn, Integer Aisle) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		req= given().spec(requestSpecificationForBook()).body(data.addBook(Isbn, Aisle));
	}
	@When("user calls with {string} with {string} http request")
	public void user_calls_with_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resourceApi=APIResources.valueOf(resource);
		if(method.equalsIgnoreCase("POST")) {
		response=req.when().post(resourceApi.getResources());
		}
		else if(method.equalsIgnoreCase("GET"))
		{
		response=req.when().post(resourceApi.getResources());
		}
		else if(method.equalsIgnoreCase("DELETE"))
		{
		response=req.when().post(resourceApi.getResources());
		}
	}
	@Then("the API call got success with status code is {int}")
	public void the_api_call_got_success_with_status_code_is(int statuscode) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),statuscode);
	}
	@Then("{string} reponse body is {string}")
	public void reponse_body_is(String key, String expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(getJsonPath(response,key),expectedvalue);
	}
	@Then("verify the ID created using {string} and {int}")
	public void verify_the_id_created_using_and(String isbn, Integer aisle) {
	    // Write code here that turns the phrase above into concrete actions
		String Aisle=String.valueOf(aisle);
		String expectedID=isbn+Aisle;
		id=getJsonPath(response,"ID");
		assertEquals(id,expectedID);
	}
	
	@Given("Get the book details with ID")
	public void get_the_book_details_with_id() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		req=given().spec(requestSpecificationForBook()).queryParam("ID",id);
	}

	@Then("verify {string} and <{int}> details in the reponse")
	public void verify_and_details_in_the_reponse(String Isbn, Integer Aisle) {
		assertEquals(getJsonArrayObject(response,"isbn"),Isbn);
		assertEquals(getJsonArrayObject(response,"aisle"),Aisle.toString());

	}
	@Given("Delete the book with http request with payload")
	public void delete_the_book_with_http_request_with_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		req=given().spec(requestSpecificationForBook()).body(data.deleteBook(id));
	}

	@Then("verify the {string} value is {string}")
	public void verify_the_value_is(String key, String expectedvalue) {
		assertEquals(getJsonPath(response,key),expectedvalue);
	}
}
