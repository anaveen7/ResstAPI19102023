package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

public class stepDefination extends Utils{
	
	
	ResponseSpecification resspec;
	RequestSpecification res2;
	Response response2;
	static String place_id;	
	
	TestDataBuild data = new TestDataBuild();
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws Exception {
		
		res2= given().spec(requestSpecification("baseUrl")).body(data.addPlacePayLoad(name,language,address));
		
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		//resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		APIResources resourceAPI=APIResources.valueOf(resource);
		if(method.equalsIgnoreCase("POST"))
		response2 =res2.when().post(resourceAPI.getResources());
		else if(method.equalsIgnoreCase("GET"))
		response2 =res2.when().log().all().get(resourceAPI.getResources());
		//assertEquals(response.getStatusCode(),200);
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int statuscode) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response2.getStatusCode(),statuscode);
	}
	@Then("{string} is response body is {string}")
	public void is_response_body_is(String keyValue, String expectedValue) {
		
		assertEquals(getJsonPath(response2,keyValue),expectedValue);
	}
	@Then("verify place_ID created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String ExpectedName, String resource) throws IOException {
		
		 place_id=getJsonPath(response2,"place_id");
		res2= given().spec(requestSpecification("baseUrl")).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String ActualName=getJsonPath(response2,"name");
		Assert.assertEquals(ExpectedName,ActualName);
		System.out.println("Place id retrived");
		System.out.println("Place id retrived2");
	}
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		res2=given().spec(requestSpecification("baseUrl")).body(data.deletePlacePayLoad(place_id));
	}

}
