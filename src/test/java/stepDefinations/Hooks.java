package stepDefinations;

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
		}
	}
	

}
