package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public  RequestSpecification req;
	//public RequestSpecification loginReq;
	public RequestSpecification requestSpecification(String url) throws IOException {
		
		if(req==null) {
		PrintStream log = new PrintStream(new FileOutputStream("loggingmap.txt"));
				req =new RequestSpecBuilder().setBaseUri(globalValue(url)).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	public RequestSpecification requestSpecificationForBook() throws IOException {
		PrintStream booklog = new PrintStream(new FileOutputStream("loggingbook.txt"));
		req=new RequestSpecBuilder().setBaseUri(globalValue("LibraryApi")).addHeader("Content-Type","application/json")
				.addFilter(RequestLoggingFilter.logRequestTo(booklog))
				.addFilter(ResponseLoggingFilter.logResponseTo(booklog))
				.build();
		return req;
	}
	public RequestSpecification loginEcommerceSpec() {
		req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Content-Type","application/json")
				.setBody("{\r\n"
				+ "  \"userEmail\": \"naveenpractice7@gmail.com\",\r\n"
				+ "  \"userPassword\": \"Test@1234\"\r\n"
				+ "}").build();
		return req;
	}
	public RequestSpecification ecomLoginSpec(String token) {
		req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization",token).build();
		return req;
	}
	public RequestSpecification createOrderSpec(String loginToken, String country, String productId) {
		req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Content-Type","application/json").addHeader("Authorization",loginToken)
				.setBody("{\r\n"
						+ "    \"orders\": [\r\n"
						+ "        {\r\n"
						+ "            \"country\": \""+country+"\",\r\n"
						+ "            \"productOrderedId\": \""+productId+"\" \r\n"
						+ "        }\r\n"
						+ "    ]\r\n"
						+ "}").build();
		return req;
	}
	public RequestSpecification getOrderSpec(String token, String orderid) {
		// TODO Auto-generated method stub
		req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token)
				.addQueryParam("id",orderid).build();
		return req;
	}
	

	public static String  globalValue(String key) throws IOException {
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public static String getJsonPath(Response response,String key) {
		String resp= response.asString();
		JsonPath js= new JsonPath(resp);
		return js.get(key).toString();
	}
	public String getJsonArrayObject(Response response,String key) {
		//JsonArray resp=response.asString();
		String resp= response.asString();
		JsonPath js= new JsonPath(resp);
		String value=js.getString("[0]."+key+"");
		return value;
	}

}

//public RequestSpecification getBookRequestSpecification() throws IOException {
//req= new RequestSpecBuilder().setBaseUri(globalValue("LibraryApi")).addHeader("Content-Type","application/json").build();
//return req;
//}
//
//public RequestSpecification deleteBookRequestSpecification() throws IOException {
//req=new RequestSpecBuilder().setBaseUri(globalValue("LibraryApi")).addHeader("Content-Type","application/json").build();
//return req;
//}
//FileInputStream fis = new FileInputStream("");
//FileInputStream fstream = new FileInputStream(selectedFile);
