package excelDriven;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.Utils;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import ExeclDriven.DataDriven;


public class ExcelDrivenClass {
	
	@Test
	public void addBook() throws IOException {
		
		DataDriven d= new DataDriven();
		ArrayList data=d.getData("RestAssuredBook", "RestAssured");
		RestAssured.baseURI="http://216.10.245.166";
		HashMap<String,Object> map= new HashMap<String,Object>();
		map.put("name",data.get(1));
		map.put("isbn",data.get(2));
		map.put("aisle",data.get(3));
		map.put("author",data.get(4));
		Response res=given().header("Content-Type","application/json")
		.body(map)
		.when().post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response();
		
		String id= Utils.getJsonPath(res,"ID");
		System.out.println(id);
		
	}

}
