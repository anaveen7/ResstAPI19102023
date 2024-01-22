package stepDefinations;

import java.io.IOException;
import java.util.Collection;

import org.junit.AfterClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Hooks {
	
	Ecommerce e;
	static Collection<String> tagname;
	@Before("@DeletePlace")
	public void scenario() throws Exception {
		stepDefination m = new stepDefination();
		if(m.place_id==null) {
		m.add_place_payload_with("Naveen","English","Street2");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Naveen", "GetPlaceAPI");
		}}
	
	@Before("@DeleteBook")
	public void addBook() throws IOException {
		LibraryApi l = new LibraryApi();
		if(l.id==null) {
			l.add_the_addbook_payload_with_and("ANaveen",123567);
			l.user_calls_with_with_http_request("AddBookAPI", "Post");
			l.verify_the_id_created_using_and("ANaveen",123567);
			
		}}
	
	@Before("@DeleteProduct")
	public void addProduct() throws IOException {
		 e= new Ecommerce();
		if(e.ProductId==null) {
		e.login_the_ecommerce_app_with_auth_login_endpoint("LoginEcomAPI","naveenpractice7@gmail.com","Test@1234");
		e.add_the_product_with_and_user_id("Car");
		e.user_calls_with_and_request("AddProductAPI","Post");
		e.get_the_product_id_and_verify_the_message_is("Product Added Successfully");
		}
	}
	@After("@DeleteProduct")
	public void deleteOrderScenario(Scenario scenario) throws IOException {
		tagname=scenario.getSourceTagNames();
	}
	
	@Before("@DeleteOrder")
	public void createOrder() throws IOException {
		addProduct();
		if(e.orderId==null) {
			e.create_order_with_country_and_product_id("India");
			e.user_calls_with_and_request("CreateProductAPI","Post");
			e.get_order_id_and_verify_the_message_is("Order Placed Successfully");
		}
	}
	@After("@DeleteOrder")
	public void deleteProductAfterDeleteOrder(Scenario scenario) throws IOException {
		if(tagname==null) {
		e.delete_the_product_with_product_id();
		e.user_calls_with_and_request("DeleteProductAPI","Delete");
		}
	}
}
