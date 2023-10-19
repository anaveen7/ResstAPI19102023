package stepDefinations;

import java.io.IOException;

import org.junit.AfterClass;

import io.cucumber.java.Before;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Hooks {
	
	@Before
	public void scenario() throws Exception {
		stepDefination m = new stepDefination();
		if(m.place_id==null) {
		m.add_place_payload_with("Naveen","English","Street2");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Naveen", "GetPlaceAPI");
		}}
	
	@Before
	public void addBook() throws IOException {
		LibraryApi l = new LibraryApi();
		if(l.id==null) {
			l.add_the_addbook_payload_with_and("ANaveen",123567);
			l.user_calls_with_with_http_request("AddBookAPI", "Post");
			l.verify_the_id_created_using_and("ANaveen",123567);
			
		}}
	
	

}
